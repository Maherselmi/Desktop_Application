package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.DAO.ajoutermatrielDAO;
import org.example.DAO.matrielsDAO;
import org.example.Model.genID;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ajoutermatriel   implements Initializable {
    private int id;
    private int pred=0;
    @FXML
    private Label txtchaine;
    @FXML
    private ChoiceBox<String> lblchoise;

    @FXML
    private TextField tfid;

    @FXML
    private TextField tftype;

    @FXML
    private TextField tfmarque;

    @FXML
    private TextField tfdateaj;

    @FXML
    private Button btnajout;

    @FXML
    private Button btnannuler;
    org.example.DAO.matrielsDAO matrielsDAO= new matrielsDAO();

 org.example.DAO.ajoutermatrielDAO ajoutermatrielDAO =new ajoutermatrielDAO();
    ajoutermatrielDAO ajoutermatrielDAO2 =new ajoutermatrielDAO();
 Connection  conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;


ajoutermatrielDAO am=new ajoutermatrielDAO();
    public void handleButtonapp(MouseEvent event) throws IOException{
        if((am.find(id))) {
            ajoutermatrielDAO2.modifiermatriel(id, tftype.getText(), tfmarque.getText(), tfdateaj.getText(), lblchoise.getSelectionModel().getSelectedItem());



        }


    }
    public void handleButtonretour(MouseEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();
        if(pred==0) {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/org/example/Consultermatriel.fxml")));
            stage.setScene(scene);
            stage.setTitle("Consulter matriel");
            stage.show();
        }




    }

    public void handleButtonAction(MouseEvent event) throws IOException {
ajoutermatrielDAO a=new ajoutermatrielDAO();
        System.out.println("you click");
        if ((event.getSource() == btnajout) ) {
            if (!(ajoutermatrielDAO.find(id))) {


                //ajoutermatrielDAO2.find(Integer.parseInt(tfid.getText()))
                if (tftype.getText().isEmpty() || tftype.getText().isEmpty() || tfmarque.getText().isEmpty() || tfdateaj.getText().isEmpty() || lblchoise.getSelectionModel().getSelectedItem().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "veuillez s'il vous plaît remplir tous les informations");

                } else {

                    System.out.println(lblchoise.getSelectionModel().getSelectedItem());
                    matriel m = new matriel(new genID().gen_matriel(), tftype.getText(), tfmarque.getText(), tfdateaj.getText(), lblchoise.getSelectionModel().getSelectedItem());
                    ajoutermatrielDAO.Add_users(m);

                }

            }

        }else {JOptionPane.showMessageDialog(null,"deja exist");


        }


    }



    public void showInformation(int id,String type2,String marque ,String date,String etat,int pred){

        tftype.setText(type2);
        tfmarque.setText(marque);
        tfdateaj.setText(date);
        lblchoise.getSelectionModel().select(0);
        this.pred=1;
        txtchaine.setText("Modifier Employe ID:" + id);
        this.id=id;




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblchoise.getItems().add("disponible");
        lblchoise.getItems().add("En panne");
        lblchoise.getItems().add("En travail");
        lblchoise.getItems().add("en cours de réparation");



    }
}
