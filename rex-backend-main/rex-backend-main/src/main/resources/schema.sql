-- 0. Global Configuration (External to transaction)
-- DO $$
-- BEGIN
--     IF EXISTS (SELECT 1 FROM pg_roles WHERE rolname = 'vishal') THEN
--         ALTER ROLE vishal SET TIMEZONE TO 'UTC';
--     END IF;
-- END $$^;

-- ALTER DATABASE "rexbase-new" SET TIMEZONE TO 'UTC'^;

-- Wrapped in a transaction for safety
BEGIN^;

-- 1. Create Schema
CREATE SCHEMA IF NOT EXISTS rexbase^;

-- 2. Create Custom Collation (required for the tables below)
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_collation WHERE collname = 'case_insensitive') THEN
        CREATE COLLATION public.case_insensitive (
            provider = 'icu',
            locale = 'und-u-ks-level2',
            deterministic = false
        );
    END IF;
END $$^;

-- 3. Core Tables and Sequences (Using IF NOT EXISTS for persistence)
CREATE SEQUENCE IF NOT EXISTS rexbase.beneficiary_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.beneficiary_orgdata_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.deputy_assignment_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.deputyright_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.employer_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.guest_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.guesttype_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.platform_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.rexuser_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.userright_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.userrole_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.translations_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.platform_domain_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.picklist_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.projectnumber_id_seq INCREMENT 50 START 1^;
CREATE SEQUENCE IF NOT EXISTS rexbase.projectleader_id_seq INCREMENT 50 START 1^;

CREATE TABLE IF NOT EXISTS rexbase.beneficiary
(
    created_at timestamp(6) with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp(6) with time zone,
    id bigint NOT NULL,
    commodisid uuid NOT NULL,
    beneficiaryid uuid NOT NULL,
    lastname text COLLATE public.case_insensitive NOT NULL,
    firstname text COLLATE public.case_insensitive NOT NULL,
    academictitle text COLLATE public.case_insensitive,
    listname text COLLATE public.case_insensitive GENERATED ALWAYS AS (TRIM(BOTH FROM ((COALESCE(lastname, '*unbekannt*'::text) || ', '::text) || btrim(((COALESCE(academictitle, ''::text) || ' '::text) || COALESCE(firstname, '*unbekannt*'::text)))))) STORED,
    leavingdate date,
    CONSTRAINT beneficiary_pkey PRIMARY KEY (id),
    CONSTRAINT uq_beneficiary_beneficiaryid UNIQUE (beneficiaryid)
)^;

CREATE TABLE IF NOT EXISTS rexbase.beneficiary_account
(
    created_at timestamp(6) with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp(6) with time zone,
    beneficiary_id bigint NOT NULL,
    delegation_permission boolean,
    platform_id bigint NOT NULL,
    keycloak_userid uuid NOT NULL,
    CONSTRAINT beneficiary_account_pkey PRIMARY KEY (beneficiary_id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.beneficiary_orgdata
(
    created_at timestamp(6) with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp(6) with time zone,
    id bigint NOT NULL,
    beneficiary_id bigint NOT NULL,
    employer_id bigint NOT NULL,
    validfrom date NOT NULL,
    validthrough date,
    validityrange daterange GENERATED ALWAYS AS (daterange(validfrom, (validthrough + 1), '[)'::text)) STORED,
    personnelnumberpayroll text COLLATE pg_catalog."default",
    personnelnumbersystem text COLLATE pg_catalog."default",
    companysite text COLLATE public.case_insensitive,
    costcenter text COLLATE public.case_insensitive,
    identity_tag text COLLATE public.case_insensitive,
    orgunit text COLLATE public.case_insensitive,
    CONSTRAINT beneficiary_orgdata_pkey PRIMARY KEY (id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.deputy_assignment
(
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone,
    id bigint NOT NULL,
    beneficiary_account_id bigint NOT NULL,
    deputy_id bigint NOT NULL,
    validfrom date NOT NULL,
    validthrough date NOT NULL,
    validityrange daterange GENERATED ALWAYS AS (daterange(validfrom, (validthrough + 1), '[)'::text)) STORED,
    CONSTRAINT deputy_assignment_pkey PRIMARY KEY (id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.deputyright
(
    id bigint NOT NULL,
    deputy_right_de text COLLATE public.case_insensitive,
    deputy_right_en text COLLATE public.case_insensitive,
    CONSTRAINT deputyright_pkey PRIMARY KEY (id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.deputyright_assignment
(
    deputy_assignment_id bigint NOT NULL,
    deputyright_id bigint NOT NULL,
    CONSTRAINT deputyright_assignment_pkey PRIMARY KEY (deputy_assignment_id, deputyright_id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.employer
(
    created_at timestamp(6) with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp(6) with time zone,
    id bigint NOT NULL,
    employer_uuid uuid NOT NULL,
    displayname text COLLATE public.case_insensitive NOT NULL,
    platform_id bigint NOT NULL,
    activefrom date NOT NULL,
    activethrough date,
    activeperiod daterange GENERATED ALWAYS AS (daterange(activefrom, (activethrough + 1), '[)'::text)) STORED,
    CONSTRAINT employer_pkey PRIMARY KEY (id),
    CONSTRAINT uq_employer_employer_uuid UNIQUE (employer_uuid)
)^;

CREATE TABLE IF NOT EXISTS rexbase.guest
(
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone,
    id bigint NOT NULL,
    organisationwide_guest boolean NOT NULL,
    default_guesttype_id bigint NOT NULL,
    creator_beneficiary_account_id bigint NOT NULL,
    lastname text COLLATE public.case_insensitive NOT NULL,
    firstname text COLLATE public.case_insensitive NOT NULL,
    listname text COLLATE public.case_insensitive GENERATED ALWAYS AS (TRIM(BOTH FROM ((COALESCE(lastname, '*unbekannt*'::text) || ', '::text) || COALESCE(firstname, '*unbekannt*'::text)))) STORED,
    organization text COLLATE public.case_insensitive NOT NULL,
    CONSTRAINT guest_pkey PRIMARY KEY (id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.guesttype
(
    active boolean NOT NULL,
    id bigint NOT NULL,
    code character varying(50) COLLATE pg_catalog."default" NOT NULL,
    display_value text COLLATE public.case_insensitive NOT NULL,
    CONSTRAINT guesttype_pkey PRIMARY KEY (id),
    CONSTRAINT guesttype_code_key UNIQUE (code)
)^;

CREATE TABLE IF NOT EXISTS rexbase.keycloak_sim
(
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone,
    id uuid NOT NULL,
    realm text COLLATE pg_catalog."default" NOT NULL,
    lastname text COLLATE pg_catalog."default" NOT NULL,
    firstname text COLLATE pg_catalog."default" NOT NULL,
    organisation text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT keycloak_sim_pkey PRIMARY KEY (id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.picklist
(
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone,
    id bigint NOT NULL,
    sort_id smallint NOT NULL,
    displayname text COLLATE public.case_insensitive NOT NULL,
    beneficiary_account_id bigint NOT NULL,
    CONSTRAINT picklist_pkey PRIMARY KEY (id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.picklist_to_beneficiary
(
    created_at timestamp(6) with time zone NOT NULL,
    beneficiary_id bigint NOT NULL,
    picklist_id bigint NOT NULL,
    CONSTRAINT picklist_to_beneficiary_pkey PRIMARY KEY (beneficiary_id, picklist_id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.picklist_to_guest
(
    created_at timestamp(6) with time zone NOT NULL,
    guest_id bigint NOT NULL,
    picklist_id bigint NOT NULL,
    CONSTRAINT picklist_to_guest_pkey PRIMARY KEY (guest_id, picklist_id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.platform
(
    created_at timestamp(6) with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp(6) with time zone,
    id bigint NOT NULL,
    platform_uuid uuid NOT NULL,
    displayname text COLLATE public.case_insensitive NOT NULL,
    activefrom date NOT NULL,
    activethrough date,
    activeperiod daterange GENERATED ALWAYS AS (daterange(activefrom, (activethrough + 1), '[)'::text)) STORED,
    CONSTRAINT platform_pkey PRIMARY KEY (id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.platform_domain
(
    id bigint NOT NULL,
    platform_id bigint NOT NULL,
    domainname text COLLATE public.case_insensitive,
    CONSTRAINT platform_domain_pkey PRIMARY KEY (id),
    CONSTRAINT domainname_ukey UNIQUE (domainname)
)^;

CREATE TABLE IF NOT EXISTS rexbase.rexuser
(
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone,
    id bigint NOT NULL,
    keycloak_userid uuid NOT NULL,
    lastname text COLLATE public.case_insensitive NOT NULL,
    firstname text COLLATE public.case_insensitive NOT NULL,
    academictitle text COLLATE public.case_insensitive,
    listname text COLLATE public.case_insensitive GENERATED ALWAYS AS (((COALESCE(lastname, '*** MISSING ***'::text) || ', '::text) || btrim(((COALESCE(academictitle, ''::text) || ' '::text) || COALESCE(firstname, ''::text))))) STORED,
    fullname text COLLATE public.case_insensitive GENERATED ALWAYS AS (((btrim(((COALESCE(academictitle, ''::text) || ' '::text) || COALESCE(firstname, ''::text))) || ' '::text) || COALESCE(lastname, '*** MISSING ***'::text))) STORED,
    organisation text COLLATE public.case_insensitive NOT NULL,
    activefrom date NOT NULL DEFAULT '2020-01-01'::date,
    activethrough date NOT NULL DEFAULT '2999-12-31'::date,
    active_range daterange GENERATED ALWAYS AS (daterange(activefrom, (activethrough + 1), '[)'::text)) STORED,
    CONSTRAINT rexuser_pkey PRIMARY KEY (id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.rexuser_to_role
(
    rexuser_id bigint NOT NULL,
    userrole_id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    activefrom date NOT NULL DEFAULT '2020-01-01'::date,
    activethrough date NOT NULL DEFAULT '2999-12-31'::date,
    active_range daterange GENERATED ALWAYS AS (daterange(activefrom, (activethrough + 1), '[)'::text)) STORED,
    CONSTRAINT rexuser_to_role_pkey PRIMARY KEY (rexuser_id, userrole_id, activefrom)
)^;

CREATE TABLE IF NOT EXISTS rexbase.translations
(
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    id bigserial NOT NULL,
    key character varying(255) COLLATE pg_catalog."default" NOT NULL,
    component character varying(255) COLLATE pg_catalog."default" NOT NULL,
    translations jsonb NOT NULL,
    CONSTRAINT translations_pkey PRIMARY KEY (id),
    CONSTRAINT translations_component_key_key UNIQUE (component, key)
)^;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    name text COLLATE public.case_insensitive,
    username text COLLATE public.case_insensitive NOT NULL,
    password text NOT NULL,
    email text COLLATE public.case_insensitive,
    user_role text,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_username_key UNIQUE (username)
)^;

CREATE TABLE IF NOT EXISTS rexbase.userright
(
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    id bigint NOT NULL,
    rightname_de text COLLATE public.case_insensitive NOT NULL,
    rightname_en text COLLATE public.case_insensitive NOT NULL,
    rightcode character varying(10) COLLATE pg_catalog."default" NOT NULL,
    activefrom date NOT NULL DEFAULT '2020-01-01'::date,
    activethrough date NOT NULL DEFAULT '2999-12-31'::date,
    active_range daterange GENERATED ALWAYS AS (daterange(activefrom, (activethrough + 1), '[)'::text)) STORED,
    CONSTRAINT userright_pkey PRIMARY KEY (id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.userrole
(
    id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    rolename_de text COLLATE public.case_insensitive NOT NULL,
    rolename_en text COLLATE public.case_insensitive NOT NULL,
    activefrom date NOT NULL DEFAULT '2020-01-01'::date,
    activethrough date NOT NULL DEFAULT '2999-12-31'::date,
    active_range daterange GENERATED ALWAYS AS (daterange(activefrom, (activethrough + 1), '[)'::text)) STORED,
    CONSTRAINT userrole_pkey PRIMARY KEY (id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.userrole_to_right
(
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    userrole_id bigint NOT NULL,
    userright_id bigint NOT NULL,
    activefrom date NOT NULL DEFAULT '2020-01-01'::date,
    activethrough date NOT NULL DEFAULT '2999-12-31'::date,
    active_range daterange GENERATED ALWAYS AS (daterange(activefrom, (activethrough + 1), '[)'::text)) STORED,
    CONSTRAINT userrole_to_right_pkey PRIMARY KEY (userrole_id, userright_id)
)^;

CREATE TABLE IF NOT EXISTS rexbase.projectnumbers
(
    created_at timestamp(6) with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp(6) with time zone,
    id bigint NOT NULL,
    platform_id bigint NOT NULL,
    projectid uuid NOT NULL,
    external_projectid text,
    projectnumber varchar(25) NOT NULL,
    projecttitle text COLLATE public.case_insensitive NOT NULL,
    projectsubtitle text COLLATE public.case_insensitive,
    description text COLLATE public.case_insensitive,
    activefrom date NULL DEFAULT '2020-01-01'::date,
    activethrough date NOT NULL DEFAULT '2999-12-31'::date,
    active_range daterange GENERATED ALWAYS AS (daterange(activefrom, (activethrough + 1), '[)'::text)) STORED,
    CONSTRAINT projectnumber_pkey PRIMARY KEY (id),
    CONSTRAINT uq_projectnumber_platform_number UNIQUE (platform_id, projectnumber)
)^;

CREATE TABLE IF NOT EXISTS rexbase.projectleader
(
    created_at timestamp(6) with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp(6) with time zone,
    id bigint NOT NULL,
    project_id bigint NOT NULL,
    rexuser_id bigint NOT NULL,
    activefrom date NULL DEFAULT '2020-01-01'::date,
    activethrough date NOT NULL DEFAULT '2999-12-31'::date,
    active_range daterange GENERATED ALWAYS AS (daterange(activefrom, (activethrough + 1), '[)'::text)) STORED,
    CONSTRAINT projectleader_pkey PRIMARY KEY (id),
    CONSTRAINT uq_projectleader_assignment UNIQUE (project_id, rexuser_id, activefrom)
)^;

-- 4. Foreign Key Constraints (Idempotent using DO blocks)

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_beneficiary_account_platform') THEN
        ALTER TABLE rexbase.beneficiary_account ADD CONSTRAINT fk_beneficiary_account_platform FOREIGN KEY (platform_id) REFERENCES rexbase.platform (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_beneficiary_orgdata_beneficiary') THEN
        ALTER TABLE rexbase.beneficiary_orgdata ADD CONSTRAINT fk_beneficiary_orgdata_beneficiary FOREIGN KEY (beneficiary_id) REFERENCES rexbase.beneficiary (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_beneficiary_orgdata_employer') THEN
        ALTER TABLE rexbase.beneficiary_orgdata ADD CONSTRAINT fk_beneficiary_orgdata_employer FOREIGN KEY (employer_id) REFERENCES rexbase.employer (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_deputy_assignment_beneficiary_account') THEN
        ALTER TABLE rexbase.deputy_assignment ADD CONSTRAINT fk_deputy_assignment_beneficiary_account FOREIGN KEY (beneficiary_account_id) REFERENCES rexbase.beneficiary_account (beneficiary_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_deputy_assignment_deputy') THEN
        ALTER TABLE rexbase.deputy_assignment ADD CONSTRAINT fk_deputy_assignment_deputy FOREIGN KEY (deputy_id) REFERENCES rexbase.rexuser (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_deputyright_assignment_deputy_assignment') THEN
        ALTER TABLE rexbase.deputyright_assignment ADD CONSTRAINT fk_deputyright_assignment_deputy_assignment FOREIGN KEY (deputy_assignment_id) REFERENCES rexbase.deputy_assignment (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_deputyright_assignment_deputyright') THEN
        ALTER TABLE rexbase.deputyright_assignment ADD CONSTRAINT fk_deputyright_assignment_deputyright FOREIGN KEY (deputyright_id) REFERENCES rexbase.deputyright (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_employer_platform') THEN
        ALTER TABLE rexbase.employer ADD CONSTRAINT fk_employer_platform FOREIGN KEY (platform_id) REFERENCES rexbase.platform (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_guest_guesttype') THEN
        ALTER TABLE rexbase.guest ADD CONSTRAINT fk_guest_guesttype FOREIGN KEY (default_guesttype_id) REFERENCES rexbase.guesttype (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_picklist_beneficiary_account') THEN
        ALTER TABLE rexbase.picklist ADD CONSTRAINT fk_picklist_beneficiary_account FOREIGN KEY (beneficiary_account_id) REFERENCES rexbase.beneficiary_account (beneficiary_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_picklist_to_beneficiary_beneficiary') THEN
        ALTER TABLE rexbase.picklist_to_beneficiary ADD CONSTRAINT fk_picklist_to_beneficiary_beneficiary FOREIGN KEY (beneficiary_id) REFERENCES rexbase.beneficiary (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_picklist_to_beneficiary_picklist') THEN
        ALTER TABLE rexbase.picklist_to_beneficiary ADD CONSTRAINT fk_picklist_to_beneficiary_picklist FOREIGN KEY (picklist_id) REFERENCES rexbase.picklist (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_picklist_to_guest_guest') THEN
        ALTER TABLE rexbase.picklist_to_guest ADD CONSTRAINT fk_picklist_to_guest_guest FOREIGN KEY (guest_id) REFERENCES rexbase.guest (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_picklist_to_guest_picklist') THEN
        ALTER TABLE rexbase.picklist_to_guest ADD CONSTRAINT fk_picklist_to_guest_picklist FOREIGN KEY (picklist_id) REFERENCES rexbase.picklist (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_platform_domain_platform') THEN
        ALTER TABLE rexbase.platform_domain ADD CONSTRAINT fk_platform_domain_platform FOREIGN KEY (platform_id) REFERENCES rexbase.platform (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'keycloak_id_fk') THEN
        ALTER TABLE rexbase.rexuser ADD CONSTRAINT keycloak_id_fk FOREIGN KEY (keycloak_userid) REFERENCES rexbase.keycloak_sim (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_rexuser_to_role_rexuser') THEN
        ALTER TABLE rexbase.rexuser_to_role ADD CONSTRAINT fk_rexuser_to_role_rexuser FOREIGN KEY (rexuser_id) REFERENCES rexbase.rexuser (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_rexuser_to_role_userrole') THEN
        ALTER TABLE rexbase.rexuser_to_role ADD CONSTRAINT fk_rexuser_to_role_userrole FOREIGN KEY (userrole_id) REFERENCES rexbase.userrole (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_userrole_to_right_userright') THEN
        ALTER TABLE rexbase.userrole_to_right ADD CONSTRAINT fk_userrole_to_right_userright FOREIGN KEY (userright_id) REFERENCES rexbase.userright (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_userrole_to_right_userrole') THEN
        ALTER TABLE rexbase.userrole_to_right ADD CONSTRAINT fk_userrole_to_right_userrole FOREIGN KEY (userrole_id) REFERENCES rexbase.userrole (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_projectnumber_platform') THEN
        ALTER TABLE rexbase.projectnumbers ADD CONSTRAINT fk_projectnumber_platform FOREIGN KEY (platform_id) REFERENCES rexbase.platform (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_projectleader_project') THEN
        ALTER TABLE rexbase.projectleader ADD CONSTRAINT fk_projectleader_project FOREIGN KEY (project_id) REFERENCES rexbase.projectnumbers (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_projectleader_rexuser') THEN
        ALTER TABLE rexbase.projectleader ADD CONSTRAINT fk_projectleader_rexuser FOREIGN KEY (rexuser_id) REFERENCES rexbase.rexuser (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
    END IF;
END $$^;

COMMIT^;

