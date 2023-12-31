package com.example.project4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Class to instantiate a mainViewController and its interfaces and execute its run() method to start the program.
 * @author Minseok Park, Amogh Sarangdhar
 */
public class MainApplication extends Application {

    /**
     * start method which initiates the program.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader mainViewLoader = new FXMLLoader(MainApplication.class.getResource("MainView.fxml"));
        Scene scene = new Scene(mainViewLoader.load(), 542, 452);   //size for mainview
        stage.setTitle("Rutgers Cafe!");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent windowEvent)
            {
                try
                {
                    Platform.exit();
                    System.exit(0);
                }
                catch (Exception e)
                {

                }
            }
        }));

    }

    /**
     * Main method to Run the project.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}