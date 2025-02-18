# Дипломный проект по профессии «Тестировщик ПО»

### Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.
Тестируемое приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:
1. Обычная оплата по дебетовой карте,
2. Уникальная технология: выдача кредита по данным банковской карты,

и в собственной СУБД должно сохранять информацию о том, успешно ли был совершён платёж и каким способом.

**[Ссылка на дипломное задание](https://github.com/netology-code/qa-diploma)**

### Подготовительный этап:
1. Установить GIT;
2. Установить браузер;
3. Установить и запустить Docker Desktop;
4. Установить и запустить Intellij IDEA;
5. Скопировать проект с Github **[по ссылке](https://github.com/Renat2287/diplomQA/tree/main)**;
6. Открыть проект в Intellij IDEA;
7. Скачать и запустить контейнеры MySQL, PostgreSQL, NodeJS через терминал командой: **docker-compose up**

### Запуск тестового приложения для MySQL:
1. В настройках ***build.gradle*** включить поле ***systemProperty 'db.url', System.getProperty('db.url', "jdbc:mysql://localhost:3306/app")***,
2. В настройках ***build.gradle*** выключить поле ***systemProperty 'db.url', System.getProperty('db.url', "jdbc:postgresql://localhost:5432/app")***,
3. В настройках ***application.properties*** включить поле ***spring.datasource.url=jdbc:mysql://localhost:3306/app***,
4. В настройках ***application.properties*** выключить поле ***spring.datasource.url=jdbc:postgresql://localhost:5432/app***,
5. В новой вкладке терминала запустить тестовое приложение командой: **java -jar ./artifacts/aqa-shop.jar**.
6. Для переключения с MySQL на PostgreSQL остановить приложение командой в терминале: **Ctrl+C** и пройти процедуру: ***Запуск тестового приложения для PostgreSQL***

### Запуск тестового приложения для PostgreSQL:
1. В настройках ***build.gradle*** выключить поле ***systemProperty 'db.url', System.getProperty('db.url', "jdbc:mysql://localhost:3306/app")***,
2. В настройках ***build.gradle*** включить поле ***systemProperty 'db.url', System.getProperty('db.url', "jdbc:postgresql://localhost:5432/app")***,
3. В настройках ***application.properties*** выключить поле ***spring.datasource.url=jdbc:mysql://localhost:3306/app***,
4. В настройках ***application.properties*** включить поле ***spring.datasource.url=jdbc:postgresql://localhost:5432/app***,
5. В новой вкладке терминала запустить тестовое приложение командой: **java -jar ./artifacts/aqa-shop.jar**.
6. Для переключения с PostgreSQL на MySQL остановить приложение командой в терминале: **Ctrl+C** и пройти процедуру: ***Запуск тестового приложения для MySQL***

### Запуск тестов:
* В новой вкладке терминала запустить тесты командой: **./gradlew clean test**

### Отчет о тестировании
* Предусмотрено формирование отчетности через Allure. Для этого в новой вкладке терминала вводим команду: **./gradlew allureServe**