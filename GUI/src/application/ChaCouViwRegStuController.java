
//easha

package application;

import elms.lib.InvalidItemException;
import elms.lib.InvalidStudentException;
import elms.lib.ManagementSystem;
import elms.lib.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ChaCouViwRegStuController {

    @FXML
    private TextField courseIdField;

    @FXML
    private RadioButton startCourseRadio;

    @FXML
    private RadioButton completeCourseRadio;

    @FXML
    private RadioButton viewRegisteredRadio;

    private ManagementSystem managementSystem; // Instance of ManagementSystem

    public void setManagementSystem(ManagementSystem managementSystem) {
        this.managementSystem = managementSystem;
    }

    @FXML
    private void handleStartCourse(ActionEvent event) {
        if (startCourseRadio.isSelected()) {
            String courseId = courseIdField.getText();
            try {
                managementSystem.startCourse(courseId); // Start the course
                showAlert("Start Course", "The course with ID " + courseId + " has been started.");
            } catch (InvalidItemException e) {
                showErrorAlert("Error", e.getMessage()); // Show error message if the course ID is invalid
            }
        } else {
            showAlert("Error", "Please select the 'Start Course' radio button.");
        }
    }

    @FXML
    private void handleCompleteCourse(ActionEvent event) {
        if (completeCourseRadio.isSelected()) {
            String courseId = courseIdField.getText();
            try {
                managementSystem.completeCourse(courseId); // Mark the course as completed
                showAlert("Complete Course", "The course with ID " + courseId + " has been marked as completed.");
            } catch (InvalidItemException e) {
                showErrorAlert("Error", e.getMessage()); // Show error message if the course ID is invalid
            }
        } else {
            showAlert("Error", "Please select the 'Complete Course' radio button.");
        }
    }

    @FXML
    private void handleViewRegistered(ActionEvent event) throws InvalidStudentException {
        if (viewRegisteredRadio.isSelected()) {
            String courseId = courseIdField.getText();
            try {
                // Assuming that getRegisteredStudentInfo returns a list of student names or IDs
                StringBuilder registeredStudents = new StringBuilder("Registered students for Course ID: " + courseId + "\n");
                for (Student student : Main.mns.getRegisteredStudentInfo(courseId)) {
                    registeredStudents.append(student.getId()).append(" - ").append(student.getName()).append("\n");
                }
                showAlert("View Registered Students", registeredStudents.toString());
            } catch (InvalidItemException e) {
                showErrorAlert("Error", e.getMessage());
            }
        } else {
            showAlert("Error", "Please select the 'View Registered' radio button.");
        }
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
    	 FXMLLoaderUtil.loadScene(Main.stage, "AdminListMenu.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
