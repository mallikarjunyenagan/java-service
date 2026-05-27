# Spring Boot PDF Generator using Thymeleaf + OpenHTMLToPDF

This project demonstrates how to generate dynamic PDF reports in Spring Boot using:

- Spring Boot
- Thymeleaf
- OpenHTMLToPDF
- HTML/CSS Templates

The application generates a Student Report PDF dynamically from Java objects.

---

# Features

- Dynamic PDF generation
- HTML template support
- Thymeleaf integration
- OpenHTMLToPDF conversion
- REST API endpoint
- Clean report layout
- Downloadable PDF response

---

# Tech Stack

| Technology | Version |
|---|---|
| Java | 17+ |
| Spring Boot | 3.x |
| Thymeleaf | Latest |
| OpenHTMLToPDF | 1.0.10 |
| Maven | 3.x |

---

# Project Structure

```text
src
 └── main
      ├── java
      │    └── com/school/mgmt/backend
      │          ├── controller
      │          ├── config
      │          ├── service
      │          ├── dto
      │          └── BackendApplication.java
      │
      └── resources
           ├── templates
           │     └── student_report.html
           │
           └── application.yml