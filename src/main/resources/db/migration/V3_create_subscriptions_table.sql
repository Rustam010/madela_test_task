CREATE TABLE subscriptions(
    id    INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    email VARCHAR(255) UNIQUE NOT NULL,
    model VARCHAR(50)         NOT NULL
);

INSERT INTO subscriptions (email, model)VALUES ('test1@mail.com', 'HONDA');
INSERT INTO subscriptions (email, model)VALUES ('test2@mail.com', 'BMW');
INSERT INTO subscriptions (email, model)VALUES ('test3@mail.com', 'VAZ');

