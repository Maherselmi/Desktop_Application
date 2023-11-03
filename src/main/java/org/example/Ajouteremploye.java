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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.example.DAO.ajouterempDAO;
import org.example.DAO.ajoutermatrielDAO;
import org.example.Model.genID;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

public class Ajouteremploye implements Initializable {

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfprenom;

    @FXML
    private TextField tfcin;

    @FXML
    private Button btnajout;

    @FXML
    private Button btnannuler;

    @FXML
    private TextField tfdatenaiss;

    @FXML
    private Button btnmodif;

    @FXML
    private ChoiceBox<String> lbljob;
    @FXML
    private Label txchaine;

    @FXML
    private int idemp;



    ajouterempDAO ajouterempDAO = new ajouterempDAO();
    genID id = new genID();




    public void handleButtonAction(MouseEvent event) throws IOException {
        ajouterempDAO ajouterempDAO = new ajouterempDAO();
        System.out.println("you click");
        if ((event.getSource() == btnajout) &&(!(ajouterempDAO2.find(idp)))) {

            if (!(tfnom.getText().isEmpty() || tfprenom.getText().isEmpty()|| tfdatenaiss.getText().isEmpty()|| tfcin.getText().isEmpty()|| lbljob.getSelectionModel().getSelectedItem().isEmpty() )){

                //ajoutermatrielDAO2.find(Integer.parseInt(tfid.getText()))
                if (((ajouterempDAO2.find(idemp)))) {
                    System.out.println("id :exist deja");


                } else {

                    System.out.println(lbljob.getSelectionModel().getSelectedItem());
                    String str = "";
                    Random rand = new Random();
                    for (int i = 0; i < 10; i++) {
                        char c = (char) (rand.nextInt(26) + 97);
                        str += c;
                    }


                    Employe e = new Employe(new genID().gen_emp(), tfnom.getText(), tfprenom.getText(), tfcin.getText(), lbljob.getSelectionModel().getSelectedItem(), tfdatenaiss.getText(), str);
                    ajouterempDAO.Add_emp(e);

                }
            }else
                JOptionPane.showMessageDialog(null, "veuillez s'il vous plaît remplir tous les informations");
        }


    }
     private int pred=0;

    public void handleButtonretour(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();

        if(pred==0) {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/org/example/Consulteremploye.fxml")));
            stage.setScene(scene);
            stage.setTitle("Consulter employe");
            stage.show();
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbljob.getItems().add("fillière administrative");
        lbljob.getItems().add("filière médico-sociale");
        lbljob.getItems().add("emplois techniques");
        lbljob.getItems().add("autre");
    }

    private int idp;
    private String code;

    public void showInformation(int id, String nom, String prenom, String cin, String poste, String aniv, String code, int pred) {
        this.idp = id;
        this.code = code;
        txchaine.setText("Modifier Employe ID:" + id);
        tfnom.setText(nom);
        tfprenom.setText(prenom);
        tfcin.setText(cin);
        tfcin.setText(cin);
        tfdatenaiss.setText(aniv);
        this.pred=pred;


        lbljob.getSelectionModel().select(0);

    }

    ajouterempDAO ajouterempDAO2 = new ajouterempDAO();

    public void handleButtonapp(MouseEvent event) throws IOException {
        if ((!(tfcin.getText().isEmpty()))&&(!(ajouterempDAO2.find(idemp)))) {
            ajouterempDAO2.modifieremp(idp, tfnom.getText(), tfprenom.getText(), tfcin.getText(), lbljob.getSelectionModel().getSelectedItem(), tfdatenaiss.getText(), code);
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.close();

        }


    }
}
