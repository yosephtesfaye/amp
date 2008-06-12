/**
 * 
 */
package org.dgfoundation.amp.ar;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.digijava.module.aim.dbentity.AmpColumns;
import org.digijava.module.aim.dbentity.AmpColumnsFilters;
import org.digijava.module.aim.util.Identifiable;

/**
 * @author mihai
 * 
 */
public class ColumnFilterGenerator {

	protected static Logger logger = Logger
			.getLogger(ColumnFilterGenerator.class);

	/**
	 * Attaches hard coded filters for hard coded columns. The FUNDING columns
	 * are hard coded, because they do not appear in amp_columns table,
	 * therefore they cannot have persisted AmpColumnsFilters, thus the objects
	 * will be created manually
	 * 
	 * @param c
	 *            the object to which the hard coded filters will be attached
	 */
	public static void attachHardcodedFilters(AmpColumns c) {
		c.setFilters(new TreeSet());
		if (ArConstants.VIEW_DONOR_FUNDING.equals(c.getExtractorView())) {
			// TODO: example here of how to add hardcoded filters for hardcoded
			// FUNDING columns
			// AmpColumnsFilters acf=new
			// AmpColumnsFilters(c,"donorGroups","donor_group_id");
			// c.getFilters().add(acf);
			AmpColumnsFilters acf = new AmpColumnsFilters(c,"donorGroups","org_grp_id");
			c.getFilters().add(acf);
		}
		if (ArConstants.VIEW_CONTRIBUTION_FUNDING.equals(c.getExtractorView())) {
			//TODO: add filters here
			AmpColumnsFilters acf = new AmpColumnsFilters(c,"donorGroups","amp_org_id");
			c.getFilters().add(acf);
		}
		if (ArConstants.VIEW_COMPONENT_FUNDING.equals(c.getExtractorView())) {
			//TODO: add filters here	
			//AmpColumnsFilters acf = new AmpColumnsFilters(c,"regions","amp_component_id");
			//c.getFilters().add(acf);
		}
		if (ArConstants.VIEW_REGIONAL_FUNDING.equals(c.getExtractorView())) {
			//TODO: add filters here
			AmpColumnsFilters acf = new AmpColumnsFilters(c,"regions","region_id");
			c.getFilters().add(acf);
		}
	}

	/**
	 * Helper method to create the sql clause for only one property of the
	 * filter bean.
	 * 
	 * @see ColumnFilterGenerator#generateColumnFilterSQLClause(AmpARFilter,
	 *      AmpColumns, boolean)
	 * @param property
	 *            the property for which the clause is generated
	 * @param viewFieldName
	 *            the sql view field name (column name is sql view)
	 * @return the logical clause for this property
	 */
	private static String generatePropertyFilterSQLClause(Object property,
			String viewFieldName) {
		if (property instanceof Collection)
			return viewFieldName
					+ " IN ("
					+ org.dgfoundation.amp.Util
							.toCSString((Collection) property) + ")";
		if (property instanceof String)
			return viewFieldName + "='" + property + "'";
		if (property instanceof Identifiable)
			return generatePropertyFilterSQLClause(((Identifiable) property)
					.getIdentifier(), viewFieldName);
		return viewFieldName + "=" + property;
	}

	/**
	 * Generates the column filter sql query for a given column, by reading the
	 * mapped properties that can affect this column, related with the filter
	 * bean. Then the bean is queried to get the values for those properties and
	 * the SQL clause is generated
	 * 
	 * @param f
	 *            the filter bean as it is generated by the filter form
	 * @param c
	 *            the column for which the filtering SQL clause is created
	 * @param exclusive
	 *            if true, it will generate an SQL clause linked with AND
	 *            operators , otherwise it will use OR
	 * @return the complete SQL logical clause
	 */
	public static String generateColumnFilterSQLClause(Filter f, AmpColumns c,
			boolean exclusive) {
		// get all bindings between this column and possible filter properties:
		StringBuffer sb = new StringBuffer("");
		Set<AmpColumnsFilters> filters = c.getFilters();
		if (filters != null) {
			Iterator<AmpColumnsFilters> i = filters.iterator();
			while (i.hasNext()) {
				AmpColumnsFilters cf = (AmpColumnsFilters) i.next();
				try {
					Object property = PropertyUtils.getSimpleProperty(f, cf
							.getBeanFieldName());
					if (property == null)
						continue;
					sb.append((exclusive ? " AND " : " OR ")
							+ generatePropertyFilterSQLClause(property, cf
									.getViewFieldName()));
				} catch (IllegalAccessException e) {
					logger.error(e);
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					logger.error(e);
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					logger.error(e);
					e.printStackTrace();
				}
			}
		}
		return sb.toString();

	}
}
