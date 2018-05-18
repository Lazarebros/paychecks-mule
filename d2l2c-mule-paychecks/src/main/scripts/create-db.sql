--
-- Table structure for tables
--

CREATE SCHEMA IF NOT EXISTS salary;

SET SCHEMA salary;

--
-- USER RELATED TABLES
--

--DROP TABLE IF EXISTS users;
--DROP TABLE IF EXISTS user_profiles;
--DROP TABLE IF EXISTS users_user_profiles;
--DROP TABLE IF EXISTS persistent_logins;


CREATE TABLE IF NOT EXISTS users (
  user_id bigint(20) NOT NULL AUTO_INCREMENT,
  user_name varchar(10) NOT NULL,
  password varchar(64) NOT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  enabled BIT,
  PRIMARY KEY (user_id),
  UNIQUE (user_name)
);

/* user_profiles table contains all possible roles */
CREATE TABLE IF NOT EXISTS user_profiles (
   profile_id bigint(20) NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (profile_id),
   UNIQUE (type)
);

/* JOIN TABLE for USERS AND USER_PROFILES */
CREATE TABLE IF NOT EXISTS users_user_profiles (
    user_id BIGINT(20) NOT NULL,
    profile_id BIGINT(20) NOT NULL,
    PRIMARY KEY (user_id, profile_id),
    CONSTRAINT FK_USERS_USER_PROFILES_USER_ID FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_USERS_USER_PROFILES_PROFILE_ID FOREIGN KEY (profile_id) REFERENCES user_profiles (profile_id)
);

/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE IF NOT EXISTS persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

--
-- PAYCHECK RELATED TABLES
--

--DROP TABLE IF EXISTS paychecks;
--DROP TABLE IF EXISTS paycheck_configs;
--DROP TABLE IF EXISTS companies;
--DROP TABLE IF EXISTS users_companies;

CREATE TABLE IF NOT EXISTS companies (
  company_id bigint(20) NOT NULL AUTO_INCREMENT,
  code varchar(32) NOT NULL,
  name varchar(64) NOT NULL,
  PRIMARY KEY (company_id),
  CONSTRAINT UC_Companies_Code UNIQUE (code)
);

CREATE TABLE IF NOT EXISTS paycheck_configs (
  config_id bigint(20) NOT NULL AUTO_INCREMENT,
  expected_number_of_hours bigint(20) DEFAULT 0,
  net_percentage_of_gross decimal(19,2) DEFAULT 0.00,
  PRIMARY KEY (config_id),
  CONSTRAINT uc_paychecks_config UNIQUE (expected_number_of_hours, net_percentage_of_gross)
);

CREATE TABLE IF NOT EXISTS paychecks (
  paycheck_id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) NOT NULL,
  company_id bigint(20) NOT NULL,
  config_id bigint(20) NOT NULL,
  year int(20) NOT NULL,
  month int(20) NOT NULL,
  bi_week int(20) NOT NULL,
  start_date datetime NOT NULL,
  end_date datetime NOT NULL,
  number_of_hours bigint(20) DEFAULT 0,
  hourly_rate decimal(19,2) DEFAULT 0.00,
  gross_amount decimal(19,2) DEFAULT 0.00,
  net_pay decimal(19,2) DEFAULT 0.00,
  reimbursement decimal(19,2) DEFAULT 0.00,
  PRIMARY KEY (paycheck_id),
  CONSTRAINT fk_paycheck_user_id FOREIGN KEY (user_id) references users(user_id),
  CONSTRAINT fk_paycheck_company_id FOREIGN KEY (company_id) references companies(company_id),
  CONSTRAINT fk_paycheck_config_id FOREIGN KEY (config_id) references paycheck_configs(config_id),
  CONSTRAINT UC_Paychecks UNIQUE (user_id,start_date,end_date)
);

/* JOIN TABLE for USERS AND COMPANIES */
CREATE TABLE IF NOT EXISTS users_companies (
    user_id BIGINT(20) NOT NULL,
    company_id BIGINT(20) NOT NULL,
    PRIMARY KEY (user_id, company_id),
    CONSTRAINT FK_USERS_COMPANIES_USER_ID FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_USERS_COMPANIES_COMPANY_ID FOREIGN KEY (company_id) REFERENCES companies (company_id)
);
