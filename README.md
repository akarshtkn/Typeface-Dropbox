# Typeface-Dropbox
## Frontend
This is a frontend application built with **React** and **TypeScript**. It utilizes **React Router** for navigation, **Axios** for API calls, and **Tailwind CSS** for styling.

To get started with the project, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/akarshtkn/Typeface-Dropbox
   cd Typeface-Dropbox/frontend

2. **Install Dependencies**:
    ```bash
    npm install

3. **Start the developement server**:
    ```bash
    npm run dev

The application will be available at http://localhost:5173 by default.


## Backend
This is a backend application built with **Spring Boot** and **Java**. It provides the API for our full-stack application.

To get started with the project, follow these steps:

1. **Install PostgreSQL**: Make sure you have PostgreSQL installed on your machine. You can download it from the official website: https://www.postgresql.org/download

2. **Create the database**: Open a PostgreSQL client (e.g., pgAdmin) and create a new database named `dropbox`.

3. **Clone the repository**:
   ```bash
   git clone https://github.com/akarshtkn/Typeface-Dropbox
   cd Typeface-Dropbox/backend

4. **Configure the database connection**: Open the application.properties file located in src/main/resources and update the following properties with your PostgreSQL credentials:
    ``bash
    spring.datasource.url=jdbc:postgresql://localhost:5432/dropbox
    spring.datasource.username=<your-username>
    spring.datasource.password=y<our-password>

5. **Build the project**: Drom the 'backend' directory
    ```bash
    ./mvnw clean install

6. **Run the application**