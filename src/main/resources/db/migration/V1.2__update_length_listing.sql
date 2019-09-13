set search_path to  listing;
ALTER TABLE listing ALTER COLUMN latitude TYPE numeric(19,11) USING latitude::numeric ;
ALTER TABLE listing ALTER COLUMN longitude TYPE numeric(19,11) USING longitude::numeric ;