CREATE DATABASE listing;
CREATE USER mpp WITH ENCRYPTED PASSWORD 'mpp';
GRANT ALL PRIVILEGES ON DATABASE listing TO mpp;
ALTER USER mpp WITH SUPERUSER;