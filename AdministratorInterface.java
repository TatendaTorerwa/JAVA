import java.util.Scanner;  // Import the Scanner class


// The `Student` class represents a student, containing attributes like name, ID, enrolled course, and grade.
class Student {
    private String name;  // The name of the student
    private int id;  // The unique ID of the student
    private Course enrolledCourse;  // The course the student is enrolled in
    private double grade;  // The grade of the student in the enrolled course

    // Constructor to initialize a new student object
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Getter and Setter methods for student attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter method to retrieve the enrolled course of the student
    public Course getEnrolledCourse() {
        return enrolledCourse;
    }

    // Getter method to retrieve the grade of the student
    public double getGrade() {
        return grade;
    }

    // Method to enroll the student in a given course
    public void enrollInCourse(Course course) {
        if (course.enrollStudent()) {
            this.enrolledCourse = course;  // Assign the course to the student
            System.out.println(name + " has been enrolled in " + course.getCourseName());
        } else {
            System.out.println("Enrollment failed. Course is full.");
        }
    }

    // Method to assign a grade to a student in a particular course
    public void assignGrade(Course course, double grade) {
        if (this.enrolledCourse == course) {  // Check if the student is enrolled in the course
            this.grade = grade;
            System.out.println("Grade assigned: " + grade + " for course " + course.getCourseName());
        } else {
            System.out.println("Student is not enrolled in this course.");
        }
    }
}

// The `Course` class represents a course with attributes like course code, course name, and enrollment details.
class Course {
    private String courseCode;  // The unique code for the course
    private String courseName;  // The name of the course
    private int maxCapacity;  // The maximum number of students that can enroll in the course
    private int enrolledStudents;  // The number of students currently enrolled in the course
    private static int totalEnrolledStudents = 0;  // Static variable to track total number of students enrolled in all courses

    // Constructor to initialize a new course
    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = 0;  // Initial number of enrolled students is zero
    }

    // Public getter methods for accessing course information
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

    // Method to enroll a student in the course if the course has available slots
    public boolean enrollStudent() {
        if (enrolledStudents < maxCapacity) {
            enrolledStudents++;  // Increment the number of enrolled students
            totalEnrolledStudents++;  // Increment the static variable tracking total enrolled students
            return true;
        }
        return false;  // Return false if the course is full
    }

    // Static method to get the total number of enrolled students across all courses
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }
}

// The `CourseManagement` class handles the management of courses, students, and grade assignments.
class CourseManagement {
    private static Course[] courses = new Course[10];  // Array to store courses (maximum 10 courses)
    private static int courseCount = 0;  // Counter to track the number of courses added

    // Method to add a new course to the system
    public static void addCourse(String courseCode, String courseName, int maxCapacity) {
        if (courseCount < courses.length) {
            Course course = new Course(courseCode, courseName, maxCapacity);
            courses[courseCount] = course;  // Add the new course to the array
            courseCount++;  // Increment the course count
            System.out.println("Course added: " + courseName);
        } else {
            System.out.println("Cannot add more courses, array is full.");
        }
    }

    // Method to enroll a student in a course
    public static void enrollStudent(Student student, Course course) {
        student.enrollInCourse(course);
    }

    // Method to assign a grade to a student for a particular course
    public static void assignGrade(Student student, Course course, double grade) {
        student.assignGrade(course, grade);
    }

    // Method to calculate the overall grade for a student
    public static double calculateOverallGrade(Student student) {
        if (student.getEnrolledCourse() == null) {
            System.out.println(student.getName() + " is not enrolled in any course.");
            return 0.0;  // Return 0 if the student is not enrolled in any course
        }

        double grade = student.getGrade();  // Get the grade of the student in the enrolled course
        System.out.println("Overall grade for " + student.getName() + ": " + grade);
        return grade;
    }

    // Method to get a course by its name
    public static Course getCourseByName(String courseName) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCourseName().equals(courseName)) {
                return courses[i];  // Return the course if found
            }
        }
        return null;  // Return null if no course with the given name is found
    }
}

// The `AdministratorInterface` class is the user interface for an administrator to manage courses and students.
class AdministratorInterface {
    private static Scanner scanner = new Scanner(System.in);  // Scanner to read user input

    public static void main(String[] args) {
        while (true) {
            displayMenu();  // Display the menu to the administrator
            int choice = getUserChoice();  // Get the administrator's choice

            // Switch-case to perform actions based on the user's choice
            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    enrollStudent();
                    break;
                case 3:
                    assignGrade();
                    break;
                case 4:
                    calculateOverallGrade();
                    break;
                case 5:
                    System.out.println("Exiting system.");
                    scanner.close();  // Close the scanner before exiting
                    return;  // Exit the program
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    // Method to display the menu options to the administrator
    private static void displayMenu() {
        System.out.println("\n==== Course Management System ====");
        System.out.println("1. Add New Course");
        System.out.println("2. Enroll Student in Course");
        System.out.println("3. Assign Grade to Student");
        System.out.println("4. Calculate Overall Grade for Student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Method to get the administrator's menu choice
    private static int getUserChoice() {
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());  // Read and parse the choice
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return choice;
    }

    // Method to add a new course to the system
    private static void addCourse() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter maximum capacity of the course: ");
        int maxCapacity = Integer.parseInt(scanner.nextLine());

        CourseManagement.addCourse(courseCode, courseName, maxCapacity);
    }

    // Method to enroll a student in a course
    private static void enrollStudent() {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        Student student = new Student(studentName, studentId);

        System.out.print("Enter course name to enroll in: ");
        String courseName = scanner.nextLine();

        Course course = CourseManagement.getCourseByName(courseName);
        if (course != null) {
            CourseManagement.enrollStudent(student, course);
        } else {
            System.out.println("Course not found.");
        }
    }

    // Method to assign a grade to a student
    private static void assignGrade() {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        Student student = new Student(studentName, studentId);

        System.out.print("Enter course name to assign grade: ");
        String courseName = scanner.nextLine();

        Course course = CourseManagement.getCourseByName(courseName);
        if (course != null) {
            System.out.print("Enter grade for student: ");
            double grade = Double.parseDouble(scanner.nextLine());
            CourseManagement.assignGrade(student, course, grade);
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

        Student student = new Student(studentName, studentId);
        CourseManagement.calculateOverallGrade(student);
    }
}
