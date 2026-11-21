# Full Stack Java Application

A modern, scalable, end-to-end **Full Stack Java Web Application** built with **Java (Spring Boot)** on the backend, **RESTful APIs**, a relational database (**MySQL / PostgreSQL**), and a responsive web frontend (**HTML5, CSS3, JavaScript / React / Thymeleaf**), following a clean **Layered Architecture (Controller – Service – Repository – Model)**.

---

## Table of Contents

1. [Project Overview & Architecture](#1-project-overview--architecture)
2. [Tech Stack](#2-tech-stack)
3. [Directory Structure](#3-directory-structure)
4. [Database Prerequisites & Schema](#4-database-prerequisites--schema)
5. [Environment & Application Configuration](#5-environment--application-configuration)
6. [Installation & Setup](#6-installation--setup)
7. [Running the Application](#7-running-the-application)
8. [API Specification & Endpoints](#8-api-specification--endpoints)
9. [Running Automated Tests](#9-running-automated-tests)
10. [Pushing & Reflecting Changes on GitHub](#10-pushing--reflecting-changes-on-github)

---

## 1. Project Overview & Architecture

This project implements a clean, decoupled **N-Tier / Layered Architecture** ensuring maintainability, testability, and clear separation of concerns:

| Layer | Package / Folder | Responsibilities | Restrictions |
| :--- | :--- | :--- | :--- |
| **Presentation (Frontend)** | `frontend/` or `src/main/resources/static/` | Render responsive UI views, capture user inputs, consume REST APIs via `fetch` / Axios | Must **NOT** access the database directly |
| **Web / Controller** | `controller/` | Expose REST endpoints, validate request payloads (`@Valid`), map HTTP status codes | Must **NOT** contain business logic or raw SQL |
| **Business / Service** | `service/` | Implement core business rules, handle transactions (`@Transactional`), map DTOs | Must **NOT** depend on HTTP request/response objects |
| **Data Access / Repository** | `repository/` | Perform CRUD and custom queries via Spring Data JPA / JDBC | Must **NOT** contain business or UI logic |
| **Domain / Entity & DTO** | `model/`, `dto/` | Represent database tables (`@Entity`) and API data transfer objects | Keep entities decoupled from external API contracts |

---

## 2. Tech Stack

* **Backend:** Java 17+, Spring Boot 3.x (Spring Web, Spring Data JPA, Spring Validation)
* **Frontend:** HTML5, CSS3, Bootstrap 5 / Tailwind CSS, JavaScript (ES6+) / React
* **Database:** PostgreSQL 15+ / MySQL 8+
* **Build & Dependency Tool:** Apache Maven (`pom.xml`) / Gradle
* **Testing:** JUnit 5, Mockito, Spring Boot Test (`MockMvc`)
* **Version Control:** Git & GitHub

---

## 3. Directory Structure

```text
full-stack-java-project/
├── .gitignore
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/example/fullstackapp/
    │   │   ├── FullStackApplication.java      # Main Spring Boot entry point
    │   │   ├── config/                        # CORS, Security & Web configurations
    │   │   ├── controller/                    # REST API controllers (@RestController)
    │   │   ├── service/                       # Business logic interfaces & implementations
    │   │   ├── repository/                    # Spring Data JPA repositories
    │   │   ├── model/                         # JPA Entity classes (@Entity)
    │   │   ├── dto/                           # Request & Response DTOs
    │   │   └── exception/                     # Global exception handler (@RestControllerAdvice)
    │   └── resources/
    │       ├── application.properties         # Database & server configuration
    │       ├── static/                        # Frontend CSS, JS, and assets
    │       └── templates/                     # HTML views (if using server-side templates)
    └── test/
        └── java/com/example/fullstackapp/     # Unit & integration tests
```

---

## 4. Database Prerequisites & Schema

Ensure your relational database server is running and create the application database:

```sql
CREATE DATABASE fullstack_java_db;
```

### Example Core Tables

```sql
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS items (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## 5. Environment & Application Configuration

Configure database credentials and server settings in `src/main/resources/application.properties` (or via environment variables so sensitive passwords are never hardcoded):

```properties
# Server Configuration
server.port=8080

# Database Configuration (PostgreSQL Example)
spring.datasource.url=jdbc:postgresql://localhost:5432/fullstack_java_db
spring.datasource.username=${DB_USER:postgres}
spring.datasource.password=${DB_PASSWORD:your_password}
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate Settings
spring.jpa.hibernate.auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 6. Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/<YOUR_USERNAME>/<YOUR_JAVA_REPO>.git
   cd <YOUR_JAVA_REPO>
   ```

2. **Verify Java & Maven versions:**
   ```bash
   java -version
   mvn -version
   ```

3. **Build the project and install dependencies:**
   ```bash
   mvn clean install
   ```

---

## 7. Running the Application

1. **Start the Spring Boot backend & web server:**
   ```bash
   mvn spring-boot:run
   ```

2. **Access the application:**
   * **Frontend UI:** `http://localhost:8080/`
   * **REST API Base URL:** `http://localhost:8080/api`

---

## 8. API Specification & Endpoints

### Global Conventions
* **Content-Type:** `application/json`
* **Error Format:** `{"timestamp": "...", "status": 400, "error": "Bad Request", "message": "Validation failed"}`

### Endpoint Reference

| Method | Endpoint | Description | Status Codes |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/health` | Check application and database health | `200`, `500` |
| `GET` | `/api/items` | Retrieve all records (supports pagination & filtering) | `200` |
| `GET` | `/api/items/{id}` | Retrieve a single record by ID | `200`, `404` |
| `POST` | `/api/items` | Create a new record | `201`, `400`, `422` |
| `PUT` | `/api/items/{id}` | Update an existing record | `200`, `400`, `404` |
| `DELETE` | `/api/items/{id}` | Delete a record by ID | `204`, `404` |

---

## 9. Running Automated Tests

Execute the JUnit and Spring Boot test suite using Maven:

```bash
mvn test
```

---

## 10. Pushing & Reflecting Changes on GitHub

To ensure `README.md` renders cleanly on GitHub without line-ending issues:

1. **Download or save `README.md`** into the root of your Java project folder with **`LF`** line endings.
2. **Sync, commit, and push from your project terminal:**
   ```powershell
   git pull --rebase origin main
   git add README.md
   git commit -m "Add structured README.md for Full Stack Java Project"
   git push origin main
   ```
3. **Refresh your GitHub repository page** in the browser to confirm the formatted `README.md` appears on the main page.

Github Link : [@https://github.com/saswata35/Java_Based_Enterprise_Taskflow_Persistence_Engine.git](https://github.com/saswata35/Java_Based_Enterprise_Taskflow_Persistence_Engine.git)
