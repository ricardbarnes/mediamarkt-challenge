# MediaMarkt Coding Challenge

This project is a Java 21 + Gradle application built as part of the MediaMarkt coding challenge.

It follows a **Domain-Driven Design (DDD)** approach combined with **Hexagonal Architecture (Ports & Adapters)** to ensure a clear separation of concerns, testability, and maintainability.

---

## 🧱 Architecture

The project is structured using:

### Domain-Driven Design (DDD)
- Core business logic is isolated in the **domain layer**
- Aggregates, entities, value objects, and domain services are pure and framework-agnostic

### Hexagonal Architecture
- **Domain** → business rules (no external dependencies)
- **Application layer** → use cases (orchestrates domain logic)
- **Infrastructure layer** → external systems (database, APIs, file systems)
- **Adapters** → controllers, persistence, messaging, etc.

This ensures that frameworks (Spring, JPA, etc.) are kept at the edges of the system.

---

## 🛠 Tech Stack

- Java 21
- Spring Boot 3.4.x
- Gradle (Kotlin DSL)
- Spring Web
- Spring Data JPA
- H2 Database (runtime)
- Apache POI (Excel processing)
- Lombok
- JUnit 5

---

## 📦 Requirements

Make sure you have installed:

- Java 21 (JDK)
- Gradle (optional — wrapper is included)

Check versions:

```bash
java -version
./gradlew -version
```

---

## 🚀 How to Run the Project

1. Clone the repository
git clone <repository-url>
cd <project-folder>
2. Run with Gradle Wrapper
Linux / macOS
./gradlew bootRun
Windows
gradlew.bat bootRun
3. Build the project
./gradlew build
This will:
Compile the code
Run all tests
Package the application
4. Run tests only
./gradlew test
5. Run the app (nix)
```bash
./gradlew bootRun
```

## 🗄 Database

The project uses an H2 in-memory database by default.

You can access the H2 console (if enabled in configuration):

http://localhost:8080/h2-console

Typical settings:

JDBC URL: jdbc:h2:mem:mediamarkt;DB_CLOSE_DELAY=-1
User: sa
Password: (empty)

## 🧪 Testing Strategy
Unit tests focus on the domain layer
Application layer is tested via use-case orchestration tests
Infrastructure components are tested separately when needed

## 📐 Design Principles

This project follows:

Domain purity (no framework leakage into domain)
Dependency inversion (outer layers depend on inner layers)
Explicit boundaries between layers
Testability by design
SOLID principles

## 📁 Project Structure (Conceptual)
domain/         → core business logic
application/    → use cases
infrastructure/ → database, external systems
adapters/       → REST controllers, persistence adapters

## 📌 Notes
This project prioritizes architecture clarity over framework convenience.
Spring Boot is used only as a delivery mechanism, not as a driver of design.