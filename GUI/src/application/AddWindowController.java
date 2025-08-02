
//easha

package application;

import elms.lib.ManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddWindowController {

    @FXML
    private TextField courseTitleField;
    @FXML
    private TextField instructorField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField capacityField;
    @FXML
    private TextField startDayField;
    @FXML
    private TextField startMonthField;
    @FXML
    private TextField startYearField;

    private ManagementSystem managementSystem;

    public void setManagementSystem(ManagementSystem managementSystem) {
        this.managementSystem = managementSystem;
    }

    @FXML
    private void handleAddCourse(ActionEvent event) {
        String courseTitle = courseTitleField.getText();
        String instructor = instructorField.getText();
        String duration = durationField.getText();
        String capacity = capacityField.getText();
        String startDateInput = startDayField.getText() + "/" + startMonthField.getText() + "/" + startYearField.getText();

        try {
            LocalDate startDate = LocalDate.parse(startDateInput, DateTimeFormatter.ofPattern("d/M/yyyy"));
            int durationInt = Integer.parseInt(duration);
            int capacityInt = Integer.parseInt(capacity);

            String courseId = Main.mns.offerCourse(courseTitle, instructor, startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), durationInt, capacityInt);
            showAlert("Course Added", "Course added successfully with ID: " + courseId);
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Duration and capacity must be numbers.");
        } catch (DateTimeParseException e) {
            showAlert("Input Error", "Invalid date format. Use dd/mm/yyyy.");
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    private void handleAddWorkshop(ActionEvent event) {
    	String courseTitle = courseTitleField.getText();
        String instructor = instructorField.getText();
        String duration = durationField.getText();
        String capacity = capacityField.getText();
        String startDateInput = startDayField.getText() + "/" + startMonthField.getText() + "/" + startYearField.getText();

        try {
            LocalDate startDate = LocalDate.parse(startDateInput, DateTimeFormatter.ofPattern("d/M/yyyy"));
            int durationInt = Integer.parseInt(duration);
            int capacityInt = Integer.parseInt(capacity);

            String courseId = Main.mns.offerCourse(courseTitle, instructor, startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), durationInt, capacityInt);
            showAlert("Course Added", "Course added successfully with ID: " + courseId);
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Duration and capacity must be numbers.");
        } catch (DateTimeParseException e) {
            showAlert("Input Error", "Invalid date format. Use dd/mm/yyyy.");
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
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
}
