CREATE TYPE gender AS ENUM ('MALE', 'FEMALE', 'OTHER');
CREATE TYPE vaccine_status AS ENUM ('NOT_VACCINATED', 'PARTIALLY_VACCINATED', 'FULLY_VACCINATED');
CREATE TYPE vaccine_type AS ENUM ('ASTRAZENECA', 'PFIZER');
CREATE TYPE account_type AS ENUM ('VR', 'HCP', 'ADMIN');
CREATE TYPE hcp_type AS ENUM ('CLINIC', 'HOSPITAL', 'GP', 'POPUP', 'OTHER');
CREATE TYPE outcome AS ENUM ('PASS', 'FAIL');

CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    account_type account_type NOT NULL
);

CREATE TABLE IF NOT EXISTS healthcare_provider (
    id SERIAL PRIMARY KEY,
    organisational_id INT NOT NULL,
    account INT UNIQUE,
    hcp_name VARCHAR(50),
    hcp_type hcp_type,
    postcode INT,
    FOREIGN KEY (account)
        REFERENCES account (id)
);

CREATE TABLE IF NOT EXISTS vaccine_recipient (
    id SERIAL PRIMARY KEY,
    account INT UNIQUE,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    address VARCHAR(100),
    date_of_birth DATE NOT NULL,
    gender gender,
    phone_number VARCHAR(20),
    email_address VARCHAR(50),
    vaccination_status vaccine_status,
    vaccination_type vaccine_type,
    FOREIGN KEY (account)
        REFERENCES account (id)
);

CREATE TABLE IF NOT EXISTS vaccine_certificate (
    id SERIAL PRIMARY KEY,
    vaccine_recipient INT UNIQUE NOT NULL,
    healthcare_provider INT NOT NULL,
    vaccination_type vaccine_type,
    date_issued DATE NOT NULL,
    FOREIGN KEY (vaccine_recipient)
        REFERENCES vaccine_recipient (id),
    FOREIGN KEY (healthcare_provider)
        REFERENCES healthcare_provider (id)
);

CREATE TABLE IF NOT EXISTS questionnaire (
    id SERIAL PRIMARY KEY,
    date_taken DATE,
    outcome outcome
);

CREATE TABLE IF NOT EXISTS timeslot (
    id SERIAL PRIMARY KEY,
    vaccine_recipient INT,
    healthcare_provider INT NOT NULL,
    questionnaire INT,
    vaccination_type vaccine_type,
    date_time TIMESTAMP NOT NULL,
    duration INT,
    location VARCHAR(100),
    FOREIGN KEY (vaccine_recipient)
        REFERENCES vaccine_recipient (id),
    FOREIGN KEY (healthcare_provider)
        REFERENCES healthcare_provider (id),
    FOREIGN KEY (questionnaire)
        REFERENCES questionnaire (id)
);