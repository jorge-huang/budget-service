--CREATE USERS
-- User user/pass
INSERT INTO users (username, password) VALUES ('user', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a');
-- User user2/pass
INSERT INTO users (username, password) VALUES ('user2', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a');
-- User user3/pass
INSERT INTO users (username, password) VALUES ('user3', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a');

--CREATE AUTHORITIES
INSERT INTO authorities (username, authority) VALUES ('user', 'ADMIN');
INSERT INTO authorities (username, authority) VALUES ('user2', 'USER');
INSERT INTO authorities (username, authority) VALUES ('user3', 'ADMIN');

--CREATE GROUPS
INSERT INTO groups (group_name) VALUES ('family budget');
INSERT INTO groups (group_name) VALUES ('family budget 2');

--CREATE GROUP MEMBERS
INSERT INTO group_members (username, group_id) VALUES ('user', 1);
INSERT INTO group_members (username, group_id) VALUES ('user2', 1);
INSERT INTO group_members (username, group_id) VALUES ('user3', 2);

--CREATE ACCOUNTS
-- ACCOUNT ID: 1
INSERT INTO accounts (name, type, group_id) VALUES ('My Bank Checking A', 'checking', 1);
-- ACCOUNT ID: 2
INSERT INTO accounts (name, type, group_id) VALUES ('My Bank Savings A', 'savings', 1);
-- ACCOUNT ID: 3
INSERT INTO accounts (name, type, group_id) VALUES ('My Brokerage A', 'investment', 1);
-- ACCOUNT ID: 4
INSERT INTO accounts (name, type, group_id) VALUES ('My Bank Checking B', 'checking', 1);
-- ACCOUNT ID: 5
INSERT INTO accounts (name, type, group_id) VALUES ('My Bank Savings B', 'savings', 1);
-- ACCOUNT ID: 6
INSERT INTO accounts (name, type, group_id) VALUES ('My Brokerage B', 'investment', 1);
-- ACCOUNT ID: 7
INSERT INTO accounts (name, type, group_id) VALUES ('My Bank Checking C', 'checking', 2);
-- ACCOUNT ID: 8
INSERT INTO accounts (name, type, group_id) VALUES ('My Bank Savings C', 'savings', 2);
-- ACCOUNT ID: 9
INSERT INTO accounts (name, type, group_id) VALUES ('My Brokerage C', 'investment', 2);

--CREATE TRANSACTIONS
-- ACCOUNT ID: 1
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('food from best grocery', 'food', 10.50, PARSEDATETIME('2021-01-01', 'yyyy-MM-dd'), 1, 1);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('remedy from pharmacy', 'health', 150.50, PARSEDATETIME('2021-02-02', 'yyyy-MM-dd'), 1, 1);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('rent', 'house', 1000.50, PARSEDATETIME('2021-03-03', 'yyyy-MM-dd'), 1, 1);

-- ACCOUNT ID: 2
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('food from best grocery', 'food', 10.50, PARSEDATETIME('2021-03-01', 'yyyy-MM-dd'), 2, 1);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('remedy from pharmacy', 'health', 150.50, PARSEDATETIME('2021-04-02', 'yyyy-MM-dd'), 2, 1);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('rent', 'house', 1000.50, PARSEDATETIME('2021-05-03', 'yyyy-MM-dd'), 2, 1);

-- ACCOUNT ID: 3
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('food from best grocery', 'food', 10.50, PARSEDATETIME('2021-06-01', 'yyyy-MM-dd'), 3, 1);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('remedy from pharmacy', 'health', 150.50, PARSEDATETIME('2021-07-02', 'yyyy-MM-dd'), 3, 1);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('rent', 'house', 1000.50, PARSEDATETIME('2021-08-03', 'yyyy-MM-dd'), 3, 1);

-- ACCOUNT ID: 4
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('food from best grocery', 'food', 10.50, PARSEDATETIME('2021-01-01', 'yyyy-MM-dd'), 4, 1);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('remedy from pharmacy', 'health', 150.50, PARSEDATETIME('2021-02-02', 'yyyy-MM-dd'), 4, 1);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('rent', 'house', 1000.50, PARSEDATETIME('2021-03-03', 'yyyy-MM-dd'), 4, 1);

-- ACCOUNT ID: 5
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('food from best grocery', 'food', 10.50, PARSEDATETIME('2021-03-01', 'yyyy-MM-dd'), 5, 1);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('remedy from pharmacy', 'health', 150.50, PARSEDATETIME('2021-04-02', 'yyyy-MM-dd'), 5, 1);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('rent', 'house', 1000.50, PARSEDATETIME('2021-05-03', 'yyyy-MM-dd'), 5, 1);

-- ACCOUNT ID: 6
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('food from best grocery', 'food', 10.50, PARSEDATETIME('2021-06-01', 'yyyy-MM-dd'), 6, 1);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('remedy from pharmacy', 'health', 150.50, PARSEDATETIME('2021-07-02', 'yyyy-MM-dd'), 6, 1);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('rent', 'house', 1000.50, PARSEDATETIME('2021-08-03', 'yyyy-MM-dd'), 6, 1);

-- ACCOUNT ID: 7
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('food from best grocery', 'food', 10.50, PARSEDATETIME('2021-06-01', 'yyyy-MM-dd'), 7, 2);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('remedy from pharmacy', 'health', 150.50, PARSEDATETIME('2021-07-02', 'yyyy-MM-dd'), 7, 2);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('rent', 'house', 1000.50, PARSEDATETIME('2021-08-03', 'yyyy-MM-dd'), 7, 2);

-- ACCOUNT ID: 8
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('food from best grocery', 'food', 10.50, PARSEDATETIME('2021-06-01', 'yyyy-MM-dd'), 8, 2);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('remedy from pharmacy', 'health', 150.50, PARSEDATETIME('2021-07-02', 'yyyy-MM-dd'), 8, 2);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('rent', 'house', 1000.50, PARSEDATETIME('2021-08-03', 'yyyy-MM-dd'), 8, 2);

-- ACCOUNT ID: 9
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('food from best grocery', 'food', 10.50, PARSEDATETIME('2021-06-01', 'yyyy-MM-dd'), 9, 2);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('remedy from pharmacy', 'health', 150.50, PARSEDATETIME('2021-07-02', 'yyyy-MM-dd'), 9, 2);
INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES ('rent', 'house', 1000.50, PARSEDATETIME('2021-08-03', 'yyyy-MM-dd'), 9, 2);