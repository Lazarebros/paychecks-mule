%dw 1.0
%output application/json
%function getRealNet(paychecks) (sum paychecks.*netPay) - (sum paychecks.*reimbursement)
%function getExpectedGross(paychecks) (sum (paychecks map {
		expectedGross: $.expectedNumberOfHours * $.hourlyRate
	}).*expectedGross
)
%function getExpectedNet(paychecks) (sum (paychecks map {
	expectedNetPay: $.expectedNumberOfHours * $.hourlyRate * $.netPercentageOfGross
	}).*expectedNetPay
)

%type currency = :string {format: ".00"}
---
(payload groupBy $.year map ((paychecks, index) -> {
	year: paychecks[0].year,
	numnerOfPaychecks: sizeOf paychecks,
	expectedGrossAmount: getExpectedGross(paychecks),
	grossAmount: sum paychecks.*grossAmount,
	grossAmountRemain: (sum paychecks.*grossAmount) - getExpectedGross(paychecks),
	reimbursement: sum paychecks.*reimbursement,
	expectedNetPay: getExpectedNet(paychecks),
	netPay: sum paychecks.*netPay,
	netPayReal: getRealNet(paychecks),
	netPayRemain: (getRealNet(paychecks) - getExpectedNet(paychecks)) as :currency as :number,
	paychecks: paychecks map ((paycheck, index) -> {
		id: paycheck.id,
		companyCode: paycheck.companyCode,
		startDate: paycheck.startDate,
		endDate: paycheck.endDate,
		numberOfHours: paycheck.numberOfHours,
		hourlyRate: paycheck.hourlyRate,
		expectedGrossAmount: paycheck.expectedNumberOfHours * paycheck.hourlyRate,
		grossAmount: paycheck.grossAmount,
		expectedNetPay: (paycheck.expectedNumberOfHours * paycheck.hourlyRate * paycheck.netPercentageOfGross) as :currency as :number,
		netPay: paycheck.netPay,
		reimbursement: paycheck.reimbursement
	})
}) orderBy $.year)[-1 to 0]