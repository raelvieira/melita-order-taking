# 🌐 Melita Order Taking Stack

## 👨‍💻 Technologies used

- [Java](https://www.java.com/pt_BR/download/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Flyway](https://flywaydb.org/)
- [MySQL](https://www.mysql.com/)
- [Docker](https://www.docker.com/products/docker-desktop)
- [Swagger](https://swagger.io/)
- [Junit 5](https://junit.org/junit5/)
- [Lombok](https://projectlombok.org/)
- [SendGrid](https://sendgrid.com/)
- [RabbitMQ](https://www.rabbitmq.com/)

## 🤔 How it works.

Putting the services to work is very easy, you just need to run this command ```docker-compose up -d --build``` in the root folder of the project.

Watch out! Before running everything make sure that ports **3306, 5672, 15672, 8080, 8081, 8083, 8089 and 9090** are free on your computer.

A code snippet that sends e-mail in the** ordering-fulfilment** service was commented, because you need to add your password and e-mail configured from the **SendGrid** service, if you want to modify it just add the information in the environment variables of the service:

`email.agent.receiving=CONFIGURE_YOUR_EMAIL`
`spring.mail.password=CONFIGURE_YOUR_PASSWORD`

### ℹ️ Useful information

In the *root folder* of the project there is a **postman** file with some project routes.

The service has **OAuth2** authentication, therefore, before making a new request, make sure that you have authenticated and are passing the Token `bearer` in the request.

------------
by Israel Vieira 👋 [Contact me by Linkedin!](https://www.linkedin.com/in/israelvieiraa/)
