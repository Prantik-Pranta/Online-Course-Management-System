

//jihad

package application;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import elms.lib.Course;
import elms.lib.DataHandler;
import elms.lib.InvalidItemException;
import elms.lib.InvalidStudentException;
import elms.lib.Item;
import elms.lib.Student;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
public class ViwSrcCouWrkController {

	Item c;
	   @FXML
	    private TextField courseworkshopSearchIdTf;
	    @FXML
	    private TableView<String[]> regStu;
	    @FXML
	    private TableColumn<String[], String> regStuId;
	    @FXML
	    private TableColumn<String[], String> regStuName;

	    ObservableList<String[]> nameList;
	@FXML
	Label courseStatusLabel = new Label();

	
	@FXML
	Button startBtn = new Button();
	@FXML
	Button compBtn = new Button();

	
	@FXML
	public void initialize() {
		nameList = FXCollections.observableArrayList();

		regStuId.setCellValueFactory(
				cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));
		regStuName.setCellValueFactory(
				cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));

		// Bind the ObservableList to the TableView
		regStu.setItems(nameList);

		startBtn.setDisable(true);
		compBtn.setDisable(true);

	}

	public void search(ActionEvent event) {

		try {
			nameList.clear();

			String searchId = courseworkshopSearchIdTf.getText();
			c = Main.mns.findItem(searchId);

			ArrayList<String> regStu = c.getRegisteredStudentIds();

			String courseStatus = c.getStatus();

			if (c instanceof Course) {
				courseStatusLabel.setText("Course Status: " + courseStatus);
			} else if (c instanceof WorkShop) {
				courseStatusLabel.setText("Workshop Status: " + courseStatus);
			}

			if (c != null && c.getStatus().equalsIgnoreCase("scheduled")) {
				startBtn.setDisable(false);
				compBtn.setDisable(true);

			}

			if (c != null && c.getStatus().equalsIgnoreCase("in progress")) {
				startBtn.setDisable(true);
				compBtn.setDisable(false);
			}

			if (c != null && c.getStatus().equalsIgnoreCase("complete")) {
				startBtn.setDisable(true);
				compBtn.setDisable(true);
			}

			for (String s : regStu) {
				String sId = Main.mns.findStudent(s).getId();
				String sName = Main.mns.findStudent(s).getName();
				nameList.add(new String[] { sId, sName });
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error.");
		}

	}

	public void start(ActionEvent event) throws InvalidItemException, IOException, ClassNotFoundException {

		Main.mns.startCourse(c.getId());

		if (c instanceof Course) {
			courseStatusLabel.setText("Course Status: " + c.getStatus());
		} else if (c instanceof WorkShop) {
			courseStatusLabel.setText("Workshop Status: " + c.getStatus());
		}

		startBtn.setDisable(true);
		compBtn.setDisable(false);

		DataHandler.saveData(Main.mns);

	}

	public void complete(ActionEvent event)
			throws InvalidItemException, ClassNotFoundException, IOException, InvalidStudentException {

		Main.mns.completeCourse(c.getId());

		if (c instanceof Course) {
			courseStatusLabel.setText("Course Status: " + c.getStatus());
		} else if (c instanceof WorkShop) {
			courseStatusLabel.setText("Workshop Status: " + c.getStatus());
		}

		for (String id : c.getRegisteredStudentIds()) {
			Student s = Main.mns.findStudent(id);
			s.completeCourse(c.getId());

		}
		c.getRegisteredStudentIds().clear();
		nameList.clear();

		startBtn.setDisable(true);
		compBtn.setDisable(true);

		DataHandler.saveData(Main.mns);
	}

	public void gotoStudentMenu(ActionEvent event) throws IOException {

		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		Parent root = FXMLLoader.load(getClass().getResource("StudentListMenu.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("UAP - Admin Menu - Add Topic/Project To Course/Workshop");
		stage.setScene(scene);
		stage.show();

	}

	public void logout(ActionEvent event) throws IOException {
		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		Parent root = FXMLLoader.load(getClass().getResource("MainStartMenu.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("UAP - Login");
		stage.setScene(scene);
		stage.show();
	}
}
