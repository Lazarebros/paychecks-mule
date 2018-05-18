/**
 * 
 */
package com.d2l2c.mule.paychecks.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dlazare
 *
 */
public class PaycheckSummary {

	private int year;
	private Long numnerOfPaychecks;
	private BigDecimal expectedGrossAmount;
	private BigDecimal grossAmount;
	private BigDecimal grossAmountRemain;
	private BigDecimal reimbursement;
	private BigDecimal expectedNetPay;
	private BigDecimal netPay;
	private BigDecimal netPayReal;
	private BigDecimal netPayRemain;

	private List<PaycheckDetails> paycheckDetailsList = new ArrayList<PaycheckDetails>();

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the numnerOfPaychecks
	 */
	public Long getNumnerOfPaychecks() {
		return numnerOfPaychecks;
	}

	/**
	 * @param numnerOfPaychecks
	 *            the numnerOfPaychecks to set
	 */
	public void setNumnerOfPaychecks(Long numnerOfPaychecks) {
		this.numnerOfPaychecks = numnerOfPaychecks;
	}

	/**
	 * @return the expectedGrossAmount
	 */
	public BigDecimal getExpectedGrossAmount() {
		return expectedGrossAmount;
	}

	/**
	 * @param expectedGrossAmount
	 *            the expectedGrossAmount to set
	 */
	public void setExpectedGrossAmount(BigDecimal expectedGrossAmount) {
		this.expectedGrossAmount = expectedGrossAmount;
	}

	/**
	 * @return the grossAmount
	 */
	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	/**
	 * @param grossAmount
	 *            the grossAmount to set
	 */
	public void setGrossAmount(BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	/**
	 * @return the grossAmountRemain
	 */
	public BigDecimal getGrossAmountRemain() {
		return grossAmountRemain;
	}

	/**
	 * @param grossAmountRemain
	 *            the grossAmountRemain to set
	 */
	public void setGrossAmountRemain(BigDecimal grossAmountRemain) {
		this.grossAmountRemain = grossAmountRemain;
	}

	/**
	 * @return the reimbursement
	 */
	public BigDecimal getReimbursement() {
		return reimbursement;
	}

	/**
	 * @param reimbursement
	 *            the reimbursement to set
	 */
	public void setReimbursement(BigDecimal reimbursement) {
		this.reimbursement = reimbursement;
	}

	/**
	 * @return the expectedNetPay
	 */
	public BigDecimal getExpectedNetPay() {
		return expectedNetPay;
	}

	/**
	 * @param expectedNetPay
	 *            the expectedNetPay to set
	 */
	public void setExpectedNetPay(BigDecimal expectedNetPay) {
		this.expectedNetPay = expectedNetPay;
	}

	/**
	 * @return the netPay
	 */
	public BigDecimal getNetPay() {
		return netPay;
	}

	/**
	 * @param netPay
	 *            the netPay to set
	 */
	public void setNetPay(BigDecimal netPay) {
		this.netPay = netPay;
	}

	/**
	 * @return the netPayReal
	 */
	public BigDecimal getNetPayReal() {
		return netPayReal;
	}

	/**
	 * @param netPayReal
	 *            the netPayReal to set
	 */
	public void setNetPayReal(BigDecimal netPayReal) {
		this.netPayReal = netPayReal;
	}

	/**
	 * @return the netPayRemain
	 */
	public BigDecimal getNetPayRemain() {
		return netPayRemain;
	}

	/**
	 * @param netPayRemain
	 *            the netPayRemain to set
	 */
	public void setNetPayRemain(BigDecimal netPayRemain) {
		this.netPayRemain = netPayRemain;
	}

	/**
	 * @return the paycheckDetailsList
	 */
	public List<PaycheckDetails> getPaycheckDetailsList() {
		return paycheckDetailsList;
	}

	/**
	 * @param paycheckDetailsList
	 *            the paycheckDetailsList to set
	 */
	public void setPaycheckDetailsList(List<PaycheckDetails> paycheckDetailsList) {
		this.paycheckDetailsList = paycheckDetailsList;
	}
	
	public void add(PaycheckDetails paycheckDetails) {
		paycheckDetailsList.add(paycheckDetails);
	}

}
