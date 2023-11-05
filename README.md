# Shoppinglist backend

This is the backend application for the shoppinglist project. It is developed with Java and Spring Boot.

# Deployment

Backend application is deployed to Heroku and can be accessed here:
- https://haagaheliashoppinglist-c2c43878d673.herokuapp.com/

It uses PostgreSQL database which is also in Heroku.

# OpenAPI and Swagger UI

REST API has OpenAPI specification documentation and it can be interactively tested with Swagger UI. These tools automatically generate documentation for the API endpoints.

- OpenAPI docs: `/api-docs`
- Swagger UI: `/swagger-ui/index.html` or `/swagger-ui.html`

# Testing

Tests are written with JUnit unit testing framework and use a separate H2 in-memory database.

To run tests with H2 database, you need to set Spring profile to test mode with `SPRING_PROFILES_ACTIVE=test` environment variable when running tests. This will then use the `src/test/resources/application.properties` configurations.

# Development mode

There is development mode that you can enable by setting environment variable `SPRING_PROFILES_ACTIVE=development` when running the application. This development mode uses H2 database instead of PostgreSQL.

`application-development.properties` will be used over `application.properties`. These can be found in `src/main/resources`.

# How to run with Docker

With Docker you can run the backend application and PostgreSQL locally using Docker Compose. You don't need to install PostgreSQL on your own machine.

Run the app and database
```bash
cd shoppinglist-backend
```
```bash
docker compose up -d
```

The application will be available at localhost:8080

Stop and remove containers
```bash
docker compose down
```
