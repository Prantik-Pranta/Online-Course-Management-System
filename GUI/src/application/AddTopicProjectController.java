
//hemal
package application;

import java.io.IOException;

import javax.swing.JOptionPane;

import elms.lib.Course;
import elms.lib.Item;
import elms.lib.WorkShop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AddTopicProjectController {
	Item c;
	Course co;
	
	@FXML
	Button addTopicBtn = new Button();

	@FXML
	Button addProjectBtn = new Button();

	@FXML
	TextField courseworkshopIdSearchTf = new TextField();
	@FXML
	TextField topicprojectNameTf = new TextField();

		 @FXML
	    private TableView<String[]> courseDet;
	    @FXML
	    private TableColumn<String[], String> courseID;
	    @FXML
	    private TableColumn<String[], String> courseTitle;
	    @FXML
	    private TableColumn<String[], String> courseInstructor;
	    @FXML
	    private TableColumn<String[], String> courseStart;
	    @FXML
	    private TableColumn<String[], String> courseDuration;
	    @FXML
	    private TableColumn<String[], String> courseCapacity;

	    ObservableList<String[]> detList;

	    @FXML
	    public void initialize() {
	        detList = FXCollections.observableArrayList();
	        courseID.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
	        courseTitle.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));
	        courseInstructor.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));
	        courseStart.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[3]));
	        courseDuration.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[4]));
	        courseCapacity.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[5]));
	        courseDet.setItems(detList);
	    }
	    

	public void search(ActionEvent event) throws Exception {
		detList.clear();
		String searchId = courseworkshopIdSearchTf.getText();
		c = Main.mns.findItem(searchId);
		String date = c.getStartDate().toString();
		String fixDate = "" + date.charAt(8) + date.charAt(9) + "-" + date.charAt(5) + date.charAt(6) + "-"
				+ date.charAt(0) + date.charAt(1) + date.charAt(2) + date.charAt(3);
		detList.add(new String[] { c.getId(), c.getTitle(), c.getInstructor(), fixDate, c.getDuration() + "",
				c.getCapacity() + "" });
		if (c instanceof Course) {
			addTopicBtn.setDisable(false);
		} else if (c instanceof WorkShop) {
			addProjectBtn.setDisable(false);
		}
		

	}

	public void addTopic(ActionEvent event) {
		String topic = topicprojectNameTf.getText();
		co = (Course) c;
		co.addTopic(topic);
		JOptionPane.showMessageDialog(null, topic + " added to " + co.getTitle());
		topicprojectNameTf.clear();

	}

	public void assignProject(ActionEvent event) {
		String topic = topicprojectNameTf.getText();
		co = (Course) c;
		co.addTopic(topic);
		JOptionPane.showMessageDialog(null, topic + " added to " + co.getTitle()+ " in workshop");
		topicprojectNameTf.clear();
	}

	public void gotoAdminMenu(ActionEvent event) throws IOException {

		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		Parent root = FXMLLoader.load(getClass().getResource("AdminListMenu.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("UAP - Admin Menu");
		stage.setScene(scene);
		stage.show();

	}

	
}
