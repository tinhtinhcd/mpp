create schema if not exists listing;
set search_path to  listing;

-- User
create table if not exists users
(
    id serial not null,
    first_name varchar(100),
    last_name varchar(100),
    email varchar(100) not null,
    password varchar(500),
    gender varchar(10),
    dob date,
    phone varchar(50),
   	active bool,
    date_created timestamp default null,
    last_modified timestamp,
    constraint user_pk primary key (id)
);


create table if not exists favorite (
    id serial NOT NULL,
    user_id bigint,
    listing_id bigint,
    constraint favorite_pk primary key (id)
);

create table if not exists listing (
    id serial NOT NULL,
    latitude numeric,
    longitude numeric(10,0),
    address varchar(500),
    price money,
    available_from date ,
    status varchar(100) NOT NULL,
    minimum_lease integer ,
    num_bed integer ,
    num_bath integer,
    utulities integer,
    area integer,
    title varchar(500),
    description varchar(1000),
    list_type bigint,
    date_created timestamp default null,
    last_modified timestamp,
    constraint listing_pk primary key (id)
);

create table if not exists listing_type (
    id serial NOT NULL,
    description varchar(100),
    constraint listing_type_pk primary key (id)
);

create table if not exists listing_report (
    id serial NOT NULL,
    listing_id bigint,
    user_id bigint,
    commnet varchar(1000),
    constraint listing_report_pk primary key (id)
);

create table if not exists listing_statistic (
    id serial NOT NULL,
    listing_id bigint,
    user_id bigint,
    constraint listing_statistic_pk primary key (id)
);


create table if not exists role (
    id serial NOT NULL,
    role_name varchar(100),
    constraint role_pk primary key (id)
);


create table if not exists user_role (
    id serial NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    constraint user_role_pk primary key (id)
);


create table if not exists utility (
    id serial NOT NULL,
    description varchar (200),
    constraint utility_pk primary key (id)
);



-- Foreign keys
ALTER TABLE user_role ADD CONSTRAINT users_role_fk1 FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE user_role ADD CONSTRAINT users_role_fk2 FOREIGN KEY (role_id) REFERENCES role(id);
ALTER TABLE favorite ADD CONSTRAINT favorite_fk1 FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE favorite ADD CONSTRAINT favorite_fk2 FOREIGN KEY (listing_id) REFERENCES listing(id);
ALTER TABLE listing ADD CONSTRAINT listing_type_fk1 FOREIGN KEY (list_type) REFERENCES listing_type(id);
ALTER TABLE listing_report ADD CONSTRAINT listing_report_fk1 FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE listing_report ADD CONSTRAINT listing_report_fk2 FOREIGN KEY (listing_id) REFERENCES listing(id);
ALTER TABLE listing_statistic ADD CONSTRAINT listing_statistic_fk1 FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE listing_statistic ADD CONSTRAINT listing_statistic_fk2 FOREIGN KEY (listing_id) REFERENCES listing(id);

--
-- Triggers
--

create or replace function get_date()
returns trigger as $$
begin
    new.last_modified = now();
    return new;
end;
$$ language 'plpgsql';

create trigger users_tg before insert or update on users for each row execute procedure get_date();
create trigger listing_tg before insert or update on listing for each row execute procedure get_date();

-- Extension GEO
CREATE EXTENSION cube;
CREATE EXTENSION earthdistance;
CREATE INDEX if not exists listing_geo_index on listing USING gist(ll_to_earth(latitude, longitude));




