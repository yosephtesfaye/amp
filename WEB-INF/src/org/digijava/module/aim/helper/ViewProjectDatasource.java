package org.digijava.module.aim.helper;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.log4j.Logger;


public class ViewProjectDatasource implements JRDataSource 
{
	
	private static Logger logger = Logger.getLogger(ViewProjectDatasource.class);
	private Object data[][];
	private int index;
	public int val = 0;
	public int colCnt;
	public String name[];
	
	public ViewProjectDatasource() 
	{
		data = null;
		index = -1;
	}
	
	public ViewProjectDatasource(Object[][] obj) 
	{
		data = new Object[obj.length][obj[0].length];
		index = -1;
		for (int i = 0;i < obj.length;i ++) {
			for (int j = 0;j < obj[0].length;j ++) {
				data[i][j] = obj[i][j];
//				System.out.print( j +" : " + data[i][j]);
			}
//			//System.out.println();
		}
		colCnt = obj[0].length;
		name = new String[colCnt];
		for(int i=0; i<colCnt; i++)
		{
			name[i]= "c" + Integer.toString(i+1);
		}
	}
	
	
	public boolean next() throws JRException
	{
		index++;
		return (index < data.length);
	}
	
	
	public Object getFieldValue(JRField field) throws JRException
	{
		String fieldName = field.getName();
		Object value = null;
		for(int i=0; i<colCnt; i++)
		{
			if(name[i].equals(fieldName))
			{
				value = data[index][i];
			}
		}
		return value;
	}		  

} // end of Class Datasource
