# Food Ordering API Test Guide

BASE URL: http://localhost:8080

## Categories

### Create Category
POST /categories
Content-Type: application/json
```
{
  "name": "Beverages"
}
```

### Create Another Category
POST /categories
Content-Type: application/json
```
{
  "name": "Snacks"
}
```

### Get All Categories
GET /categories

---

## Menu Items

### Create Menu Item 1
POST /menu-items
Content-Type: application/json
```
{
  "title": "Cappuccino",
  "price": 3.5,
  "description": "Hot coffee with milk foam",
  "category": { "id": 1 }
}
```

### Create Menu Item 2
POST /menu-items
Content-Type: application/json
```
{
  "title": "Espresso",
  "price": 2.5,
  "description": "Strong black coffee",
  "category": { "id": 1 }
}
```

### Create Menu Item 3
POST /menu-items
Content-Type: application/json
```
{
  "title": "Sandwich",
  "price": 5.0,
  "description": "Veg sandwich",
  "category": { "id": 2 }
}
```

### Get All Menu Items
GET /menu-items

### Get Menu Items by Category
GET /menu-items/category/1
GET /menu-items/category/2

---

## Orders

### Place Order for Table 5
POST /orders
Content-Type: application/json
```
{
  "tableNumber": 5,
  "items": [
    { "quantity": 2, "menuItem": { "id": 1 } },
    { "quantity": 1, "menuItem": { "id": 3 } }
  ]
}
```

### Get All Orders
GET /orders

### Get Order by ID
GET /orders/1

### Get Orders by Status
GET /orders/status/PENDING
GET /orders/status/COMPLETED

### Update Order Status
PATCH /orders/1/status?status=COMPLETED

---

## Tables

### List All Tables
GET /tables

### Get Orders for Table
GET /tables/5/orders

### Get Active Orders for Table
GET /tables/5/active-orders

### Get Table Status
GET /tables/5/status

### Occupy Table
POST /tables/5/occupy

### Free Table
POST /tables/5/free

---

## Example Test Flow
1. Create categories and menu items.
2. Occupy a table.
3. Place an order for the table.
4. Mark order as completed.
5. Free the table.

Use the above endpoints and JSON bodies in Postman for testing.
