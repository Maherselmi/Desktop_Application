package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Espaceadmindemo {




    @FXML
    private Label lblmenu;

    @FXML
    private Button btndeconnexion;

    @FXML
    private Button btnmateriel;

    @FXML
    private Button btnemploye;

    public void deconnecter(ActionEvent event) {
        try {
            //add you loading or delays - ;-)
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/org/example/login.fxml")));
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }


    }








    public void Consultermateriel(ActionEvent event) {

        try {
            //add you loading or delays - ;-)
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/org/example/consultermatriel.fxml")));
            stage.setScene(scene);
            stage.setTitle("Consulter matriel");
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }


    }

    public void Consulteremploye(ActionEvent event) {


        try {
            //add you loading or delays - ;-)
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/org/example/consulteremploye.fxml")));
            stage.setScene(scene);
            stage.setTitle("Consulter employe");
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
