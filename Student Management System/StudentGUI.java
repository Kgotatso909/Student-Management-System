import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StudentGUI extends Application {
    private TableView<Student> tableView;
    private TextField textField;
    private TextField campusField;
    private TextField facultyField;
    private TextField nameField;
    private TextField idField;
    private TextField statusField;
    private Scene mainScene;
    private Scene addStudentScene;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Student Data");

        // Create table columns
        TableColumn<Student, String> campusColumn = new TableColumn<>("Campus");
        campusColumn.setCellValueFactory(new PropertyValueFactory<>("campus"));

        TableColumn<Student, String> facultyColumn = new TableColumn<>("Faculty");
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Create table view
        tableView = new TableView<>();
        tableView.getColumns().addAll(campusColumn, facultyColumn, nameColumn, idColumn, statusColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Create TextField
        textField = new TextField();
        textField.setEditable(false);

        // Create button to display students
        Button displayButton = new Button("Display Students");
        displayButton.setOnAction(e -> displayStudents());

        // Create button to switch to add student scene
        Button addStudentButton = new Button("Add Student");
        addStudentButton.setOnAction(e -> primaryStage.setScene(addStudentScene));

        // Create layout for main scene
        VBox mainVBox = new VBox(tableView, textField, displayButton, addStudentButton);
        mainVBox.setPadding(new Insets(10));
        mainVBox.setSpacing(10);
        mainScene = new Scene(mainVBox);

        // Create input fields for adding a student
        campusField = new TextField();
        campusField.setPromptText("Campus");
        facultyField = new TextField();
        facultyField.setPromptText("Faculty");
        nameField = new TextField();
        nameField.setPromptText("Name");
        idField = new TextField();
        idField.setPromptText("ID");
        statusField = new TextField();
        statusField.setPromptText("Status");

        // Create button to add student
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addStudent());

        // Create button to switch back to main scene
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));

        // Create layout for add student scene
        VBox addStudentVBox = new VBox(campusField, facultyField, nameField, idField, statusField, addButton, backButton);
        addStudentVBox.setPadding(new Insets(10));
        addStudentVBox.setSpacing(10);
        addStudentScene = new Scene(addStudentVBox);

        // Set main scene as the initial scene
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void displayStudents() {
    // Load data from file
    ObservableList<Student> students = readFile("student.txt");
    tableView.setItems(students);

    // Display students in the TextField
    StringBuilder stringBuilder = new StringBuilder();
    for (Student student : students) {
        stringBuilder.append(student.toString()).append("\n");
    }
    textField.setText(stringBuilder.toString());
}

private void addStudent() {
    String campus = campusField.getText();
    String faculty = facultyField.getText();
    String name = nameField.getText();
    String id = idField.getText();
    String status = statusField.getText();

    Student newStudent = new Student(campus, faculty, name, id, status);
    tableView.getItems().add(newStudent);

    campusField.clear();
    facultyField.clear();
    nameField.clear();
    idField.clear();
    statusField.clear();

    primaryStage.setScene(mainScene);
}

private ObservableList<Student> readFile(String filename) {
    ObservableList<Student> students = FXCollections.observableArrayList();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            String campus = values[0];
            String faculty = values[1];
            String name = values[2]; 
            String id = values[3];
            String status = values[4];
            Student student = new Student(campus, faculty, name, id, status);
            students.add(student);
        }
    } catch (FileNotFoundException e) {
        System.err.println("File Not Found");
        e.printStackTrace();
    } catch (IOException e) {
        System.err.println("You do not have access to read the file");
        e.printStackTrace();
    }

    return students;
}
}
