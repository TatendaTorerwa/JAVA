import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

public class EmployeeProcessor {
    public static void main(String[] args) {
        // Step 1: Creating a list of employees
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 28, "IT", 70000),
                new Employee("Bob", 35, "HR", 55000),
                new Employee("Charlie", 40, "Finance", 90000),
                new Employee("David", 32, "IT", 75000),
                new Employee("Eve", 29, "Marketing", 62000)
        );

        // Step 2: Function Interface Implementation for Concatenation
        Function<Employee, String> nameAndDeptFunction = emp -> emp.getName() + " - " + emp.getDepartment();

        // Step 3: Generating new collection with concatenated name and department
        List<String> nameAndDepartmentList = employees.stream()
                .map(nameAndDeptFunction)
                .collect(Collectors.toList());

        // Step 4: Calculating the average salary of all employees
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        // Step 5: Filtering employees older than 30
        List<Employee> filteredEmployees = employees.stream()
                .filter(emp -> emp.getAge() > 30)
                .collect(Collectors.toList());

        // Output Results
        System.out.println("Concatenated Name and Department:");
        nameAndDepartmentList.forEach(System.out::println);
        
        System.out.println("\nAverage Salary: " + averageSalary);
        
        System.out.println("\nEmployees above age 30:");
        filteredEmployees.forEach(emp -> 
                System.out.println(emp.getName() + " (" + emp.getAge() + ") - " + emp.getDepartment())
        );
    }
}
