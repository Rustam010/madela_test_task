CREATE TABLE car(
    id               INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    color            VARCHAR(30),
    year             INT CHECK (year >= 1990 AND year <= 2024),
    country          VARCHAR(30),
    number_of_owners INT CHECK (number_of_owners >= 0),
    model_id         INT,
    FOREIGN KEY (model_id) REFERENCES nsi_auto_model (id)
);

INSERT INTO car (color, year, country, number_of_owners, model_id) VALUES ('Red', 2020, 'USA', 1, 2);
INSERT INTO car (color, year, country, number_of_owners, model_id) VALUES ('Blue', 2018, 'Germany', 2, 3);
INSERT INTO car (color, year, country, number_of_owners, model_id) VALUES ('Green', 2019, 'Japan', 1, 4);
INSERT INTO car (color, year, country, number_of_owners, model_id) VALUES ('Black', 2021, 'Russia', 1, 1);
INSERT INTO car (color, year, country, number_of_owners, model_id) VALUES ('White', 2022, 'France', 3, 2);
