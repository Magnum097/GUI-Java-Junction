package demo;

import java.io.File;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert.AlertType;

public class SampleController {

    @FXML
    private Button addButton, clearButton;

    @FXML
    private TextField int1, int2, sum;

    @FXML
    private TextArea messageArea;

    @FXML
    /**
     * Event Handler for the add button
     * @param event
     */
    void add(ActionEvent event) {
    	//messageArea.clear(); //clear the TextArea.
    	try {
    		int result = Integer.parseInt(int1.getText()) + Integer.parseInt(int2.getText());
    		sum.setText(String.valueOf(result));
    	}
    	//Show the error message with a pop-up window.
    	catch (NumberFormatException e) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Data");
			alert.setHeaderText("Non-numeric data has been entered.");
			alert.setContentText("Please enter an integer.");
			alert.showAndWait();
//			Optional<ButtonType> result = alert.showAndWait();
//			if (result.isPresent() && result.get() == ButtonType.OK) {
//			      
//			}
//			else;
    	}
    }
    
    @FXML
    void checkInteger1(MouseEvent event) {
    	//messageArea.clear();
    	try {
    		int integer = Integer.parseInt(int1.getText()); 
    	}
    	//Show the error message in the TextArea.
    	catch (NumberFormatException e) {
    		messageArea.appendText("Must enter an integer.\n");
    		return;
    	}
    }
    
    @FXML
    void checkInteger2(KeyEvent event) {
    	//messageArea.clear();
    	if (event.getCode().equals(KeyCode.ENTER)) { //check if the ENTER key is pressed
    		try {
    			int integer = Integer.parseInt(int1.getText()); 
    		}
    		catch (NumberFormatException e) {
    			messageArea.appendText("Not an integer.\n");
    			return;
    		}
    	}
    }
    
    @FXML
    void importFile(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Source File for the Import");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
		//write code to read from the file.
    }
    
    @FXML
    void exportFile(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Target File for the Export");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File targeFile = chooser.showSaveDialog(stage); //get the reference of the target file
		//write code to write to the file.
    }
    
    @FXML
    void clear(ActionEvent event) {
    	int1.clear();
    	int2.clear();
    	sum.clear();
    	messageArea.clear();
    }

}
