DROP TABLE users, roles, user_roles, addresses, user_addresses;

-- Table: users
CREATE TABLE users (
  id                   SERIAL    NOT NULL PRIMARY KEY,
  username             TEXT      NOT NULL,
  password             TEXT      NOT NULL,
  firstname            TEXT      NOT NULL,
  lastname             TEXT      NOT NULL,
  email                TEXT      NOT NULL,
  active               BOOLEAN   NOT NULL,
  birthday             DATE      NOT NULL,
  createdTimestamp     TIMESTAMP NOT NULL DEFAULT NOW(),
  lastUpdatedTimestamp TIMESTAMP NOT NULL
);

-- Table: roles
CREATE TABLE roles (
  id   SERIAL NOT NULL PRIMARY KEY,
  name TEXT   NOT NULL
);

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- Table: addresses
CREATE TABLE addresses (
  id       SERIAL NOT NULL PRIMARY KEY,
  zip      INT    NOT NULL,
  country  TEXT   NOT NULL,
  city     TEXT   NOT NULL,
  district TEXT   NOT NULL,
  street   TEXT   NOT NULL
);

-- Table for mapping user and addresses: user_addresses
CREATE TABLE user_addresses (
  user_id    INT NOT NULL,
  address_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (address_id) REFERENCES addresses (id),

  UNIQUE (user_id, address_id)
);

-- Insert data

INSERT INTO users VALUES
  (1, 'guest',
   '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG',
   'Nameless',
   'Guest',
   'guest@web.ru',
   TRUE,
   '2012-12-13',
   '2012-12-13 14:54:30',
   '2012-12-13 14:54:30'
  );

INSERT INTO users VALUES
  (2, 'admin',
   '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG',
   'Scrooge',
   'McDuck',
   'admin@web.ru',
   TRUE,
   '2012-12-12',
   '2012-12-12 14:54:30',
   '2012-12-12 14:54:30'
  );

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 1);
INSERT INTO user_roles VALUES (2, 2);

INSERT INTO addresses VALUES (1, 0, '', '', '', '');
INSERT INTO addresses VALUES (2, 188777, 'Russia', 'Saint Petersburg', 'Saint Petersburg', 'New Street');

INSERT INTO user_addresses VALUES (1, 1);
INSERT INTO user_addresses VALUES (2, 2);

-- Data part:

DROP TABLE journal, routes, autos, auto_personnel;

CREATE TABLE auto_personnel (
  "id"          SERIAL CONSTRAINT auto_personnel_pkey PRIMARY KEY,
  "first_name"  TEXT,
  "last_name"   TEXT,
  "father_name" TEXT
);

CREATE TABLE routes (
  "id"   SERIAL CONSTRAINT routes_pkey PRIMARY KEY,
  "name" TEXT
);

CREATE TABLE autos (
  "id"           SERIAL CONSTRAINT auto_pkey PRIMARY KEY,
  "num"          TEXT,
  "color"        TEXT,
  "mark"         TEXT,
  "personnel_id" INTEGER REFERENCES auto_personnel (id)
  ON UPDATE NO ACTION
);

CREATE TABLE journal (
  "id"       SERIAL CONSTRAINT journal_pkey PRIMARY KEY,
  "time_out" TIMESTAMP NOT NULL,
  "time_in"  TIMESTAMP NOT NULL,
  "auto_id"  INTEGER REFERENCES autos (id),
  "route_id" INTEGER REFERENCES routes (id)
  ON UPDATE NO ACTION
);

-- Insert data:

INSERT INTO auto_personnel ("id", "first_name", "last_name", "father_name") VALUES
  (1, 'Вася', 'Васильев', 'Васильевич'),
  (2, 'Петя', 'Обломов', 'Антонович'),
  (3, 'Коля', 'Расломов', 'Александрович'),
  (4, 'Виталий', 'Адеев', 'Арсентьевич'),
  (5, 'Алексей', 'Хрущёв', 'Леонтьевич');

INSERT INTO routes ("id", "name") VALUES
  (1, 'Аэропорт'),
  (2, 'Метро'),
  (3, 'Домой'),
  (4, 'Туда'),
  (5, 'Сюда'),
  (6, 'Наверх');

INSERT INTO autos ("id", "num", "color", "mark", "personnel_id") VALUES
  (1, 'а999ва', 'Red', 'Nissan', 1),
  (2, 'г777тк', 'Green', 'Syzuki', 2),
  (3, 'х778нн', 'Yellow', 'Syzuki', 2),
  (4, 'с779пп', 'Black', 'Syzuki', 2),
  (5, 'м666оо', 'Blue', 'Dodge', 3);

INSERT INTO journal ("id", "time_out", "time_in", "auto_id", "route_id") VALUES
  (1, '2012-12-13 00:00:01', '2012-12-13 21:44:00', 3, 2),
  (2, '2012-10-13 02:00:01', '2012-10-13 14:44:00', 3, 1),
  (3, '2012-12-13 04:00:01', '2012-12-13 07:44:00', 3, 1),
  (4, '2015-12-13 05:00:01', '2015-12-13 19:44:00', 5, 1),
  (5, '2013-12-13 08:00:01', '2013-12-13 19:44:00', 2, 3),
  (6, '2016-10-13 01:00:01', '2016-10-13 02:44:00', 2, 2),
  (7, '2016-12-13 06:00:01', '2016-12-13 10:44:00', 2, 1),
  (8, '2012-12-13 09:02:01', '2012-12-13 12:44:00', 2, 1),
  (9, '2017-10-13 17:00:07', '2017-10-13 18:44:00', 2, 1),
  (10, '2012-12-13 08:17:04', '2012-12-13 19:44:00', 3, 3);