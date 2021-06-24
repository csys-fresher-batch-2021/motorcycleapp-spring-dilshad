DROP TABLE member_details; 
CREATE TABLE member_details (
id SERIAL PRIMARY KEY,
name VARCHAR(30) NOT NULL ,
phone_number BIGINT NOT NULL UNIQUE,
address VARCHAR(100),
email_id VARCHAR(50)  NOT NULL UNIQUE,
role VARCHAR(15) NOT NULL,
password VARCHAR(30) NOT NULL,
registered_date DATE NOT NULL,
last_login_date DATE
);

SELECT * FROM member_details;