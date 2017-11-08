Пример веб сервиса с авторизацией и управлением таблицами.

Основные использованные технологии: Spring, Hibernate, mySQL, Tomcat, Maven
IDE: IntelliJ IDEA

Для проверки:
Для удобства рекомендуется использовать указанное выше ide.
Настройки подключения к базе mySQL в файле '/resources/database.properties'.
Для быстрого заполнения базы данный для проверки выполнить скрипт '/resources/database.sql'.

Собранный WAR пакет для запуска без ide находится в главной директории, но так как настройки бд не вынесены во внешние зависимости, а находятся в'/resources/database.properties', необходимо указать данные подключения и пересобрать пакет.  Поместить в <TOMCAT_HOME>\webapps, после чего зайти на http://localhost:8080/autoPark/ для локального запуска или по адресу используемого для внешнего сервера.

![alt text](https://raw.githubusercontent.com/0xFaulty/X-Users/master/ex3.png)
![alt text](https://raw.githubusercontent.com/0xFaulty/X-Users/master/ex2.png)
![alt text](https://raw.githubusercontent.com/0xFaulty/X-Users/master/ex1.png)

"# autoPark" 
