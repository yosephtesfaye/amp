package org.dgfoundation.amp.newreports;

import java.util.List;
import java.util.Map;

/**
 * a generic structure describing an area of a report - be it a rectangular area (ColumnReportData) or a holder of subreports
 * @author Dolghier Constantin
 *
 */
public interface ReportArea {
	
	/**
	 * returns the <strong>localized</strong> owner. For a row, it is the activity/pledge/whatever which owns the row. For a report subregion, it is the "splitter cell"
	 * @return
	 */
	public NamedTypedEntity getOwner();
	
	//public void setOwner(NamedTypedEntity owner);
			
	/**
	 * for a region, holds aggregates (trail cells). For a row, holds the values
	 * Since order matters, should (probably) be a LinkedHashMap
	 * @return
	 */
	public Map<ReportOutputColumn, ReportCell> getContents();
	
	//public void setContents(Map<ReportOutputColumn, ReportCell> contents);
	
	/**
	 * returns the subareas, if any. Returns NULL if there are none (for example, a row)
	 * @return
	 */
	public List<ReportArea> getChildren();
	
	//public void setChildren(List<ReportArea> children);
}
