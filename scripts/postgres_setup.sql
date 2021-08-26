CREATE TYPE gender AS ENUM ('MALE', 'FEMALE', 'OTHER');
CREATE TYPE vaccine_status AS ENUM ('NOT_VACCINATED', 'PARTIALLY_VACCINATED', 'FULLY_VACCINATED');
CREATE TYPE vaccine_type AS ENUM ('ASTRAZENECA', 'PFIZER');
CREATE TYPE account_type AS ENUM ('VR', 'HCP', 'ADMIN');
CREATE TYPE hcp_type AS ENUM ('CLINIC', 'HOSPITAL', 'GP', 'POPUP', 'OTHER');
CREATE TYPE outcome AS ENUM ('PASS', 'FAIL');

CREATE TABLE IF NOT EXISTS accounts (
    account_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    account_type account_type NOT NULL
);

CREATE TABLE IF NOT EXISTS healthcare_providers (
    hcp_id INT NOT NULL PRIMARY KEY,
    account_id INT NOT NULL UNIQUE,
    hcp_name VARCHAR(50) NOT NULL,
    hcp_type hcp_type NOT NULL,
    postcode INT,
    FOREIGN KEY (account_id)
        REFERENCES accounts (account_id)
);

CREATE TABLE IF NOT EXISTS vaccine_recipients (
    vr_id SERIAL PRIMARY KEY,
    account_id INT NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    address VARCHAR(50),
    date_of_birth DATE NOT NULL,
    gender gender NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email_address VARCHAR(50) NOT NULL,
    vaccination_status vaccine_status NOT NULL,
    vaccination_type vaccine_type NOT NULL,
    FOREIGN KEY (account_id)
        REFERENCES accounts (account_id)
);

CREATE TABLE IF NOT EXISTS vaccine_certificates (
    cert_id SERIAL PRIMARY KEY,
    vr_id INT UNIQUE NOT NULL,
    hcp_id INT NOT NULL,
    vaccination_type vaccine_type NOT NULL,
    date_issued DATE NOT NULL,
    FOREIGN KEY (vr_id)
        REFERENCES vaccine_recipients (vr_id),
    FOREIGN KEY (hcp_id)
        REFERENCES healthcare_providers (hcp_id)
);

CREATE TABLE IF NOT EXISTS questionnaires (
    questionnaire_id SERIAL PRIMARY KEY,
    date_taken DATE NOT NULL,
    outcome outcome NOT NULL,
    answers VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS timeslots (
    timeslot_id SERIAL PRIMARY KEY,
    vr_id INT,
    hcp_id INT NOT NULL,
    questionnaire_id INT,
    vaccination_type vaccine_type NOT NULL,
    date_time TIMESTAMP NOT NULL,
    duration INT NOT NULL,
    location VARCHAR(50) NOT NULL,
    FOREIGN KEY (vr_id)
        REFERENCES vaccine_recipients (vr_id),
    FOREIGN KEY (hcp_id)
        REFERENCES healthcare_providers (hcp_id),
    FOREIGN KEY (questionnaire_id)
        REFERENCES questionnaires (questionnaire_id)
);