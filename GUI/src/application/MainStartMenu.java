package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MainStartMenu {

    @FXML
    private Button adminLoginButton;
    @FXML
    private Button studentLoginButton;
    @FXML
    private Button exitButton;
//**************************************************************************
    @FXML
    private void handleAdminLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminListMenu.fxml"));
            
            Scene scene = new Scene(loader.load());
            
            Main.stage.setTitle("Online Course Management System");
            
            Main.stage.setScene(scene); 
                       
            Main.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleStudentLogin(ActionEvent event) {
    	   try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("StuSignLogMenu.fxml"));
               
               Scene scene = new Scene(loader.load());
               
               Main.stage.setTitle("Online Course Management System");
               Main.stage.setScene(scene);
               Main.stage.show();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
        
    

    @FXML
    private void handleExit(ActionEvent event) {
        System.out.println("Exit Button Clicked");
        System.exit(0); 
    }
}