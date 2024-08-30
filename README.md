# Nimap Machine Test

## Project Overview

This project is a Spring Boot application for managing `Category` and `Product` entities with CRUD operations and relational mappings. The application uses JPA & Hibernate for ORM and includes server-side pagination.

## Features

1. **Category CRUD Operations:**
   - **GET** `/api/categories?page={page}`: Fetch all categories with pagination.
   - **POST** `/api/categories`: Create a new category.
   - **GET** `/api/categories/{id}`: Fetch a category by ID.
   - **PUT** `/api/categories/{id}`: Update an existing category by ID.
   - **DELETE** `/api/categories/{id}`: Delete a category by ID.

2. **Product CRUD Operations:**
   - **GET** `/api/products?page={page}`: Fetch all products with pagination.
   - **POST** `/api/products`: Create a new product.
   - **GET** `/api/products/{id}`: Fetch a product by ID.
   - **PUT** `/api/products/{id}`: Update an existing product by ID.
   - **DELETE** `/api/products/{id}`: Delete a product by ID.

3. **Category-Product Relationship:**
   - One-to-many relationship: A category can have multiple products.

4. **Pagination:**
   - Server-side pagination is implemented for both categories and products.

5. **Product Details:**
   - Fetching product details includes the associated category information.

## Project Setup

### Prerequisites

- Java 17 or later
- MySQL Database
- Maven (for dependency management)

### Configuration

1. **Database Setup:**

   Create a MySQL database and configure the connection details in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/nimap
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
Starting the Application:

To start the application, run the following Maven command:

mvn spring-boot:run
The application will start on http://localhost:8080.

API Endpoints
Categories
GET /api/categories?page={page}

Fetch all categories with pagination. Example:

curl -X GET "http://localhost:8080/api/categories?page=3"
POST /api/categories

Create a new category. Example request body:


{
  "name": "Electronics"
}
GET /api/categories/{id}


Fetch a category by ID. Example:

curl -X GET "http://localhost:8080/api/categories/1"
PUT /api/categories/{id}
Update an existing category by ID. Example request body:
{
  "name": "Updated Category Name"
}


DELETE /api/categories/{id}
Delete a category by ID. Example:
curl -X DELETE "http://localhost:8080/api/categories/1"

Products:

GET /api/products?page={page}
Fetch all products with pagination. Example:
curl -X GET "http://localhost:8080/api/products?page=2"


POST /api/products
Create a new product. Example request body:
{
  "name": "Smartphone",
  "price": 299.99,
  "category": {
    "id": 1
  }
}


GET /api/products/{id}

Fetch a product by ID. Example:
curl -X GET "http://localhost:8080/api/products/1"
PUT /api/products/{id}


Update an existing product by ID. Example request body:
{
  "name": "Updated Smartphone",
  "price": 349.99,
  "category": {
    "id": 1
  }
}

DELETE /api/products/{id}

Delete a product by ID. Example:
curl -X DELETE "http://localhost:8080/api/products/1"

Error Handling
500 Internal Server Error: Indicates an issue with the server or application. Check the application logs for details.
Troubleshooting
Foreign Key Constraint Issues: If you encounter foreign key constraint errors, ensure that the referenced Category exists before creating or updating Product entries.
Contributing
Feel free to contribute by submitting issues or pull requests. Ensure that your contributions are well-documented and tested.

License
This project is licensed under the MIT License.

For any questions or additional help, please contact Harshal Kishor Patil.
hkpatil2002@gmail.com

This `README.md` file provides an overview, setup instructions, usage examples, and troubleshooting tips to help others understand and work with your project.
