

package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import elms.lib.DataHandler;
import elms.lib.ManagementSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage stage;
	
	public static ManagementSystem mns=new ManagementSystem("CSE");
	
	//Main.mns
	

    @Override
    public void start(Stage primaryStage) {
    	stage = primaryStage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainStartMenu.fxml"));
            
            Scene scene = new Scene(loader.load());
            
            primaryStage.setTitle("Online Course Management System");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
    	try {
			mns = DataHandler.loadData();
		} catch (FileNotFoundException e) {
			DataHandler.saveData(mns);
		}
    	
    	
    	launch(args);
        
        
    }
}
