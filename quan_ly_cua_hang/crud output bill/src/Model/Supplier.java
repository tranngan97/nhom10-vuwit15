/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.MyConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nguyen Hiep
 */
public class Supplier implements CRUD {

    private String Code_supplier;
    private String Name_supplier;
    private String Country;
    MyConnect db = new MyConnect();
    Connection con = null;

    public Supplier() {
    }

    public Supplier(String Ma_NXS, String Ten_NSX, String Quoc_gia) {
        this.Code_supplier = Ma_NXS;
        this.Name_supplier = Ten_NSX;
        this.Country = Quoc_gia;
    }

    public Supplier(String Ten_NSX, String Quoc_gia) {
        this.Code_supplier = generate_CODE();
        this.Name_supplier = Ten_NSX;
        this.Country = Quoc_gia;
    }

    public String getCode_Supplier() {
        return Code_supplier;
    }

    public void setCode_Supplier(String Ma_NSX) {
        this.Code_supplier = Ma_NSX;
    }

    public String getName_Supplier() {
        return Name_supplier;
    }

    public void setName_Supplier(String Ten_NSX) {
        this.Name_supplier = Ten_NSX;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Quoc_gia) {
        this.Country = Quoc_gia;
    }

    public static void main(String[] args) {
        Supplier te = new Supplier();
        te.setCode_Supplier("SUPPL-2430-9222");
        System.out.println(te.delete());
    }

    @Override
    public String toString() {
        return "Supplier{" + "Code_supplier=" + Code_supplier + ", Name_supplier=" + Name_supplier + ", Country=" + Country + ", db=" + db + ", con=" + con + '}';
    }

    @Override

    public boolean insert() {

        String sql = "INSERT INTO SUPPLIER (CODE_SUPPLIER,NAME,COUNTRY)\n"
                + "VALUES ('" + this.Code_supplier + "', '" + this.Name_supplier + "', '" + this.Country + "');";
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
            return false;
        }
    }

    @Override
    public boolean update() {
        String sql = "UPDATE SUPPLIER\n"
                + "SET NAME = \"" + this.getName_Supplier() + "\", COUNTRY = " + this.getCountry() + "\n"
                + "WHERE CODE_SUPPLIER = \"" + this.getCode_Supplier() + "\"; ";
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

        }
        return false;
    }

    @Override
    public boolean delete() {
        String sql = "DELETE\n"
                + "FROM\n"
                + "	`status`\n"
                + "WHERE\n"
                + "	CODE_PRODUCT IN (\n"
                + "		SELECT\n"
                + "			products.CODE_PRODUCT\n"
                + "		FROM\n"
                + "			products,\n"
                + "			supplier\n"
                + "		WHERE\n"
                + "			(\n"
                + "				products.CODE_SUPPLIER = supplier.CODE_SUPPLIER\n"
                + "			)\n"
                + "		AND (\n"
                + "			supplier.CODE_SUPPLIER = \"" + this.getCode_Supplier() + "\"\n"
                + "		)\n"
                + "	);\n";
        String sql_ = "DELETE\n"
                + "FROM\n"
                + "	products\n"
                + "WHERE\n"
                + "	products.CODE_SUPPLIER = \"" + this.getCode_Supplier() + "\";\n";
        String sql__ = "DELETE\n"
                + "FROM\n"
                + "	supplier\n"
                + "WHERE\n"
                + "	CODE_SUPPLIER = \"" + this.getCode_Supplier() + "\";";

        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(sql);
            s.executeUpdate(sql_);
            s.executeUpdate(sql__);
            // ngắn kết nối

            con.close();
            s.close();
            return true;
        } catch (Exception e1) {
        }
        return false;
    }

    @Override
    public boolean getDetail(String key) {
        String sql = "SELECT * FROM SUPPLIER WHERE (CODE_SUPPLIER =\"" + key + "\") OR (NAME =\"" + key + "\") ";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                String Ma_NSX = rs.getString(1);
                String Ten_NSX = rs.getString(2);
                String Quoc_Gia = rs.getString(3);

                this.setCode_Supplier(Ma_NSX);
                this.setName_Supplier(Ten_NSX);
                this.setCountry(Quoc_Gia);

            }
            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println("error in function Get_NSX");
        }
        return false;
    }

    @Override
    public String generate_CODE() {
        String s = "SUPPL-";
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
        String sql = "SELECT * FROM SUPPLIER WHERE (NAME =\"" + key + "\") ";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {

                return true;
            }
            con.close();
            s.close();
        } catch (Exception e) {
            System.out.println("error in function is_Available");
        }
        return false;
    }

    @Override
    public boolean getDetail() {
        String sql = "SELECT * FROM SUPPLIER WHERE CODE_SUPPLIER = '" + this.getCode_Supplier() + "'";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                String Ma_NSX = rs.getString(1);
                String Ten_NSX = rs.getString(2);
                String Quoc_Gia = rs.getString(3);

                this.setCode_Supplier(Ma_NSX);
                this.setName_Supplier(Ten_NSX);
                this.setCountry(Quoc_Gia);

            }

            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println("error in function Get_NSX");
        }
        return false;
    }

}
