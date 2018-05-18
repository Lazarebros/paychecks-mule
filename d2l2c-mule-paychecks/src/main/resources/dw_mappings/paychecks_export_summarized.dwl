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
	grossRemain: (sum paychecks.*grossAmount) - getExpectedGross(paychecks),
	reimbursement: sum paychecks.*reimbursement,
	expectedNet: getExpectedNet(paychecks),
	netPay: sum paychecks.*netPay,
	realNet: getRealNet(paychecks),
	netRemain: (getRealNet(paychecks) - getExpectedNet(paychecks)) as :currency as :number
}) orderBy $.year)[-1 to 0]