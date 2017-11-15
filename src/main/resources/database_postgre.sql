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
  (nextval('users_id_seq'),
   'guest',
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
  (nextval('users_id_seq'),
   'admin',
   '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG',
   'Scrooge',
   'McDuck',
   'admin@web.ru',
   TRUE,
   '2012-12-12',
   '2012-12-12 14:54:30',
   '2012-12-12 14:54:30'
  );

INSERT INTO roles VALUES (nextval('roles_id_seq'), 'ROLE_USER');
INSERT INTO roles VALUES (nextval('roles_id_seq'), 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 1);
INSERT INTO user_roles VALUES (2, 2);

INSERT INTO addresses VALUES (
  nextval('addresses_id_seq'),
  0,
  '',
  '',
  '',
  ''
);
INSERT INTO addresses VALUES (
  nextval('addresses_id_seq'),
  188777,
  'Russia',
  'Saint Petersburg',
  'Saint Petersburg',
  'New Street'
);

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
  (nextval('auto_personnel_id_seq'), 'Вася', 'Васильев', 'Васильевич'),
  (nextval('auto_personnel_id_seq'), 'Петя', 'Обломов', 'Антонович'),
  (nextval('auto_personnel_id_seq'), 'Коля', 'Расломов', 'Александрович'),
  (nextval('auto_personnel_id_seq'), 'Виталий', 'Адеев', 'Арсентьевич'),
  (nextval('auto_personnel_id_seq'), 'Алексей', 'Хрущёв', 'Леонтьевич');

INSERT INTO routes ("id", "name") VALUES
  (nextval('routes_id_seq'), 'Аэропорт'),
  (nextval('routes_id_seq'), 'Метро'),
  (nextval('routes_id_seq'), 'Купчино'),
  (nextval('routes_id_seq'), 'пл. Ленина'),
  (nextval('routes_id_seq'), 'пер. Некрасова'),
  (nextval('routes_id_seq'), 'ул. Пушкина'),
  (nextval('routes_id_seq'), 'Павловск');

INSERT INTO autos ("id", "num", "color", "mark", "personnel_id") VALUES
  (nextval('autos_id_seq'), 'а999ва', 'Red', 'Nissan', 1),
  (nextval('autos_id_seq'), 'г777тк', 'Green', 'Syzuki', 2),
  (nextval('autos_id_seq'), 'х778нн', 'Yellow', 'Syzuki', 2),
  (nextval('autos_id_seq'), 'с779пп', 'Black', 'Syzuki', 2),
  (nextval('autos_id_seq'), 'м666оо', 'Blue', 'Dodge', 3);

INSERT INTO journal ("id", "time_out", "time_in", "auto_id", "route_id") VALUES
  (nextval('journal_id_seq'), '2012-12-13 00:00:01', '2012-12-13 21:44:00', 3, 2),
  (nextval('journal_id_seq'), '2012-10-13 02:00:01', '2012-10-13 14:44:00', 3, 1),
  (nextval('journal_id_seq'), '2012-12-13 04:00:01', '2012-12-13 07:44:00', 3, 1),
  (nextval('journal_id_seq'), '2015-12-13 05:00:01', '2015-12-13 19:44:00', 5, 1),
  (nextval('journal_id_seq'), '2013-12-13 08:00:01', '2013-12-13 19:44:00', 2, 3),
  (nextval('journal_id_seq'), '2016-10-13 01:00:01', '2016-10-13 02:44:00', 2, 2),
  (nextval('journal_id_seq'), '2016-12-13 06:00:01', '2016-12-13 10:44:00', 2, 1),
  (nextval('journal_id_seq'), '2012-12-13 09:02:01', '2012-12-13 12:44:00', 2, 1),
  (nextval('journal_id_seq'), '2017-10-13 17:00:07', '2017-10-13 18:44:00', 2, 1),
  (nextval('journal_id_seq'), '2012-12-13 08:17:04', '2012-12-13 19:44:00', 3, 3);