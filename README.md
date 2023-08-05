# loginRegistrationV3

User Registration and Login Application This application is built using Spring Boot, providing a seamless and secure user registration and login system. The application leverages MySQL as the backend database for data storage, Thymeleaf and Bootstrap as the templating engine for dynamic web pages, and Spring Boot Security to ensure robust authentication and authorization mechanisms.

Key Features:

User Registration: Allows new users to create accounts by providing necessary information securely. 
User Login: Registered users can log in using their credentials to access the application's features. 
MySQL Database: Stores user information in a reliable and scalable MySQL database. 
Thymeleaf and Bootstrap: Employs Thymeleaf and Bootstrap to create dynamic and user-friendly web pages.
Spring Boot Security: Ensures that user authentication and authorization are handled efficiently and securely. With this application, users can seamlessly register, login, and access the provided services, all while ensuring their information and actions are protected by modern security standards.

Instructions:
1. Created application.properties (in resources)
2. Input this, and create a SQL database called loginRegistrationDXC
3. Input your username and password for your MySQL database
4. Import as Maven project into Eclipse/your editor,
5. Run the file: RegistrationWebAppApplication.java


spring.datasource.url=jdbc:mysql://localhost:3306/loginRegistrationDXC
spring.datasource.username=
spring.datasource.password=

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type=TRACE
