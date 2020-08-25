CREATE DATABASE ib WITH OWNER = postgres ENCODING = 'UTF8';

CONNECT TO ib USING postgres;

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

-- turn on large object compatibility
ALTER DATABASE ib SET lo_compat_privileges TO ON;
