DROP TABLE users, user_addresses, user_roles, addresses, roles;

-- Table: users
CREATE TABLE users (
  id                   INT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username             TEXT      NOT NULL,
  password             TEXT      NOT NULL,
  firstname            TEXT      NOT NULL,
  lastname             TEXT      NOT NULL,
  email                TEXT      NOT NULL,
  active               TINYINT   NOT NULL,
  birthday             DATE      NOT NULL,
  createdTimestamp     TIMESTAMP NOT NULL DEFAULT NOW(),
  lastUpdatedTimestamp TIMESTAMP NOT NULL
)
  ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles (
  id   INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name TEXT NOT NULL
)
  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB;

-- Table: addresses
CREATE TABLE addresses (
  id       INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  zip      INT  NOT NULL,
  country  TEXT NOT NULL,
  city     TEXT NOT NULL,
  district TEXT NOT NULL,
  street   TEXT NOT NULL
)
  ENGINE = InnoDB;

-- Table for mapping user and addresses: user_addresses
CREATE TABLE user_addresses (
  user_id    INT NOT NULL,
  address_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (address_id) REFERENCES addresses (id),

  UNIQUE (user_id, address_id)
)
  ENGINE = InnoDB;


INSERT INTO users (username, password, firstname, lastname, email, active, birthday, createdTimestamp, lastUpdatedTimestamp)
VALUES
  ('guest',
   '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG',
   'Nameless',
   'Guest',
   'guest@web.ru',
   1,
   '2012-12-13',
   '2012-12-13 14:54:30',
   '2012-12-13 14:54:30'
  );

INSERT INTO users (username, password, firstname, lastname, email, active, birthday, createdTimestamp, lastUpdatedTimestamp)
VALUES
  ('admin',
   '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG',
   'Scrooge',
   'McDuck',
   'admin@web.ru',
   1,
   '2012-12-12',
   '2012-12-12 14:54:30',
   '2012-12-12 14:54:30'
  );

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 1);
INSERT INTO user_roles VALUES (2, 2);

INSERT INTO addresses (zip, country, city, district, street)
VALUES (0, '', '', '', '');
INSERT INTO addresses (zip, country, city, district, street)
VALUES (188777, 'Russia', 'Saint Petersburg', 'Saint Petersburg', 'New Street');

INSERT INTO user_addresses VALUES (1, 1);
INSERT INTO user_addresses VALUES (2, 2);

-- Data part:

DROP TABLE journal, routes, autos, auto_personnel;

CREATE TABLE auto_personnel (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name  TEXT,
  last_name   TEXT,
  father_name TEXT
);

CREATE TABLE routes (
  id   INT AUTO_INCREMENT PRIMARY KEY,
  name TEXT
);

CREATE TABLE autos (
  id           INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  num          TEXT,
  color        TEXT,
  mark         TEXT,
  personnel_id INTEGER REFERENCES auto_personnel (id)
    ON UPDATE NO ACTION
);

CREATE TABLE journal (
  id       INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  time_out TIMESTAMP,
  time_in  TIMESTAMP,
  auto_id  INTEGER REFERENCES autos (id),
  route_id INTEGER REFERENCES routes (id)
    ON UPDATE NO ACTION
);

-- Insert data:

INSERT INTO auto_personnel (first_name, last_name, father_name) VALUES
  ('Вася', 'Васильев', 'Васильевич'),
  ('Петя', 'Обломов', 'Антонович'),
  ('Коля', 'Расломов', 'Александрович'),
  ('Виталий', 'Адеев', 'Арсентьевич'),
  ('Алексей', 'Хрущёв', 'Леонтьевич');

INSERT INTO routes (name) VALUES
  ('Аэропорт'),
  ('Метро'),
  ('Домой'),
  ('Туда'),
  ('Сюда'),
  ('Наверх');

INSERT INTO autos (num, color, mark, personnel_id) VALUES
  ('а999ва', 'Red', 'Nissan', 1),
  ('г777тк', 'Green', 'Syzuki', 2),
  ('х778нн', 'Yellow', 'Syzuki', 2),
  ('с779пп', 'Black', 'Syzuki', 2),
  ('м666оо', 'Blue', 'Dodge', 3);

INSERT INTO journal (time_out, time_in, auto_id, route_id) VALUES
  ('2012-12-12 00:00:01', '2012-12-12 21:44:00', 3, 2),
  ('2012-12-12 02:00:01', '2012-12-12 14:44:00', 3, 1),
  ('2012-12-12 04:00:01', '2012-12-12 07:44:00', 3, 1),
  ('2012-12-12 05:00:01', '2012-12-12 19:44:00', 5, 1),
  ('2012-12-12 08:00:01', '2012-12-12 19:44:00', 2, 3),
  ('2012-12-12 01:00:01', '2012-12-12 02:44:00', 2, 2),
  ('2012-12-12 06:00:01', '2012-12-12 10:44:00', 2, 1),
  ('2012-12-12 09:02:01', '2012-12-12 12:44:00', 2, 1),
  ('2012-12-12 17:00:07', '2012-12-12 18:44:00', 2, 1),
  ('2012-12-12 08:17:04', '2012-12-12 19:44:00', 3, 3);