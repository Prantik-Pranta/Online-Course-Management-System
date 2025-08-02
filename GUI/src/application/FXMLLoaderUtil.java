//pranta

package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLLoaderUtil {

    public static void loadScene(Stage stage, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(FXMLLoaderUtil.class.getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Online Course Management System");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showErrorAlert("Error Loading View", "Could not load the requested view.");
            e.printStackTrace();
        }
    }

    private static void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
