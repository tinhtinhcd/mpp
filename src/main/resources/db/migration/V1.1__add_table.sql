-- ADD new two table: listing_utility, listing_image
create table if not exists listing.listing_utility (
	id bigint NOT NULL,
	listingid bigint NOT NULL,
	utilityid bigint NOT NULL,
	CONSTRAINT listing_utility_pk PRIMARY KEY (id),
	CONSTRAINT listing_utility_fk FOREIGN KEY (listingid) REFERENCES listing.listing(id),
	CONSTRAINT listing_utility_fk_1 FOREIGN KEY (utilityid) REFERENCES listing.utility(id)
);

create table if not exists listing.listing_image (
	id int8 NOT NULL,
	listingid int8 NOT NULL,
	url varchar NOT NULL,
    CONSTRAINT listing_image_pk PRIMARY KEY (id),
	CONSTRAINT listing_image_fk FOREIGN KEY (listingid) REFERENCES listing.listing(id)
);

ALTER TABLE public.listing ADD created_by bigint NOT NULL;
ALTER TABLE public.listing ADD CONSTRAINT listing_fk2 FOREIGN KEY (created_by) REFERENCES public.users(id);

