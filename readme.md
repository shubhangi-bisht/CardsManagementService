# Cards Management Service

A Spring Boot–based service responsible for managing card lifecycle operations such as card creation, status updates, and transaction retrieval. This service is designed with clean architecture principles, RESTful APIs, and enterprise-ready practices.

# Features

* Create cards
* Fetch card details by card number
* Update card status (e.g., ACTIVE, BLOCKED)
* Pagination support for card listings
* An external weather API (nested) is incorporated.
* Centralized exception handling

# Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot
* **Build Tool:** Maven
* **Database:** MS SQL Server (Docker-supported)
* **ORM:** Spring Data JPA / Native Queries
* **Logging:** Log4j2
* **API Style:** REST

# Project Structure
```
com.maybank.cards
├── controller        # REST controllers
├── service           # Business logic
│   └── impl
├── repository        # JPA / Native repositories
├── dto               # Request/Response DTOs
├── entity            # JPA entities
├── exception         # Custom exceptions & handlers
└── util              # Utility classes
```

# Setup & Run Locally

### Prerequisites

* Java 17
* Maven 3.8+
* Docker (for database) with db name : TESTDB

# Application will start on:

```
http://localhost:8081
```

# API Endpoints (Sample)

| Method  | Endpoint                                | Description                              |
|---------|-----------------------------------------|------------------------------------------|
| POST    | `/card/create-card`                     | Create a new card                        |
| GET     | `/card/fetch-card-details `             | Fetch cards details by card number       |
| PUT     | `/card/update-card-status`              | Update card status                       |
| GET     | `card/fetch-cardholder-details/{pages}` | Fetch total card details with pagination |
| GET     | `weather/{latitutde}/{longitude}`       | Fetch weather details from External API  |            

# Exception Handling

* Centralized using `@ControllerAdvice`
* Meaningful HTTP status codes
* Consistent error response structure

# External API Integration & Testing
As part of this effort, an external Weather API has been incorporated to test:
REST client integration
Request/response handling
Error scenarios and resilience
This setup helps ensure reliable external API consumption and keeps the core service clean and decoupled.

In addition to this :
A separate Spring Boot project named CardTransactionsService has been created to validate and test external API integrations.
This project is used to simulate and verify communication with third-party services before integrating them into the core Cards Management Service.

# LOGGING

* Log4j2-based logging

# Testing

* **Postman collection provided for API testing**

A Postman collection is available in the **root directory of the project (same level as `pom.xml`)**. This collection can be used to quickly test all exposed APIs without additional setup.

## 📄 Configuration

Update application properties as needed:

```
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=TESTDB;TrustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=system123#
```
