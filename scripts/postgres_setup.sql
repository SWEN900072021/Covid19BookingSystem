-- DO NOT UNCOMMENT THIS
-- DROP SCHEMA public CASCADE;
-- CREATE SCHEMA public;


CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    account_type VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS health_care_provider (
    id SERIAL PRIMARY KEY,
    organisational_id INT NOT NULL,
    health_care_provider_name VARCHAR(50),
    health_care_provider_type VARCHAR(50),
    postcode VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS vaccine_recipient (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    address VARCHAR(100),
    date_of_birth DATE NOT NULL,
    gender VARCHAR(50),
    phone_number VARCHAR(20),
    email_address VARCHAR(50),
    vaccination_status VARCHAR(50),
    vaccination_type VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS vaccine_certificate (
    id SERIAL PRIMARY KEY,
    vaccine_recipient INT UNIQUE NOT NULL,
    healthcare_provider INT NOT NULL,
    vaccination_type VARCHAR(50),
    date_issued DATE NOT NULL,
    FOREIGN KEY (vaccine_recipient)
        REFERENCES vaccine_recipient (id),
    FOREIGN KEY (healthcare_provider)
        REFERENCES health_care_provider (id)
);

CREATE TABLE IF NOT EXISTS questionnaire (
    id SERIAL PRIMARY KEY,
    date_taken DATE,
    outcome VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS timeslot (
    id SERIAL PRIMARY KEY,
    vaccine_recipient INT,
    health_care_provider INT NOT NULL,
    questionnaire INT,
    vaccination_type VARCHAR(50),
    date_time TIMESTAMP NOT NULL,
    duration INT,
    location VARCHAR(100),
    FOREIGN KEY (vaccine_recipient)
        REFERENCES vaccine_recipient (id),
    FOREIGN KEY (health_care_provider)
        REFERENCES health_care_provider (id),
    FOREIGN KEY (questionnaire)
        REFERENCES questionnaire (id)
);