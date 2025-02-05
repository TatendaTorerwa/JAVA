import java.util.Scanner; //import scanner class to read user input

public class myQuiz { //Main class for the Quiz
	public static void main(String[] args) { //Main method to start the Quiz
		Scanner input = new Scanner(System.in); //Create a scanner object for input
		int score = 0; //Initialize score to 0 to track the number of correct answers

		//Question 1
		System.out.println("\nQuestion 1: What is the Capital of France?");
		System.out.println("A. Rome");
		System.out.println("B. Paris");
		System.out.println("C. Berlin");
		System.out.println("D. Madrid");
		System.out.print("Your answer: ");
		char answer1 = input.next().toUpperCase().charAt(0); // Convert input to uppercase to standardize comparison, and retrieving character at first index

		switch (answer1) {
			case 'A':
				break;
			case 'B':
				score++; // Correct answer, increment score
				break;
			case 'C':
			case 'D':
				break; // Incorrect answer, no score
			default:
				System.out.println("Invalid input! Please enter A, B, C OR D."); // Entered the wrong character
		}

		//Question 2

		System.out.println("\nQuestion 2: Which planet is known as the Red planet?");
                System.out.println("A. Jupiter");
                System.out.println("B. Mars");
                System.out.println("C. Venus");
                System.out.println("D. Saturn");
		System.out.print("Your answer: ");
                char answer2 = input.next().toUpperCase().charAt(0); // Convert input to uppercase to standardize comparison, and retrieving character at first index
              

                switch (answer2) {
                        case 'A':
                                break;
                        case 'B':
                                score++; // Correct answer, increment score
                                break;
                        case 'C':
                        case 'D':
                                break; // Incorrect answer, no score
                        default:
                                System.out.println("Invalid input! Please enter A, B, C OR D."); // Entered the wrong character
                }

		//Question 3

		System.out.println("\nQuestion 3: What is the largest mammal in the world?");
                System.out.println("A. African Elephant");
                System.out.println("B. Polar Bear");
                System.out.println("C. Blue Wale");
                System.out.println("D. Giraffe");
		System.out.print("Your answer: ");
                char answer3 = input.next().toUpperCase().charAt(0); // Convert input to uppercase to standardize comparison, and retrieving character at first index

                switch (answer3) {
                        case 'A':
                                break;
                        case 'B':
                                break;
                        case 'C':
				score++; // Correct answer, increment score
                                break;
                        case 'D':
                                break; // Incorrect answer, no score
                        default:
                                System.out.println("Invalid input! Please enter A, B, C OR D."); // Entered the wrong character
                }

		//Question 4

		System.out.println("\nQuestion 4: In what year did world war II begin?");
                System.out.println("A. 1914");
                System.out.println("B. 1939");
                System.out.println("C. 1945");
                System.out.println("D. 1929");
		System.out.print("Your answer: ");
                char answer4 = input.next().toUpperCase().charAt(0); // Convert input to uppercase to standardize comparison, and retrieving character at first index

                switch (answer4) {
                        case 'A':
                                break;
                        case 'B':
                                score++; // Correct answer, increment score
                                break;
                        case 'C':
                        case 'D':
                                break; // Incorrect answer, no score
                        default:
                                System.out.println("Invalid input! Please enter A, B, C OR D."); // Entered the wrong character
                }
		
		//Question 5
		
		System.out.println("\nQuestion 5: What is the chemical symbol for water?");
                System.out.println("A. H2O");
                System.out.println("B. CO2");
                System.out.println("C. NaCL");
                System.out.println("D. O2");
		System.out.print("Your answer: ");
                char answer5 = input.next().toUpperCase().charAt(0); // Convert input to uppercase to standardize comparison, and retrieving character at first index

                switch (answer5) {
                        case 'A':
				score++; //Correct answer, increment score
                                break;
                        case 'B':
                        case 'C':
                        case 'D':
                                break; // Incorrect answer, no score
                        default:
                                System.out.println("Invalid input! Please enter A, B, C OR D."); // Entered the wrong character
                }
		

		//Calculate and display the final score
		double percentage = (double) score / 5 * 100; //Calculate percentage score
		System.out.println("\nYour final score: " + score + " out of 5 (" + percentage + "%)");

		input.close(); // Close the scanner to prevent resource leaks
	}
}
