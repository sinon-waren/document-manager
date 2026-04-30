# 📌 Document Management API

A RESTful API built with Spring Boot for managing requests (Demande).

---

# 🚀 Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 / PostgreSQL
- Mockito & MockMvc (testing)
- JaCoCo (code coverage)
- GitHub Actions (CI/CD)

---

# 🧱 Architecture

The application follows a clean layered architecture:

- **Controller** → Handles REST API endpoints
- **Service** → Contains business logic
- **Repository** → Handles data persistence
- **DTO** → Separates API layer from Entity layer
- **Exception Handling** → Global error handling using `@ControllerAdvice`

---

# ⚙️ Features

- ✅ Create a request
- ✅ Retrieve all requests
- ✅ Retrieve a request by ID
- ✅ Update a request
- ✅ Delete a request

---

# 🧠 Business Rules

- The `status` is automatically set to `EN_COURS` upon creation
- The `fillingDate` is automatically set to the current date
- The `status` cannot be modified via the API

---

# 🧪 Testing

- Unit tests using Mockito (service layer)
- Integration tests using MockMvc (controller layer)
- Covers both positive and negative scenarios (400, 404, etc.)

---

# 📊 Code Coverage

Code coverage is measured using JaCoCo.

```bash
mvn clean test