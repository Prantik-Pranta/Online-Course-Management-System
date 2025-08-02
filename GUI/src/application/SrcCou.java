//hemal

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

public class SrcCou {

    @FXML
    private TextField courseTitleField;

    @FXML
    private TextArea searchResultsArea;

    @FXML
    private Button searchButton;

    @FXML
    private Button goBackButton;

    @FXML
    private void handleSearch(ActionEvent event) {
        String courseTitle = courseTitleField.getText();
        if (courseTitle.isEmpty()) {
            showAlert("Error", "Please enter a Course Title to search.");
        } else {
            String result = "Search Results for \"" + courseTitle + "\":\n" +
                    "- Course ID: 101, Title: Intro to Java\n" +
                    "- Course ID: 102, Title: Advanced Java Programming\n";
            searchResultsArea.setText(result);
        }
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
    	 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListMenu.fxml"));
             
             Scene scene = new Scene(loader.load());
             
             Main.stage.setTitle("Online Course Management System");
             Main.stage.setScene(scene);
             Main.stage.show();
         } catch (Exception e) {
             e.printStackTrace();
         }    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
