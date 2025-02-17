import java.util.ArrayList;
import java.util.Scanner;

// Student class to store individual student details
class Student {
    String name;
    int ID;
    int age;
    float grade;

    // Constructor
    public Student(String name, int ID, int age, float grade) {
        this.name = name;
        this.ID = ID;
        this.age = age;
        this.grade = grade;
    }

    // Display student details
    public void display() {
        System.out.println("ID: " + ID + ", Name: " + name + ", Age: " + age + ", Grade: " + grade);
    }
}

// Student Management class to handle student records
class StudentManagement {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static int totalStudents = 0;

    // Add a new student
    public static void addStudent(String name, int ID, int age, float grade) {
        // Ensure the ID is unique
        for (Student t : studentList) {
            if (t.ID == ID) {
                System.out.println("Error: A student with this ID already exists.");
                return;
            }
        }

        Student newStudent = new Student(name, ID, age, grade);
        studentList.add(newStudent);
        totalStudents++;
        System.out.println("Student added successfully.");
    }

    // Update student details
    public static void updateStudent(int ID, String newName, int newAge, float newGrade) {
        for (Student t : studentList) {
            if (t.ID == ID) {
                t.name = newName;
                t.age = newAge;
                t.grade = newGrade;
                System.out.println("Student information updated successfully.");
                return;
            }
        }
        System.out.println("Error: Student ID not found.");
    }

    // View all students
    public static void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\n--- Student Records ---");
            for (Student t : studentList) {
                t.display();
            }
        }
    }
}

// Main class with menu-driven administrator interface
public class RecordManagementSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Record Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. View Students");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1-4.");
                input.next(); // Clear invalid input
                continue;
            }

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add student
                    System.out.print("Enter Name: ");
                    String name = input.nextLine();

                    System.out.print("Enter ID: ");
                    int ID = getIntInput(input);

                    System.out.print("Enter Age: ");
                    int age = getIntInput(input);

                    System.out.print("Enter Grade: ");
                    float grade = getFloatInput(input);

                    StudentManagement.addStudent(name, ID, age, grade);
                    break;

                case 2: // Update student
                    System.out.print("Enter Student ID to update: ");
                    int updateID = getIntInput(input);

                    System.out.print("Enter New Name: ");
                    String newName = input.nextLine();

                    System.out.print("Enter New Age: ");
                    int newAge = getIntInput(input);

                    System.out.print("Enter New Grade: ");
                    float newGrade = getFloatInput(input);

                    StudentManagement.updateStudent(updateID, newName, newAge, newGrade);
                    break;

                case 3: // View Students
                    StudentManagement.viewStudents();
                    break;

                case 4: // Exit
                    System.out.println("Exiting program.");
                    input.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a number between 1-4.");
            }
        }
    }

    // Helper method for integer input validation
    private static int getIntInput(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            input.next(); // Clear invalid input
        }
        return input.nextInt();
    }

    // Helper method for float input validation
    private static float getFloatInput(Scanner input) {
        while (!input.hasNextFloat()) {
            System.out.println("Invalid input. Please enter a valid grade.");
            input.next();
        }
        return input.nextFloat();
    }
}
