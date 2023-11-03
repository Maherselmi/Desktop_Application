package org.example;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.BD.BDoracle;
import org.example.DAO.ConsulterempDAO;
import org.example.DAO.ajouterempDAO;
import org.example.DAO.ajoutermatrielDAO;

import javax.imageio.IIOParam;

public class Consulteremploye implements Initializable {
    ObservableList<Employe> listM;
    ObservableList<Employe> dataList;



int index=-1;

        @FXML
        private TextField tfsearch;

        @FXML
        private Label lblErrors;

        @FXML
        private Button btnajout;

        @FXML
        private TextField tfid;

        @FXML
        private Button btnmodifier;

        @FXML
        private Button btnsupprimer;

        @FXML
        private TableView<Employe> tvemp;

        @FXML
        private TableColumn<Employe, Integer> colid;

        @FXML
        private TableColumn<Employe, String> colnom;

        @FXML
        private TableColumn<Employe, String> colprenom;

        @FXML
        private TableColumn<Employe, String> coldcin;

        @FXML
        private TableColumn<Employe, String> colposte;

        @FXML
        private TableColumn<Employe, String> coledatedenaissance;

        @FXML
        private TableColumn<Employe, String> colcode;

        @FXML
        private Button lbconnect;











    public void handleButtonSupp(ActionEvent actionEvent) {
    }


    int click=0;
        int x=-1;
    @FXML
    void getSelected (MouseEvent event){
        click++;


        index = tvemp.getSelectionModel().getSelectedIndex();
        int a=colid.getCellData(index);
        if (index <= -1){

            return;
        }
        tfid.setText(colid.getCellData(index).toString());

        if ((click==2) &&(x==a)){
            startmodify();
            click=0;
            x=-1;
        } else {
            x = a;
            click=1;

        }



    }


    public void handleButtonrefresh(MouseEvent Event)  throws IOException{

        UpdateTable();

        tfid.clear();



    }


    public void handleButtonAction(MouseEvent event) throws IOException {

        System.out.println("you click");
        if (event.getSource() == btnajout) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.close();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/ajouteremploye.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            stage.setTitle("Ajouter matriel");
            stage.setScene(scene);
            Ajouteremploye scene2controller=loader.getController();
            stage.show();



        } else if (event.getSource() == btnmodifier) {

        }

    }


    ConsulterempDAO c =new ConsulterempDAO();
    public void handleButtonSupp(MouseEvent event) throws IOException {
        if (event.getSource() == btnsupprimer) {
            if(!(tfid.getText().isEmpty())){

               c.Delete(tfid.getText());
                UpdateTable();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            UpdateTable();
            search_user();

    }
ConsulterempDAO cp= new ConsulterempDAO();

    private void UpdateTable() {
        colid.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<Employe, String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<Employe, String>("prenom"));
        coldcin.setCellValueFactory(new PropertyValueFactory<Employe, String>("cin"));
        colposte.setCellValueFactory(new PropertyValueFactory<Employe, String>("job"));
        coledatedenaissance.setCellValueFactory(new PropertyValueFactory<Employe, String>("datedenaissance"));
        colcode.setCellValueFactory(new PropertyValueFactory<Employe, String>("code"));
        listM = c.getDatausers();
        tvemp.setItems(listM);
        try {
            if (BDoracle.ConnectDb().createStatement()== null) {
                lblErrors.setTextFill(Color.TOMATO);
                lblErrors.setText("Server Error : Check");
            } else {
                lblErrors.setTextFill(Color.GREEN);
                lblErrors.setText("Server is up : Good to go");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

     }


    @FXML
    void search_user() {
        colid.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<Employe,String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<Employe,String>("prenom"));
        coldcin.setCellValueFactory(new PropertyValueFactory<Employe,String>("cin"));
        colposte.setCellValueFactory(new PropertyValueFactory<Employe,String>("job"));
        coledatedenaissance.setCellValueFactory(new PropertyValueFactory<Employe,String>("datedenaissance"));


ConsulterempDAO CDAO =new ConsulterempDAO();
        dataList = CDAO.getDatausers();
        tvemp.setItems(dataList);
        FilteredList<Employe> filteredData = new FilteredList<>(dataList, b -> true);
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                }

                else if (person.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (person.getCin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (person.getDatedenaissance().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (person.getJob().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(person.getId()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<Employe> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvemp.comparatorProperty());
        tvemp.setItems(sortedData);

    }

    public void handleButtonmodif(MouseEvent Event) {
        ajouterempDAO a = new ajouterempDAO();
        if (!(tfid.getText().isEmpty())) {
            startmodify();


        }
    }

    public void startmodify(){


        FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/ajouteremploye.fxml"));


        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Ajouteremploye scene2controller=loader.getController();

        Stage stage=new Stage();

        stage.setScene(new Scene(root));
        stage.setTitle("Modifier matriel");
        stage.show();

        int id=colid.getCellData(index);
        String nom=colnom.getCellData(index).toString();
        String prenom= colprenom.getCellData(index).toString();
        String cin= coldcin.getCellData(index).toString();
        String poste=colposte.getCellData(index).toString();
        String aniv=coledatedenaissance.getCellData(index).toString();
        String code=colcode.getCellData(index).toString();
        scene2controller.showInformation(id,nom,prenom,cin,poste,aniv,code,1);

    }


    public void handleButtonreturn(ActionEvent event) {
        try {
            //add you loading or delays - ;-)
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/org/example/espaceadmindemo.fxml")));
            stage.setScene(scene);
            stage.setTitle("Espace admin DEMO");
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
