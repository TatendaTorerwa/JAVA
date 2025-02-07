import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        // Creating a Scanner object to read input from the user.
        Scanner Input = new Scanner(System.in);

        // Variables to store a single book's details.
        String bookTitle = ""; // Hold the title of the book
        String bookAuthor = ""; // Hold the author of the book
        int bookQuantity = 0; // Hold the number of copies available
        boolean bookExists = false; // Flag to indicate whether a book has been added.
        boolean exit = false; // Control when to exit the menu loop

        // A while loop to display the menu until the user chooses to exit
        while (!exit) {
            // Display the menu options
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Books");
            System.out.println("2. Borrow Books");
            System.out.println("3. Return Books");
            System.out.println("4. Exit");

            // Variable to store the user's choice
            int choice = 0; // Declare choice here
            try {
                // Read the user's input and convert it to an integer
                choice = Integer.parseInt(Input.nextLine());
            } catch (NumberFormatException e) {
                // Error message for an invalid number
                System.out.println("Invalid input. Please enter a valid number.");
                continue; // Goes back to the beginning of the loop
            }

            // Option 1: Add Books
            if (choice == 1) {
                System.out.print("Enter book title: ");
                String inputTitle = Input.nextLine().trim();

                System.out.print("Enter author: ");
                String inputAuthor = Input.nextLine().trim();

                int quantity = 0;
                try {
                    System.out.print("Enter quantity: ");
                    quantity = Integer.parseInt(Input.nextLine());
                    if (quantity < 0) {
                        System.out.println("Quantity cannot be negative.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity. Please enter a valid number.");
                    continue;
                }

                // If no books have been added, store the new book
                if (!bookExists) {
                    bookTitle = inputTitle;
                    bookAuthor = inputAuthor;
                    bookQuantity = quantity;
                    bookExists = true;
                    System.out.println("Book added successfully.");
                } else if (bookTitle.equalsIgnoreCase(inputTitle)) {
                    bookQuantity += quantity;
                    System.out.println("Book already exists. Updated quantity: " + bookQuantity);
                } else {
                    System.out.println("A different book is already stored. Only one book record is supported.");
                }

            // Option 2: Borrow Books
            } else if (choice == 2) {
                if (!bookExists) {
                    System.out.println("No book in the library. Please add a book first.");
                    continue;
                }

                System.out.print("Enter book title to borrow: ");
                String inputTitle = Input.nextLine().trim();
                if (!bookTitle.equalsIgnoreCase(inputTitle)) {
                    System.out.println("Book not found in the library.");
                    continue;
                }

                int borrowQuantity = 0;
                try {
                    System.out.print("Enter number of books to borrow: ");
                    borrowQuantity = Integer.parseInt(Input.nextLine());
                    if (borrowQuantity <= 0) {
                        System.out.println("Quantity must be a positive number.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity. Please enter a valid number.");
                    continue;
                }

                if (borrowQuantity <= bookQuantity) {
                    bookQuantity -= borrowQuantity;
                    System.out.println("Successfully borrowed " + borrowQuantity + " copy(ies) of \"" + bookTitle + "\".");
                } else {
                    System.out.println("Not enough copies available. Available quantity: " + bookQuantity);
                }

            // Option 3: Return Books
            } else if (choice == 3) {
                if (!bookExists) {
                    System.out.println("No book in the library. Please add a book first.");
                    continue;
                }

                System.out.print("Enter book title to return: ");
                String inputTitle = Input.nextLine().trim();
                if (!bookTitle.equalsIgnoreCase(inputTitle)) {
                    System.out.println("This book is not in the library.");
                    continue;
                }

                int returnQuantity = 0;
                try {
                    System.out.print("Enter number of books to return: ");
                    returnQuantity = Integer.parseInt(Input.nextLine());
                    if (returnQuantity <= 0) {
                        System.out.println("Quantity must be a positive number.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity. Please enter a valid number.");
                    continue;
                }

                bookQuantity += returnQuantity;
                System.out.println("Successfully returned " + returnQuantity + " copy(ies) of \"" + bookTitle + "\".");

            // Option 4: Exit
            } else if (choice == 4) {
                System.out.println("Exiting program. Goodbye!");
                exit = true;
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        // Close Scanner
        Input.close();
    }
}
