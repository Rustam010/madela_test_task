# Сервис по продаже автомобилей

## Комментарии по проекту:

1) Видео работоспособности и разбора функционала проекта: [Ссылка на видео](https://disk.yandex.ru/d/YgmOMj0e6TfClA)

2) Тестовый проект для компании Madela. Выполнен с использованием Spring Framework, Hibernate, PostgreSQL. Обработано большинство возможных исключений.

3) Сначала хотел сделать графический web-интерфейс, но затем отказался от этой идеи и перешел на REST-контроллеры, т.к. в ТЗ требуются JSON-отображения + моем портфолио уже есть проект с web-интерфейсом (https://github.com/Rustam010/project_library).

4) Возникли небольшие трудности при реализации второго пункта ТЗ (подписка на рассылку), но, погуглив и разобравшись, пришел к выводу, что использование Spring Event будет оптимальным решением.



## Необходимые таблицы для запуска проекта

```sql
-- Таблица nsi_auto_model
CREATE TABLE nsi_auto_model (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    model VARCHAR(50) NOT NULL
);

-- Таблица Car
CREATE TABLE car (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    color VARCHAR(30),
    year INT CHECK (year >= 1990 AND year <= 2024),
    country VARCHAR(30),
    number_of_owners INT CHECK (number_of_owners >= 0),
    model_id INT,
    FOREIGN KEY (model_id) REFERENCES nsi_auto_model(id)
);

-- Таблица subscriptions
CREATE TABLE subscriptions (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    email VARCHAR(255) UNIQUE NOT NULL,
    model VARCHAR(50) NOT NULL
);
