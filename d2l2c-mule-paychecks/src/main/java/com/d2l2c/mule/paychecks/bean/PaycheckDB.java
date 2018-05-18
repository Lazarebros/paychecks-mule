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
public class PaycheckDB {

	private Long id;
	private String companyCode;
	private int year;
	private int month;
	private Date startDate;
	private Date endDate;
	private Long numberOfHours;
	private Long expectedNumberOfHours;
	private BigDecimal netPercentageOfGross;
	private BigDecimal hourlyRate;
	private BigDecimal grossAmount;
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
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
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
	 * @return the expectedNumberOfHours
	 */
	public Long getExpectedNumberOfHours() {
		return expectedNumberOfHours;
	}

	/**
	 * @param expectedNumberOfHours the expectedNumberOfHours to set
	 */
	public void setExpectedNumberOfHours(Long expectedNumberOfHours) {
		this.expectedNumberOfHours = expectedNumberOfHours;
	}

	/**
	 * @return the netPercentageOfGross
	 */
	public BigDecimal getNetPercentageOfGross() {
		return netPercentageOfGross;
	}

	/**
	 * @param netPercentageOfGross
	 *            the netPercentageOfGross to set
	 */
	public void setNetPercentageOfGross(BigDecimal netPercentageOfGross) {
		this.netPercentageOfGross = netPercentageOfGross;
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
	 * @return the grossAmount
	 */
	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	/**
	 * @param grossAmount the grossAmount to set
	 */
	public void setGrossAmount(BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	/**
	 * @return the netPay
	 */
	public BigDecimal getNetPay() {
		return netPay;
	}

	/**
	 * @param netPay the netPay to set
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
	 * @param reimbursement the reimbursement to set
	 */
	public void setReimbursement(BigDecimal reimbursement) {
		this.reimbursement = reimbursement;
	}

}
