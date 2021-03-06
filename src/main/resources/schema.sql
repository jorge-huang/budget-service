CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT true,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username,authority);

CREATE TABLE IF NOT EXISTS groups (
    id BIGINT NOT NULL AUTO_INCREMENT,
    group_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

--CREATE TABLE IF NOT EXISTS group_authorities (
--    group_id BIGINT NOT NULL,
--    authority VARCHAR(50) NOT NULL,
--    FOREIGN KEY (group_id) REFERENCES groups(id)
--);

CREATE TABLE IF NOT EXISTS group_members (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    group_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (group_id) REFERENCES groups(id)
);


CREATE TABLE IF NOT EXISTS accounts (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	type VARCHAR(255),
    group_id BIGINT NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT true,
	PRIMARY KEY (id),
	FOREIGN KEY (group_id) REFERENCES groups(id)
);

CREATE TABLE IF NOT EXISTS transactions (
	id INT NOT NULL AUTO_INCREMENT,
	description VARCHAR(255),
	category VARCHAR(255),
	amount DECIMAL(13, 2),
	date DATE,
	account_id INT NOT NULL,
	group_id BIGINT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (account_id) REFERENCES accounts(id),
	FOREIGN KEY (group_id) REFERENCES groups(id)
);