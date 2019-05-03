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
 * @author Nguyen Hiep
 */
public class Item implements CRUD {

    private String CODE_BILL;
    private String CODE_PRODUCT;
    private String CODE_SUPPLIER;
    private String CODE_TYPE;
    private int QUANTITY;
    MyConnect db = new MyConnect();
    Connection con = null;

    // Lấy từ database ra
    public Item(String CODE_BILL, String CODE_PRODUCT, String CODE_SUPPLIER, String CODE_TYPE, int QUANTIY) {
        this.CODE_BILL = CODE_BILL;
        this.CODE_PRODUCT = CODE_PRODUCT;
        this.CODE_SUPPLIER = CODE_SUPPLIER;
        this.CODE_TYPE = CODE_TYPE;
        this.QUANTITY = QUANTIY;
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

    public String getCODE_BILL() {
        return CODE_BILL;
    }

    public void setCODE_BILL(String CODE_BILL) {
        this.CODE_BILL = CODE_BILL;
    }

    public String getCODE_PRODUCT() {
        return CODE_PRODUCT;
    }

    public void setCODE_PRODUCT(String CODE_PRODUCT) {
        this.CODE_PRODUCT = CODE_PRODUCT;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public static void main(String[] args) {
        Item item = new Item();
        //item.getDetail(null, null)
    }

    @Override
    public String toString() {
        return "Item{" + "CODE_BILL=" + CODE_BILL + ", CODE_PRODUCT=" + CODE_PRODUCT + ", CODE_SUPPLIER=" + CODE_SUPPLIER + ", CODE_TYPE=" + CODE_TYPE + ", QUANTITY=" + QUANTITY + '}';
    }

    public boolean insert_input() {
        String query = "INSERT INTO list_items (\n"
                + "	CODE_BILL,\n"
                + "	CODE_PRODUCT,\n"
                + "	CODE_SUPPLIER,\n"
                + "	CODE_TYPE,\n"
                + "	QUANTITY\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		'" + this.getCODE_BILL() + "',\n"
                + "		'" + this.getCODE_PRODUCT() + "',\n"
                + "		'" + this.getCODE_SUPPLIER() + "',\n"
                + "		'" + this.getCODE_TYPE() + "',\n"
                + "		'" + this.getQUANTITY() + "'\n"
                + "	);";
        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(query);

            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);

        }
        return false;
    }

    public boolean insert_output() {
        String query = "INSERT INTO LIST_ITEMS (\n"
                + "	CODE_BILL,\n"
                + "	CODE_PRODUCT,\n"
                + "	CODE_SUPPLIER,\n"
                + " CODE_TYPE,\n"
                + " QUANTITY\n"
                + ") VALUES ('" + this.getCODE_BILL() + "','" + this.getCODE_PRODUCT() + "','" + this.getCODE_SUPPLIER() + "','" + this.getCODE_TYPE() + "','" + this.getQUANTITY() + "');";
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
    public boolean is_Available(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generate_CODE() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getDetail(String id) {
        String query = "SELECT * FROM LIST_ITEMS WHERE (CODE_PRODUCT='" + id + "')";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(query);

            while (rs.next()) {
                String CODE_BILL = rs.getString(1);
                String CODE_PRODUCT = rs.getString(2);
                String CODE_SUPPLIER = rs.getString(3);
                String CODE_TYPE = rs.getString(4);
                int QUANTITY = rs.getInt(5);

                this.setCODE_BILL(this.CODE_BILL);
                this.setCODE_PRODUCT(CODE_PRODUCT);
                this.setCODE_SUPPLIER(CODE_SUPPLIER);
                this.setCODE_TYPE(CODE_TYPE);
                this.setQUANTITY(QUANTITY);
                break;
            }
            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println("error in function Get_detail item");
        }
        return false;
    }

    @Override
    public boolean getDetail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert() {
        String query = "INSERT INTO LIST_ITEMS(CODE_BILL,CODE_PRODUCT,CODE_SUPPLIER,CODE_TYPE,QUANTITY) "
                + "VALUES('" + this.getCODE_BILL() + "','" + this.getCODE_PRODUCT()
                + "','" + this.getCODE_SUPPLIER() + "','" + this.getCODE_TYPE() + "','" + this.getQUANTITY() + "')";

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

    public boolean decrease_status() {
        String sql = "UPDATE STATUS\n"
                + "SET QUANTITY = QUANTITY - " + this.getQUANTITY() + "\n"
                + "WHERE\n"
                + "(CODE_PRODUCT = '" + this.getCODE_PRODUCT() + "')";
        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(sql);

            // ngắn kết nối
            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("lỗi decrease_status");

        }
        return false;
    }

    public boolean increase_status() {
        String sql = "UPDATE STATUS\n"
                + "SET QUANTITY = QUANTITY + " + this.getQUANTITY() + "\n"
                + "WHERE\n"
                + "(CODE_PRODUCT = '" + this.getCODE_PRODUCT() + "')";
        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(sql);

            // ngắn kết nối
            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("lỗi increase_status");

        }
        return false;
    }

    @Override
    public boolean delete() {
        String sql = "DELETE\n"
                + "FROM\n"
                + "	LIST_ITEMS\n"
                + "WHERE\n"
                + "	(CODE_BILL = '" + this.getCODE_BILL() + "')\n"
                + "AND (CODE_PRODUCT = '" + this.getCODE_PRODUCT() + "')\n"
                + "AND (CODE_SUPPLIER = '" + this.getCODE_SUPPLIER() + "')\n"
                + "AND (CODE_TYPE = '" + this.getCODE_TYPE() + "')";
        if (this.CODE_BILL.startsWith("PN")) {
            decrease_status();
        } else if (this.CODE_BILL.startsWith("PX")) {
            increase_status();
        }
        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(sql);

            // ngắn kết nối
            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Lỗi xóa item");

        }
        return false;
    }

}
