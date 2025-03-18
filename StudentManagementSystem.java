import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentManagementSystem extends Application {

    private TableView<Student> studentTable;
    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management System");

        // Menu
        MenuBar menuBar = new MenuBar();

        Menu studentMenu = new Menu("Students");
        MenuItem addStudent = new MenuItem("Add Student");
        MenuItem updateStudent = new MenuItem("Update Student");
        MenuItem viewStudents = new MenuItem("View Students");
        studentMenu.getItems().addAll(addStudent, updateStudent, viewStudents);

        Menu courseMenu = new Menu("Courses");
        MenuItem enrollStudent = new MenuItem("Enroll Student");
        courseMenu.getItems().add(enrollStudent);

        Menu gradeMenu = new Menu("Grades");
        MenuItem assignGrade = new MenuItem("Assign Grade");
        gradeMenu.getItems().add(assignGrade);

        menuBar.getMenus().addAll(studentMenu, courseMenu, gradeMenu);

        // Event Handlers
        addStudent.setOnAction(e -> showAddStudentDialog());
        updateStudent.setOnAction(e -> showUpdateStudentDialog());
        viewStudents.setOnAction(e -> showViewStudentsDialog());
        enrollStudent.setOnAction(e -> showEnrollStudentDialog());
        assignGrade.setOnAction(e -> showAssignGradeDialog());

        // Layout
        VBox layout = new VBox(menuBar);
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Add Student Dialog
    private void showAddStudentDialog() {
        Dialog<Student> dialog = new Dialog<>();
        dialog.setTitle("Add Student");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nameField = new TextField();
        TextField ageField = new TextField();
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Age:"), 0, 1);
        grid.add(ageField, 1, 1);

        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(button -> {
            if (button == addButton) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                studentList.add(new Student(name, age));
                showSuccess("Student added successfully.");
            }
            return null;
        });

        dialog.showAndWait();
    }

    // Update Student Dialog
    private void showUpdateStudentDialog() {
        Dialog<Student> dialog = new Dialog<>();
        dialog.setTitle("Update Student");

        ComboBox<Student> studentComboBox = new ComboBox<>(studentList);
        TextField nameField = new TextField();
        TextField ageField = new TextField();

        studentComboBox.setOnAction(e -> {
            Student selected = studentComboBox.getValue();
            if (selected != null) {
                nameField.setText(selected.getName());
                ageField.setText(String.valueOf(selected.getAge()));
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Select Student:"), 0, 0);
        grid.add(studentComboBox, 1, 0);
        grid.add(new Label("New Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("New Age:"), 0, 2);
        grid.add(ageField, 1, 2);

        ButtonType updateButton = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButton, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(button -> {
            if (button == updateButton) {
                Student selected = studentComboBox.getValue();
                if (selected != null) {
                    selected.setName(nameField.getText());
                    selected.setAge(Integer.parseInt(ageField.getText()));
                    showSuccess("Student updated successfully.");
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    // View Students
    private void showViewStudentsDialog() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("View Students");

        studentTable = new TableView<>();
        studentTable.setItems(studentList);

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        TableColumn<Student, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(data -> data.getValue().ageProperty().asObject());

        // Add columns one by one to avoid the varargs warning
        studentTable.getColumns().add(nameColumn);
        studentTable.getColumns().add(ageColumn);

        dialog.getDialogPane().setContent(studentTable);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }

    // Enroll Student in Course
    private void showEnrollStudentDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Enroll Student");

        ComboBox<Student> studentComboBox = new ComboBox<>(studentList);
        ComboBox<String> courseComboBox = new ComboBox<>();
        courseComboBox.getItems().addAll("Mathematics", "Computer Science");

        VBox layout = new VBox(10, new Label("Student:"), studentComboBox, new Label("Course:"), courseComboBox);
        dialog.getDialogPane().setContent(layout);

        ButtonType enrollButton = new ButtonType("Enroll", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(enrollButton, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == enrollButton && studentComboBox.getValue() != null && courseComboBox.getValue() != null) {
                showSuccess(studentComboBox.getValue().getName() + " enrolled in " + courseComboBox.getValue());
            }
            return null;
        });

        dialog.showAndWait();
    }

    // Assign Grade
    private void showAssignGradeDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Assign Grade");

        ComboBox<Student> studentComboBox = new ComboBox<>(studentList);
        ComboBox<String> courseComboBox = new ComboBox<>();
        courseComboBox.getItems().addAll("Mathematics", "Computer Science");
        TextField gradeField = new TextField();

        VBox layout = new VBox(10, new Label("Student:"), studentComboBox, new Label("Course:"), courseComboBox, new Label("Grade:"), gradeField);
        dialog.getDialogPane().setContent(layout);

        ButtonType assignButton = new ButtonType("Assign", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(assignButton, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == assignButton && studentComboBox.getValue() != null && courseComboBox.getValue() != null && !gradeField.getText().isEmpty()) {
                showSuccess("Assigned grade " + gradeField.getText());
            }
            return null;
        });

        dialog.showAndWait();
    }

    // Alert Messages
    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

// Student Model
class Student {
    private final StringProperty name;
    private final IntegerProperty age;

    public Student(String name, int age) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
    }

    public StringProperty nameProperty() { return name; }
    public IntegerProperty ageProperty() { return age; }
    public String getName() { return name.get(); }
    public int getAge() { return age.get(); }
    public void setName(String name) { this.name.set(name); }
    public void setAge(int age) { this.age.set(age); }
}
