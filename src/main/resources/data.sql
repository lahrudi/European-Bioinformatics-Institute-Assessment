
-- USER
-- hashed password: Aa135642
INSERT INTO security_user (id, username, password, first_name, last_name) VALUES
(1,  'admin', '$2a$12$jHP3PkQkvgbCbLMOVd1WyOoUHFMSBGAFMNO4t7lvAh2X9PcA0fAqG', 'Administrator', 'Adminstrator'),
(2,  'sr_jane', '$2a$12$jHP3PkQkvgbCbLMOVd1WyOoUHFMSBGAFMNO4t7lvAh2X9PcA0fAqG', 'Jane', 'Doe'),
(3,  'sr_mark', '$2a$12$jHP3PkQkvgbCbLMOVd1WyOoUHFMSBGAFMNO4t7lvAh2X9PcA0fAqG', 'Mark', 'Smith'),
(4,  'sr_wally', '$2a$12$jHP3PkQkvgbCbLMOVd1WyOoUHFMSBGAFMNO4t7lvAh2X9PcA0fAqG', 'Walter', 'Adams');

-- ROLES
INSERT INTO security_role (id, role_name, description) VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO security_role (id, role_name, description) VALUES (2, 'ROLE_SR', 'Service Representative');

INSERT INTO user_role(user_id, role_id) VALUES
 (1, 1), -- give admin ROLE_ADMIN
 (2, 2),  -- give Jane ROLE_SR
 (3, 2),  -- give Mark ROLE_SR
 (4, 1),  -- give Wally ROLE_ADMIN
 (4, 2);  -- give Wally ROLE_SR

INSERT INTO Person (id, first_name, last_name, age, favourite_colour, nationality) VALUES
  (NEXTVAL('PERSON_SEQ_ID'), 'Sarah', 'Robinson',54, 'Blue','British');
INSERT INTO Person (id, first_name, last_name, age, favourite_colour) VALUES
  (NEXTVAL('PERSON_SEQ_ID'), 'John', 'Keynes', 29, 'Red');
INSERT INTO Person (id, first_name, last_name, age, favourite_colour, nationality) VALUES
  (NEXTVAL('PERSON_SEQ_ID'), 'Mark', 'Smith', 32, 'Green','British');