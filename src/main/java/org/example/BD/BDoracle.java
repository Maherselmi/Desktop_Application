package org.example.BD;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.matriel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BDoracle {


    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection  conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "TEST", "123456");
            System.out.println("Driver Loaded");// JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }





}

