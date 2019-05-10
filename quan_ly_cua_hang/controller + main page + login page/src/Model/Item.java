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
 * 
 */
public class Item implements CRUD {

    private String CODE_INPUT_BILL;
    private String CODE_PRODUCT;
    private String CODE_SUPPLIER;
    private String CODE_TYPE;
    private int QUANTITY;
    private int PRICE;
    MyConnect db = new MyConnect();
    Connection con = null;

    // Lấy từ database ra
    public Item(String CODE_INPUT_BILL, String CODE_PRODUCT, String CODE_SUPPLIER, String CODE_TYPE, int QUANTITY, int PRICE) {
        this.CODE_INPUT_BILL = CODE_INPUT_BILL;
        this.CODE_PRODUCT = CODE_PRODUCT;
        this.CODE_SUPPLIER = CODE_SUPPLIER;
        this.CODE_TYPE = CODE_TYPE;
        this.QUANTITY = QUANTITY;
        this.PRICE = PRICE;
    }

    public Item() {
    }

    public String getCODE_SUPPLIER() {
        return CODE_SUPPLIER;
    }

    public void setCODE_SUPPLIER(String CODE_SUPPLIER) {
        this.CODE_SUPPLIER = CODE_SUPPLIER;
    }

    public String getCODE_TYPE() {
        return CODE_TYPE;
    }

    public void setCODE_TYPE(String CODE_TYPE) {
        this.CODE_TYPE = CODE_TYPE;
    }

    public String getCODE_INPUT_BILL() {
        return CODE_INPUT_BILL;
    }

    public void setCODE_INPUT_BILL(String CODE_INPUT_BILL) {
        this.CODE_INPUT_BILL = CODE_INPUT_BILL;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public int getPRICE() {
        return PRICE;
    }

    public void setPRICE(int PRICE) {
        this.PRICE = PRICE;
    }

    public String getCODE_PRODUCT() {
        return CODE_PRODUCT;
    }

    public void setCODE_PRODUCT(String CODE_PRODUCT) {
        this.CODE_PRODUCT = CODE_PRODUCT;
    }

    @Override
    public String toString() {
        return "Item{" + "CODE_INPUT_BILL=" + CODE_INPUT_BILL + ", CODE_PRODUCT=" + CODE_PRODUCT + ", CODE_SUPPLIER=" + CODE_SUPPLIER + ", CODE_TYPE=" + CODE_TYPE + ", QUANTITY=" + QUANTITY + ", PRICE=" + PRICE + '}';
    }

    @Override
    public boolean insert() {
        String query = "INSERT INTO LIST_ITEMS (\n"
                + "	CODE_BILL,\n"
                + "	CODE_PRODUCT,\n"
                + "	CODE_SUPPLIER,\n"
                + " CODE_TYPE,\n"
                + "	QUANTITY,\n"
                + "	PRICE\n"
                + ") VALUES ('" + this.getCODE_INPUT_BILL() + "','" + this.getCODE_PRODUCT() + "','" + this.getCODE_SUPPLIER() + "','" + this.getCODE_TYPE() + "','" + this.getQUANTITY() + "','" + this.getPRICE() + "');";
        String query_ = "UPDATE `status` SET QUANTITY = QUANTITY + " + this.getQUANTITY() + ", PRICE = '" + this.getPRICE() + "' WHERE CODE_PRODUCT ='" + this.getCODE_PRODUCT() + "';";
        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(query);
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
    public boolean update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete() {

        String query = "DELETE FROM LIST_ITEMS WHERE CODE_BILL = '" + this.getCODE_INPUT_BILL() + "';";
        String query_ = "UPDATE `status` SET QUANTITY = QUANTITY - " + this.getQUANTITY() + "' WHERE CODE_PRODUCT ='" + this.getCODE_PRODUCT() + "';";
        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(query);
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
    public boolean is_Available(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generate_CODE() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getDetail(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getDetail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
