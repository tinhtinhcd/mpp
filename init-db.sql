CREATE DATABASE listingdb;
CREATE USER mpp WITH ENCRYPTED PASSWORD 'mpp';
GRANT ALL PRIVILEGES ON DATABASE listingdb TO mpp;