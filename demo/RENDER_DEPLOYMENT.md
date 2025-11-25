# TP7 - Spring Boot Application

A Spring Boot REST API application with PostgreSQL database and Flyway migrations.

## Features

- ✅ Spring Boot 3.5.7 with Java 17
- ✅ PostgreSQL Database Integration
- ✅ Flyway Database Migrations
- ✅ RESTful API for Student Management
- ✅ Docker Support
- ✅ GitHub Actions CI/CD
- ✅ Ready for Render Deployment

## Project Structure

```
demo/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── Tp7Application.java (Main Entry Point)
│   │   │   ├── controller/StudentController.java
│   │   │   ├── model/Student.java
│   │   │   ├── repository/StudentRepository.java
│   │   │   └── service/StudentService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/V1__Create_Student_Table.sql
│   └── test/
│       └── java/com/example/demo/
│           ├── Controller/ControllerTest.java
│           └── Tp7ApplicationTests.java
├── .github/workflows/ci.yml (GitHub Actions)
├── Dockerfile
├── render.yaml (Render Configuration)
├── pom.xml
└── README.md

```

## Local Development

### Prerequisites

- Java 17+
- Maven 3.9+
- PostgreSQL 12+

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/nadablk/TP07Idl.git
   cd demo
   ```

2. **Create PostgreSQL Database**
   ```bash
   createdb TP02Deploy
   ```

3. **Configure Database Connection**
   - Edit `src/main/resources/application.properties`
   - Set `DB_HOST`, `DB_USER`, `DB_PASSWORD` if different

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   
   The app will start on `http://localhost:8081`

5. **Flyway migrations** will automatically run on startup

### Testing

```bash
mvn clean test
```

## API Endpoints

### Add Student
```
POST http://localhost:8081/student/add
Content-Type: application/json

{
  "name": "John Doe",
  "address": "123 Main St"
}
```

### Get All Students
```
GET http://localhost:8081/student/getAll
```

## Deployment to Render

### Step 1: Connect GitHub Repository

1. Go to [Render Dashboard](https://dashboard.render.com)
2. Click **New +** → **Web Service**
3. Connect your GitHub repository
4. Select repository: `nadablk/TP07Idl`

### Step 2: Configure Web Service

- **Name:** `tp7-app`
- **Environment:** Docker
- **Region:** Ohio (or your preference)
- **Branch:** main
- **Build Command:** (Leave empty - Docker builds it)
- **Start Command:** (Leave empty - Docker runs it)
- **Plan:** Free (or Pro)

### Step 3: Set Environment Variables

In Render Dashboard, add these environment variables:

```
DB_HOST=<your-render-postgres-internal-host>
DB_PORT=5432
DB_NAME=TP02Deploy
DB_USER=<your-postgres-user>
DB_PASSWORD=<your-postgres-password>
JAVA_OPTS=-Xmx512m -Xms256m
PORT=8080
```

### Step 4: Create PostgreSQL Database (if not using render.yaml)

1. In Render Dashboard, click **New +** → **PostgreSQL**
2. **Name:** `tp7-postgres`
3. **Database:** `TP02Deploy`
4. **User:** Choose your user
5. **Region:** Same as web service
6. **Plan:** Free (for testing)

### Step 5: Connect Services

Link the PostgreSQL service to the web service in Render and update environment variables with the PostgreSQL connection details.

### Step 6: Deploy

Click **Create Web Service** - Render will automatically:
1. Build Docker image
2. Deploy container
3. Run Flyway migrations
4. Start the application

## GitHub Actions CI/CD

The project includes GitHub Actions workflow (`.github/workflows/ci.yml`) that:

- ✅ Runs on every push to `main` branch
- ✅ Builds with Maven
- ✅ Runs unit tests
- ✅ Generates test reports

View results: https://github.com/nadablk/TP07Idl/actions

## Database Migrations

Flyway migrations are stored in `src/main/resources/db/migration/`

### Current Migrations:
- `V1__Create_Student_Table.sql` - Creates student table with schema

### Adding New Migrations:

1. Create new file: `V2__Your_Migration_Name.sql`
2. Place in `src/main/resources/db/migration/`
3. Version must be sequential (V1, V2, V3, etc.)

Migrations run automatically on application startup.

## Troubleshooting

### Database Connection Issues
- Verify PostgreSQL is running
- Check environment variables are set correctly
- Ensure database exists: `TP02Deploy`

### Port Already in Use
- Change `server.port` in `application.properties`
- Or kill existing process on port 8081

### Flyway Errors
- Check migration files in `src/main/resources/db/migration/`
- Ensure version numbering is sequential

## Tech Stack

- **Framework:** Spring Boot 3.5.7
- **Language:** Java 17
- **Build Tool:** Maven
- **Database:** PostgreSQL 18
- **Migrations:** Flyway 11.7
- **ORM:** Hibernate 6.6
- **Testing:** JUnit 5, AssertJ
- **Containerization:** Docker
- **CI/CD:** GitHub Actions
- **Deployment:** Render

## License

MIT

## Author

nadablk

---

**Ready to deploy?** Follow the [Deployment to Render](#deployment-to-render) section above!
