-- This script is automatically run by the container as SYS in CDB$ROOT.
-- Open XEPDB1 and execute the user creation there.

-- Open pluggable database
ALTER PLUGGABLE DATABASE XEPDB1 OPEN;

-- Connect to pluggable database
ALTER SESSION SET CONTAINER = XEPDB1;

-- Drop user if exists to avoid errors on rebuild
BEGIN
  EXECUTE IMMEDIATE 'DROP USER andrea CASCADE';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

-- Create user and grant privileges
CREATE USER andrea IDENTIFIED BY andrea;

GRANT CONNECT, RESOURCE TO andrea;
GRANT CREATE SESSION TO andrea;
GRANT CREATE TABLE TO andrea;
GRANT CREATE VIEW TO andrea;
GRANT CREATE SEQUENCE TO andrea;
GRANT CREATE TRIGGER TO andrea;
GRANT CREATE PROCEDURE TO andrea;
GRANT CREATE SYNONYM TO andrea;
GRANT UNLIMITED TABLESPACE TO andrea;
GRANT SELECT ANY DICTIONARY TO andrea;
