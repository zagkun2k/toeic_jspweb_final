use toieconline;

ALTER TABLE user MODIFY COLUMN name VARCHAR(255) NOT NULL;
ALTER TABLE user MODIFY COLUMN password VARCHAR(255) NOT NULL;
ALTER TABLE user MODIFY COLUMN roleid BIGINT(20) NOT NULL;