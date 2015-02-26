package org.dgfoundation.amp.mondrian;

import java.util.Set;

import org.dgfoundation.amp.Util;

/**
 * the configuration data of an ETL run, e.g. which entities it should touch
 * @author Dolghier Constantin
 *
 */
public class EtlConfiguration {
	
	/**
	 * activities to add/remove from all the relevant tables
	 */
	public final Set<Long> activityIds;

	/**
	 * pledges to add/remove from all the relevant tables
	 */
	public final Set<Long> pledgeIds;
	
	/**
	 * components to add/remove from all the relevant tables
	 */
	public final Set<Long> componentIds;
	
	/**
	 * Julian date codes to add/remove from the relevant tables. null means "all"
	 */
	public final Set<Long> dateCodes;
	
	public final boolean fullEtl;

	
	public EtlConfiguration(Set<Long> activityIds, Set<Long> pledgeIds, Set<Long> componentIds, Set<Long> dateCodes, boolean fullEtl) {
		this.activityIds = activityIds;
		this.pledgeIds = pledgeIds;
		this.componentIds = componentIds;
		this.dateCodes = dateCodes;
		this.fullEtl = fullEtl;
	}
	
	protected String activityIdsInQuery;
	
	/**
	 * builds an SQL subfragment which filters based on the activity IDs
	 * @param prefix
	 * @return
	 */
	public String activityIdsIn(String prefix) {
		//if (fullEtl) return "1=1"; 
		if (activityIdsInQuery == null)
			activityIdsInQuery = " IN (" + Util.toCSStringForIN(activityIds) + ")";
		return prefix + activityIdsInQuery;
	}
	
	protected String pledgeIdsInQuery;
	
	/**
	 * builds an SQL subfragment which filters based on the pledge IDs
	 * @param prefix
	 * @return
	 */
	public String pledgeIdsIn(String prefix) {
		//if (fullEtl) return "1=1"; 
		if (pledgeIdsInQuery == null)
			pledgeIdsInQuery = " IN (" + Util.toCSStringForIN(pledgeIds) + ")";
		return prefix + pledgeIdsInQuery;
	}
	
	protected String componentIdsInQuery;
	
	/**
	 * builds an SQL subfragment which filters based on the pledge IDs
	 * @param prefix
	 * @return
	 */
	public String componentIdsIn(String prefix) {
		//if (fullEtl) return "1=1"; 
		if (componentIdsInQuery == null)
			componentIdsInQuery = " IN (" + Util.toCSStringForIN(componentIds) + ")";
		return prefix + componentIdsInQuery;
	}
	
	protected String entityIdsInQuery;
	/**
	 * builds an SQL subfragment which filters based on the entity IDs (activity IDs + shifted pledge IDs)
	 * @param prefix
	 * @return
	 */
	public String entityIdsIn(String prefix) {
		//if (fullEtl) return "1=1"; 
		if (entityIdsInQuery == null)
			entityIdsInQuery = " IN (" + Util.toCSStringForIN(getAllEntityIds()) + ")";
		return prefix + entityIdsInQuery;
	}
	
	public Set<Long> getAllEntityIds() {
		Set<Long> res = new java.util.HashSet<>(this.activityIds);
		for(long pledgeId:this.pledgeIds)
			res.add(pledgeId + MondrianETL.PLEDGE_ID_ADDER);
		return res;
	}
	
	
	@Override public String toString() {
		return String.format("activities: <%s>; pledges: <%s>; components: <%s>; dates: <%s>", 
				activityIds.size() > 20 ? String.format("[%d] activities", activityIds.size()) : activityIds.toString(),
				pledgeIds.size() > 20 ? String.format("[%d] pledges", pledgeIds.size()) : pledgeIds.toString(),
				componentIds.size() > 20 ? String.format("[%d] components", componentIds.size()) : componentIds.toString(),
				dateCodes == null ? "(all)" : dateCodes.toString());
	}
}
