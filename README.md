Product details application with basic REST api operation on Spring Boot using JPA.

========================================================================================
Swagger UI URL : http://localhost:8080/swagger-ui.html

========================================================================================
Adding the Product:
Request: http://localhost:8080/v2/transactions/
Request Body:
{
	"name": "Chair",
	"price": "12000.24"
}

========================================================================================
Getting List of product based on Page:
Request: http://localhost:8080/v2/transactions/?orderBy=name&direction=desc&pageNumber=1&pageSize=3
Response Body:
[
  {
    "id": 4,
    "name": "Desktop",
    "price": 32540.23
  },
  {
    "id": 1,
    "name": "Chair",
    "price": 12000.24
  }
]

========================================================================================
Getting product based on ID:
Request: http://localhost:8080/v2/transactions/5
Response Body:
{
  "id": 5,
  "name": "Keyboard",
  "price": 540.23
}

========================================================================================
Deleting product based on ID:
Request: http://localhost:8080/v2/transactions/2
Response Body : Product has been deleted successfully.

========================================================================================
Getting List of Product Deleted:
Request: http://localhost:8080/v2/transactions/deleted-product
Response Body:
[
  {
    "id": 6,
    "name": "T Shirt",
    "price": 458.56
  }
]
========================================================================================

Docker Image build command Details:

Command used:
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=springio/gs-spring-boot-docker
========================================================================================
