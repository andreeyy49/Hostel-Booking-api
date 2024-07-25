INSERT INTO "users" VALUES (10, 'User2', '$2y$12$1AWEcu5vqY2NNfbbWPkaF.GhgTv/QVXB5jfBslX6.KoMIbyfnZW.y', 'someMail1@mail.com');
INSERT INTO "users" VALUES (11, 'User3', '$2y$12$1AWEcu5vqY2NNfbbWPkaF.GhgTv/QVXB5jfBslX6.KoMIbyfnZW.y', 'someMail2@mail.com');

INSERT INTO roles VALUES (10, 'ROLE_USER', 10);
INSERT INTO roles VALUES (11, 'ROLE_ADMIN', 11);

INSERT INTO hostels VALUES (10, 'Hostel1', 'Title1', 'City1', 'Address1', 1.1, 0, 0);

INSERT INTO rooms VALUES (10, 'Room1', 'Description1', '1a', 100.0, 2, '2024-06-06 00:00:00', 10);

INSERT INTO bookings VALUES (10, '2024-06-06 00:00:00', '2024-06-07 00:00:00', 10, 10);
