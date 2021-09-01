INSERT INTO account (username, password, account_type) VALUES ('vr', 'secure', 'VR');
INSERT INTO account (username, password, account_type) VALUES ('hcp', 'secure', 'HCP');

INSERT INTO healthcare_provider (organisational_id, account, hcp_name, hcp_type, postcode) VALUES (1234, 2, 'good hcp', 'HOSPITAL', '3000');
