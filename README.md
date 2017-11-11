Пример веб сервиса с авторизацией и управлением таблицами основанный на проекте X-Users.

Добавленные таблицы: Auto, Routes, AutoPersonnel, Journal

Основные использованные технологии: Spring, Hibernate, mySQL, Tomcat, Maven
IDE: IntelliJ IDEA

Для использования:
Настройки подключения к базе mySQL в файле '/resources/database.properties'.
Для быстрого заполнения базы данный для проверки выполнить скрипт '/resources/database_postgre.sql' для PostgreSQL или '/resources/database_mysql.sql' для MySQL.

Настройки подключения к базе данных находятся в'/resources/database.properties'. Для запуска поместить в <TOMCAT_HOME>\webapps, после чего зайти на http://localhost:8080/autoPark/ для локального запуска или по адресу используемого для внешнего сервера.

![alt text](https://raw.githubusercontent.com/0xFaulty/autoPark/master/screenshots/ex3.png)
![alt text](https://raw.githubusercontent.com/0xFaulty/autoPark/master/screenshots/ex2.png)
![alt text](https://raw.githubusercontent.com/0xFaulty/autoPark/master/screenshots/ex1.png)
