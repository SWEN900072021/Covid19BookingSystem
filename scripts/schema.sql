-- DO NOT UNCOMMENT THIS
-- DROP SCHEMA public CASCADE;
-- CREATE SCHEMA public;

CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(500) NOT NULL,
    account_type VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS vaccine_type (
    name VARCHAR(50) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS health_care_provider (
    id SERIAL PRIMARY KEY,
    account_id INT NOT NULL,
    organisational_id INT NOT NULL,
    health_care_provider_name VARCHAR(50),
    health_care_provider_type VARCHAR(50),
    postcode VARCHAR(20),
    FOREIGN KEY (account_id)
        REFERENCES account (id)
);

CREATE TABLE IF NOT EXISTS vaccine_recipient (
    id SERIAL PRIMARY KEY,
    account_id INT NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    address_line_1 VARCHAR(50),
    address_line_2 VARCHAR(50),
    postcode VARCHAR(20),
    state VARCHAR(50),
    country VARCHAR(50),
    date_of_birth DATE NOT NULL,
    gender VARCHAR(50),
    phone_number VARCHAR(20),
    email_address VARCHAR(50),
    FOREIGN KEY (account_id)
        REFERENCES account (id)
);

CREATE TABLE IF NOT EXISTS vaccine_certificate (
    vaccine_recipient INT NOT NULL,
    vaccine_type VARCHAR(50),
    FOREIGN KEY (vaccine_recipient)
        REFERENCES vaccine_recipient (id),
    FOREIGN KEY (vaccine_type)
        REFERENCES vaccine_type (name),
    PRIMARY KEY (vaccine_recipient, vaccine_type)
);

CREATE TABLE IF NOT EXISTS question (
    id SERIAL PRIMARY KEY,
    question VARCHAR(50),
    success_answer BOOLEAN
);

CREATE TABLE IF NOT EXISTS vaccine_question (
    vaccine_type VARCHAR(50),
    question_id INT,
    FOREIGN KEY (vaccine_type)
        REFERENCES vaccine_type (name),
    FOREIGN KEY (question_id)
        REFERENCES question (id),
    PRIMARY KEY (vaccine_type, question_id)
);

CREATE TABLE IF NOT EXISTS timeslot (
    id SERIAL PRIMARY KEY,
    vaccine_recipient INT,
    health_care_provider INT NOT NULL,
    vaccine_type VARCHAR(50),
    status VARCHAR(50),
    date_time TIMESTAMP NOT NULL,
    duration INT,
    address_line_1 VARCHAR(50),
    address_line_2 VARCHAR(50),
    postcode VARCHAR(20),
    state VARCHAR(50),
    country VARCHAR(50),
    FOREIGN KEY (vaccine_recipient)
        REFERENCES vaccine_recipient (id),
    FOREIGN KEY (health_care_provider)
        REFERENCES health_care_provider (id),
    FOREIGN KEY (vaccine_type)
        REFERENCES vaccine_type (name)
);