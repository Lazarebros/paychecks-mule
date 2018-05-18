/**
 * 
 */
package com.d2l2c.mule.paychecks.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dlazare
 *
 */
public class PaycheckDetails {

	private Long id;
	private String companyCode;
	private Date startDate;
	private Date endDate;
	private Long numberOfHours;
	private BigDecimal hourlyRate;
	private BigDecimal expectedGrossAmount;
	private BigDecimal grossAmount;
	private BigDecimal expectedNetPay;
	private BigDecimal netPay;
	private BigDecimal reimbursement;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode
	 *            the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the numberOfHours
	 */
	public Long getNumberOfHours() {
		return numberOfHours;
	}

	/**
	 * @param numberOfHours
	 *            the numberOfHours to set
	 */
	public void setNumberOfHours(Long numberOfHours) {
		this.numberOfHours = numberOfHours;
	}

	/**
	 * @return the hourlyRate
	 */
	public BigDecimal getHourlyRate() {
		return hourlyRate;
	}

	/**
	 * @param hourlyRate
	 *            the hourlyRate to set
	 */
	public void setHourlyRate(BigDecimal hourlyRate) {
		this.hourlyRate = hourlyRate;
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

}
