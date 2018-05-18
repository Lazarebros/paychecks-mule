SET SCHEMA salary;

--DELETE FROM users_user_profiles;
--DELETE FROM users;
--DELETE FROM user_profiles;
--DELETE FROM persistent_logins;

/* Populate users Table */
INSERT INTO users (user_id, user_name, password, first_name, last_name, enabled) VALUES
(1, 'test', 'test', 'test', 'test', 1),
(2, 'test1', 'test1', 'test1', 'test1', 1);

update users set password = '$2a$10$NS7rmMeQ9OFQIzDy0daPVu8vjRryl85Nvsy.cAURz6/DNiOqgpdNi' where user_id = 1;
update users set password = '$2a$10$NS7rmMeQ9OFQIzDy0daPVu8vjRryl85Nvsy.cAURz6/DNiOqgpdNi' where user_id = 2;

/* Populate user_profiles Table */
INSERT INTO user_profiles (profile_id, type) VALUES
(1, 'ADMIN'),
(2, 'DBA'),
(3, 'USER');

/* Populate JOIN Table */
INSERT INTO users_user_profiles (user_id, profile_id)
  SELECT user.user_id, profile.profile_id FROM users user, user_profiles profile
  where user.user_name='test' and profile.type='ADMIN';

/* Populate JOIN Table */
INSERT INTO users_user_profiles (user_id, profile_id)
  SELECT user.user_id, profile.profile_id FROM users user, user_profiles profile
  where user.user_name='test1' and profile.type='USER';

/* Populate persistent_logins Table */
INSERT INTO persistent_logins (username, series, token, last_used) VALUES
('test', 'test', 'test', NOW());
