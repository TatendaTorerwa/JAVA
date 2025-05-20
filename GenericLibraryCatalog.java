import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;



/**
 * Represents a generic item in the library catalog.
 * Includes title, author, and a unique item ID.
 * 
 * Author: Rain
 */
class LibraryItem {
    private String title;
    private String author;
    private String itemID;

    /**
     * Constructs a LibraryItem with the given title, author, and item ID.
     * 
     * @param title  The title of the library item.
     * @param author The author of the library item.
     * @param itemID The unique ID of the library item.
     */
    public LibraryItem(String title, String author, String itemID) {
        this.title = title;
        this.author = author;
        this.itemID = itemID;
    }

    /**
     * Returns the unique ID of the item.
     * 
     * @return The item ID.
     */
    public String getItemID() {
        return itemID;
    }

    @Override
    public String toString() {
        return "Title: " + title + " | Author: " + author + " | ID: " + itemID;
    }
}

/**
 * A generic catalog class for managing library items.
 * 
 * @param <T> A type that extends LibraryItem.
 */
class Catalog<T extends LibraryItem> {
    private List<T> items;

    /**
     * Constructs an empty catalog.
     */
    public Catalog() {
        items = new ArrayList<>();
    }

    /**
     * Adds a library item to the catalog.
     * 
     * @param item The item to be added.
     */
    public void addItem(T item) {
        items.add(item);
        System.out.println("Item added: " + item);
    }

    /**
     * Removes a library item by its ID.
     * 
     * @param itemID The ID of the item to remove.
     */
    public void removeItem(String itemID) {
        boolean found = false;
        Iterator<T> iterator = items.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (item.getItemID().equals(itemID)) {
                iterator.remove();
                System.out.println("Item removed: " + item);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Error: Item with ID " + itemID + " not found.");
        }
    }

    /**
     * Displays the current catalog.
     */
    public void displayCatalog() {
        if (items.isEmpty()) {
            System.out.println("Catalog is empty.");
        } else {
            System.out.println("\nCurrent Catalog:");
            for (T item : items) {
                System.out.println(item);
            }
        }
    }
}

/**
 * The main class that provides a command-line interface to interact with the catalog.
 * Author: Rain
 */
public class GenericLibraryCatalog {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Catalog<LibraryItem> catalog = new Catalog<>();

        while (true) {
            System.out.println("\n--- Library Catalog Menu ---");
            System.out.println("1. Add Library Item");
            System.out.println("2. Remove Library Item");
            System.out.println("3. View Catalog");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter item ID: ");
                    String itemID = scanner.nextLine();
                    catalog.addItem(new LibraryItem(title, author, itemID));
                    break;
                case 2:
                    System.out.print("Enter item ID to remove: ");
                    String removeID = scanner.nextLine();
                    catalog.removeItem(removeID);
                    break;
                case 3:
                    catalog.displayCatalog();
                    break;
                case 4:
                    System.out.println("Exiting catalog...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
