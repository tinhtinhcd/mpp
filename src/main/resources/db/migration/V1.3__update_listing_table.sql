ALTER TABLE listing.listing ADD is_include_utilities varchar NULL;
UPDATE listing.listing SET is_include_utilities=false;