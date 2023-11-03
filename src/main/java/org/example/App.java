package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();

    }

    public static void main(String[] args) {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        System.out.println(generatedString);
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {


        scene = new Scene(loadFXML("Login"));
        stage.setScene(scene);
        stage.setTitle("Login");
        /**stage.sizeToScene();
        stage.setFullScreen(true);
        stage.centerOnScreen();**/
        stage.show();
    }

}