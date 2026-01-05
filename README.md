# U-Man Healthcare Platform

A comprehensive healthcare management platform built with Spring Boot that connects patients with healthcare professionals. The platform provides appointment booking, payment processing, document management, and communication features.

## 🚀 Features

### Core Functionality
- **User Management**
  - Patient and Professional user registration and authentication
  - JWT-based authentication
  - OAuth 2.0 integration (Google, Facebook)
  - User profile management with photo uploads
  - Role-based access control (RBAC)

- **Appointment Booking System**
  - Calendar-based appointment scheduling
  - Slot management and availability tracking
  - Booking status management
  - Real-time availability updates

- **Professional Profiles**
  - Healthcare professional profile management
  - Specialization and medical domain tracking
  - Experience and qualifications management
  - Opening hours configuration
  - Pricing information

- **Payment Processing**
  - Stripe payment integration
  - Payment intent creation
  - Subscription management
  - Bank information management

- **Document Management**
  - Medical document storage
  - Document type categorization
  - Secure document access

- **Review & Rating System**
  - Patient reviews for professionals
  - Rating management
  - Review analytics

- **Search Functionality**
  - Advanced search for healthcare professionals
  - Filter by specialization, location, and availability
  - Search result optimization

- **Communication**
  - Conversation management
  - Messaging system
  - Email notifications

- **Statistics & Analytics**
  - Patient statistics
  - Professional statistics
  - Platform analytics

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot 3.1.4
- **Java Version**: 17
- **Build Tool**: Maven
- **Security**: Spring Security with JWT
- **OAuth**: Spring OAuth2 Client

### Databases
- **Primary Database**: MySQL 8.2.0
- **Document Storage**: MongoDB 4.9.1
- **Caching**: Redis

### Additional Technologies
- **API Documentation**: SpringDoc OpenAPI (Swagger)
- **Object Mapping**: MapStruct 1.5.5
- **Payment Processing**: Stripe API
- **Email Service**: JavaMail API
- **Natural Language Processing**: Apache OpenNLP
- **Validation**: Jakarta Validation API
- **Migration**: Mongock 5.4.0

### Deployment
- **Containerization**: Docker
- **Cloud Platform**: AWS Elastic Beanstalk
- **Web Server**: Nginx

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- Java 17 or higher
- Maven 3.6+ 
- MySQL 8.0+ (or compatible database)
- MongoDB 4.9+ 
- Redis (optional, for caching)
- Docker (optional, for containerized deployment)

## 🔧 Configuration

### Application Properties

Create an `application-local.properties` file in `src/main/resources/` with the following configuration:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/your_database
health.mongo.host=localhost
health.mongo.port=27017
health.mongo.db=your_database

# JWT Configuration
wellink.app.jwtSecret=your_jwt_secret_key
wellink.app.jwtExpirationMs=86400000
wellink.app.jwtCookieName=wellink

# OAuth2 Configuration
spring.security.oauth2.client.registration.google.client-id=your_google_client_id
spring.security.oauth2.client.registration.google.client-secret=your_google_client_secret
spring.security.oauth2.client.registration.facebook.client-id=your_facebook_client_id
spring.security.oauth2.client.registration.facebook.client-secret=your_facebook_client_secret

# Stripe Configuration
stripe.secretKey=your_stripe_secret_key
stripe.publicKey=your_stripe_public_key

# Email Configuration
spring.mail.host=your_smtp_host
spring.mail.port=587
spring.mail.username=your_email
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# File Upload Configuration
spring.servlet.multipart.max-file-size=256MB
spring.servlet.multipart.max-request-size=256MB
spring.servlet.multipart.enabled=true
```

**Note**: Never commit sensitive configuration files to version control. Use environment variables or secure configuration management in production.

## 🏃 Running the Application

### Local Development

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd health
   ```

2. **Set up databases**
   - Create MySQL database
   - Ensure MongoDB is running
   - Update connection strings in `application-local.properties`

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   Or using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

   On Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

5. **Access the application**
   - API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html
   - API Documentation: http://localhost:8080/v3/api-docs

### Docker Deployment

1. **Build the Docker image**
   ```bash
   docker build -t uman-healthcare .
   ```

2. **Run the container**
   ```bash
   docker run -p 8080:8080 uman-healthcare
   ```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/healthcare/uman/
│   │       ├── annotation/          # Custom annotations
│   │       ├── config/              # Configuration classes
│   │       ├── controller/          # REST controllers
│   │       ├── dto/                 # Data Transfer Objects
│   │       ├── exception/           # Custom exception handlers
│   │       ├── mapper/              # MapStruct mappers
│   │       ├── model/                # Entity models
│   │       ├── repository/          # Data access layer
│   │       ├── security/            # Security configurations
│   │       ├── service/             # Business logic layer
│   │       ├── utils/               # Utility classes
│   │       └── validator/           # Custom validators
│   └── resources/
│       ├── application.properties   # Default configuration
│       └── templates/               # Email templates
└── test/                            # Test files
```

## 🔌 API Endpoints

The application provides RESTful APIs organized by domain:

- `/api/v1/users` - User management endpoints
- `/api/v1/bookings` - Booking management endpoints
- `/api/v1/pro` - Professional profile endpoints
- `/api/v1/payment` - Payment processing endpoints
- `/api/v1/calendar` - Calendar and availability endpoints
- `/api/v1/documents` - Document management endpoints
- `/api/v1/reviews` - Review and rating endpoints
- `/api/v1/search` - Search endpoints
- `/api/v1/stats` - Statistics endpoints

Full API documentation is available via Swagger UI when the application is running.

## 🔒 Security

- JWT token-based authentication
- OAuth2 integration for social login
- Role-based access control (RBAC)
- Password encryption
- CORS configuration
- SQL injection prevention via JPA
- Input validation

## 🧪 Testing

Run tests using Maven:

```bash
mvn test
```

## 📦 Building for Production

1. **Create a production build**
   ```bash
   mvn clean package -Pprod
   ```

2. **The JAR file will be created in the `target/` directory**

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is private and proprietary.

## 👤 Author

**Muhammad Mansoor**

## 🙏 Acknowledgments

- Spring Boot community
- All contributors and open-source libraries used in this project

## 📞 Support

For support, please open an issue in the repository or contact the development team.

---

**Note**: This is a portfolio project demonstrating full-stack healthcare platform development with Spring Boot.

