DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS user_access_rights;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    registered       DATE                DEFAULT now() NOT NULL,
    enabled          BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE user_access_rights
(
    user_id INTEGER NOT NULL,
    access_right    INTEGER,
    CONSTRAINT user_access_rights_idx UNIQUE (user_id, access_right),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE measurements
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id       INTEGER                           NOT NULL,
    date          DATE                DEFAULT now() NOT NULL,
    weight        REAL                              NOT NULL,
    waist         REAL                              NOT NULL,
    hips          REAL                              NOT NULL,
    shoulders     REAL,
    quad          REAL,
    bicep         REAL,
    avgCalories   INTEGER,
    trainingCount INTEGER,
    avgSteps      INTEGER,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX measurements_unique_user_date_idx ON measurements (user_id, date);