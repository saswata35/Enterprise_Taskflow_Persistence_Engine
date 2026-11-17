# Enterprise_Taskflow_Persistence_Engine

Enterprise TaskFlow & Persistence EngineAn enterprise-grade, full-stack task management and real-time audit logging application built with Spring Boot, PostgreSQL, and a modern Bootstrap 5 frontend dashboard. This system features strict database persistence, ACID transactions, and a dedicated database operations audit trail tracking every INSERT, UPDATE, and DELETE action.🛠️ Tech Stack & ArchitectureBackend: Java, Spring Boot, Spring Data JPA, RESTful API ArchitectureDatabase: PostgreSQL (Managed via pgAdmin & psql CLI)Frontend: HTML5, CSS3, JavaScript (ES6+), Bootstrap 5, Bootstrap IconsVersion Control: Git & GitHub📊 Database SchemaThe application uses two core relational tables configured in PostgreSQL:1. tasks Table (Core Entity)id: BIGSERIAL (Primary Key)title: VARCHAR(255) (Not Null)completed: BOOLEAN (Default: FALSE)2. task_audit_log Table (Audit Trail)id: BIGSERIAL (Primary Key)operation_type: VARCHAR(50) (INSERT, UPDATE, DELETE)details: TEXT (Operation description)timestamp: TIMESTAMP (Automatic logging timestamp)✨ Key FeaturesInteractive Task Management Dashboard: Add, complete, and delete tasks dynamically without full page reloads.Real-Time Statistics Counter: Live tracking of Total, Completed, and Pending tasks.Automated Database Audit Trail: Every transaction (INSERT, UPDATE, DELETE) automatically writes a detailed audit log entry into PostgreSQL.Multi-Tab Interface: Switch effortlessly between the Task Manager workspace and the Database Operations Log view.Enterprise UI Styling: Styled with gradients, card shadows, custom hover animations, and Bootstrap 5 components.⚙️ Getting Started & PrerequisitesMake sure you have the following installed on your local machine:Java JDK (17 or higher)PostgreSQL 17+ & pgAdmin 4Eclipse IDE or any Java-compatible IDE with Maven support1. Database SetupOpen your PostgreSQL terminal (psql) or pgAdmin and run the following script:CREATE DATABASE task_db;

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
2. Configure application.propertiesNavigate to src/main/resources/application.properties and verify your local PostgreSQL credentials:spring.datasource.url=jdbc:postgresql://localhost:5432/task_db
spring.datasource.username=postgres
spring.datasource.password=your_secure_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

server.port=8083
🏃 Running the ApplicationClone the repository or open the project folder inside Eclipse IDE.Locate DemoApplication.java (src/main/java/com/example/demo/DemoApplication.java).Right-click -> Run As -> Java Application.Open your web browser and navigate to:http://localhost:8083
📡 REST API EndpointsMethodEndpointDescriptionGET/api/tasksRetrieve all tasks from PostgreSQLPOST/api/tasksCreate a new task & record INSERT audit logPUT/api/tasks/{id}Update task status & record UPDATE audit logDELETE/api/tasks/{id}Permanently delete task & record DELETE audit logGET/api/auditRetrieve complete database audit history👨‍💻 AuthorSaswata BanerjeeGitHub: @saswata35
