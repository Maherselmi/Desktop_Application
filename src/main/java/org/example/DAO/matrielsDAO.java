package org.example.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.BD.BDoracle;
import org.example.matriel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class matrielsDAO {



    Connection conn =null;
    Statement con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public matrielsDAO()  {
        try{
            con= BDoracle.ConnectDb().createStatement();
            System.out.println("instance created");} catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static ObservableList<matriel> getDatausers(){
        Connection conn = BDoracle.ConnectDb();
        ObservableList<matriel> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from NEWMATS");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new matriel(rs.getInt("id"), rs.getString("type"), rs.getString("marque"),rs.getString("dateaj"), rs.getString("etat")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public void Delete(String id){
        conn = BDoracle.ConnectDb();
        String sql = "delete from NEWMATS where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(id));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }


    }


}
