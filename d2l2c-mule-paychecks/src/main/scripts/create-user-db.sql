--
-- Table structure for tables
--

SET SCHEMA SALARY;

--DROP TABLE IF EXISTS users;
--DROP TABLE IF EXISTS user_profiles;
--DROP TABLE IF EXISTS users_user_profiles;
--DROP TABLE IF EXISTS persistent_logins;

CREATE TABLE users (
  user_id bigint(20) NOT NULL AUTO_INCREMENT,
  user_name varchar(10) NOT NULL,
  password varchar(64) NOT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (user_id),
  UNIQUE (user_name)
);

/* user_profiles table contains all possible roles */
create table user_profiles (
   profile_id bigint(20) NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (profile_id),
   UNIQUE (type)
);

/* JOIN TABLE for MANY-TO-MANY relationship*/
CREATE TABLE users_user_profiles (
    user_id BIGINT NOT NULL,
    profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, profile_id),
    UNIQUE (user_id, profile_id),
    CONSTRAINT FK_USERS FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_USER_PROFILES FOREIGN KEY (profile_id) REFERENCES user_profiles (profile_id)
);

/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);
