--CREATE USER PERMISSION
INSERT INTO permission (id) VALUES ('ADMIN');
INSERT INTO permission (id) VALUES ('USER');

--Create GROUP
INSERT INTO `group` (description) VALUES ('my awesome group');

--CREATE ADMIN USER
INSERT INTO user (id) VALUES ('marco');
INSERT INTO `group` (description) VALUES ('my awesome group');
INSERT INTO user_group (user_id, group_id) VALUES ('marco', LAST_INSERT_ID());
INSERT INTO user_permission (user_id, permission_id) VALUES ('marco', 'ADMIN');

INSERT INTO user (id) VALUES ('penny');
INSERT INTO user_group (user_id, group_id) VALUES ('penny', 1);
INSERT INTO user_permission (user_id, permission_id) VALUES ('penny', 'USER');

----CREATE USER BY ADMIN
INSERT INTO user (id) VALUES ('camilo');
INSERT INTO user_group (user_id, group_id) VALUES ('camilo', 1);
INSERT INTO user_permission (user_id, permission_id) VALUES ('camilo', 'USER');

--CREATE ACCOUNTS
INSERT INTO account (name, type, user_id) VALUES ('My Bank Checking A', 'checking', 'marco');
INSERT INTO account (name, type, user_id) VALUES ('My Bank Savings A', 'savings', 'marco');
INSERT INTO account (name, type, user_id) VALUES ('My Brokerage A', 'investment', 'marco');

INSERT INTO account (name, type, user_id) VALUES ('My Bank Checking B', 'checking', 'penny');
INSERT INTO account (name, type, user_id) VALUES ('My Bank Savings B', 'savings', 'penny');
INSERT INTO account (name, type, user_id) VALUES ('My Brokerage B', 'investment', 'penny');

INSERT INTO account (name, type, user_id) VALUES ('My Bank Checking C', 'checking', 'camilo');
INSERT INTO account (name, type, user_id) VALUES ('My Bank Savings C', 'savings', 'camilo');
INSERT INTO account (name, type, user_id) VALUES ('My Brokerage C', 'investment', 'camilo');

--CREATE ACTIVITIES
--INSERT INTO activity (description, category, amount, date, account_id, user_id) VALUES ('test', 'home', 0, '2021-01-31', 1, 'marco');

