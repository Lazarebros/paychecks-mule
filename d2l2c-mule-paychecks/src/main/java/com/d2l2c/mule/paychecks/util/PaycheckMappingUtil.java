/**
 * 
 */
package com.d2l2c.mule.paychecks.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.d2l2c.paycheck.util.bean.PaycheckDB;
import com.d2l2c.paycheck.util.bean.PaycheckDetail;
import com.d2l2c.paycheck.util.bean.PaycheckSummary;
import com.d2l2c.paycheck.util.bean.PaycheckUnit;

/**
 * @author dlazare
 *
 */
public class PaycheckMappingUtil {

	private static final Logger logger = LoggerFactory.getLogger(PaycheckMappingUtil.class);

	private static DozerBeanMapper mapper;

	static {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("dozer_mapping.xml"));
	}

	public static Collection<PaycheckSummary> getSummarizedPaychecks(List<Map<String, Object>> mapObject) {
		List<PaycheckDB> paycheckDBList = mapPaycheckDB(mapObject);

		Map<Integer, PaycheckSummary> mapOfPaycheckSummaryMap = paycheckDBList.stream().collect(
				Collectors.groupingBy(PaycheckDB::getYear, Collectors.collectingAndThen(Collectors.toList(), l -> {
					return getPaychecks(l, true);
				})));

		return mapOfPaycheckSummaryMap.values();
	}

	public static Collection<PaycheckSummary> getDetailledPaychecks(List<Map<String, Object>> mapObject) {
		List<PaycheckSummary> paycheckSummaryList = new ArrayList<PaycheckSummary>();
		List<PaycheckDB> paycheckDBList = mapPaycheckDB(mapObject);
		PaycheckSummary paycheckSummary = getPaychecks(paycheckDBList, true);
		paycheckSummaryList.add(paycheckSummary);
		return paycheckSummaryList;
	}

	private static List<PaycheckDB> mapPaycheckDB(List<Map<String, Object>> mapObject) {
		List<PaycheckDB> paycheckDBList = new ArrayList<PaycheckDB>();
		try {
			for (Map<String, Object> map : mapObject) {
				PaycheckDB paycheckDB = mapper.map(map, PaycheckDB.class);
				paycheckDBList.add(paycheckDB);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return paycheckDBList;
	}

	private static PaycheckSummary getPaychecks(List<PaycheckDB> paycheckDBList, boolean withDetails) {
		PaycheckSummary paycheckSummary = new PaycheckSummary();
		paycheckSummary.setYear(paycheckDBList.get(0).getYear());
		paycheckSummary.setNumnerOfPaychecks(new Long(paycheckDBList.size()));

		BigDecimal totalGrossAmount = new BigDecimal("0.00");
		BigDecimal totalNetPay = new BigDecimal("0.00");
		BigDecimal totalRealNetPay = new BigDecimal("0.00");
		BigDecimal totalReimbursement = new BigDecimal("0.00");
		BigDecimal totalExpectedGrossAmount = new BigDecimal("0.00");
		BigDecimal totalExpectedNetPay = new BigDecimal("0.00");
		
		int maxMonth = 1;

		Map<Integer, PaycheckDetail> paycheckDetailsMap = new HashMap<Integer, PaycheckDetail>();
		
		for(PaycheckDB paycheckDB : paycheckDBList) {
			
			maxMonth = maxMonth < paycheckDB.getMonth() ? paycheckDB.getMonth() : maxMonth;
			
			totalGrossAmount = totalGrossAmount.add(paycheckDB.getGrossAmount());
			totalNetPay = totalNetPay.add(paycheckDB.getNetPay());
			totalReimbursement = totalReimbursement.add(paycheckDB.getReimbursement());
			totalRealNetPay = totalNetPay.subtract(totalReimbursement);

			BigDecimal expectedGrossAmount = paycheckDB.getHourlyRate().multiply(new BigDecimal(paycheckDB.getExpectedNumberOfHours()));
			totalExpectedGrossAmount = totalExpectedGrossAmount.add(expectedGrossAmount);
			
			BigDecimal expectedNetPay = expectedGrossAmount.multiply(paycheckDB.getNetPercentageOfGross());
			totalExpectedNetPay = totalExpectedGrossAmount.multiply(paycheckDB.getNetPercentageOfGross());

			if (withDetails) {
				PaycheckDetail paycheckDetail = null;
				
				if (paycheckDetailsMap.containsKey(paycheckDB.getMonth())) {
					paycheckDetail = paycheckDetailsMap.get(paycheckDB.getMonth());
					
					PaycheckDetail paycheckDetailsCopy = mapper.map(paycheckDB, PaycheckDetail.class);
					
					BigDecimal grossAmount = paycheckDetail.getGrossAmount().add(paycheckDetailsCopy.getGrossAmount());
					BigDecimal netPay = paycheckDetail.getNetPay().add(paycheckDetailsCopy.getNetPay());
					BigDecimal reimbursement = paycheckDetail.getReimbursement().add(paycheckDetailsCopy.getReimbursement());
					
					Long numberOfHours = paycheckDetail.getNumberOfHours() + paycheckDetailsCopy.getNumberOfHours();

					paycheckDetail.setGrossAmount(grossAmount);
					paycheckDetail.setNetPay(netPay);
					paycheckDetail.setReimbursement(reimbursement);
					paycheckDetail.setNumberOfHours(numberOfHours);
					
					paycheckDetail.setExpectedGrossAmount(expectedGrossAmount.multiply(new BigDecimal("2.00")));
					paycheckDetail.setExpectedNetPay(expectedNetPay.multiply(new BigDecimal("2.00")));
					
				} else {
					paycheckDetail = mapper.map(paycheckDB, PaycheckDetail.class);
					paycheckDetail.setExpectedGrossAmount(expectedGrossAmount);
					paycheckDetail.setExpectedNetPay(expectedNetPay);
					paycheckDetailsMap.put(paycheckDetail.getMonth(), paycheckDetail);
				}
				paycheckDetail.setNetPayReal(paycheckDetail.getNetPay().subtract(paycheckDetail.getReimbursement()));
				
				PaycheckUnit paycheckUnit = mapper.map(paycheckDB, PaycheckUnit.class);
				paycheckUnit.setNetPayReal(paycheckDB.getNetPay().subtract(paycheckDB.getReimbursement()));
				paycheckDetail.add(paycheckUnit);
			}
		}

		paycheckSummary.setExpectedGrossAmount(totalExpectedGrossAmount);
		paycheckSummary.setGrossAmount(totalGrossAmount);
		paycheckSummary.setGrossAmountRemain(totalGrossAmount.subtract(totalExpectedGrossAmount));
		paycheckSummary.setReimbursement(totalReimbursement);
		paycheckSummary.setExpectedNetPay(totalExpectedNetPay);
		paycheckSummary.setNetPay(totalNetPay);
		paycheckSummary.setNetPayReal(totalRealNetPay);
		paycheckSummary.setNetPayRemain(totalRealNetPay.subtract(totalExpectedNetPay));
		
		paycheckSummary.setYearProgress((maxMonth * 100) / 12);
		paycheckSummary.setNetPayRealMean(totalRealNetPay.divide(new BigDecimal(maxMonth), RoundingMode.HALF_UP));
		
		paycheckSummary.setPaycheckDetails(paycheckDetailsMap.values());

		return paycheckSummary;
	}

}
