-- ✅ Initialization script for MySQL container
-- Runs automatically at first startup when the data directory is empty.
-- Creates multiple schemas so each microservice has its own database.

CREATE DATABASE IF NOT EXISTS accountsdb;   -- Database for Accounts microservice
CREATE DATABASE IF NOT EXISTS loansdb;      -- Database for Loans microservice
CREATE DATABASE IF NOT EXISTS cardsdb;      -- Database for Cards microservice
