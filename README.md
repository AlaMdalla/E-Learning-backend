# E-Learning Backend

This is the backend for the **E-Learning** platform, built using **Spring Boot** to handle RESTful APIs and business logic. It provides services to manage courses, users, and competitions efficiently.

---

## 📚 Features

- **RESTful API**: Clean and structured API endpoints for all backend operations.
- **Entity Management**: Handles user, course, and competition data.
- **SOLID Architecture**: Adheres to clean code principles with layers for controllers, services, and repositories.
- **Database Integration**: Uses **MySQL** as the primary database with JPA for ORM.
- **Scalability**: Designed to support new features without breaking existing functionality.

---

## 🛠️ Technologies Used

- **Spring Boot**: Framework for building the application.
- **Spring Data JPA**: For database operations.
- **MySQL**: Relational database.
- **MapStruct**: For DTO mapping.
- **Swagger**: API documentation and testing

---

## 📂 Architecture Overview

The backend is structured following a **layered architecture**:

- **Controller Layer**: Handles HTTP requests and maps them to service methods.
- **Service Layer**: Contains the business logic and coordinates data flow between the controllers and repositories.
- **Repository Layer**: Interfaces directly with the database using **Spring Data JPA**.
- **Entity Layer**: Defines the database models and their relationships.
- **DTO Layer**: Facilitates data exchange between APIs and services.

---

## 🚀 Setup and Installation

Follow these steps to run the project locally:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/AlaMdalla/E-Learning-backend.git

2. **cd e-learning-backend**:
    ```bash
   cd e-learning-backend
   
3. **Run the application**:
    ```bash
   ./mvnw spring-boot:run


---

## 📖 API Documentation
http://localhost:8082/swagger-ui/index.html#/


