INSERT INTO "users" VALUES (1, "User1", "$2a$12$Vi7nvZa04TicIPe5ngaeB.0Dm32WivF/bcc82Wp4ZTHe12OMmC/Hu", "someMail1@mail.com");
INSERT INTO "users" VALUES (2, "User2", "$2a$12$Vi7nvZa04TicIPe5ngaeB.0Dm32WivF/bcc82Wp4ZTHe12OMmC/Hu", "someMail2@mail.com");

INSERT INTO roles VALUES (1, "ROLE_USER", 1);
INSERT INTO roles VALUES (2, "ROLE_ADMIN", 2);

INSERT INTO hostels VALUES (1, "Hostel1", "Title1", "City1", "Address1", 1.1, 0, 0);

INSERT INTO rooms VALUES (1, "Room1", "Description1", "1a", 100.0, 2, 2024-06-06 00:00:00, 1);

INSERT INTO bookings VALUES (1, 2024-06-06 00:00:00, 2024-06-07 00:00:00, 1, 1)