SET MODE PostgreSQL

CREATE TABLE IF NOT EXISTS teams (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    product VARCHAR,
    memberCount INTEGER
);

CREATE TABLE IF NOT EXISTS members (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    teamId INTEGER
);

