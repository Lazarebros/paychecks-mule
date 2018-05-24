/**
 * 
 */
package com.d2l2c.mule.paychecks.util;

import java.math.BigDecimal;
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

import com.d2l2c.mule.paychecks.bean.PaycheckDB;
import com.d2l2c.mule.paychecks.bean.PaycheckDetails;
import com.d2l2c.mule.paychecks.bean.PaycheckSummary;

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
					return getPaychecks(l, false);
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
		BigDecimal realNetPay = new BigDecimal("0.00");
		BigDecimal totalReimbursement = new BigDecimal("0.00");
		BigDecimal totalExpectedGrossAmount = new BigDecimal("0.00");
		BigDecimal totalExpectedNetPay = new BigDecimal("0.00");

		Map<Integer, PaycheckDetails> paycheckDetailsMap = new HashMap<Integer, PaycheckDetails>();
		
		for(PaycheckDB paycheckDB : paycheckDBList) {
			totalGrossAmount = totalGrossAmount.add(paycheckDB.getGrossAmount());
			totalNetPay = totalNetPay.add(paycheckDB.getNetPay());
			totalReimbursement = totalReimbursement.add(paycheckDB.getReimbursement());
			realNetPay = totalNetPay.subtract(totalReimbursement);

			BigDecimal expectedGrossAmount = paycheckDB.getHourlyRate().multiply(new BigDecimal(paycheckDB.getExpectedNumberOfHours()));
			totalExpectedGrossAmount = totalExpectedGrossAmount.add(expectedGrossAmount);
			
			BigDecimal expectedNetPay = expectedGrossAmount.multiply(paycheckDB.getNetPercentageOfGross());
			totalExpectedNetPay = totalExpectedGrossAmount.multiply(paycheckDB.getNetPercentageOfGross());

			if (withDetails) {
				PaycheckDetails paycheckDetails = null;
				
				if (paycheckDetailsMap.containsKey(paycheckDB.getMonth())) {
					paycheckDetails = paycheckDetailsMap.get(paycheckDB.getMonth());
					
					PaycheckDetails paycheckDetailsCopy = mapper.map(paycheckDB, PaycheckDetails.class);
					
					BigDecimal grossAmount = paycheckDetails.getGrossAmount().add(paycheckDetailsCopy.getGrossAmount());
					BigDecimal netPay = paycheckDetails.getNetPay().add(paycheckDetailsCopy.getNetPay());
					BigDecimal reimbursement = paycheckDetails.getReimbursement().add(paycheckDetailsCopy.getReimbursement());
					
					Long numberOfHours = paycheckDetails.getNumberOfHours() + paycheckDetailsCopy.getNumberOfHours();

					paycheckDetails.setGrossAmount(grossAmount);
					paycheckDetails.setNetPay(netPay);
					paycheckDetails.setReimbursement(reimbursement);
					paycheckDetails.setNumberOfHours(numberOfHours);
					paycheckDetails.setEndDate(paycheckDetailsCopy.getEndDate());
					
					paycheckDetails.setExpectedGrossAmount(expectedGrossAmount.multiply(new BigDecimal("2.00")));
					paycheckDetails.setExpectedNetPay(expectedNetPay.multiply(new BigDecimal("2.00")));
					
				} else {
					paycheckDetails = mapper.map(paycheckDB, PaycheckDetails.class);
					paycheckDetails.setExpectedGrossAmount(expectedGrossAmount);
					paycheckDetails.setExpectedNetPay(expectedNetPay);
					paycheckDetailsMap.put(paycheckDetails.getMonth(), paycheckDetails);
				}
				
			}
		}

		paycheckSummary.setExpectedGrossAmount(totalExpectedGrossAmount);
		paycheckSummary.setGrossAmount(totalGrossAmount);
		paycheckSummary.setGrossAmountRemain(totalGrossAmount.subtract(totalExpectedGrossAmount));
		paycheckSummary.setReimbursement(totalReimbursement);
		paycheckSummary.setExpectedNetPay(totalExpectedNetPay);
		paycheckSummary.setNetPay(totalNetPay);
		paycheckSummary.setNetPayReal(realNetPay);
		paycheckSummary.setNetPayRemain(realNetPay.subtract(totalExpectedNetPay));
		
		paycheckSummary.setPaycheckDetailsList(paycheckDetailsMap.values());

		return paycheckSummary;
	}

}
