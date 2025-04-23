// File: com/ecommerce/Customer.java
package com.ecommerce;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer with a shopping cart.
 * Can add/remove products, view cart and place orders.
 * 
 * @author Tatenda
 */
public class Customer {
    private String customerID;
    private String name;
    private List<Product> shoppingCart;

    /**
     * Constructs a new customer.
     * @param customerID the ID of the customer
     * @param name the name of the customer
     */
    public Customer(String customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.shoppingCart = new ArrayList<>();
    }

    public String getCustomerID() { return customerID; }
    public String getName() { return name; }

    /**
     * Adds a product to the shopping cart.
     * @param product the product to add
     */
    public void addProductToCart(Product product) {
        shoppingCart.add(product);
        System.out.println(product.getName() + " added to cart.");
    }

    /**
     * Removes a product from the shopping cart.
     * @param product the product to remove
     */
    public void removeProductFromCart(Product product) {
        shoppingCart.remove(product);
        System.out.println(product.getName() + " removed from cart.");
    }

    /**
     * Calculates total cost of items in cart.
     * @return total price
     */
    public double calculateTotal() {
        double total = 0;
        for (Product p : shoppingCart) {
            total += p.getPrice();
        }
        return total;
    }

    public List<Product> getCart() {
        return shoppingCart;
    }
}
