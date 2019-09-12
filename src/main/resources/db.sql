--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2019-09-10 09:31:28 CDT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: housing; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA housing;


ALTER SCHEMA housing OWNER TO postgres;

--
-- TOC entry 3210 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA housing; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA housing IS 'standard housing schema';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 200 (class 1259 OID 16441)
-- Name: favorite; Type: TABLE; Schema: housing; Owner: postgres
--

CREATE TABLE housing.favorite (
    id bigint NOT NULL,
    "userId" bigint NOT NULL,
    "listingId" bigint NOT NULL
);


ALTER TABLE housing.favorite OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16425)
-- Name: listing; Type: TABLE; Schema: housing; Owner: postgres
--

CREATE TABLE housing.listing (
    id bigint NOT NULL,
    latitude numeric,
    longitude numeric(10,0),
    address "char"[] NOT NULL,
    price money NOT NULL,
    "availableFrom" date NOT NULL,
    "minimumLease" integer NOT NULL,
    "numOfbed" integer NOT NULL,
    "numOfbath" integer NOT NULL,
    utulities integer NOT NULL,
    size integer,
    title character varying NOT NULL,
    rentaltype bigint,
    createdate date,
    createby bigint NOT NULL,
    status bigint NOT NULL,
    description character varying NOT NULL
);


ALTER TABLE housing.listing OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16560)
-- Name: listingType; Type: TABLE; Schema: housing; Owner: postgres
--

CREATE TABLE housing."listingType" (
    id bigint NOT NULL,
    "listingType" character varying NOT NULL
);


ALTER TABLE housing."listingType" OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16446)
-- Name: listingreport; Type: TABLE; Schema: housing; Owner: postgres
--

CREATE TABLE housing.listingreport (
    id bigint NOT NULL,
    "listingId" bigint NOT NULL,
    "userId" bigint,
    commnet character varying NOT NULL
);


ALTER TABLE housing.listingreport OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16667)
-- Name: listingstatistic; Type: TABLE; Schema: housing; Owner: postgres
--

CREATE TABLE housing.listingstatistic (
    id bigint NOT NULL,
    listingid bigint NOT NULL,
    "timestamp" date NOT NULL,
    typeaction bigint
);


ALTER TABLE housing.listingstatistic OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16573)
-- Name: listingstatus; Type: TABLE; Schema: housing; Owner: postgres
--

CREATE TABLE housing.listingstatus (
    id bigint NOT NULL,
    status character varying NOT NULL
);


ALTER TABLE housing.listingstatus OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16486)
-- Name: role; Type: TABLE; Schema: housing; Owner: postgres
--

CREATE TABLE housing.role (
    id bigint NOT NULL,
    role character varying NOT NULL
);


ALTER TABLE housing.role OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16492)
-- Name: userrole; Type: TABLE; Schema: housing; Owner: postgres
--

CREATE TABLE housing.userrole (
    id bigint NOT NULL,
    userid bigint NOT NULL,
    roleid bigint NOT NULL
);


ALTER TABLE housing.userrole OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16393)
-- Name: users; Type: TABLE; Schema: housing; Owner: postgres
--

CREATE TABLE housing.users (
    id bigint NOT NULL,
    "userName" "char"[] NOT NULL,
    password "char"[] NOT NULL,
    status integer NOT NULL,
    "joinDate" date NOT NULL,
    name character varying,
    gender character varying,
    dob date,
    address character varying,
    email character varying,
    phone character varying NOT NULL
);


ALTER TABLE housing.users OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16433)
-- Name: utulities; Type: TABLE; Schema: housing; Owner: postgres
--

CREATE TABLE housing.utulities (
    id integer NOT NULL,
    "utilitiesName" "char"[] NOT NULL,
    description "char"[] NOT NULL
);


ALTER TABLE housing.utulities OWNER TO postgres;

--
-- TOC entry 3198 (class 0 OID 16441)
-- Dependencies: 200
-- Data for Name: favorite; Type: TABLE DATA; Schema: housing; Owner: postgres
--

COPY housing.favorite (id, "userId", "listingId") FROM stdin;
\.


--
-- TOC entry 3196 (class 0 OID 16425)
-- Dependencies: 198
-- Data for Name: listing; Type: TABLE DATA; Schema: housing; Owner: postgres
--

COPY housing.listing (id, latitude, longitude, address, price, "availableFrom", "minimumLease", "numOfbed", "numOfbath", utulities, size, title, rentaltype, createdate, createby, status, description) FROM stdin;
\.


--
-- TOC entry 3202 (class 0 OID 16560)
-- Dependencies: 204
-- Data for Name: listingType; Type: TABLE DATA; Schema: housing; Owner: postgres
--

COPY housing."listingType" (id, "listingType") FROM stdin;
\.


--
-- TOC entry 3199 (class 0 OID 16446)
-- Dependencies: 201
-- Data for Name: listingreport; Type: TABLE DATA; Schema: housing; Owner: postgres
--

COPY housing.listingreport (id, "listingId", "userId", commnet) FROM stdin;
\.


--
-- TOC entry 3204 (class 0 OID 16667)
-- Dependencies: 206
-- Data for Name: listingstatistic; Type: TABLE DATA; Schema: housing; Owner: postgres
--

COPY housing.listingstatistic (id, listingid, "timestamp", typeaction) FROM stdin;
\.


--
-- TOC entry 3203 (class 0 OID 16573)
-- Dependencies: 205
-- Data for Name: listingstatus; Type: TABLE DATA; Schema: housing; Owner: postgres
--

COPY housing.listingstatus (id, status) FROM stdin;
\.


--
-- TOC entry 3200 (class 0 OID 16486)
-- Dependencies: 202
-- Data for Name: role; Type: TABLE DATA; Schema: housing; Owner: postgres
--

COPY housing.role (id, role) FROM stdin;
\.


--
-- TOC entry 3201 (class 0 OID 16492)
-- Dependencies: 203
-- Data for Name: userrole; Type: TABLE DATA; Schema: housing; Owner: postgres
--

COPY housing.userrole (id, userid, roleid) FROM stdin;
\.


--
-- TOC entry 3195 (class 0 OID 16393)
-- Dependencies: 197
-- Data for Name: users; Type: TABLE DATA; Schema: housing; Owner: postgres
--

COPY housing.users (id, "userName", password, status, "joinDate", name, gender, dob, address, email, phone) FROM stdin;
\.


--
-- TOC entry 3197 (class 0 OID 16433)
-- Dependencies: 199
-- Data for Name: utulities; Type: TABLE DATA; Schema: housing; Owner: postgres
--

COPY housing.utulities (id, "utilitiesName", description) FROM stdin;
\.


--
-- TOC entry 3052 (class 2606 OID 16611)
-- Name: favorite Favorite_pkey; Type: CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.favorite
    ADD CONSTRAINT "Favorite_pkey" PRIMARY KEY (id);


--
-- TOC entry 3054 (class 2606 OID 16587)
-- Name: listingreport Postreport_pkey; Type: CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.listingreport
    ADD CONSTRAINT "Postreport_pkey" PRIMARY KEY (id);


--
-- TOC entry 3046 (class 2606 OID 16635)
-- Name: users Users_pkey; Type: CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.users
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY (id);


--
-- TOC entry 3050 (class 2606 OID 16440)
-- Name: utulities Utulities_pkey; Type: CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.utulities
    ADD CONSTRAINT "Utulities_pkey" PRIMARY KEY (id);


--
-- TOC entry 3048 (class 2606 OID 16539)
-- Name: listing listing_pk; Type: CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.listing
    ADD CONSTRAINT listing_pk PRIMARY KEY (id);


--
-- TOC entry 3062 (class 2606 OID 16674)
-- Name: listingstatistic listingstatistic_pk; Type: CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.listingstatistic
    ADD CONSTRAINT listingstatistic_pk PRIMARY KEY (id);


--
-- TOC entry 3060 (class 2606 OID 16580)
-- Name: listingstatus rentalstatus_pk; Type: CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.listingstatus
    ADD CONSTRAINT rentalstatus_pk PRIMARY KEY (id);


--
-- TOC entry 3058 (class 2606 OID 16567)
-- Name: listingType rentaltype_pk; Type: CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing."listingType"
    ADD CONSTRAINT rentaltype_pk PRIMARY KEY (id);


--
-- TOC entry 3056 (class 2606 OID 16496)
-- Name: role role_pk; Type: CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.role
    ADD CONSTRAINT role_pk PRIMARY KEY (id);


--
-- TOC entry 3068 (class 2606 OID 16636)
-- Name: favorite favorite_fk; Type: FK CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.favorite
    ADD CONSTRAINT favorite_fk FOREIGN KEY ("userId") REFERENCES housing.users(id);


--
-- TOC entry 3067 (class 2606 OID 16625)
-- Name: favorite favorite_fk2; Type: FK CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.favorite
    ADD CONSTRAINT favorite_fk2 FOREIGN KEY ("listingId") REFERENCES housing.listing(id);


--
-- TOC entry 3063 (class 2606 OID 16550)
-- Name: listing listing_fk; Type: FK CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.listing
    ADD CONSTRAINT listing_fk FOREIGN KEY (utulities) REFERENCES housing.utulities(id);


--
-- TOC entry 3065 (class 2606 OID 16581)
-- Name: listing listing_fk2; Type: FK CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.listing
    ADD CONSTRAINT listing_fk2 FOREIGN KEY (status) REFERENCES housing.listingstatus(id);


--
-- TOC entry 3064 (class 2606 OID 16568)
-- Name: listing listing_fk3; Type: FK CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.listing
    ADD CONSTRAINT listing_fk3 FOREIGN KEY (rentaltype) REFERENCES housing."listingType"(id);


--
-- TOC entry 3066 (class 2606 OID 16646)
-- Name: listing listing_fk_1; Type: FK CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.listing
    ADD CONSTRAINT listing_fk_1 FOREIGN KEY (createby) REFERENCES housing.users(id);


--
-- TOC entry 3069 (class 2606 OID 16592)
-- Name: listingreport listingreport_fk; Type: FK CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.listingreport
    ADD CONSTRAINT listingreport_fk FOREIGN KEY ("listingId") REFERENCES housing.listing(id);


--
-- TOC entry 3073 (class 2606 OID 16675)
-- Name: listingstatistic listingstatistic_fk; Type: FK CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.listingstatistic
    ADD CONSTRAINT listingstatistic_fk FOREIGN KEY (listingid) REFERENCES housing.listing(id);


--
-- TOC entry 3070 (class 2606 OID 16641)
-- Name: listingreport postreport_fk_1; Type: FK CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.listingreport
    ADD CONSTRAINT postreport_fk_1 FOREIGN KEY ("userId") REFERENCES housing.users(id);


--
-- TOC entry 3072 (class 2606 OID 16651)
-- Name: userrole userrole_fk; Type: FK CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.userrole
    ADD CONSTRAINT userrole_fk FOREIGN KEY (userid) REFERENCES housing.users(id);


--
-- TOC entry 3071 (class 2606 OID 16502)
-- Name: userrole userrole_fk_1; Type: FK CONSTRAINT; Schema: housing; Owner: postgres
--

ALTER TABLE ONLY housing.userrole
    ADD CONSTRAINT userrole_fk_1 FOREIGN KEY (roleid) REFERENCES housing.role(id);


-- Completed on 2019-09-10 09:31:28 CDT

--
-- PostgreSQL database dump complete
--

