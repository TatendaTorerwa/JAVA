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

    public int getId() {
        return id;
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
        if (this.enrolledCourse != null && this.enrolledCourse == course) {
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

    public boolean enrollStudent() {
        if (enrolledStudents < maxCapacity) {
            enrolledStudents++;
            totalEnrolledStudents++;
            return true;
        }
        return false;
    }

    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }
}

class CourseManagement {
    private static Course[] courses = new Course[10];
    private static int courseCount = 0;

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
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n==== Course Management System ====");
        System.out.println("1. Add New Course");
        System.out.println("2. Enroll Student in Course");
        System.out.println("3. Assign Grade to Student");
        System.out.println("4. Calculate Overall Grade for Student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return choice;
    }

    private static void addCourse() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter maximum capacity of the course: ");
        int maxCapacity = Integer.parseInt(scanner.nextLine());

        CourseManagement.addCourse(courseCode, courseName, maxCapacity);
    }

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
            student.enrollInCourse(course);  // Ensure the student is enrolled first
        } else {
            System.out.println("Course not found.");
        }
    }

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
            // Enroll the student first (if not already enrolled)
            student.enrollInCourse(course);
            System.out.print("Enter grade for student: ");
            double grade = Double.parseDouble(scanner.nextLine());
            student.assignGrade(course, grade);
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void calculateOverallGrade() {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        Student student = new Student(studentName, studentId);

        System.out.print("Enter course name to calculate grade: ");
        String courseName = scanner.nextLine();

        Course course = CourseManagement.getCourseByName(courseName);
        if (course != null) {
            // Enroll the student first (if not already enrolled)
            student.enrollInCourse(course);
            double grade = student.getGrade();
            System.out.println("Overall grade for " + student.getName() + ": " + grade);
        } else {
            System.out.println("Course not found.");
        }
    }
}
