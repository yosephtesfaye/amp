package org.digijava.module.aim.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.digijava.module.aim.dbentity.AmpTheme;
import org.digijava.module.aim.dbentity.AmpThemeIndicatorValue;
import org.digijava.module.aim.dbentity.AmpThemeIndicators;
import org.digijava.module.aim.exception.AimException;
import org.digijava.module.aim.form.NpdGraphForm;
import org.digijava.module.aim.helper.NpdGraphTooltipGenerator;
import org.digijava.module.aim.util.ChartUtil;
import org.digijava.module.aim.util.ProgramUtil;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * NPD Indicators graph generator action.
 * Generates differen (currently only one) types of graphs for specified indicators
 * Respons of the Action is image generated by JFreeChart.
 * @author Irakli Kobiashvili - ikobiashvili@picktek.com
 *
 */
public class getNPDgraph extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		NpdGraphForm npdForm = (NpdGraphForm) form;

		try {
			Long currentThemeId = npdForm.getCurrentProgramId();
			long[] selIndicators = npdForm.getSelectedIndicators();
			String[] selYears = npdForm.getSelectedYears();

			//session for storing latest map for graph
			HttpSession session = request.getSession();
			
			CategoryDataset dataset = null;
			if (currentThemeId != null && currentThemeId.longValue() > 0) {
				AmpTheme currentTheme = ProgramUtil.getThemeObject(currentThemeId);

				dataset = createPercentsDataset(currentTheme, selIndicators,selYears);
			}
			JFreeChart chart = ChartUtil.createChart(dataset,ChartUtil.CHART_TYPE_BAR);
			
			ChartRenderingInfo info = new ChartRenderingInfo();

			response.setContentType("image/png");
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, ChartUtil.CHART_WIDTH, ChartUtil.CHART_HEIGHT, info);

			//generate map for this graph
			NpdGraphTooltipGenerator ttGen = new NpdGraphTooltipGenerator();
			String map = ChartUtilities.getImageMap("npdChartMap", info);
			//save map tor this timestamp for later use
			ChartUtil.saveMap(map,npdForm.getTimestamp(),session);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static CategoryDataset createPercentsDataset(AmpTheme currentTheme,
			long[] selectedIndicators, String[] selectedYears)
			throws AimException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		if (selectedIndicators != null && currentTheme.getIndicators() != null) {
			Arrays.sort(selectedIndicators);

			dataset = new DefaultCategoryDataset();
			
			//Set sortedIndicators = new TreeSet(currentTheme.getIndicators());
			List sortedIndicators = new ArrayList(currentTheme.getIndicators());
			//Iterator iter = sortedIndicators.iterator();
			Collections.sort(sortedIndicators,new ProgramUtil.IndicatorNameComparator());
			Iterator iter = sortedIndicators.iterator();
			while (iter.hasNext()) {
				AmpThemeIndicators item = (AmpThemeIndicators) iter.next();

				int pos = Arrays.binarySearch(selectedIndicators, item.getAmpThemeIndId().longValue());

				if (pos >= 0) {
					String displayLabel = item.getName();
					try {
						Collection indValues = ProgramUtil.getThemeIndicatorValuesDB(item.getAmpThemeIndId());
						Map actualValues = new HashMap();
						Double targetValue = null;
						Double baseValue = null;

						// arrage all values by types
						for (Iterator valueIter = indValues.iterator(); valueIter.hasNext();) {
							AmpThemeIndicatorValue valueItem = (AmpThemeIndicatorValue) valueIter.next();

							// target value
							if (valueItem.getValueType() == 0) {
								targetValue = valueItem.getValueAmount();
							}
							// actual Value
							if (valueItem.getValueType() == 1 && isInSelectedYears(valueItem,selectedYears)) {
								Date actualDate = valueItem.getCreationDate();
								String year = extractYearString(actualDate);
								// for every year we should have only latest
								// actual value
								// so check if we already have atual value fro
								// this year
								AmpThemeIndicatorValue v = (AmpThemeIndicatorValue) actualValues.get(year);
								if (v == null) {
									// if not then store this actual value
									actualValues.put(year, valueItem);
								} else {
									// if we have valu, chack and store latest
									// value
									Date crDate = v.getCreationDate();
									if (crDate != null && crDate.compareTo(actualDate) < 0) {
										actualValues.put(year, valueItem);
									}
								}
							}
							// base value - not used
							if (valueItem.getValueType() == 2) {
								baseValue = valueItem.getValueAmount();
							}
						}
						if (targetValue == null) {
							targetValue = new Double(1.0);
						}
						if (baseValue == null){
							
						}

						// now put all values in the dataset

						if (selectedYears != null) {
							for (int i = 0; i < selectedYears.length; i++) {
								AmpThemeIndicatorValue actualValue = (AmpThemeIndicatorValue) actualValues.get(selectedYears[i]);
								if (actualValue != null) {
									Double realActual = actualValue.getValueAmount();
									if (realActual != null) {
//										realActual = new Double(realActual.doubleValue() / targetValue.doubleValue());
										realActual = computePercent(item,targetValue,realActual,baseValue);
										dataset.addValue(realActual.doubleValue(),selectedYears[i], displayLabel);
									}
								} else {
									dataset.addValue(0, selectedYears[i],displayLabel);
								}
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						throw new AimException("Error creating dataset for graph.", ex);
					}
				}
			}

		}
		return dataset;

	}
	
	private static Double computePercent(AmpThemeIndicators indic, Double _target, Double _actual, Double _base){
		double actual=(_actual==null)?0:_actual.doubleValue();
		double base=(_base==null)?actual:_base.doubleValue();//if no base value than using actual as base
		double target=(_target==null)?actual:_target.doubleValue();
		double result = 0;
		if (indic.getType().equals("A")){
			//ascending
			result = actual / target;
		}else{
			//descending
			base-=target;
			actual-=target;
			target=0;
			if (base!=0 && actual!=0){
				result = actual/(base/100);
				result = 1 - result/100;
				
			}
		}
		return new Double(result);
	}

	private static int extractYearInt(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int iYear = cal.get(Calendar.YEAR);
		return iYear;
	}

	private static String extractYearString(Date date) {
		String sYear = null;
		if (date != null){
			int iYear = extractYearInt(date);
			sYear = String.valueOf(iYear);
		}
		return sYear;
	}

	private static boolean isInSelectedYears(AmpThemeIndicatorValue value, String[] selYars) {
		String sYear = extractYearString(value.getCreationDate());
		if (sYear !=null){
			for (int i = 0; i < selYars.length; i++) {
				if (selYars[i].equals(sYear)) {
					return true;
				}
			}
		}
		return false;
	}
	

}
