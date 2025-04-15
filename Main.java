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

        Scanner inputText = new Scanner(System.in);

        // Prompt user for a block of text
        System.out.println("Enter a paragraph or lengthy text:");
        String text = inputText.nextLine();

        // Calculate total characters in the text (including spaces and punctuation)
        int totalCharacters = text.length();
        System.out.println("Total number of characters: " + totalCharacters);

        // Split input into words using whitespace as separator
        String[] words = text.trim().split("\\s+");
        int totalWords = words.length;
        System.out.println("Total number of words: " + totalWords);

        // Convert input to lowercase and convert to character array for easier analysis
        char[] textChars = text.toLowerCase().toCharArray();

        // Variables to store the most common character and its occurrence count
        int maxCount = 0;
        char mostCommonChar = ' ';

        // Loop through each character to determine the most frequent alphanumeric character
        for (int i = 0; i < textChars.length; i++) {
            char currentChar = textChars[i];

            if (!Character.isLetterOrDigit(currentChar)) {
                continue; // Ignore non-alphanumeric characters
            }

            int count = 0;

            // Count occurrences of the current character
            for (int j = 0; j < textChars.length; j++) {
                if (textChars[j] == currentChar) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                mostCommonChar = currentChar;
            }
        }

        System.out.println("Most common character: '" + mostCommonChar + "' appears " + maxCount + " times.");

        // Prompt user for a character to check its frequency in the text
        System.out.println("Enter a character to check its frequency:");
        char searchChar = inputText.next().toLowerCase().charAt(0); // Accept first character only
        int charFrequency = 0;

        // Count the frequency of the user-specified character
        for (int i = 0; i < textChars.length; i++) {
            if (textChars[i] == searchChar) {
                charFrequency++;
            }
        }

        System.out.println("Character '" + searchChar + "' appears " + charFrequency + " times.");

        inputText.nextLine(); // Clear the newline character from the input buffer

        // Prompt user for a word to check its frequency
        System.out.println("Enter a word to check its frequency:");
        String searchWord = inputText.nextLine().toLowerCase();
        int wordFrequency = 0;

        // Count the number of times the specified word appears in the input
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(searchWord)) {
                wordFrequency++;
            }
        }

        System.out.println("Word \"" + searchWord + "\" appears " + wordFrequency + " times.");

        // Compute the number of unique words (case insensitive)
        int uniqueCount = 0;
        for (int i = 0; i < words.length; i++) {
            boolean isUnique = true;
            String currentWord = words[i].toLowerCase();

            // Check if this word has already been counted
            for (int j = 0; j < i; j++) {
                if (currentWord.equals(words[j].toLowerCase())) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                uniqueCount++;
            }
        }

        System.out.println("Number of unique words: " + uniqueCount);

        // Close the Scanner to release resources
        inputText.close();
    }
}
