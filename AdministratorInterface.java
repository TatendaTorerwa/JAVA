import java.util.Scanner;

// Class to represent a student
class Student {
    private String name; // Student's name
    private int id; // Student's ID
    private Course enrolledCourse; // The course the student is enrolled in
    private double grade; // The grade the student receives for the course

    // Constructor to initialize a student with their name and ID
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Getter methods for name, ID, enrolled course, and grade
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Course getEnrolledCourse() {
        return enrolledCourse;
    }

    public double getGrade() {
        return grade;
    }

    // Method for enrolling a student in a course
    public void enrollInCourse(Course course) {
        // Check if the course has space available for enrollment
        if (course.enrollStudent()) {
            this.enrolledCourse = course;
            System.out.println(name + " has been enrolled in " + course.getCourseName());
        } else {
            System.out.println("Enrollment failed. Course is full.");
        }
    }

    // Method to assign a grade to the student for a specific course
    public void assignGrade(Course course, double grade) {
        // Check if the student is enrolled in the course
        if (this.enrolledCourse != null && this.enrolledCourse == course) {
            this.grade = grade;
            System.out.println("Grade assigned: " + grade + " for course " + course.getCourseName());
        } else {
            System.out.println("Student is not enrolled in this course.");
        }
    }
}

// Class to represent a course
class Course {
    private String courseCode; // Unique code for the course
    private String courseName; // Name of the course
    private int maxCapacity; // Maximum number of students allowed in the course
    private int enrolledStudents; // Number of students currently enrolled
    private static int totalEnrolledStudents = 0; // Total number of enrolled students across all courses

    // Constructor to initialize a course with code, name, and max capacity
    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = 0;
    }

    // Getter methods for course details
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    // Method to enroll a student in the course if there is space
    public boolean enrollStudent() {
        if (enrolledStudents < maxCapacity) {
            enrolledStudents++;
            totalEnrolledStudents++; // Increment the global student counter
            return true;
        }
        return false; // Return false if the course is full
    }

    // Static method to get the total number of enrolled students across all courses
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }
}

// Class responsible for managing courses
class CourseManagement {
    private static Course[] courses = new Course[10]; // Array to store courses
    private static int courseCount = 0; // Count of courses added

    // Method to add a new course to the course array
    public static void addCourse(String courseCode, String courseName, int maxCapacity) {
        if (courseCount < courses.length) {
            Course course = new Course(courseCode, courseName, maxCapacity);
            courses[courseCount] = course;
            courseCount++; // Increment the course count
            System.out.println("Course added: " + courseName);
        } else {
            System.out.println("Cannot add more courses, array is full.");
        }
    }

    // Method to retrieve a course by its name
    public static Course getCourseByName(String courseName) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCourseName().equals(courseName)) {
                return courses[i]; // Return the course if found
            }
        }
        return null; // Return null if course is not found
    }
}

// Class for the Administrator interface (user input and management)
class AdministratorInterface {
    private static Scanner scanner = new Scanner(System.in); // Scanner for user input

    // Main method to handle the system menu and user actions
    public static void main(String[] args) {
        while (true) {
            displayMenu(); // Display the menu options
            int choice = getUserChoice(); // Get user input for choice

            // Switch-case structure to handle the different menu options
            switch (choice) {
                case 1: // Option 1: Add new course
                    addCourse();
                    break;
                case 2: // Option 2: Enroll student in a course
                    enrollStudent();
                    break;
                case 3: // Option 3: Assign grade to student
                    assignGrade();
                    break;
                case 4: // Option 4: Calculate overall grade for student
                    calculateOverallGrade();
                    break;
                case 5: // Option 5: Exit the system
                    System.out.println("Exiting system.");
                    scanner.close(); // Close the scanner when done
                    return;
                default: // Invalid input
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    // Method to display the menu with options
    private static void displayMenu() {
        System.out.println("\n==== Course Management System ====");
        System.out.println("1. Add New Course");
        System.out.println("2. Enroll Student in Course");
        System.out.println("3. Assign Grade to Student");
        System.out.println("4. Calculate Overall Grade for Student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Method to get the user's choice input
    private static int getUserChoice() {
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine()); // Convert input to integer
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return choice;
    }

    // Method to handle adding a new course
    private static void addCourse() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter maximum capacity of the course: ");
        int maxCapacity = Integer.parseInt(scanner.nextLine());

        // Call CourseManagement to add the course
        CourseManagement.addCourse(courseCode, courseName, maxCapacity);
    }

    // Method to handle enrolling a student in a course
    private static void enrollStudent() {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        Student student = new Student(studentName, studentId); // Create new student

        System.out.print("Enter course name to enroll in: ");
        String courseName = scanner.nextLine();

        Course course = CourseManagement.getCourseByName(courseName);
        if (course != null) {
            student.enrollInCourse(course);  // Enroll the student in the course if found
        } else {
            System.out.println("Course not found.");
        }
    }

    // Method to handle assigning a grade to a student
    private static void assignGrade() {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        Student student = new Student(studentName, studentId); // Create new student

        System.out.print("Enter course name to assign grade: ");
        String courseName = scanner.nextLine();

        Course course = CourseManagement.getCourseByName(courseName);
        if (course != null) {
            // Enroll the student in the course if not already enrolled
            student.enrollInCourse(course);
            System.out.print("Enter grade for student: ");
            double grade = Double.parseDouble(scanner.nextLine());
            student.assignGrade(course, grade); // Assign the grade
        } else {
            System.out.println("Course not found.");
        }
    }

    // Method to calculate the overall grade for a student
    private static void calculateOverallGrade() {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        Student student = new Student(studentName, studentId); // Create new student

        System.out.print("Enter course name to calculate grade: ");
        String courseName = scanner.nextLine();

        Course course = CourseManagement.getCourseByName(courseName);
        if (course != null) {
            // Enroll the student in the course if not already enrolled
            student.enrollInCourse(course);
            double grade = student.getGrade(); // Get the grade for the student
            System.out.println("Overall grade for " + student.getName() + ": " + grade);
        } else {
            System.out.println("Course not found.");
        }
    }
}

