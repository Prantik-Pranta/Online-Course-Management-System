//pavel

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import elms.lib.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StuSignLogMenuController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField dayField;
    @FXML
    private TextField monthField;
    @FXML
    private TextField yearField;
    @FXML
    private Button signUpButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label errorLabel;

  
    @FXML
    private void signUp() {
        String name = nameField.getText();
        String day = dayField.getText();
        String month = monthField.getText();
        String year = yearField.getText();

        if (name.isEmpty() || day.isEmpty() || month.isEmpty() || year.isEmpty()) {
            showError("All fields are required for sign-up.");
            return;
        }

        try {
            int dayInt = Integer.parseInt(day);
            int monthInt = Integer.parseInt(month);
            int yearInt = Integer.parseInt(year);
            LocalDate dob = LocalDate.of(yearInt, monthInt, dayInt);

            String studentId = Main.mns.signUp(name, dob.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            
            showSignupSuccess(studentId);

            // Clear input fields after successful signup
            nameField.clear();
            dayField.clear();
            monthField.clear();
            yearField.clear();


        } catch (NumberFormatException e) {
            showError("Invalid date format. Please use numbers.");
        } catch (DateTimeParseException e) {
            showError("Invalid date. Please enter a valid date.");
        }
    }

    private void showSignupSuccess(String studentId) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Up Successful");
        alert.setHeaderText("Your Student ID:");
        alert.setContentText(studentId);
        alert.showAndWait();
    }

    @FXML
    private void gotoLogin(ActionEvent event) throws IOException {
        String id = idField.getText();
        if (id.isEmpty()) {
            showError("Please enter your Student ID.");
            return;
        }

        try {
            Student student = Main.mns.findStudent(id);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentListMenu.fxml")); // Replace with your FXML file
            Scene scene = new Scene(loader.load());

            // Assuming Main.stage is your primary stage
            Main.stage.setTitle("Online Course Management System"); // Set the title
            Main.stage.setScene(scene);
            Main.stage.show();



        } catch (InvalidStudentException e) {
            showError(e.getMessage());
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
    	FXMLLoaderUtil.loadScene(Main.stage, "MainStartMenu.fxml");
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    
}
