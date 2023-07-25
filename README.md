# Microservice challenge

## Installation

1. Clone the repository:

```bash
https://github.com/Alequeixam/desafio-ms.git
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


GET /roles - Retrieve a list of all roles. 

POST /roles - Create a new role.

PUT /roles/{roleId} - Update a role by it's id.

DELETE /roles/{roleId} - Delete a role.


```
## Purpose of each MS
### ms-authentication
The goal of this microservice is to authenticate and authorize users and any request of the API.

Unfortunately, I wasn't able to correctly implement it, so, as of right now it is not currently working properly.

### ms-gateway
The gateway redirects the requests to the responsible api.

### ms-product
The products MS controls all of the items in the database related to Product, Category, Users and Roles.

### ms-notification
This MS is suposed to send an email notification via RabbitMQ to the user when there is a modification to a registry.
It is currently not working as intended, even though the endpoint is properly functioning.



## Database
The project utilizes [PostgresSQL](https://www.postgresql.org/) as the database.

