/**
 * 
 */
package com.d2l2c.mule.paychecks.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
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

	private static final Mapper mapper = new DozerBeanMapper();
	
	public static Collection<PaycheckSummary> getSummarizedPaychecks(List<Map<String, Object>> mapObject) {
		List<PaycheckDB> paycheckDBList = mapPaycheckDB(mapObject);
		
		Map<Integer, PaycheckSummary> mapOfPaycheckSummaryMap = paycheckDBList.stream().collect(
				Collectors.groupingBy(PaycheckDB::getYear, Collectors.collectingAndThen(
						Collectors.toList(), l -> {
							return getPaychecks(l, false);
						})));

		return mapOfPaycheckSummaryMap.values();
	}
	
	public static PaycheckSummary getDetailledPaychecks(List<Map<String, Object>> mapObject) {
		List<PaycheckDB> paycheckDBList = mapPaycheckDB(mapObject);
		return getPaychecks(paycheckDBList, true);
	}
	
	private static List<PaycheckDB> mapPaycheckDB(List<Map<String, Object>> mapObject) {
		List<PaycheckDB> paycheckDBList = new ArrayList<PaycheckDB>();
		try {
			for(Map<String, Object> map: mapObject) {
				PaycheckDB paycheckDB = mapper.map(map, PaycheckDB.class);
				paycheckDBList.add(paycheckDB);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return paycheckDBList;
	}

	public static PaycheckSummary getPaychecks(List<PaycheckDB> paycheckDBList, boolean withDetails) {
		PaycheckSummary paycheckSummary = new PaycheckSummary();
		paycheckSummary.setYear(paycheckDBList.get(0).getYear());
		paycheckSummary.setNumnerOfPaychecks(new Long(paycheckDBList.size()));

		BigDecimal totalGrossAmount = new BigDecimal("0.00");
		BigDecimal totalNetPay = new BigDecimal("0.00");
		BigDecimal totalReimbursement = new BigDecimal("0.00");
		BigDecimal totalExpectedGrossAmount = new BigDecimal("0.00");
		BigDecimal totalExpectedNetPay = new BigDecimal("0.00");

		for(PaycheckDB paycheckDB: paycheckDBList) {
			totalGrossAmount = totalGrossAmount.add(paycheckDB.getGrossAmount());
			totalNetPay = totalNetPay.add(paycheckDB.getNetPay());
			totalReimbursement = totalReimbursement.add(paycheckDB.getReimbursement());
			totalExpectedGrossAmount = totalExpectedGrossAmount.add(paycheckDB.getHourlyRate().multiply(new BigDecimal(paycheckDB.getExpectedNumberOfHours())));
			totalExpectedNetPay = totalExpectedNetPay.add(totalExpectedGrossAmount.multiply(paycheckDB.getNetPercentageOfGross()));
			
			if(withDetails) {
				PaycheckDetails paycheckDetails= mapper.map(paycheckDB, PaycheckDetails.class);
				paycheckSummary.add(paycheckDetails);
			}
		}

		paycheckSummary.setExpectedGrossAmount(totalExpectedGrossAmount);
		paycheckSummary.setGrossAmount(totalGrossAmount);
		paycheckSummary.setGrossAmountRemain(totalGrossAmount.subtract(totalExpectedGrossAmount));
		paycheckSummary.setReimbursement(totalReimbursement);
		paycheckSummary.setExpectedNetPay(totalExpectedNetPay);
		paycheckSummary.setNetPay(totalNetPay);
		paycheckSummary.setNetPayReal(totalNetPay.subtract(totalReimbursement));
		paycheckSummary.setNetPayRemain(totalNetPay.subtract(totalExpectedNetPay));

		return paycheckSummary;
	}

}
