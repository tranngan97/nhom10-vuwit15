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
public class Product implements CRUD {

    private String Code_product;
    private String Name;
    private String Code_supplier;
    private String Code_type;
    private int Quantity;
    private int Price;
    private String Image;
    MyConnect db = new MyConnect();
    Connection con = null;

    static String default_ava = "product.png";

    public Product() {
    }

    //lấy ra từ database
    public Product(String Ma_Thiet_Bi, String Ten_Thiet_Bi, String Ma_NSX, String Ma_Loai, int So_Luong, int Gia_Thanh, String Anh_SP) {
        this.Code_product = Ma_Thiet_Bi;
        this.Name = Ten_Thiet_Bi;
        this.Code_supplier = Ma_NSX;
        this.Code_type = Ma_Loai;
        this.Quantity = So_Luong;
        this.Price = Gia_Thanh;
        this.Image = Anh_SP;
    }

    //Tạo mới không ảnh
    public Product(String Ten_Thiet_Bi, String Ma_NSX, String Ma_Loai) {
        this.Code_product = generate_CODE();
        this.Name = Ten_Thiet_Bi;
        this.Code_supplier = Ma_NSX;
        this.Code_type = Ma_Loai;
        this.Quantity = 0;
        this.Price = 0;
        this.Image = default_ava;
    }

    //Tạo mới có ảnh
    public Product(String Ten_Thiet_Bi, String Ma_NSX, String Ma_Loai, String Anh_SP) {
        this.Code_product = generate_CODE();
        this.Name = Ten_Thiet_Bi;
        this.Code_supplier = Ma_NSX;
        this.Code_type = Ma_Loai;
        this.Quantity = 0;
        this.Price = 0;
        this.Image = Anh_SP;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Anh_SP) {
        this.Image = Anh_SP;
    }

    public String getCode_product() {
        return Code_product;
    }

    public void setCode_product(String Ma_Thiet_Bi) {
        this.Code_product = Ma_Thiet_Bi;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Ten_Thiet_Bi) {
        this.Name = Ten_Thiet_Bi;
    }

    public String getCode_supplier() {
        return Code_supplier;
    }

    public void setCode_supplier(String Ma_NSX) {
        this.Code_supplier = Ma_NSX;
    }

    public String getCode_type() {
        return Code_type;
    }

    public void setCode_type(String Ma_Loai) {
        this.Code_type = Ma_Loai;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int So_Luong) {
        this.Quantity = So_Luong;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Gia_Thanh) {
        this.Price = Gia_Thanh;
    }

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date date = new java.util.Date();
        String date_insert = dateFormat.format(date);
        System.out.println(date_insert);
    }

    @Override
    public String toString() {
        return "Product{" + "Code_product=" + Code_product + ", Name=" + Name + ", Code_supplier=" + Code_supplier + ", Code_type=" + Code_type + ", Quantity=" + Quantity + ", Price=" + Price + ", Image=" + Image + '}';
    }

    @Override

    public boolean insert() {

        String sql = "INSERT INTO PRODUCTS (CODE_PRODUCT,NAME,CODE_SUPPLIER,CODE_TYPE,IMAGE)\n"
                + "VALUES ('" + this.getCode_product() + "', '" + this.getName()
                + "', '" + this.getCode_supplier() + "', '" + this.getCode_type() + "', '" + this.getImage() + "');";
        String sql_ = "INSERT INTO STATUS (\n"
                + "	CODE_PRODUCT,\n"
                + "	QUANTITY,\n"
                + "	PRICE\n"
                + ")\n"
                + "VALUES\n"
                + "	(\n"
                + "		\"" + this.getCode_product() + "\",\n"
                + "		\"" + this.getQuantity() + "\",\n"
                + "		\"" + this.getPrice() + "\"\n"
                + "	)";
        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(sql);
            s.executeUpdate(sql_);
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
        String sql = "UPDATE products\n"
                + "SET `NAME` = '" + this.getName() + "',\n"
                + " CODE_SUPPLIER = '" + this.getCode_supplier() + "',\n"
                + " CODE_TYPE = '" + this.getCode_type() + "',\n"
                + " IMAGE = '" + this.getImage() + "'\n"
                + "WHERE\n"
                + "	CODE_PRODUCT = '" + this.getCode_product() + "';\n";
        String sql_ = "UPDATE status\n"
                + "SET QUANTITY = '" + this.getQuantity() + "' , PRICE = '" + this.getPrice() + "'\n"
                + "WHERE\n"
                + "	CODE_PRODUCT = '" + this.getCode_product() + "'";
        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(sql);
            s.executeUpdate(sql_);
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
        String sql = "DELETE FROM STATUS\n"
                + "WHERE CODE_PRODUCT = \"" + this.getCode_product() + "\"; ";
        String sql__ = "DELETE FROM LIST_ITEMS\n"
                + "WHERE CODE_PRODUCT = \"" + this.getCode_product() + "\"; ";
        String sql_ = "DELETE FROM PRODUCTS\n"
                + "WHERE CODE_PRODUCT = \"" + this.getCode_product() + "\"; ";

        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(sql);
            s.executeUpdate(sql__);
            s.executeUpdate(sql_);
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
        String sql = "SELECT * FROM PRODUCTS NATURAL JOIN STATUS WHERE CODE_PRODUCT = \"" + id + "\"";

        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                String Ma_Thiet_bi = rs.getString(1);
                String Ten_Thiet_bi = rs.getString(2);
                String Ma_NSX = rs.getString(3);
                String Ma_loai = rs.getString(4);
                String Anh_SP = rs.getString(5);
                int So_Luong = rs.getInt(6);
                int Gia_Thanh = rs.getInt(7);

                this.setCode_product(Ma_Thiet_bi);
                this.setName(Ten_Thiet_bi);
                this.setCode_supplier(Ma_NSX);
                this.setCode_type(Ma_loai);
                this.setQuantity(So_Luong);
                this.setPrice(Gia_Thanh);
                this.setImage(Anh_SP);

            }
            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println("error in function Get_sp");
        }
        return false;
    }

    @Override
    public String generate_CODE() {
        String s = "PROD-";
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

    public boolean is_Available(String name, String maNSX) {
        String sql = "SELECT\n"
                + "	*\n"
                + "FROM\n"
                + "	PRODUCTS\n"
                + "WHERE\n"
                + "	(\n"
                + "		NAME = \"" + name + "\"\n"
                + "	)\n"
                + "AND (\n"
                + "	CODE_PRODUCT = \"" + maNSX + "\"\n"
                + ")";

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
    public boolean is_Available(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getDetail() {
        String sql = "SELECT * FROM PRODUCTS NATURAL JOIN STATUS WHERE CODE_PRODUCT = \"" + this.getCode_product() + "\"";

        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                String Ma_Thiet_bi = rs.getString(1);
                String Ten_Thiet_bi = rs.getString(2);
                String Ma_NSX = rs.getString(3);
                String Ma_loai = rs.getString(4);
                String Anh_SP = rs.getString(5);
                int So_Luong = rs.getInt(6);
                int Gia_Thanh = rs.getInt(7);

                this.setCode_product(Ma_Thiet_bi);
                this.setName(Ten_Thiet_bi);
                this.setCode_supplier(Ma_NSX);
                this.setCode_type(Ma_loai);
                this.setQuantity(So_Luong);
                this.setPrice(Gia_Thanh);
                this.setImage(Anh_SP);

            }
            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println("error in function Get_sp");
        }
        return false;
    }
}
