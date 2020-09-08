-- schema owner
CREATE USER sovcom WITH PASSWORD 'sovcom';

-- schema user
CREATE USER sovcom_ms WITH PASSWORD 'sovcom_ms';

-- create schema
-- DROP SCHEMA sovcombank_schema CASCADE;
CREATE SCHEMA sovcombank_schema
    AUTHORIZATION sovcom;

ALTER USER sovcom SET search_path = 'sovcombank_schema';

GRANT USAGE ON SCHEMA sovcombank_schema TO sovcom_ms;

ALTER DEFAULT PRIVILEGES FOR USER sovcom IN SCHEMA sovcombank_schema GRANT SELECT, INSERT, UPDATE, DELETE, TRUNCATE ON TABLES TO sovcom_ms;
ALTER DEFAULT PRIVILEGES FOR USER sovcom IN SCHEMA sovcombank_schema GRANT USAGE ON SEQUENCES TO sovcom_ms;
ALTER DEFAULT PRIVILEGES FOR USER sovcom IN SCHEMA sovcombank_schema GRANT EXECUTE ON FUNCTIONS TO sovcom_ms;

CREATE TABLE sovcombank_schema.scb_location (
    id SERIAL PRIMARY KEY,
    city VARCHAR(255),
    street VARCHAR(255),
    house VARCHAR(255),
    fias_code VARCHAR(255)
);

INSERT INTO sovcombank_schema.scb_location (city, street, house, fias_code) VALUES
    ('Саратов', 'им академика О.К.Антонова', '11', '2d2407fd-126e-48ab-87f7-b2e0475615a7'),
    ('Саратов', 'им Зарубина В.С.', '167', '9d3a17df-7c43-45fc-865c-51a69eb5cf6f'),
    ('Саратов', 'им Лисина С.П.', '10', '6cc62d07-2ecd-49a2-b362-2373d9540180'),
    ('Саратов', 'им Осипова В.И.', '27Ю','2973b02b-8e15-41ae-8582-bd42b87f7264'),
    ('Саратов', 'им Разина С.Т.', '58', 'f52b4cf4-7fd9-4617-b5ea-0aa4dc715e59'),
    ('Москва', 'Академика Виноградова', '6', 'b99f274f-75b8-4172-941d-b7c40a2b1ae5'),
    ('Москва', 'Тверская', '21 стр. 12', 'bed1fee9-fc36-477b-9dff-1fe420ea589f');

GRANT SELECT ON sovcombank_schema.scb_location TO sovcom_ms;

-- turn on large object compatibility
ALTER DATABASE ib SET lo_compat_privileges TO ON;
