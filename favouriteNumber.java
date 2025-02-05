import java.util.Scanner;

public class favouriteNumber {
	public static void main(String[] args) {

		System.out.print("Enter your favorite integer: ");

		Scanner input = new Scanner(System.in);
		int myNumber = input.nextInt();

		System.out.println(myNumber + " is my favorite integer too!");
	}
}
				
