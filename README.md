# 📧 User & Email Microservices - Java Spring Boot

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/springboot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-FF6600.svg?style=for-the-badge&logo=rabbitmq&logoColor=white)

This project contains two microservices: one for user registration and another for sending email notifications. When a user is registered, an email is automatically sent using RabbitMQ as the message broker.

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Mail
- Spring AMQP (RabbitMQ)
- PostgreSQL
- Maven

---

## Microservices

### 1. UserMS (Port: 8081)
- Registers users
- Publishes messages to RabbitMQ

### 2. EmailMS (Port: 8082)
- Listens to messages from RabbitMQ
- Sends emails using Gmail SMTP
- Saves email status to PostgreSQL

---

## ⚙️ How to Run Locally

### Requirements

- PostgreSQL running locally with two databases named ms-email and ms-user
- RabbitMQ instance (CloudAMQP or local)
- Gmail account with an app password

---

### 1️⃣ Clone and configure the projects

```bash
git clone https://github.com/bruno2leite/email-MS.git
```

### 2️⃣ Configure the `application.properties` files

#### UserMS (`src/main/resources/application.properties`)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ms-user
spring.datasource.username=your-db-username
spring.datasource.password=your-db-password
spring.jpa.hibernate.ddl-auto=update

spring.rabbitmq.addresses=amqps://<user>:<password>@<host>.cloudamqp.com/<vhost>
broker.queue.email.name=default.email
```

#### EmailMS (`src/main/resources/application.properties`)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ms-email
spring.datasource.username=your-db-username
spring.datasource.password=your-db-password
spring.jpa.hibernate.ddl-auto=update

spring.rabbitmq.addresses=amqps://<user>:<password>@<host>.cloudamqp.com/<vhost>
broker.queue.email.name=default.email

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-your-email@email.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

---

### Start the microservices

In two separate terminals:

```bash
# In the UserMS directory
mvn spring-boot:run

# In the EmailMS directory
mvn spring-boot:run
```

---

## Testing

Use Postman or Insomnia to send a `POST` request:

### ➕ Create User (UserMS)
`POST http://localhost:8081/users`

```json
{
  "name": "Name",
  "email": "your-email@email.com"
}
```

✅ The user will be saved and an email will be sent via EmailMS.

---

## 🛠️ Troubleshooting

- Make sure your Gmail app password is correct
- Ensure RabbitMQ is active and queue names match

---

## 👨‍💻 Author

Developed by **Bruno Leite**  
🔗 [linkedin.com/in/brunoprestesleite](https://www.linkedin.com/in/brunoprestesleite)
