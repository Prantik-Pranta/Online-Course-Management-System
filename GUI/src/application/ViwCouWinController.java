


//hemal



package application;

import elms.lib.InvalidItemException;
import elms.lib.InvalidStudentException;
import elms.lib.Item;
import elms.lib.ManagementSystem;
import elms.lib.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViwCouWinController {

    @FXML
    private TextArea coursesTextArea;

    @FXML
    private Button viewRegisteredCoursesButton;

    @FXML
    private Button viewCompletedCoursesButton;

    @FXML
    private Button goBackButton;

    private Student currentStudent; // Store the current student

    public void setCurrentStudent(Student student) {
        this.currentStudent = student; // Inject the current student into the controller
    }

    @FXML
    private void handleViewRegisteredCourses(ActionEvent event) throws InvalidItemException {
        if (currentStudent == null) {
            showAlert("Error", "No student is currently logged in.");
            return;
        }

        try {
            ArrayList<Item> registeredCourses = Main.mns.getRegisteredCourseInfo(currentStudent.getId());
            StringBuilder courses = new StringBuilder("Registered Courses:\n");
            if (registeredCourses.isEmpty()) {
                courses.append("No registered courses found.");
            } else {
                for (Item course : registeredCourses) {
                    courses.append(course.getTitle()).append(" (ID: ").append(course.getId()).append(")\n");
                }
            }
            coursesTextArea.setText(courses.toString());
            showAlert("Registered Courses", "Registered courses are displayed in the text area.");
        } catch (InvalidStudentException e) {
            showAlert("Error", e.getMessage());
        }
    }

    @FXML
    private void handleViewCompletedCourses(ActionEvent event) throws InvalidItemException {
        if (currentStudent == null) {
            showAlert("Error", "No student is currently logged in.");
            return;
        }

        try {
            ArrayList<Item> completedCourses = Main.mns.getCompletedCourseInfo(currentStudent.getId());
            StringBuilder courses = new StringBuilder("Completed Courses:\n");
            if (completedCourses.isEmpty()) {
                courses.append("No completed courses found.");
            } else {
                for (Item course : completedCourses) {
                    courses.append(course.getTitle()).append(" (ID: ").append(course.getId()).append(")\n");
                }
            }
            coursesTextArea.setText(courses.toString());
            showAlert("Completed Courses", "Completed courses are displayed in the text area.");
        } catch (InvalidStudentException e) {
            showAlert("Error", e.getMessage());
        }
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentListMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Main.stage.setTitle("Online Course Management System");
            Main.stage.setScene(scene);
            Main.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
