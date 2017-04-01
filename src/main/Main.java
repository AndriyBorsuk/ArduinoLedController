package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));
        primaryStage.setTitle("Arduino led controller v1.0");
        primaryStage.setMinHeight(280);
        primaryStage.setMinWidth(460);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 460, 280));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}