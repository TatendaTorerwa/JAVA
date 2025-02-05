import java.util.Scanner;

public class NameAndAge {
	public static void main(String[] args) {
		System.out.print("Enter your name and age: ");
		
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		int age = input.nextInt();

		System.out.println(name + "! You are " + age + " years old!");
	}
}
