# Price Fetching Microservice

## Overview
This project is a Spring Boot microservice that retrieves prices for products within a specific timeframe. It leverages an H2 in-memory database for storage and provides RESTful endpoints for querying the data.

## Features
- Fetch prices based on product ID and specific date/time range
- In-memory H2 database
- RESTful API endpoints
- Easy setup and configuration

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6.3 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/price-fetching-microservice.git
    cd price-fetching-microservice
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

### Accessing the H2 Console
The H2 console can be accessed at [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

### API Endpoints
- **Get Price by Product ID and Date/Time**: Fetches the price for a given product ID and date/time range.
    - **URL**: `/prices`
    - **Method**: `GET`
    - **Parameters**:
        - `dateTime`: The specific date/time to fetch the price for (required)
        - `productId`: The ID of the product (required)
        - `brandId`: The ID of the brand (required)
    - **Response**: JSON object containing the price details

### Example Requests

#### Fetch Price
```sh
curl -X GET "http://localhost:8080/prices?productId=35455&dateTime=2020-06-15T10:00:00"