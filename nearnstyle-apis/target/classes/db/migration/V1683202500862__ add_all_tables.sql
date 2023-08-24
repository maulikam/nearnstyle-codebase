

drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BYTEA,
  refresh_token VARCHAR(255)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication BYTEA
);

CREATE TABLE ns_role_master
(
  id bigserial NOT NULL,
  name character varying(50) NOT NULL,
  state character varying(255),
  description VARCHAR(300),
  created_by bigint NOT NULL,
  created_on timestamp without time zone NOT NULL,
  modified_by bigint,
  modified_on timestamp without time zone,
  CONSTRAINT ns_role_master_pkey PRIMARY KEY (id)
);

CREATE TABLE ns_salon
(
  id bigserial NOT NULL,
  name character varying(100) NOT NULL,
  code character varying(30),
  code_generated_at timestamp without time zone,
  code_expires_at timestamp without time zone,
  address text,
  ad_state character varying(100),
  ad_district character varying(100),
  ad_city character varying(100),
  ad_pincode  character varying(100),
  state character varying(255),
    latitude double precision,
    longitude double precision,
  contact_name character varying(100),
  contact_email character varying(255),
  contact_mobile character varying(20),
  created_by bigint NOT NULL,
  created_on timestamp without time zone NOT NULL,
  modified_by bigint,
  modified_on timestamp without time zone,
  CONSTRAINT ns_org_pkey PRIMARY KEY (id)
);

CREATE TABLE ns_user
(
  id bigserial NOT NULL,
  user_name character varying(30),
  role_id bigint,
  mobile_number character varying(15) NOT NULL,
  date_of_birth date,
  email_id character varying(150),
  first_name character varying(100),
  middle_name character varying(100),
  last_name character varying(100),
  gender character varying(15),
  password character varying(50),
  address text,
  state character varying(255),
  org_id BIGINT,
  google_id character varying(50),
  no_of_attempts integer,
  created_by bigint,
  created_on timestamp without time zone,
  modified_by bigint,
  modified_on timestamp without time zone,
  CONSTRAINT ns_user_pkey PRIMARY KEY (id),
  CONSTRAINT fk_ns_role_master_id_ns_user_role_id FOREIGN KEY (role_id)
      REFERENCES ns_role_master (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ns_salon_id_ns_user_org_id FOREIGN KEY (org_id)
        REFERENCES ns_salon (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_ns_user_user_name UNIQUE (user_name),
  CONSTRAINT uk_ns_user_mobile_number UNIQUE (mobile_number),
  CONSTRAINT uk_ns_user_email_id UNIQUE (email_id)
);





CREATE TABLE public.ns_availability (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    salon_id bigint,
    day_of_week integer NOT NULL,
    start_time time without time zone NOT NULL,
    end_time time without time zone NOT NULL,
    status character varying(255) NOT NULL CHECK (status IN ('AVAILABLE', 'UNAVAILABLE')),
    duration_minutes integer,
    is_active boolean NOT NULL DEFAULT true,
    booking_reference character varying(255),
    notes text,
    is_recurring boolean NOT NULL DEFAULT false,
    notification_sent boolean DEFAULT false,

    -- Fields from EntityAuditInfo (assuming integer data types for created_by and modified_by)
    created_by integer NOT NULL,
    created_on timestamp without time zone NOT NULL,
    modified_by integer,
    modified_on timestamp without time zone,

    -- Foreign key constraint
    CONSTRAINT ns_availability_salon_id_fk FOREIGN KEY (salon_id) REFERENCES public.ns_salon(id)
);

-- Add index for salon_id for better performance on joins and queries:
CREATE INDEX idx_ns_availability_salon_id ON public.ns_availability(salon_id);



CREATE TABLE public.ns_review (
    id BIGSERIAL NOT NULL  PRIMARY KEY,
    created_by integer NOT NULL,
    created_on timestamp without time zone NOT NULL,
    modified_by integer,
    modified_on timestamp without time zone,
    description character varying(1000),
    salon_id bigint,
    user_id bigint,
    rating integer NOT NULL,
    title character varying(255) NOT NULL,
    CONSTRAINT ns_review_salon_id_fk FOREIGN KEY (salon_id) REFERENCES public.ns_salon(id),
    CONSTRAINT ns_review_user_id_fk FOREIGN KEY (user_id) REFERENCES public.ns_user(id)
);

CREATE TABLE ns_user_otps (
    id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR,
    mobile_number VARCHAR,
    otp VARCHAR(10) NOT NULL,
    expiration_time TIMESTAMP NOT NULL,
    otp_verified BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (user_name) REFERENCES ns_user(user_name)
);

CREATE TABLE ns_feature_master (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    description VARCHAR(300)
);

CREATE TABLE ns_role_features (
    role_id BIGINT,
    feature_id BIGINT,
    PRIMARY KEY (role_id, feature_id),
    FOREIGN KEY (role_id) REFERENCES ns_role_master(id) ON DELETE CASCADE,
    FOREIGN KEY (feature_id) REFERENCES ns_feature_master(id) ON DELETE CASCADE
);
