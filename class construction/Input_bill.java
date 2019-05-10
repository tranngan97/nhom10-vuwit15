/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.MyConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 
 */
public class Input_bill implements CRUD {

    private String CODE_INPUT_BILL;
    private String CODE_STAFF;
    private ArrayList<Item> listItems;
    private Date DATE;

    MyConnect db = new MyConnect();
    Connection con = null;
//láy dữ liệu từ database ra:

    public Input_bill(String CODE_INPUT_BILL, String CODE_STAFF, Date DATE) {
        this.CODE_INPUT_BILL = CODE_INPUT_BILL;
        this.CODE_STAFF = CODE_STAFF;
        this.DATE = DATE;
    }
//Thêm mới

    public Input_bill(String CODE_STAFF, String DATE) throws ParseException {
        this.CODE_INPUT_BILL = generate_CODE();
        this.CODE_STAFF = CODE_STAFF;
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(DATE);
        this.DATE = new java.sql.Date(date.getTime());
    }

    public Input_bill() {
        this.CODE_INPUT_BILL = generate_CODE();
    }

    public String getCODE_INPUT_BILL() {
        return CODE_INPUT_BILL;
    }

    public void setCODE_INPUT_BILL(String CODE_INPUT_BILL) {
        this.CODE_INPUT_BILL = CODE_INPUT_BILL;
    }

    public String getCODE_STAFF() {
        return CODE_STAFF;
    }

    public void setCODE_STAFF(String CODE_STAFF) {
        this.CODE_STAFF = CODE_STAFF;
    }

    public ArrayList<Item> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<Item> listItems) {
        this.listItems = listItems;
    }

    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }

    public void setDATE(String DATE) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(DATE);
        this.DATE = new java.sql.Date(date.getTime());
    }

    @Override
    public boolean insert() {
        String query = "INSERT INTO INPUT_BILL(CODE_INPUT_BILL,CODE_STAFF,DATE)\n"
                + " VALUES ('" + this.getCODE_INPUT_BILL() + "','" + this.getCODE_STAFF() + "','" + this.getDATE() + "');";

        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(query);
            // ngắn kết nối

            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);

        }
        return false;
    }

    @Override
    public boolean update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete() {
         String query_ = "DELETE FROM INPUT_BILL\n"
                + "WHERE CODE_INPUT_BILL = \"" + this.getCODE_INPUT_BILL() + "\"; ";
        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(query_);

            // ngắn kết nối
            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);

        }
        return false;
    }

    @Override
    public boolean getDetail(String id) {

        String query = "SELECT * FROM INPUT_BILL WHERE CODE_INPUT_BILL = '" + id + "';";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(query);

            while (rs.next()) {
                String code_bill = rs.getString(1);
                String code_staff = rs.getString(2);
                java.sql.Date date = rs.getDate(3);
                this.setCODE_INPUT_BILL(code_bill);
                this.setCODE_STAFF(code_staff);
                this.setDATE(date);
            }
            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println("error in function getDetail");
        }
        return false;
    }

    @Override
    public boolean getDetail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generate_CODE() {
        String s = "PN-";
        double d;
        for (int i = 1; i <= 8; i++) {
            d = Math.random() * 10;
            s = s + ((int) d);
            if (i % 4 == 0 && i != 8) {
                s = s + "-";
            }
        }
        return s;
    }

    @Override
    public boolean is_Available(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
