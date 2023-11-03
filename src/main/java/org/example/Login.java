package org.example;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.BD.BDoracle;
import org.example.Model.Compteadmin;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

import static org.example.BD.BDoracle.ConnectDb;


public class Login implements Initializable {
    Connection conn =null;


    @FXML
    private TextField txtid;

    @FXML
    private PasswordField txtmdp;

    @FXML
    private Button cnx;

    @FXML
    private Label mpo;

    @FXML
    private Button ins;

    @FXML
    private Label lblbienvenue;

    @FXML
    private Button btnretour;
    @FXML
    private Label lblerreur;
    public void loginButtonAction(ActionEvent event) {
        if (txtid.getText().isBlank() == false && txtmdp.getText().isBlank() == false) {
            Compteadmin c = new Compteadmin(txtid.getText(), txtmdp.getText());
            if (Vlidatelogin(c)) {
                lblerreur.setText("connexion reussie");


                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/org/example/espaceadmindemo.fxml")));
                    stage.setScene(scene);
                    stage.setTitle("Espace admin demo");
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }


            }else{
                lblerreur.setText("erreur");
            }


        } else {
                lblerreur.setText("enter vote id / mot de pass");
            }
        }







    private boolean Vlidatelogin(Compteadmin c)
    {

        boolean test= false;
        BDoracle bd=null;

        try {
            conn = ConnectDb();

            String s="select * from admin where email = '"+c.getLogin()
                    + "' and password = '"+c.getPassword()+"'";


            PreparedStatement ps = conn.prepareStatement(s);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { test=true;}


        } catch (SQLException throwables) {
            System.out.println("prob ici");
            throwables.printStackTrace();
        }
        return test;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }













    public void handleButtonRetourn(ActionEvent event) {


        try {
            //add you loading or delays - ;-)
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();


        } finally {

        }


    }









}