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
6. Открыть проект в Intellij IDEA.

### Запуск контейнеров MySQL, PostgreSQL, NodeJS:
* В терминале ввезти команду:

**docker-compose up**

### Запуск тестового приложения:
#### 1. Для MySQL:
* В новой вкладке терминала запустить тестовое приложение командой:

**java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar ./artifacts/aqa-shop.jar**

#### 2. Для PostgreSQL:
* В терминале запустить тестовое приложение командой:

**java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar ./artifacts/aqa-shop.jar**

#### Для переключения между PostgreSQL и MySQL:
* Остановить приложение командой в терминале: **Ctrl+C** и повторить необходимые действия из предыдущих разделов.

### Запуск тестов:
#### 1. Для MySQL:
* В новой вкладке терминала запустить тесты командой:

**./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"**

#### 2. Для PostgreSQL:
* В вкладке терминала запустить тесты командой:

**./gradlew clean test "-Ddb.url=jdbc:posgresql://localhost:5432/app"**

### Отчет о тестировании
* Предусмотрено формирование отчетности через Allure. Для запуска Allure в новой вкладке терминала ввести команду:

**./gradlew allureServe**