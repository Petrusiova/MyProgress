DROP TABLE user_roles IF EXISTS;
DROP TABLE user_access_rights IF EXISTS;
DROP TABLE measurements IF EXISTS;
DROP TABLE users IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE global_seq AS INTEGER START WITH 100000;

CREATE TABLE users
(
    id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name       VARCHAR(255)          NOT NULL,
    email      VARCHAR(255)          NOT NULL,
    password   VARCHAR(255)          NOT NULL,
    registered DATE    DEFAULT now() NOT NULL,
    enabled    BOOLEAN DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE user_access_rights
(
    user_id      INTEGER NOT NULL REFERENCES USERS,
    follower_id INTEGER  NOT NULL REFERENCES USERS,
    PRIMARY KEY (user_id, follower_id)
);

CREATE TABLE measurements
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    user_id       INTEGER            NOT NULL,
    date          DATE DEFAULT now() NOT NULL,
    weight        DECIMAL(4, 1)      NOT NULL,
    waist         DECIMAL(4, 1)      NOT NULL,
    hips          DECIMAL(4, 1)      NOT NULL,
    shoulders     DECIMAL(4, 1),
    quad          DECIMAL(4, 1),
    bicep         DECIMAL(4, 1),
    avgCalories   INTEGER,
    trainingCount INTEGER,
    avgSteps      INTEGER,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX measurements_unique_user_date_idx ON measurements (user_id, date);