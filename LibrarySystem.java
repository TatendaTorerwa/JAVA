import java.util.Scanner;

public class LibrarySyatem {
	public static void main(String[] args) {
		//Creating a Scanner object to read input from the user.
		Scanner Input = new Scanner(System.in);

		//Variables to store a single book's details.
		String bookTitle = ""; //Hold the title of the book
		String bookAuthor = ""; //Hold the author of the book
		int bookQuantity = 0; // Hold th enumber of copies available
		boolean bookExists = false; //Flag to indicate whether a book has been added.
		boolean exit = false; //Control when to exit the menu loop

		//A whil eloop to display the menu until the user chooses to exit
		while (!exit) {
			//Display the menu options
			System.out.println("\n--- Library Menu ---");
			System.out.println("1. Add Books");
			System.out.println("2. Borrow Books");
			System.out.println("3. Return Books");
			System.out.println("4. Exit");

			//varibale to store the user's choice
			try {
				//Read the user's input and convert it to an integer
				choice = Integer.parseInt(Input.nextLine());
			} catch (NumberFormatException e) {
				//Error message for an invalid number
				System.out.println("Invalid input. Please enter a valid number.");
				continue; //Goes back to the beginning of the loop
			}
			
			//option 1: Add Books
			if (choice == 1) {
				//Ask th euser for book title
				System.out.print("Enter book title: ");
				//Read the title and remove any extra spaces at the end and beginning.
				String inputTitle = Input.nextLine.trim();

				//Ask the user for the author name
				System.out.print("Enter author: ");
				//Read author name and remove any extra spaces
				String inputAuthor = Input.nextLine.trim();


				//variable to store the quantity of books to add
				int quantity = 0;
				//Ask user for the book's quantity
				System.out.print("Enter quantity: ");
				//Raed the quantity
				quantity = Integer.parseInt(Input.nextLine());
				//Checking and ensuring the quantity is not a negative
				try {
					//Ask the user for the quantity of the books
					System.out.print("Enter quantity: ");
					//Raed the quantity
					quantity = Integer.parseInt(Input.nextLine());
					//ensure quantity is not negative
					if (quantity < 0) {
						System.out.println("Quantity can not be a negative.");
						continue; //Go back to the menu
					} 
				} catch (NumberFormatException e) {
					//error message for a negative quantity
					System.out.println("Invalid quantity. Please enter a valid number.");
					continue; // Back to the menu
				}

				//if no books have been added, we add to librar
				if (!bookExists) {
					bookTitle = inputTitle; //save the title
					bookAuthor = inputAuthor; //save the author
					bookQuantity = quantity; //save the quantity
					bookExists = true; //mark the book now exists
					System.out.println("Book added successfully.");
					//Check if the book exists
					if (bookTitle.equalsIgnoreCase(inputTitle)) {
						bookQuantity += quantity;
						System.out.println("Book exists. Updated quantity: + bookQuantity");
					} else {
						//when a different book entered, only one book record is supported
						System.out.println("A different book is already stored. Only one book record is supported.");
					continue; //Back to the menu loop
					}
				}
			
			//Option 2: Borrow Books
			} else if (choice == 2) {
				//Check if a book has been added to the library
				if (!bookExists) {
					System.out.println("No book in the library. Please add a book first.");
					continue; // back to the menu

				//Ask the user which book they wantto borrow
				System.out.print("Enter book title to borrow: ");
				String inputTitle = input.nextLine().trim();
				//check if the input title matches the stored book title
				if (!bookTitle.equalIgnoreCase(inputTitle)) {
					System.out.println("Book not found in the library.");
					continue; //Back to the menu
	
				//variable to store the number of books the user wants to borrow
				int borrowQuantity = 0;
				try {
					//Ask the user the number of copies to borrow
					System.out.print("Enter number of books to borrow: ");
					borrowQuantity = Integer.parseInt(Input.nextLine());
					//Ensure the quantity is positive
					if (borrowQuantity <= 0) {
						System.out.println("Quantity must be a positive number.");
						continue;
					}
				} catch (NumberFormatException e) {
					//if the input is invalid, display an error message
					System.out.println("Invalid quantity. Please enter a valid number.");
					continue; //Back to the menu
				}

				// Check if there are enough coipes available to borrow
				if (borrowQuantity <= bookQuantity) {
					//subtract the borrowed quantity from the available copies
					bookQuantity = bookQuantity - borrowQuantity;
					System.out.println("Successfully borrowed" + borrowQuantity + " copy(ies) of \"" + bookTitle + "\".");
				} else {
					//Error if not enough copies are available
					System.out.println("Not enough copies available. Available quantity: " + bookQuantity);
				}
			
			//Option 3: Return Books
			} else if (choice == 3) {
				//check if the book exists in the library
				if (!bookExists) {
					System.out.println("No bok in the library. Please add a book first.");
					continue; //Back to the menu
				}

				//Ask the user for the title of the book to return
				System.out.print("Enter book title to return: ");
				String inputTitle = input.nextLine().trim();
				//Check if the title matches the stored book title
				if (!bookTitle.equalIgnoreCase(inputTitle)) {
					System.out.println("This book is not in the library.");
					continue; //Back to the menu

				//Variable tostore number of copies to return
				int returnQuantity = 0;
				try {
					//Ask the number of copies to return
					System.out.print("Enter number of books to return: ");
					returnQty = Integer.parseInt(sc.nextLine());
					// Ensure the return quantity is positive
					if (returnQty <= 0) {
					System.out.println("Quantity must be a positive number.");
					continue;  // Go back to the menu loop
					}
				} catch (NumberFormatException e) {
					// If the input is invalid, show an error message
					System.out.println("Invalid quantity. Please enter a valid number.");
					continue;  // Go back to the menu loop
				}
                
				// Add the returned books to the available quantity
				bookQuantity = bookQuantity + returnQty;
				System.out.println("Successfully returned " + returnQty + " copy(ies) of \"" + bookTitle + "\".");
                
			// Option 4: Exit the program
            		} else if (choice == 4) {
				System.out.println("Exiting program. Goodbye!");
				exit = true;  // Set the exit flag to true to end the loop
			} else {
				// If the user enters a choice that is not 1-4, display an error message
				System.out.println("Invalid choice. Please select a valid option.");
			}
		}
		// Close the Scanner to free up resources
		sc.close();
		}
	}
}

	
