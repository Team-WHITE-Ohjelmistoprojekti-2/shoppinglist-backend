# Shoppinglist backend

This is the backend application for the shoppinglist project. It is developed with Java snd Spring Boot.

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
