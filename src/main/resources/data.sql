-- User user/pass
INSERT INTO users (username, password) VALUES ('user', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a');
-- User user2/pass
INSERT INTO users (username, password) VALUES ('user2', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a');
-- User user3/pass
INSERT INTO users (username, password) VALUES ('user3', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a');

INSERT INTO authorities (username, authority) VALUES ('user', 'ADMIN');
INSERT INTO authorities (username, authority) VALUES ('user2', 'USER');

INSERT INTO authorities (username, authority) VALUES ('user3', 'ADMIN');

INSERT INTO groups (group_name) VALUES ('family budget');
INSERT INTO groups (group_name) VALUES ('family budget 2');

INSERT INTO group_members (username, group_id) VALUES ('user', 1);
INSERT INTO group_members (username, group_id) VALUES ('user2', 1);

INSERT INTO group_members (username, group_id) VALUES ('user3', 2);

--CREATE ACCOUNTS
INSERT INTO accounts (name, type, group_id) VALUES ('My Bank Checking A', 'checking', 1);
INSERT INTO accounts (name, type, group_id) VALUES ('My Bank Savings A', 'savings', 1);
INSERT INTO accounts (name, type, group_id) VALUES ('My Brokerage A', 'investment', 1);

INSERT INTO accounts (name, type, group_id) VALUES ('My Bank Checking B', 'checking', 1);
INSERT INTO accounts (name, type, group_id) VALUES ('My Bank Savings B', 'savings', 1);
INSERT INTO accounts (name, type, group_id) VALUES ('My Brokerage B', 'investment', 1);

INSERT INTO accounts (name, type, group_id) VALUES ('My Bank Checking C', 'checking', 2);
INSERT INTO accounts (name, type, group_id) VALUES ('My Bank Savings C', 'savings', 2);
INSERT INTO accounts (name, type, group_id) VALUES ('My Brokerage C', 'investment', 2);

