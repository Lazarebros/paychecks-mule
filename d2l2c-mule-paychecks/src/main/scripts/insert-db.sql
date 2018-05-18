DELETE FROM paychecks;
DELETE FROM rates;
DELETE FROM companies;

/* COMPANIES */
INSERT INTO companies (company_id, code, name) VALUES (1,'MMI', 'Mitchell Martin INC');
INSERT INTO companies (company_id, code, name) VALUES (2,'MS3', 'Mountain State Software Solutions LLC');

/* PAYCHECKS */
INSERT INTO paychecks (paycheck_id, company_id, year, month, bi_week, start_date, end_date, number_of_hours, gross_amount, net_pay, reimbursement)
VALUES (1, 1, 2017, 01, 1, parsedatetime('2017-01-01', 'yyyy-MM-dd'), parsedatetime('2017-01-15', 'yyyy-MM-dd'), 2, 200.00, 100.00, 10.00);

INSERT INTO paychecks (paycheck_id, company_id, year, month, bi_week, start_date, end_date, number_of_hours, gross_amount, net_pay, reimbursement)
VALUES (2, 1, 2017, 01, 2, parsedatetime('2017-01-16', 'yyyy-MM-dd'), parsedatetime('2017-01-30', 'yyyy-MM-dd'), 3, 200.00, 100.00, 10.00);

INSERT INTO paychecks (paycheck_id, company_id, year, month, bi_week, start_date, end_date, number_of_hours, gross_amount, net_pay, reimbursement)
VALUES (3, 1, 2017, 02, 1, parsedatetime('2017-02-01', 'yyyy-MM-dd'), parsedatetime('2017-02-15', 'yyyy-MM-dd'), 2, 200.00, 100.00, 10.00);

INSERT INTO paychecks (paycheck_id, company_id, year, month, bi_week, start_date, end_date, number_of_hours, gross_amount, net_pay, reimbursement)
VALUES (4, 2, 2017, 02, 1, parsedatetime('2017-02-01', 'yyyy-MM-dd'), parsedatetime('2017-02-15', 'yyyy-MM-dd'), 4, 200.00, 100.00, 10.00);

INSERT INTO paychecks (paycheck_id, company_id, year, month, bi_week, start_date, end_date, number_of_hours, gross_amount, net_pay, reimbursement)
VALUES (5, 2, 2017, 02, 2, parsedatetime('2017-02-16', 'yyyy-MM-dd'), parsedatetime('2017-02-28', 'yyyy-MM-dd'), 4, 200.00, 100.00, 10.00);

INSERT INTO paychecks (paycheck_id, company_id, year, month, bi_week, start_date, end_date, number_of_hours, gross_amount, net_pay, reimbursement)
VALUES (6, 2, 2016, 02, 2, parsedatetime('2016-02-16', 'yyyy-MM-dd'), parsedatetime('2016-02-28', 'yyyy-MM-dd'), 4, 200.00, 100.00, 10.00);

/* RATES */
INSERT INTO rates (rate_id, company_id, hourly_rate, start_date, end_date, expected_gross, expected_net_pay) VALUES (1, 1, 81.00, parsedatetime('2015-02-16', 'yyyy-MM-dd'), NULL, 6390.00, 4415.00);
INSERT INTO rates (rate_id, company_id, hourly_rate, start_date, end_date, expected_gross, expected_net_pay) VALUES (2, 2, 100.00, parsedatetime('2015-02-16', 'yyyy-MM-dd'), NULL, 8000.00, 5600.00);
