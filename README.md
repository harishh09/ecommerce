# E-Commerce Backend System

## Project Overview

This project is a **Spring Boot based E-Commerce Backend System** developed to manage online shopping operations such as **user registration, product management, shopping cart, order placement, payment simulation, and inventory management**.

The system exposes **RESTful APIs** that can be consumed by web or mobile applications. The project follows an **industry-standard layered architecture**.

---

## Technology Stack

| Component         | Technology                  |
| ----------------- | --------------------------- |
| Language          | Java 17                     |
| Framework         | Spring Boot                 |
| ORM               | Spring Data JPA / Hibernate |
| Database          | MySQL                       |
| Security          | Spring Security + JWT       |
| Build Tool        | Maven                       |
| API Testing       | Postman                     |
| API Documentation | Swagger                     |
| Object Mapping    | ModelMapper                 |

---

## Project Architecture

The backend follows a **layered architecture**:

```
Controller → Service → Repository → Entity
```

### Layer Explanation

**Controller Layer**

* Handles HTTP requests
* Returns API responses

**Service Layer**

* Contains business logic

**Repository Layer**

* Handles database operations using JPA

**Entity Layer**

* Maps Java classes to database tables

---

## Package Structure

```
com.incture.ecommerce
 ├ controller
 ├ service
 ├ repository
 ├ entity
 ├ dto
 ├ config
 ├ exception
 └ utils
```

---

## Core Features

### 1. User Management

* User registration
* User login
* Profile update
* Role-based users (**ADMIN, CUSTOMER**)
* Admin can manage users

### APIs

```
POST /api/users/register
POST /api/users/login
GET /api/users/{id}
PUT /api/users/{id}
DELETE /api/users/{id}
```

---

### 2. Product Management

* Admin can add products
* Admin can update products
* Admin can delete products
* Customers can view products
* Pagination support

### Product Fields

```
id
name
description
price
stock
category
imageUrl
rating
```

### APIs

```
POST /api/products
GET /api/products
GET /api/products/{id}
PUT /api/products/{id}
DELETE /api/products/{id}
```

---

### 3. Shopping Cart

Each user maintains their own shopping cart.

Features:

* Add product to cart
* Remove product from cart
* Update quantity
* View total cart price

### APIs

```
POST /api/cart/add/{userId}/{productId}
PUT /api/cart/update/{userId}/{productId}
DELETE /api/cart/remove/{userId}/{productId}
GET /api/cart/{userId}
```

---

### 4. Order Management

* Convert cart to order during checkout
* Store order details
* View order history

### Order Fields

```
orderId
userId
orderDate
items
totalAmount
paymentStatus
orderStatus
```

### APIs

```
POST /api/orders/checkout/{userId}
GET /api/orders/{userId}
GET /api/orders/order/{id}
```

---

### 5. Payment Management (Simulation)

Payment is simulated during checkout.

Possible statuses:

```
SUCCESS
FAILED
```

If payment succeeds:

```
OrderStatus = PLACED
```

If payment fails:

```
OrderStatus = CANCELLED
```

---

### 6. Inventory Management

After successful order placement:

```
product.stock = product.stock - quantity
```

The system prevents ordering products that are **out of stock**.

---

## Database Tables

The system uses the following entities:

```
User
Product
Cart
CartItem
Order
OrderItem
```



## API Documentation

Swagger UI is available at:

```
http://localhost:8080/swagger-ui/index.html
```

Swagger provides an interactive interface for testing APIs.

---

## API Testing

All APIs were tested using **Postman**.

The repository includes the **Postman Collection** containing all API requests.

---

## How to Run the Project

### Step 1

Clone the repository.

### Step 2

Create MySQL database:

```
CREATE DATABASE ecommerce_db;
```

### Step 3

Update `application.properties`

```
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### Step 4

Run the application

```
EcommerceApplication.java
```

### Step 5

Open Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

## Deliverables

This project includes:

* Complete source code
* README documentation
* Postman API collection
* Swagger API documentation

---

## Future Enhancements

Possible improvements:

* Email notifications for order confirmation
* Docker containerization
* Advanced role-based authorization
* Payment gateway integration

---

## Author

Harish Sahoo
