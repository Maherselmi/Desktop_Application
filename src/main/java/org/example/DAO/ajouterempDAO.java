package org.example.DAO;

import org.example.BD.BDoracle;
import org.example.Employe;
import org.example.matriel;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

import static org.example.BD.BDoracle.ConnectDb;

public class ajouterempDAO {
    Statement con = null;
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public ajouterempDAO() {
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

            PreparedStatement ps = conn.prepareStatement("select * from employe where id= '"+c+"'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { test=true;}


        } catch (SQLException throwables) {
            System.out.println("prob ici");
            throwables.printStackTrace();
        }
        return test;
    }

    public void modifieremp(int value1, String value2, String value3, String value4, String value5, String value6,String code) throws IOException {

        try {
            conn = ConnectDb();
          String ch=  "update NEWMATS set type = '"+value2+"',marque= '"+
                    value3+"',dateaj= '"+value4+"',etat='"+value5+"'  where id='"+value1+"' ";



            String sql = "update employe set nom= '"+value2+"',prenom= '"+
                    value3+"',cin= '"+value4+"',job= '"+value5+"',datedenaissance='"+value6+"' ,code= '"+code+"'   where id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }


    }



    public void Add_emp (Employe e) {

        conn = ConnectDb();
        String sql = "insert into employe (id,nom,prenom,cin,job,datedenaissance,code)values(?,?,?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, e.getId());
            pst.setString(2, e.getNom());
            pst.setString(3, e.getPrenom());
            pst.setString(4, e.getCin());
            pst.setString(5,e.getJob());
            pst.setString(6,e.getDatedenaissance());
            pst.setString(7,e.getCode());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Employe Add succes");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }






}
