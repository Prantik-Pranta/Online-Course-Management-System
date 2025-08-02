
//jihad

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegViwAssProController {

    @FXML
    private TextField courseIdField;

    @FXML
    private TextField workshopIdField;

    @FXML
    private TextArea outputArea;

    @FXML
    private Button registerButton;

    @FXML
    private Button viewButton;

    @FXML
    private Button goBackButton;

    @FXML
    private void handleRegister(ActionEvent event) {
        String courseId = courseIdField.getText();
        if (courseId.isEmpty()) {
            showAlert("Error", "Please enter a Course/Workshop ID to register.");
        } else {
            showAlert("Registration Successful", "You have been registered for Course/Workshop ID: " + courseId);
            outputArea.appendText("Registered for: " + courseId + "\n");
        }
    }

    @FXML
    private void handleView(ActionEvent event) {
        String workshopId = workshopIdField.getText();
        if (workshopId.isEmpty()) {
            showAlert("Error", "Please enter a Workshop ID to view assigned projects.");
        } else {
            String assignedProjects = "Workshop ID: " + workshopId + "\nAssigned Projects:\n- Project A\n- Project B\n";
            outputArea.setText(assignedProjects);
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
