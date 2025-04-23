/**
 * This class represents a simple e-commerce system where customers can browse products,
 * add them to a shopping cart, and place orders. The code is organized using Java packages.
 * 
 * @author Tatenda Torerwa
 */

// File: com/ecommerce/Product.java
package com.ecommerce;

/**
 * Represents a product in the e-commerce system.
 * Contains product details and operations.
 * 
 * @author Tatenda
 */
public class Product {
    private String productID;
    private String name;
    private double price;

    /**
     * Constructs a new product.
     * @param productID the product ID
     * @param name the name of the product
     * @param price the price of the product
     */
    public Product(String productID, String name, double price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

    public String getProductID() { return productID; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    /**
     * Displays product information.
     */
    public void displayProduct() {
        System.out.println("Product ID: " + productID);
        System.out.println("Name: " + name);
        System.out.println("Price: $" + price);
    }
}
