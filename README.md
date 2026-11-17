# 🚀 Enterprise TaskFlow & Persistence Engine

An enterprise-grade, full-stack task management and real-time audit logging application built with **Spring Boot**, **PostgreSQL**, and a modern **Bootstrap 5** frontend dashboard. This system features strict database persistence, ACID transactions, and a dedicated database operations audit trail tracking every `INSERT`, `UPDATE`, and `DELETE` action.

---

## 🛠️ Tech Stack & Architecture

* **Backend:** Java, Spring Boot, Spring Data JPA, RESTful API Architecture
* **Database:** PostgreSQL (Managed via pgAdmin & `psql` CLI)
* **Frontend:** HTML5, CSS3, JavaScript (ES6+), Bootstrap 5, Bootstrap Icons
* **Version Control:** Git & GitHub

---

## 📊 Database Schema

The application uses two core relational tables configured in PostgreSQL:

### 1. `tasks` Table (Core Entity)
* `id`: `BIGSERIAL` (Primary Key)
* `title`: `VARCHAR(255)` (Not Null)
* `completed`: `BOOLEAN` (Default: `FALSE`)

### 2. `task_audit_log` Table (Audit Trail)
* `id`: `BIGSERIAL` (Primary Key)
* `operation_type`: `VARCHAR(50)` (`INSERT`, `UPDATE`, `DELETE`)
* `details`: `TEXT` (Operation description)
* `timestamp`: `TIMESTAMP` (Automatic logging timestamp)

---

## ✨ Key Features

* **Interactive Task Management Dashboard:** Add, complete, and delete tasks dynamically without full page reloads.
* **Real-Time Statistics Counter:** Live tracking of Total, Completed, and Pending tasks.
* **Automated Database Audit Trail:** Every transaction (`INSERT`, `UPDATE`, `DELETE`) automatically writes a detailed audit log entry into PostgreSQL.
* **Multi-Tab Interface:** Switch effortlessly between the **Task Manager** workspace and the **Database Operations Log** view.
* **Enterprise UI Styling:** Styled with gradients, card shadows, custom hover animations, and Bootstrap 5 components.

---

## ⚙️ Getting Started & Prerequisites

Make sure you have the following installed on your local machine:
* Java JDK (17 or higher)
* PostgreSQL 17+ & pgAdmin 4
* Eclipse IDE or any Java-compatible IDE with Maven support

### 1. Database Setup
Open your PostgreSQL terminal (`psql`) or pgAdmin and run the following script:
```sql
CREATE DATABASE task_db;

\c task_db;

CREATE TABLE IF NOT EXISTS tasks (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    completed BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS task_audit_log (
    id BIGSERIAL PRIMARY KEY,
    operation_type VARCHAR(50) NOT NULL,
    details TEXT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 2. Configure `application.properties`
Navigate to `src/main/resources/application.properties` and verify your local PostgreSQL credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/task_db
spring.datasource.username=postgres
spring.datasource.password=your_secure_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

server.port=8083
```

---

## 🏃 Running the Application

1. Clone the repository or open the project folder inside **Eclipse IDE**.
2. Locate `DemoApplication.java` (`src/main/java/com/example/demo/DemoApplication.java`).
3. Right-click -> **Run As** -> **Java Application**.
4. Open your web browser and navigate to:
   ```text
   http://localhost:8083
   ```

---

## 📡 REST API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/tasks` | Retrieve all tasks from PostgreSQL |
| `POST` | `/api/tasks` | Create a new task & record `INSERT` audit log |
| `PUT` | `/api/tasks/{id}` | Update task status & record `UPDATE` audit log |
| `DELETE` | `/api/tasks/{id}` | Permanently delete task & record `DELETE` audit log |
| `GET` | `/api/audit` | Retrieve complete database audit history |

---

## 👨‍💻 Author

* **Saswata Banerjee**
* GitHub: [@saswata35/Enterprise_Taskflow_Persistence_Engine.git](https://github.com/saswata35/Enterprise_Taskflow_Persistence_Engine.git)
