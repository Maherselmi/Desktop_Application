package org.example.DAO;

import org.example.BD.BDoracle;

import java.sql.*;

import static org.example.BD.BDoracle.ConnectDb;

public class genID_DAO {

    Connection conn =null;
    int id=(int)Math.floor(Math.random()*(10000-1+1)+1);

    PreparedStatement pst = null;
    public  int genID_matriel(){


        while (find(id,"NEWMATS"))
            id=(int)Math.floor(Math.random()*(10000-1+1)+1);



        return  id;
    }
    public  int genID_emp(){


        while (find(id,"employe"))
            id=(int)Math.floor(Math.random()*(10000-1+1)+1);

        System.out.println(id);

        return  id;
    }
    public boolean find(int c,String t) {

        boolean test= false;
        BDoracle bd=null;


        //String sql = "SELECT * FROM admins Where email = ? and password = ?";

        try {
            conn = ConnectDb();
            String q=String.valueOf(c);
            String s="select * from "+t+" where id="+q;


            PreparedStatement ps = conn.prepareStatement(s);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { test=true;}


        } catch (SQLException throwables) {
            System.out.println("prob ici");
            throwables.printStackTrace();
        }
        return test;
    }


}
