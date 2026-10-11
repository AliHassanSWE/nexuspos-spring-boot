# AI Prompt & Development Log
**Project:** NexusPOS Web Application
**Tech Stack:** Java (Spring Boot), MySQL (via XAMPP), Hibernate/JPA, HTML/JS/CSS
**Architecture:** Model-View-Controller (MVC)

## 1. Project Initialization & Architecture Setup
**Prompt Used:** *"I need to develop a professional Point of Sale (POS) web application using Java Spring Boot. How should I structure my project folders to follow the MVC (Model-View-Controller) architecture?"*
**AI Assistance:** The AI guided the creation of a standard Spring Boot directory structure, separating logic into `controller`, `model`, `repository`, and `service` packages. This ensures professional separation of concerns where the backend routing, data representation, and business logic are fully isolated.

## 2. Object-Oriented Programming (OOP) Implementation
**Prompt Used:** *"Help me design the Java classes for a POS system. I need a Product, a CartItem, and a Cart. Use OOP principles like encapsulation so the cart calculates its own totals."*
**AI Assistance:** The AI generated encapsulated entity classes (`Product.java`, `CartItem.java`, `Transaction.java`). 
* **Encapsulation:** Private fields were created with secure public getters. Stock reduction was restricted to a specific `reduceStock(int quantity)` method rather than exposing the raw variable. 
* **Behavior within Objects:** The `CartItem` object was programmed to calculate its own subtotal (`price * quantity`), and the `Cart` object dynamically loops through items to calculate the grand total.

## 3. Data Structures & Algorithms (DSA) Integration
**Prompt Used:** *"I need to integrate Data Structures into this POS system to fulfill my professor's requirement. What are the best Data Structures to use for the Shopping Cart and the Product Inventory search?"*
**AI Assistance:** 
* **HashMap for O(1) Inventory Lookup:** The AI suggested using a `Map<String, Product>` as an in-memory cache inside the `ProductService`. When the server starts, it loads products from MySQL into the HashMap. This allows cashiers to scan barcodes (IDs) and retrieve products in $O(1)$ constant time without querying the database on every scan.
* **ArrayList for Cart & Transactions:** An `ArrayList<CartItem>` was implemented in the `Cart` and `Transaction` models to maintain the chronological, sequential insertion order of scanned items. The AI added logic to iterate through the list $O(N)$ to dynamically group duplicate products together and increment their quantity rather than creating duplicate receipt lines.

## 4. Database Persistence & Port Configuration
**Prompt Used:** *"How do I connect my Spring Boot application to a local XAMPP MySQL database? I am getting a port 8080 conflict with Apache."*
**AI Assistance:** 
* The AI diagnosed the port conflict and provided configuration for `application.properties` to shift the Java server to port `9090`. 
* It provided the exact Maven `<dependency>` for `mysql-connector-j` and `spring-boot-starter-data-jpa`.
* Using `@Entity`, `@Id`, and `@ManyToOne` annotations, the AI demonstrated how to leverage Spring Data JPA to automatically generate SQL table schemas (`ddl-auto=update`), completely removing the need to write manual SQL queries.

## 5. Debugging & Issue Resolution
**Prompt Used:** *"MySQL keeps automatically stopping in XAMPP after a few seconds and Java throws a 'Unable to determine Dialect' error. How do I fix this?"*
**AI Assistance:** The AI identified a corrupted MySQL data folder caused by a crashed process. It provided step-by-step instructions to replace the broken XAMPP `/data` directory with the clean `/backup` directory, restoring database stability and allowing Hibernate to rebuild the schemas successfully.
## 6. Enterprise UI & Analytics Conversion
**Prompt Used:** *"I want to convert my POS project like this picture I attached. Means I want a dashboard, a graph, more options to edit, and stock."*
**AI Assistance:** 
* **Backend Aggregation:** The AI guided the creation of a `DashboardController` and custom JPQL queries (`@Query("SELECT COALESCE(SUM(t.grandTotal), 0) FROM Transaction t")`) to aggregate real-time revenue and count low-stock items across the entire database.
* **Frontend Overhaul:** The AI provided the CSS Grid and Flexbox structural code to convert the application into a 3-column enterprise layout without breaking the existing shopping cart logic.
* **Data Visualization:** The AI integrated `Chart.js`, writing JavaScript fetch promises to pull the calculated SQL statistics and render a dynamic trend line for the Sales Overview widget.