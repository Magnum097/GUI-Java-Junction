package demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * This Java file is for demonstration purpose without the consideration of 
 * modularity. The following code demonstrates
 * 		1. Lambda expression.
 * 		2. Java inner class.
 * 		3. Create a scene graph without the SceneBuilder and the fxml file.
 * 	    4. Create a new stage and a scene, and show the new stage (window)
 * 	    5. Transformations of a Node
 * 
 * @author Lily Chang
 *
 */
public class CodeWithoutFXML extends Application {
	TextField tf1;
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.TOP_LEFT);

		Button bt1 = new Button("Click me.");
		tf1 = new TextField("Hello!.");

		hbox.getChildren().add(bt1); //add bt1 as a child node of hbox
		hbox.getChildren().add(tf1); //add tf1 as a child node of hbox
		ImageView imview = new ImageView("file:germany.gif");
//		imview.setTranslateY(100);
//		imview.setTranslateX(100);
//		imview.setRotate(90);
//		imview.setScaleY(2);
		hbox.getChildren().add(imview); //add imview as a child node of hbox

		Scene scene = new Scene(hbox, 600, 600); // hbox is the root of the scene graph
		primaryStage.setTitle("JavaFX Example 1"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		// create an instance of the inner class to handle the ActionEvent
		bt1.setOnAction(new Button1EventHandler());  
	}

	/**
	 * An inner class defined to handle ActionEvent.
	 */
	class Button1EventHandler implements EventHandler<ActionEvent> {
		/**
		 * Implement the abstract method to handle the ActionEvent fired by clicking the button
		 */
		@Override
		public void handle(ActionEvent event) {
			tf1.setText("Hello again!!");
			GridPane newpane = new GridPane();
			Button bt2 = new Button("OK");
			// bt2.setRotate(90);
			TextField tf2 = new TextField("2nd stage");
			newpane.add(bt2,0,1);
			newpane.add(tf2,1,1);
			tf2.setPrefWidth(100);
			ImageView imview3 = new ImageView("file:ca.gif");

			Label label = new Label("this is a label with image on top", imview3);
			label.setContentDisplay(ContentDisplay.TOP);
			ImageView imview2 = new ImageView("file:france.bmp");
			
			//Transformation properties
			// imview3.setTranslateX(100);
			// imview3.setRotate(30);
			// imview3.setScaleY(2);
			// newpan3.add(imview2, 2, 1);
			newpane.add(label,2,1);
			Scene newscene = new Scene(newpane, 500, 500);
			newpane.setAlignment(Pos.CENTER);
			
			//if you use a fxml file, then use the FXMLoader to load the fxml file
			//FXMLLoader loader = new FXMLLoader(getClass().getResource("myView.fxml"));
			//root = (BorderPane) loader.load(); //if BorderPane is the root Node
			//Scene newscene = new Scene(root, 400, 400);
			
			//create a new stage (second window)
			Stage newstage = new Stage();
			newstage.setTitle("This is the second stage");
			newstage.setScene(newscene);
			newstage.show();
			
			//Lambda expression to handle another button click event
			// bt2.setOnAction(e1 -> { tf2.setText("Button clicked!"); });
			bt2.setOnAction(e1 -> { 
				bt2.setContentDisplay(ContentDisplay.TOP);
				//imview2.setRotate(30);
				bt2.setGraphic(imview2);});

		}
	}
	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
