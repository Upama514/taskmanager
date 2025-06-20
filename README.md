# 📝 Task Manager (Spring Boot + MongoDB + JWT)

A simple and secure Task Management REST API built with Spring Boot, MongoDB, and JWT-based authentication.

---

## 🚀 Features

- User **Registration** & **Login**
- Secure **JWT Authentication**
- **Create, Read, Update, Delete (CRUD)** operations for tasks
- Each user can access only their own tasks

---

## 🧑‍💻 Tech Stack

- **Java 17**
- **Spring Boot**
- **MongoDB**
- **Spring Security**
- **JWT (JSON Web Token)**
- **IntelliJ IDEA**

---

## 📦 API Endpoints

### 🔐 Authentication

- `POST /api/auth/register` – Register a new user
- `POST /api/auth/login` – Login and receive a JWT token

### 📌 Task Management (JWT token required)

- `POST /tasks` – Create a new task
- `GET /tasks` – Get all tasks for the logged-in user
- `GET /tasks/{id}` – Get a specific task by ID
- `PUT /tasks/{id}` – Update a task
- `DELETE /tasks/{id}` – Delete a task

---

## 🛡️ Security

- Passwords are hashed using **BCrypt**
- JWT tokens are validated in each request
- Only authenticated users can access or modify their own tasks

---

## 🧪 Testing the API

Use tools like:

- [Postman](https://www.postman.com/)
- `curl`
- REST Client extensions (e.g., for VS Code)

Send the JWT token in the request header:

```
Authorization: Bearer <your_token_here>
```

---

## 📁 Project Structure

```
src
└── main
    ├── java
    │   └── com.example.taskmanager
    │       ├── controller
    │       ├── model
    │       ├── payload
    │       ├── repository
    │       ├── security
    │       └── service
    └── resources
        └── application.properties
```

---

