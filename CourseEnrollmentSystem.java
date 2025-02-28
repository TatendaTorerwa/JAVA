import java.util.Scanner;

class Student {
    private String name;
    private int id;
    private Course enrolledCourse;
    private double grade;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

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

    public Course getEnrolledCourse() {
        return enrolledCourse;
    }

    public double getGrade() {
        return grade;
    }

    public void enrollInCourse(Course course) {
        if (course.enrollStudent()) {
            this.enrolledCourse = course;
            System.out.println(name + " has been enrolled in " + course.getCourseName());
        } else {
            System.out.println("Enrollment failed. Course is full.");
        }
    }

    public void assignGrade(Course course, double grade) {
        if (this.enrolledCourse == course) {
            this.grade = grade;
            System.out.println("Grade assigned: " + grade + " for course " + course.getCourseName());
        } else {
            System.out.println("Student is not enrolled in this course.");
        }
    }
}


class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int enrolledStudents;
    private static int totalEnrolledStudents = 0;

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = 0;
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

    // Method to enroll a student in the course
    public boolean enrollStudent() {
        if (enrolledStudents < maxCapacity) {
            enrolledStudents++;
            totalEnrolledStudents++;
            return true;
        }
        return false;
    }

    // Static method to get the total number of enrolled students across all courses
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }
}



class CourseManagement {
    // Using simple arrays to store courses and student grades
    private static Course[] courses = new Course[10]; // Array to store courses
    private static int courseCount = 0; // Counter to keep track of courses added

    // Method to add a new course
    public static void addCourse(String courseCode, String courseName, int maxCapacity) {
        if (courseCount < courses.length) {
            Course course = new Course(courseCode, courseName, maxCapacity);
            courses[courseCount] = course;
            courseCount++;
            System.out.println("Course added: " + courseName);
        } else {
            System.out.println("Cannot add more courses, array is full.");
        }
    }

    // Method to enroll a student in a course
    public static void enrollStudent(Student student, Course course) {
        student.enrollInCourse(course);
    }

    // Method to assign a grade to a student for a course
    public static void assignGrade(Student student, Course course, double grade) {
        student.assignGrade(course, grade);
    }

    // Method to calculate overall grade for a student based on assigned grades
    public static double calculateOverallGrade(Student student) {
        if (student.getEnrolledCourse() == null) {
            System.out.println(student.getName() + " is not enrolled in any course.");
            return 0.0;
        }

        double grade = student.getGrade();
        System.out.println("Overall grade for " + student.getName() + ": " + grade);
        return grade;
    }

    // Method to get a course by its name (to use when enrolling or assigning grades)
    public static Course getCourseByName(String courseName) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCourseName().equals(courseName)) {
                return courses[i];
            }
        }
        return null;
    }
}


class AdministratorInterface {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();

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
                    scanner.close();
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    // Display the menu for the administrator
    private static void displayMenu() {
        System.out.println("\n==== Course Management System ====");
        System.out.println("1. Add New Course");
        System.out.println("2. Enroll Student in Course");
        System.out.println("3. Assign Grade to Student");
        System.out.println("4. Calculate Overall Grade for Student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Get the choice of the administrator from the menu
    private static int getUserChoice() {
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return choice;
    }

    // Add a new course to the system
    private static void addCourse() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter maximum capacity of the course: ");
        int maxCapacity = Integer.parseInt(scanner.nextLine());

        CourseManagement.addCourse(courseCode, courseName, maxCapacity);
    }

    // Enroll a student in a course
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

    // Assign a grade to a student
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

    // Calculate the overall grade for a student
    private static void calculateOverallGrade() {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        Student student = new Student(studentName, studentId);
        CourseManagement.calculateOverallGrade(student);
    }
}
