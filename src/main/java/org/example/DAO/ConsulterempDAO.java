package org.example.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.BD.BDoracle;
import org.example.Employe;
import org.example.matriel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConsulterempDAO {
    Connection conn =null;
    Statement con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public ConsulterempDAO() {
        try{
            con= BDoracle.ConnectDb().createStatement();
            System.out.println("instance created");} catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void Delete(String id){
        conn = BDoracle.ConnectDb();
        String sql = "delete from employe where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(id));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }


    }

    public static ObservableList<Employe> getDatausers(){
        Connection conn = BDoracle.ConnectDb();
        ObservableList<Employe> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from employe");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),rs.getString("cin"), rs.getString("job"),rs.getString("datedenaissance"),rs.getString("code")));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
