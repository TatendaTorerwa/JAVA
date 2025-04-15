import java.util.Scanner;

/**
 * The Main class provides a text analysis program that:
 * - Calculates the number of characters and words in a user-supplied paragraph.
 * - Identifies the most common alphanumeric character (case insensitive).
 * - Allows the user to check the frequency of a specific character.
 * - Allows the user to check the frequency of a specific word.
 * - Computes the total number of unique words in the input text.
 *
 * This program demonstrates basic string manipulation and iteration techniques in Java.
 *
 * @author Tatenda
 */
public class Main {

    /**
     * The main method where execution starts.
     * It interacts with the user for input and prints text statistics.
     *
     * @param args command-line arguments (not used).
     */

      public static void main(String[] args) {
        try {
            Scanner inputText = new Scanner(System.in);
            System.out.println("Enter a paragraph or length text:");
            String text = inputText.nextLine();

            // Character Count
            int totalCharacters = text.length();
            System.out.println("The total characters are: " + totalCharacters);

            // Word Count
            int totalWords = text.trim().split("\\s+").length;
            System.out.println("The total words are: " + totalWords);

            // Most Common Character
            char characterOccur = text.charAt(0);
            int count = 0;
            for (int i = 0; i < totalCharacters; i++) {
                if (text.charAt(i) == characterOccur) {
                    count++;
                }
            }
            System.out.println("Character " + characterOccur + " appears " + count + " times.");

            // Validate Character Input
            char ch = getValidCharacter();

            // Character Frequency
            int charCount = 0;
            for (int i = 0; i < text.length(); i++) {
                if (Character.toLowerCase(text.charAt(i)) == ch) {
                    charCount++;
                }
            }
            System.out.println("Character " + ch + " appears " + charCount + " times.");

            // Validate Word Input
            String word = getValidWord(inputText);

            // Word Frequency
            String[] words = text.toLowerCase().split("\\s+");
            int wordCount = 0;
            for (String w : words) {
                if (w.equals(word.toLowerCase())) {
                    wordCount++;
                }
            }
            System.out.println("Word \"" + word + "\" appears " + wordCount + " times.");

            // Unique Words
            int unique = 0;
            for (int i = 0; i < words.length; i++) {
                boolean found = false;
                for (int j = 0; j < i; j++) {
                    if (words[i].equals(words[j])) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    unique++;
                }
            }
            System.out.println("Number of unique words: " + unique);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to ensure user enters a valid character using try-catch
    private static char getValidCharacter() {
        Scanner inputCharacter = new Scanner(System.in);
        char ch = '\0';
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("Enter a single character:");
                String input = inputCharacter.nextLine();
                if (input.length() == 1) {
                    ch = input.charAt(0);
                    valid = true;
                } else {
                    throw new IllegalArgumentException("Invalid input. Please enter exactly one character.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return ch;
    }

    // Method to ensure user enters a valid word using try-catch
    private static String getValidWord(Scanner inputText) {
        String word = "";
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("Enter a word:");
                word = inputText.nextLine().trim();
                if (!word.isEmpty()) {
                    valid = true;
                } else {
                    throw new IllegalArgumentException("Invalid input. Please enter a valid word.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return word;
    }
}
