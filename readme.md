# Приложение Переводчик

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.2-green)
![Java](https://img.shields.io/badge/Java-21-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-latest-blue)

Приложение Переводчик — это сервис на основе Spring Boot, который предоставляет возможности перевода текста с использованием внешнего API. Это приложение позволяет пользователям переводить текст между различными языками и хранить запросы на перевод в базе данных PostgreSQL.

## Особенности

- 🌐 Перевод текста между несколькими языками.
- 📚 Получение списка поддерживаемых языков.
- 💾 Хранение запросов на перевод в базе данных PostgreSQL.
- 🔄 Механизм повторных попыток для обработки временных ошибок.
- 📝 Логирование для отладки и мониторинга.

## Предварительные требования

Перед началом работы убедитесь, что у вас есть следующее:

- Java Development Kit (JDK) 21
- Maven
- База данных PostgreSQL
- Ключ RapidAPI для API перевода

## Начало работы

### Клонируйте репозиторий
```sh
git clone https://github.com/yourusername/translator-application.gitcd translator-application
```
### Настройте приложение

Измените файл `application.properties` в директории `src/main/java/...` и настройте следующие свойства:

### Ключ RapidAPI для доступа к API переводчика  
**rapidapi.key** = `USE_YOUR_KEY`

### Базовый URL для API переводчика     
**baseUrl** = `https://google-translator9.p.rapidapi.com/v2`

### URL для получения списка поддерживаемых языков  
**languageUrl** = `https://google-translator9.p.rapidapi.com/v2/languages`

### Настройки для подключения к PostgreSQL  
- **spring.datasource.url** = `jdbc:postgresql://localhost:5432/YOUR_DB`      
- **spring.datasource.username** =`YOUR_USERNAME`   
- **spring.datasource.password** = `YOUR_PASSWORD`

### Настройка диалекта Hibernate для PostgreSQL
**spring.jpa.properties.hibernate.dialect** = `org.hibernate.dialect.PostgreSQLDialect`
### Соберите приложение

**sh
mvn clean install**
### Запустите приложение

**sh
mvn spring-boot:run**  
Приложение будет запущено и доступно по адресу `http://localhost:8080`.

## API Эндпоинты

### Получить доступные языки

- **URL**: `/api/v1/translation/languages`
- **Метод**: `GET`
- **Ответ**: Список поддерживаемых языков в формате `TreeMap<String, String>`.

### Перевести текст

- **URL**: `/api/v1/translation/translate`
- **Метод**: `POST`
- **Тело запроса**:

json
{
"q": "Hello",
"source": "en",
"target": "es",
"format": "text"
}

- Ответ: Переведенный текст.
## Использование
Для выполнения запроса на перевод, отправьте POST-запрос на `/translate`с телом вида
```sh
{
  "q": "Hello world, this is my first program",
  "source": "en",
  "target": "ru",
  "format": "text"
}
```
Чтобы получить список доступных языков, отправьте Get-запрос на `/languages`

Пример запроса:
```sh
curl --location 'http://localhost:8080/translate' \
--header 'Content-Type: application/json' \
--data '{
  "q": "один два три четыре пять шесть семь восемь девять десять одинадцать двенадцать тринадцать четырнадцать пятнадцать шеснадцать семнадцать восемнадцать девятнадцать двадцать",
  "source": "ru",
  "target": "de",
  "format": "text"
}'
```
```sh
curl --location 'http://localhost:8080/languages'
```
