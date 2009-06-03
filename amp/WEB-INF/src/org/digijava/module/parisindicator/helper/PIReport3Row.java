package org.digijava.module.parisindicator.helper;

import java.math.BigDecimal;

/**
 * This class represents each row in the PI 3 report.
 * @author gabriel
 */
public class PIReport3Row extends PIReportAbstractRow {

	private int year;
	private BigDecimal column1;
	private BigDecimal column2;
	private BigDecimal column3;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public BigDecimal getColumn1() {
		return column1;
	}
	public void setColumn1(BigDecimal column1) {
		this.column1 = column1;
	}
	public BigDecimal getColumn2() {
		return column2;
	}
	public void setColumn2(BigDecimal column2) {
		this.column2 = column2;
	}
	public BigDecimal getColumn3() {
		return column3;
	}
	public void setColumn3(BigDecimal column3) {
		this.column3 = column3;
	}
}
