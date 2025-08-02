
//pranta
package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminListMenuController {

    private void loadScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            Main.stage.setTitle("Online Course Management System");
            Main.stage.setScene(scene);
            Main.stage.show();
        } catch (IOException e) {
            showErrorAlert("Error Loading View", "Could not load the requested view.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddCourseWorkshop(ActionEvent event) {
        loadScene("AddWindow.fxml");
    }

    @FXML
    private void handleChangeCourseStatus(ActionEvent event) {
        loadScene("ChaCouViwRegStu.fxml");
    }

    @FXML
    private void handleSearchCourse(ActionEvent event) {
        loadScene("SrcCou.fxml");
    }

    @FXML
    private void handleAddTopicProject(ActionEvent event) {
        loadScene("AddTopicProject.fxml");
    }

    @FXML
    private void handleLogOut(ActionEvent event) {
    	FXMLLoaderUtil.loadScene(Main.stage, "MainStartMenu.fxml");
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
