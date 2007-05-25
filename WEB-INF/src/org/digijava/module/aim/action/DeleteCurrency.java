package org.digijava.module.aim.action ;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.digijava.module.aim.dbentity.AmpCurrency;
import org.digijava.module.aim.dbentity.AmpCurrencyRate;
import org.digijava.module.aim.form.AddCurrencyForm;
import org.digijava.module.aim.util.CurrencyUtil;


public class DeleteCurrency extends Action {

		  private static Logger logger = Logger.getLogger(DeleteCurrency.class);

		  public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response) throws java.lang.Exception {

					 HttpSession session = request.getSession();
					 if (session.getAttribute("ampAdmin") == null) {
								return mapping.findForward("index");
					 } else {
								String str = (String)session.getAttribute("ampAdmin");
								if (str.equals("no")) {
										  return mapping.findForward("index");
								}
					 }					 

					 AddCurrencyForm deleteForm = (AddCurrencyForm) form;

					 logger.debug("In delete currency");

					 if ( deleteForm.getCurrencyId() != null) {

								if (session.getAttribute("ampCurrencies") != null) {
									session.removeAttribute("ampCurrencies");
								}

								//AmpCurrency ampCurr = DbUtil.getAmpCurrency(deleteForm.getCurrencyId());
								AmpCurrency ampCurr = new AmpCurrency();
								ampCurr.setAmpCurrencyId(deleteForm.getCurrencyId());
								//AmpCurrencyRate ampCurrRate = DbUtil.getAmpCurrencyRate(deleteForm.getCurrencyRateId());
								AmpCurrencyRate ampCurrRate = new AmpCurrencyRate();
								ampCurrRate.setAmpCurrencyRateId(deleteForm.getCurrencyRateId());
								CurrencyUtil.deleteCurrency(ampCurr,ampCurrRate);
								logger.debug("Currency deleted");
										  
					 }
					 return mapping.findForward("forward");
		  }
}

