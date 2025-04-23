// File: com/ecommerce/orders/Order.java
package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;
import java.util.List;

/**
 * Represents an order placed by a customer.
 * Includes customer details and purchased products.
 * 
 * @author Tatenda
 */
public class Order {
    private String orderID;
    private Customer customer;
    private List<Product> products;
    private double total;
    private String status;

    /**
     * Constructs a new order.
     * @param orderID the order ID
     * @param customer the customer who placed the order
     * @param products list of products ordered
     */
    public Order(String orderID, Customer customer, List<Product> products) {
        this.orderID = orderID;
        this.customer = customer;
        this.products = products;
        this.total = customer.calculateTotal();
        this.status = "Pending";
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public void printOrderSummary() {
        System.out.println("Order ID: " + orderID);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Products:");
        for (Product p : products) {
            System.out.println("- " + p.getName() + ": $" + p.getPrice());
        }
        System.out.println("Total: $" + total);
        System.out.println("Status: " + status);
    }
}
