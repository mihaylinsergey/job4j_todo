CREATE TABLE categories (
   id SERIAL PRIMARY KEY,
   name TEXT UNIQUE NOT NULL
);

INSERT INTO categories (name) VALUES ('participation in the meeting');
INSERT INTO categories (name) VALUES ('preparation of the report');
INSERT INTO categories (name) VALUES ('call the customer');
INSERT INTO categories (name) VALUES ('preparation of the presentation');
INSERT INTO categories (name) VALUES ('information search');
