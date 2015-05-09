# Data-collection-service
Application is designed to collect some data from users.
- Install PostgreSQL
http://www.postgresql.org/


- Create a database called db_data_collection_service.
SQL script path: /conf/default/1.sql
Or:

CREATE TABLE field (
field_id SERIAL PRIMARY KEY NOT NULL ,
label TEXT,
type TEXT,
required BOOLEAN,
is_active BOOLEAN
);

CREATE TABLE option (
option_id SERIAL PRIMARY KEY NOT NULL,
field_id INTEGER,
text TEXT,
FOREIGN KEY (field_id) REFERENCES field(field_id)
);

CREATE TABLE response (
response_id SERIAL PRIMARY KEY NOT NULL ,
field_id INTEGER NOT NULL,
response_name TEXT,
field_name TEXT,
FOREIGN KEY (field_id) REFERENCES field(field_id)
);

- Now run the Play application using Activator:
activator run
The application should now be accessible at http://localhost:9000
