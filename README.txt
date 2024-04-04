Steps to test the application:

- Create the "mvp_hotel_booking" database
--> See: /src/test/resources/db/00_create_database.sql

- Configure the "spring.datasource.*" (and eventually adjust "spring.jpa.*") properties
--> Main property file: /src/main/resources/application.properties

- Run the project as a Spring Boot application
--> It will automatically create the tables from the entities schema

- Insert the test data the newly created tables
--> Execute the SQL queries in: /src/test/resources/db/02_insert_test_data.sql

- Test the REST API endpoints
--> Request examples in: /src/test/resources/test_commands.txt

- You can also run the JUnit tests

Thank you.
