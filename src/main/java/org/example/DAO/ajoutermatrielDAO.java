package org.example.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.BD.BDoracle;
import org.example.matriel;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

import static org.example.BD.BDoracle.ConnectDb;


public class ajoutermatrielDAO {
    Statement con = null;
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public ajoutermatrielDAO() {
        try{
            con= ConnectDb().createStatement();
            System.out.println("instance created");} catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean find(int c) {

        boolean test= false;
        BDoracle bd=null;


        //String sql = "SELECT * FROM admins Where email = ? and password = ?";
        ResultSet r;
        try {
            conn = ConnectDb();

            String s="select * from NEWMATS where id='c'";
String q=String.valueOf(c);

            PreparedStatement ps = conn.prepareStatement("select * from NEWMATS where id= '"+c+"'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { test=true;}


        } catch (SQLException throwables) {
            System.out.println("prob ici");
            throwables.printStackTrace();
        }
        return test;
    }


    public void modifiermatriel(int value1 ,String value2,String value3,String value4,String value5) throws IOException {

        try {
            conn = ConnectDb();


            String sql = "update NEWMATS set type = '"+value2+"',marque= '"+
                    value3+"',dateaj= '"+value4+"',etat='"+value5+"'  where id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }






    }


    public void Add_users (matriel m){
         conn = ConnectDb();
        String sql = "insert into NEWMATS (id,type,marque,dateaj,etat)values(?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, m.getId());
            pst.setString(2, m.getType());
            pst.setString(3, m.getMarque());
            pst.setString(4, m.getDateaj());
            pst.setString(5,m.getEtat());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Matriel Add succes");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }














}

