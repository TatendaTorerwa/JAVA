import java.util.Scanner

class main {
    public static void main (String args[]) {
        Scanner inputText = new Scanner(System.in);
        System.out.println("Enter a paragraph or length text" + inputText.nextLine());
 
        //Character Count
        totalCharacters = inputText.length();
        System.out.println("The total characters are:" + totalCharacters);

        //Word Count
        totalWords = inputText.trim().split("\\s+").length;
        System.out.println("The total words is:" + totalWords;

        //Most Common Character
        characterOccur = inputText.next().charAt(0);
        int count = 0;
        for (i = 0; i < totalCharacters; i++) {
            if (inputText.CharAt(i) == characterOccur) {
                count++;
            }
        }

        System.out.println("Character" + characteroccur + "appears" + count + "times");

       //Character Frequency
       Scanner inputCharacter = new Scanner(System.in);
       System.out.println("Enter a character" + inputText.nextLine());

       char ch = inputCharacter.next().toLowerCase().charAt(0));
       int count = 0;

       for (int i = 0; i < inputText.lenth(); i++) {
           if (character.toLowerCase.inputCharacter.CharAt(i) == ch) {
                count++;
           }
       }
       System.out.println("Character" + characteroccur + "appears" + count + "times");


       //Word Frequency
       String [] words =  inputText.toLowerCase(0.split("");
       int count = 0;
       for (String w : words) {
          if (w.equals (word)) {
              count++;
          }
       }
       System.out.println("Word" + word + "\" appears" + count + "times.");

      //Unique Words
      int unique = 0;
      for (int i = 0; i < words.length; i++) {
          boolean found = false;
          for (int j = 0; j < i; j++) {
              if (words[i].equals (words[i])) {
                  found = true;
                  break;
               }
          }

          if (!found) {
              unique++;
           }
       }
       System.out.println("Number of unique words:" + unique);
}
