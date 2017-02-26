package org.digijava.module.aim.dbentity;

import java.io.Serializable;
import java.util.Date;

public class AmpGPINiAidOnBudget implements Serializable {

	private static final long serialVersionUID = -8747493117052602462L;

	private Long ampGPINiAidOnBudgetId;
	private AmpCurrency currency;
	private AmpOrganisation donor;
	private Double amount;
	private Date indicatorDate;

	public Long getAmpGPINiAidOnBudgetId() {
		return ampGPINiAidOnBudgetId;
	}

	public void setAmpGPINiAidOnBudgetId(Long ampGPINiAidOnBudgetId) {
		this.ampGPINiAidOnBudgetId = ampGPINiAidOnBudgetId;
	}

	public AmpCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(AmpCurrency currency) {
		this.currency = currency;
	}

	public AmpOrganisation getDonorId() {
		return donor;
	}

	public void setDonor(AmpOrganisation donor) {
		this.donor = donor;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getIndicatorDate() {
		return indicatorDate;
	}

	public void setIndicatorDate(Date indicatorDate) {
		this.indicatorDate = indicatorDate;
	}

}
