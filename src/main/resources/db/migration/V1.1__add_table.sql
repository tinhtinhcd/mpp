set search_path to  listing;

-- ADD new two table: listing_utility, listing_image
create table if not exists listing_utility (
	id bigint NOT NULL,
	listing_id bigint NOT NULL,
	utility_id bigint NOT NULL,
	CONSTRAINT listing_utility_pk PRIMARY KEY (id),
	CONSTRAINT listing_utility_fk FOREIGN KEY (listingid) REFERENCES listing(id),
	CONSTRAINT listing_utility_fk_1 FOREIGN KEY (utilityid) REFERENCES utility(id)
);

create table if not exists listing.listing_image (
	id int8 NOT NULL,
	listing_id int8 NOT NULL,
	url varchar NOT NULL,
    CONSTRAINT listing_image_pk PRIMARY KEY (id),
	CONSTRAINT listing_image_fk FOREIGN KEY (listingid) REFERENCES listing(id)
);

ALTER TABLE listing ADD created_by bigint NOT NULL;
ALTER TABLE listing ADD CONSTRAINT listing_fk2 FOREIGN KEY (created_by) REFERENCES users(id);

