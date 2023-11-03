package org.example.Model;

import org.example.DAO.genID_DAO;

public class genID {
    public int gen_emp(){
        genID_DAO a =new genID_DAO();

        return  a.genID_emp();
    }
    public int gen_matriel(){
        genID_DAO b =new genID_DAO();

        return  b.genID_matriel();
    }




}
