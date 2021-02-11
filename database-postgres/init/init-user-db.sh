##!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER xxx WITH PASSWORD 'xxx';
    CREATE DATABASE patientdoc;
    GRANT ALL PRIVILEGES ON DATABASE patientdoc TO xxx;
EOSQL
