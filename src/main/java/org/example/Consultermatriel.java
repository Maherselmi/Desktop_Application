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
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.BD.BDoracle;
import org.example.DAO.ConsulterempDAO;
import org.example.DAO.ajoutermatrielDAO;
import org.example.DAO.matrielsDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;


public class Consultermatriel  implements Initializable {
    @FXML
    private RadioButton radiocnx;
    @FXML
    private Label lblErrors;

    @FXML
    private ProgressBar bar;

    @FXML
    private Button btnajout;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfsearch;
    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsupprimer;

    @FXML
    private TableView<matriel> tvmatriel;

    @FXML
    private TableColumn<matriel, Integer> colid;

    @FXML
    private TableColumn<matriel, String> coltype;

    @FXML
    private TableColumn<matriel, String> colmarque;

    @FXML
    private TableColumn<matriel, String> coldateaj;

    @FXML
    private TableColumn<matriel, String> coletat;
    ObservableList<matriel> listM;
    ObservableList<matriel> dataList;


    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public Consultermatriel() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       UpdateTable();
        search_user();
        String uniqueID = UUID.randomUUID().toString();
        System.out.println(uniqueID);




    }

  matrielsDAO matrielsDAO = new matrielsDAO();

    public void handleButtonSupp(MouseEvent event) throws IOException {
        if (event.getSource() == btnsupprimer) {
            if(!(tfid.getText().isEmpty())){

            matrielsDAO.Delete(tfid.getText());
            UpdateTable();
            }
        }
    }

   public void handleButtonmodif(MouseEvent Event) {
        ajoutermatrielDAO a=new ajoutermatrielDAO();
        if (!(tfid.getText().isEmpty()) &&(a.find(Integer.parseInt(tfid.getText())))) {
            startmodify();





        }

    }
    matrielsDAO CDAO=new  matrielsDAO();
    private void UpdateTable() {
        colid.setCellValueFactory(new PropertyValueFactory<matriel, Integer>("id"));
        coltype.setCellValueFactory(new PropertyValueFactory<matriel, String>("type"));
        colmarque.setCellValueFactory(new PropertyValueFactory<matriel, String>("marque"));
        coldateaj.setCellValueFactory(new PropertyValueFactory<matriel, String>("dateaj"));
        coletat.setCellValueFactory(new PropertyValueFactory<matriel, String>("etat"));
        listM =CDAO.getDatausers();
        tvmatriel.setItems(listM);
        try {
            if (BDoracle.ConnectDb().createStatement()== null) {
                lblErrors.setTextFill(Color.TOMATO);
                lblErrors.setText("Server Error : Check");
                radiocnx.fire();
            } else {
                lblErrors.setTextFill(Color.GREEN);
                lblErrors.setText("Server is up : Good to go");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void handleButtonAction(MouseEvent event) throws IOException {

        System.out.println("you click");
        if (event.getSource() == btnajout) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.close();

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/org/example/ajoutermatriel.fxml")));
            stage.setTitle("Ajouter matriel");
            stage.setScene(scene);
            stage.show();

        } else if (event.getSource() == btnmodifier) {

        }

    }

    public void startmodify(){

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/ajoutermatriel.fxml"));


        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ajoutermatriel scene2controller=loader.getController();


        Stage stage=new Stage();

        stage.setScene(new Scene(root));
        stage.setTitle("Modifier matriel");
        stage.show();

        int id=colid.getCellData(index);
        String type2=coltype.getCellData(index).toString();
        String marque= colmarque.getCellData(index).toString();
        String date2= coldateaj.getCellData(index).toString();
        String etat=coletat.getCellData(index).toString();
        scene2controller.showInformation(id,type2,marque,date2,etat,1);

    }
    int click=0;
    int x=-1;
    @FXML
    void getSelected (MouseEvent event){
        click++;


        index = tvmatriel.getSelectionModel().getSelectedIndex();
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
        search_user();
        tfid.clear();



    }

    @FXML
    void search_user() {
        colid.setCellValueFactory(new PropertyValueFactory<matriel,Integer>("id"));
        coltype.setCellValueFactory(new PropertyValueFactory<matriel,String>("type"));
        colmarque.setCellValueFactory(new PropertyValueFactory<matriel,String>("marque"));
        coldateaj.setCellValueFactory(new PropertyValueFactory<matriel,String>("dateaj"));
        coletat.setCellValueFactory(new PropertyValueFactory<matriel,String>("etat"));

        dataList = CDAO.getDatausers();
        tvmatriel.setItems(dataList);
        FilteredList<matriel> filteredData = new FilteredList<>(dataList, b -> true);
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                }

                else if (person.getMarque().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (person.getDateaj().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (person.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(person.getId()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<matriel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvmatriel.comparatorProperty());
        tvmatriel.setItems(sortedData);

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
