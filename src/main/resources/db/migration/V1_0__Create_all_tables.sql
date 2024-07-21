CREATE TABLE hostels
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(255),
    title          VARCHAR(255),
    city           VARCHAR(255),
    address        VARCHAR(255),
    distance       FLOAT,
    rating         FLOAT,
    rating_counter FLOAT
);

CREATE TABLE rooms
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255),
    description     TEXT,
    number          VARCHAR(50),
    price           FLOAT,
    max_people_size INTEGER,
    booking_time    TIMESTAMP,
    hostel_id       INTEGER REFERENCES hostels (id) ON DELETE CASCADE
);

CREATE TABLE "users"
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE bookings
(
    id          SERIAL PRIMARY KEY,
    in_booking  TIMESTAMP,
    out_booking TIMESTAMP,
    room_id     INTEGER REFERENCES rooms (id) ON DELETE CASCADE,
    user_id     INTEGER REFERENCES "users" (id) ON DELETE CASCADE
);

CREATE TABLE roles
(
    id          SERIAL PRIMARY KEY,
    authorities VARCHAR(255),
    user_id     INTEGER REFERENCES "users" (id) ON DELETE CASCADE
)