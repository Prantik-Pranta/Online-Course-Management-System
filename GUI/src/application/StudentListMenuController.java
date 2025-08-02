
//pavel

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class StudentListMenuController {

    @FXML
    private Button registerViewProjectsButton;

    @FXML
    private Button viewCoursesButton;

    @FXML
    private Button searchCoursesWorkshopsButton;

    @FXML
    private Button logOutButton;

    @FXML
    private void handleRegisterViewProjects(ActionEvent event) {
    	 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("RegViwAssPro.fxml"));
             
             Scene scene = new Scene(loader.load());
             
             Main.stage.setTitle("Online Course Management System");
             Main.stage.setScene(scene);
             Main.stage.show();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    @FXML
    private void handleViewCourses(ActionEvent event) {
    	 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ViwCouWin.fxml"));
             
             Scene scene = new Scene(loader.load());
             
             Main.stage.setTitle("Online Course Management System");
             Main.stage.setScene(scene);
             Main.stage.show();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    @FXML
    private void handleSearchCoursesWorkshops(ActionEvent event) {
    	 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ViwSrcCouWrk.fxml"));
             
             Scene scene = new Scene(loader.load());
             
             Main.stage.setTitle("Online Course Management System");
             Main.stage.setScene(scene);
             Main.stage.show();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    @FXML
    private void handleLogOut(ActionEvent event) {
    	FXMLLoaderUtil.loadScene(Main.stage, "MainStartMenu.fxml");
    }
}
