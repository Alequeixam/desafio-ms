# CompassUOL-SP-Challenge-03-Squad-1-Shenlong

## Installation

1. Clone the repository:

```bash
git clone https://github.com/Alequeixam/CompassUOL-SP-Challenge-03-Squad-1-Shenlong.git
```

2. Install dependencies with Maven

3. Install [PostgresSQL](https://www.postgresql.org/)

## Usage

1. Start the applications with Maven
2. The API Gateway will be accessible at http://localhost:8080


## API Endpoints
The API provides the following endpoints:

```markdown
GET /categories - Retrieve a list of all categories. 

POST /categories - Create a new category.

PUT /categories/{categoryId} - Update a category by it's id.

DELETE /categories/{categoryId} - Delete a category.


GET /products - Retrieve a list of all products with the option of pagination. 

POST /products - Create a new product.

PUT /products/{productId} - Update a product by it's id.

DELETE /products/{productId} - Delete a product.


GET /users - Retrieve a list of all users. 

POST /users - Create a new user.

PUT /users/{userId} - Update a user by it's id.

DELETE /users/{userId} - Delete a user.


```


## Database
The project utilizes [PostgresSQL](https://www.postgresql.org/) as the database.
