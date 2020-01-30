CREATE DATABASE IF NOT EXISTS abhakara_admiral;
use abhakara_admiral;
CREATe TABLE IF NOT EXISTS users (
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (email)
);
CREATE TABLE IF NOT EXISTS authorities (
  email VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (email) REFERENCES dev_users(email)
);
 
CREATE UNIQUE INDEX ix_auth_email on authorities (email,authority);

INSERT INTO users (name, email, password, enabled)
  values ('admin',
    'admin@email.com',
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
    1);
 
INSERT INTO authorities (email, authority)
  values ('admin@email.com', 'ROLE_ADMIRAL');