package org.dgfoundation.amp.ar.viewfetcher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * <b>IMMUTABLE</b>class holding the data necessary reading the i18n value of a property of a Translatable model<br />
 * <b>NEVER EVER MAKE THIS CLASS MUTABLE OR CONTAIN MUTABLE FIELDS</b>
 * there are two sources for fetching a translated value: <br />
 * 1. SELECT [modelColumnName] FROM [modelTableName] WHERE [modelTableId] = (id) -> for English
 * 2. SELECT translation FROM amp_content_translation where field_name = [propertyName] AND object_class = [className] AND locale = (locale) AND object_id = (id)
 * @author Dolghier Constantin
 *
 */
public class InternationalizedPropertyDescription implements PropertyDescription
{
	public final String propertyName;
	public final String className;
	public final String modelColumnName;
	public final String modelTableName;
	public final String modelTableId;
	
	private final String _toString;
	private final int _hashCode;
	
	public InternationalizedPropertyDescription(String propertyName, String className, String modelTableName, String modelTableId, String modelColumnName)
	{
		this.propertyName = propertyName;
		this.className = className;
		this.modelTableName = modelTableName;
		this.modelColumnName = modelColumnName;
		this.modelTableId = modelTableId;
		
		_toString = String.format("i18n IPP: %s.%s -> %s[%s]::%s", className.substring(className.lastIndexOf('.') + 1), propertyName, modelTableName, modelTableId, modelColumnName);
		_hashCode = _toString.hashCode();
	}	
	
	/**
	 * take care when changing this function, as its output is part of the instance's hash!
	 */
	@Override
	public String toString()
	{
		return _toString;
	}
	
	@Override
	public int hashCode()
	{
		return _hashCode;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (other == null)
			return false;
		if (!(other instanceof InternationalizedPropertyDescription))
			return false;
		return this.toString().equals(other.toString());
	}
	
	/**
	 * generates the SQL query which will fetch all the (id, translation) values for this field in the original model: those of them which have the id in (ids)
	 * @param ids
	 * @return
	 */
	public String generateEnglishQuery(Collection<Long> ids)
	{
		return String.format("SELECT %s, %s FROM %s WHERE %s IN (%s)", modelTableId, modelColumnName, modelTableName, modelTableId, ids.isEmpty() ? "-999" : DatabaseViewFetcher.generateCSV(ids));
	}
	
	/**
	 * generates the SQL query which will fetch all the (id, translation) values for this field
	 * @param ids
	 * @param locale
	 * @return
	 */
	public String generateGeneralizedQuery(Collection<Long> ids, String locale)
	{
		return String.format("SELECT object_id, translation FROM amp_content_translation where field_name = '%s' AND object_class = '%s' AND locale = '%s' AND object_id IN (%s)",
				propertyName, className, locale, ids.isEmpty() ? "-999" : DatabaseViewFetcher.generateCSV(ids));
	}
	
	/**
	 * imports result generated by a query of the type "SELECT id, value FROM model". The only functions called on the ResultSet are <br />
	 * rs.getLong(1) and rs.getString(2) - this is useful if you are supplying a mock implementation
	 * @param rs
	 */
	public void importValues(Map<Long, String> values, ResultSet rs)
	{
		try
		{
			while (rs.next())
			{
				Long id = rs.getLong(1);
				String value = rs.getString(2);
			
				values.put(id, value);
			}
		}
		catch(SQLException e)
		{
			throw new RuntimeException("error while fetching translations", e);
		}
	}
	
	@Override
	public Map<Long, String> generateValues(java.sql.Connection connection, Collection<Long> ids, String locale) throws SQLException// will only be called for non-calculated
	{
		Map<Long, String> res = new HashMap<Long, String>();
		
		/**
		 * fetch order:
		 * 1) fetch basic names (the ones written in the model)
		 * 2) then overwrite those with the English-translated ones
		 * 3) then overwrite those with the current-locale-translated ones (if current locale != English)
		 */
		importValues(res, DatabaseViewFetcher.rawRunQuery(connection, generateEnglishQuery(ids), null)); 
		importValues(res, DatabaseViewFetcher.rawRunQuery(connection, generateGeneralizedQuery(ids, "en"), null));
		if (!locale.equals("en"))
			importValues(res, DatabaseViewFetcher.rawRunQuery(connection, generateGeneralizedQuery(ids, locale), null));
		return res;
	}
	
	@Override
	public boolean isCalculated()
	{
		return false;
	}
	
	@Override
	public String getValueFor(java.sql.ResultSet currentLine) // will only be called for cacheable
	{
		throw new UnsupportedOperationException("cacheable properties do not get values one-by-one");
	}
	
	@Override
	public String getNiceDescription()
	{
		return String.format("%s[%s]::%s", modelTableName, modelTableId, modelColumnName);
	}
}
