// File: Main.java
import com.ecommerce.*;
import com.ecommerce.orders.Order;
import java.util.*;

/**
 * Main class to demonstrate e-commerce system.
 * Creates products, customers, and processes orders.
 * 
 * @author Tatenda
 */
public class Main {
    public static void main(String[] args) {
        Product laptop = new Product("P001", "Laptop", 1200);
        Product phone = new Product("P002", "Smartphone", 800);

        Customer customer = new Customer("C001", "Alice");
        customer.addProductToCart(laptop);
        customer.addProductToCart(phone);

        Order order = new Order("O001", customer, customer.getCart());
        order.printOrderSummary();
        order.updateStatus("Shipped");
        System.out.println("\nAfter status update:");
        order.printOrderSummary();
    }
}
