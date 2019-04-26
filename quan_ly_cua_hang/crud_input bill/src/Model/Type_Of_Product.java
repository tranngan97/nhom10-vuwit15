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
 * @author Nguyen Hieu
 */
public class Type_Of_Product implements CRUD {

    private String Code_type;
    private String Name_type;
    private String Unit;
    MyConnect db = new MyConnect();
    Connection con = null;

    public Type_Of_Product() {
    }

    public Type_Of_Product(String Code, String Name, String Unit) {
        this.Code_type = Code;
        this.Name_type = Name;
        this.Unit = Unit;
    }

    public Type_Of_Product(String Name, String Unit) {
        this.Code_type = generate_CODE();
        this.Name_type = Name;
        this.Unit = Unit;
    }

    public String getCodeType() {
        return Code_type;
    }

    public void setCodeType(String Code) {
        this.Code_type = Code;
    }

    public String getNameType() {
        return Name_type;
    }

    public void setNameType(String Name) {
        this.Name_type = Name;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    @Override
    public String toString() {
        return "Loai_Thiet_Bi{" + "Ma_Loai=" + Code_type + ", Ten_Loai=" + Name_type + ", Don_Vi_Tinh=" + Unit + '}';
    }

    @Override
    public boolean insert() {

        String sql = "INSERT INTO TYPE_OF_PRODUCT (CODE_TYPE,NAME,UNIT)\n"
                + "VALUES ('" + this.getCodeType() + "', '" + this.getNameType() + "', '" + this.getUnit() + "');";

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

        String sql = "UPDATE type_of_product\n"
                + "SET NAME = \"" + this.getNameType() + "\",\n"
                + " UNIT = \"" + this.getUnit() + "\"\n"
                + "WHERE\n"
                + "	CODE_TYPE = \"" + this.getCodeType() + "\"";
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
                + "			type_of_product\n"
                + "		WHERE\n"
                + "			(\n"
                + "				products.CODE_TYPE = type_of_product.CODE_TYPE\n"
                + "			)\n"
                + "		AND (\n"
                + "			 type_of_product.CODE_TYPE = \"" + this.getCodeType() + "\"\n"
                + "		)\n"
                + "	);\n"
                + "\n"
                + "DELETE\n"
                + "FROM\n"
                + "	products\n"
                + "WHERE\n"
                + "	products.CODE_TYPE = \"" + this.getCodeType() + "\";\n"
                + "\n"
                + "DELETE\n"
                + "FROM\n"
                + "	type_of_product\n"
                + "WHERE\n"
                + "	CODE_TYPE = \"" + this.getCodeType() + "\";";
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
    public boolean getDetail(String key) {
        String sql = "SELECT * FROM TYPE_OF_PRODUCT WHERE (CODE_TYPE =\"" + key + "\") OR (NAME =\"" + key + "\")";

        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                String Ma_Loai = rs.getString(1);
                String Ten_Loai = rs.getString(2);
                String Don_Vi_Tinh = rs.getString(3);

                this.setCodeType(Ma_Loai);
                this.setNameType(Ten_Loai);
                this.setUnit(Don_Vi_Tinh);

            }
            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println("error in function Get_Loaisp");
        }
        return false;
    }

    @Override
    public String generate_CODE() {
        String ID = "TYP-";
        double d;
        for (int i = 1; i <= 8; i++) {
            d = Math.random() * 10;
            ID = ID + ((int) d);
            if (i % 4 == 0 && i != 8) {
                ID = ID + "-";
            }
        }
        return ID;
    }

    @Override
    public boolean is_Available(String key) {
        String sql = "SELECT * FROM TYPE_OF_PRODUCT WHERE (NAME =\"" + key + "\")";

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
        String sql = "SELECT * FROM type_of_product WHERE CODE_TYPE = '" + this.getCodeType() + "'";

        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                String Ma_Loai = rs.getString(1);
                String Ten_Loai = rs.getString(2);
                String Don_Vi_Tinh = rs.getString(3);

                this.setCodeType(Ma_Loai);
                this.setNameType(Ten_Loai);
                this.setUnit(Don_Vi_Tinh);

            }
            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println("error in function Get_Loaisp");
        }
        return false;
    }

}
