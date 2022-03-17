--
-- PostgreSQL database dump
--

-- Dumped from database version 12.10 (Ubuntu 12.10-1.pgdg20.04+1)
-- Dumped by pg_dump version 12.10 (Ubuntu 12.10-1.pgdg20.04+1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admins; Type: TABLE; Schema: public; Owner: access
--

CREATE TABLE public.admins (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE public.admins OWNER TO access;

--
-- Name: admins_id_seq; Type: SEQUENCE; Schema: public; Owner: access
--

CREATE SEQUENCE public.admins_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admins_id_seq OWNER TO access;

--
-- Name: admins_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: access
--

ALTER SEQUENCE public.admins_id_seq OWNED BY public.admins.id;


--
-- Name: department_news; Type: TABLE; Schema: public; Owner: access
--

CREATE TABLE public.department_news (
    id integer NOT NULL,
    departmentid integer,
    newsid integer
);


ALTER TABLE public.department_news OWNER TO access;

--
-- Name: department_news_id_seq; Type: SEQUENCE; Schema: public; Owner: access
--

CREATE SEQUENCE public.department_news_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.department_news_id_seq OWNER TO access;

--
-- Name: department_news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: access
--

ALTER SEQUENCE public.department_news_id_seq OWNED BY public.department_news.id;


--
-- Name: departments; Type: TABLE; Schema: public; Owner: access
--

CREATE TABLE public.departments (
    id integer NOT NULL,
    name character varying,
    description character varying
);


ALTER TABLE public.departments OWNER TO access;

--
-- Name: departments_id_seq; Type: SEQUENCE; Schema: public; Owner: access
--

CREATE SEQUENCE public.departments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.departments_id_seq OWNER TO access;

--
-- Name: departments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: access
--

ALTER SEQUENCE public.departments_id_seq OWNED BY public.departments.id;


--
-- Name: members; Type: TABLE; Schema: public; Owner: access
--

CREATE TABLE public.members (
    id integer NOT NULL,
    name character varying,
    "position" character varying,
    roles text[],
    rolesstring character varying,
    departmentid integer
);


ALTER TABLE public.members OWNER TO access;

--
-- Name: members_id_seq; Type: SEQUENCE; Schema: public; Owner: access
--

CREATE SEQUENCE public.members_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.members_id_seq OWNER TO access;

--
-- Name: members_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: access
--

ALTER SEQUENCE public.members_id_seq OWNED BY public.members.id;


--
-- Name: news; Type: TABLE; Schema: public; Owner: access
--

CREATE TABLE public.news (
    id integer NOT NULL,
    information character varying,
    category character varying,
    datecreated bigint,
    readabledate character varying,
    type character varying
);


ALTER TABLE public.news OWNER TO access;

--
-- Name: news_id_seq; Type: SEQUENCE; Schema: public; Owner: access
--

CREATE SEQUENCE public.news_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_id_seq OWNER TO access;

--
-- Name: news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: access
--

ALTER SEQUENCE public.news_id_seq OWNED BY public.news.id;


--
-- Name: admins id; Type: DEFAULT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.admins ALTER COLUMN id SET DEFAULT nextval('public.admins_id_seq'::regclass);


--
-- Name: department_news id; Type: DEFAULT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.department_news ALTER COLUMN id SET DEFAULT nextval('public.department_news_id_seq'::regclass);


--
-- Name: departments id; Type: DEFAULT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.departments ALTER COLUMN id SET DEFAULT nextval('public.departments_id_seq'::regclass);


--
-- Name: members id; Type: DEFAULT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.members ALTER COLUMN id SET DEFAULT nextval('public.members_id_seq'::regclass);


--
-- Name: news id; Type: DEFAULT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.news ALTER COLUMN id SET DEFAULT nextval('public.news_id_seq'::regclass);


--
-- Data for Name: admins; Type: TABLE DATA; Schema: public; Owner: access
--

COPY public.admins (id, name) FROM stdin;
\.


--
-- Data for Name: department_news; Type: TABLE DATA; Schema: public; Owner: access
--

COPY public.department_news (id, departmentid, newsid) FROM stdin;
\.


--
-- Data for Name: departments; Type: TABLE DATA; Schema: public; Owner: access
--

COPY public.departments (id, name, description) FROM stdin;
\.


--
-- Data for Name: members; Type: TABLE DATA; Schema: public; Owner: access
--

COPY public.members (id, name, "position", roles, rolesstring, departmentid) FROM stdin;
\.


--
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: access
--

COPY public.news (id, information, category, datecreated, readabledate, type) FROM stdin;
\.


--
-- Name: admins_id_seq; Type: SEQUENCE SET; Schema: public; Owner: access
--

SELECT pg_catalog.setval('public.admins_id_seq', 1, false);


--
-- Name: department_news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: access
--

SELECT pg_catalog.setval('public.department_news_id_seq', 1, false);


--
-- Name: departments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: access
--

SELECT pg_catalog.setval('public.departments_id_seq', 1, false);


--
-- Name: members_id_seq; Type: SEQUENCE SET; Schema: public; Owner: access
--

SELECT pg_catalog.setval('public.members_id_seq', 1, false);


--
-- Name: news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: access
--

SELECT pg_catalog.setval('public.news_id_seq', 1, false);


--
-- Name: admins admins_pkey; Type: CONSTRAINT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (id);


--
-- Name: department_news department_news_pkey; Type: CONSTRAINT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.department_news
    ADD CONSTRAINT department_news_pkey PRIMARY KEY (id);


--
-- Name: departments departments_pkey; Type: CONSTRAINT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_pkey PRIMARY KEY (id);


--
-- Name: members members_pkey; Type: CONSTRAINT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.members
    ADD CONSTRAINT members_pkey PRIMARY KEY (id);


--
-- Name: news news_pkey; Type: CONSTRAINT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

