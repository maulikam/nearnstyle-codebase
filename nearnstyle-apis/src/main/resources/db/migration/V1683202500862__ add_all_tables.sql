

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

CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255),
    email_address VARCHAR(255) UNIQUE,
    phone_number VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    user_type VARCHAR(20) NOT NULL CHECK (user_type IN ('CUSTOMER', 'SALON_OWNER', 'ADMIN')),
    profile_picture VARCHAR(255)
);


CREATE TABLE salons (
    salon_id BIGSERIAL PRIMARY KEY,
    salon_name VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(500) NOT NULL,
    phone_number VARCHAR(50),
    description VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    facilities VARCHAR(1000),
    owner_name VARCHAR(255)
);


-- Create indices for the unique and frequently accessed columns for improved search performance:
CREATE INDEX idx_email_address ON users(email_address);
CREATE INDEX idx_phone_number ON users(phone_number);

CREATE TABLE reviews (
    review_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(user_id),
    rating INTEGER NOT NULL,
    comment VARCHAR(500),
    review_date TIMESTAMP NOT NULL
);

-- Index for faster look-up based on user_id
CREATE INDEX idx_reviews_user_id ON reviews(user_id);

CREATE TABLE services (
    service_id BIGSERIAL PRIMARY KEY,
    service_name VARCHAR(255) NOT NULL UNIQUE,
    price DOUBLE PRECISION NOT NULL,
    description VARCHAR(500),
    duration_in_minutes INTEGER NOT NULL,
    tools_required VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE, -- Assuming you'd mostly add active services first.
    max_booking_per_day INTEGER,
    prerequisites VARCHAR(500)
);

-- Indexes for faster look-ups
CREATE INDEX idx_services_service_name ON services(service_name);


CREATE TABLE appointments (
    appointment_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(user_id),
    service_id BIGINT NOT NULL REFERENCES services(service_id),
    appointment_date TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('CONFIRMED', 'COMPLETED', 'CANCELED')),
    special_requests VARCHAR(200),
    salon_id BIGINT NOT NULL REFERENCES salons(salon_id)
);

-- Indexes for faster look-ups
CREATE INDEX idx_appointments_user_id ON appointments(user_id);
CREATE INDEX idx_appointments_service_id ON appointments(service_id);
CREATE INDEX idx_appointments_salon_id ON appointments(salon_id);


CREATE TABLE operating_hours (
    id BIGSERIAL PRIMARY KEY,
    salon_id BIGINT NOT NULL REFERENCES salons(id) ON DELETE CASCADE,
    day VARCHAR(10) NOT NULL CHECK (day IN ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY')),
    opening_time TIME NOT NULL,
    closing_time TIME NOT NULL,
    UNIQUE(salon_id, day)  -- Ensuring that for each salon, there's only one set of operating hours per day
);


CREATE TABLE ns_user_otps (
    id BIGSERIAL PRIMARY KEY,
    user_id VARCHAR,
    mobile_number VARCHAR,
    otp VARCHAR(10) NOT NULL,
    expiration_time TIMESTAMP NOT NULL,
    otp_verified BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (user_id) REFERENCES ns_user(user_id)
);
