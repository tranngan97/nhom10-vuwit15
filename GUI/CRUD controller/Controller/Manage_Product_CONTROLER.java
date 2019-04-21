/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.MyConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import Model.Type_Of_Product;
import Model.Supplier;
import Model.Staff;
import Model.Product;

/**
 *
 * @author Nguyen Hiep
 */
public class Manage_Product_CONTROLER {

    MyConnect db = new MyConnect();
    Connection con = null;

    // kiểm tra loại sản phẩm đã có chưa?
    public boolean is_Available_type(String key) {
        Type_Of_Product temp = new Type_Of_Product();
        return temp.is_Available(key);
    }

    // kiểm tra Nhà sản xuất đã có chưa?
    public boolean is_Available_sup(String key) {
        Supplier temp = new Supplier();
        return temp.is_Available(key);
    }

    // kiểm tra sản phẩm đã có chuaw
    public boolean is_Available_pro(String name, String maNSX) {
        Product temp = new Product();
        return temp.is_Available(name, maNSX);
    }
//Tìm kiếm

    public ArrayList<Product> Search(String key) {
        ArrayList<Product> list_result = new ArrayList<>();
        String sql = "SELECT\n"
                + "	p.*,stt.QUANTITY,stt.PRICE\n"
                + "FROM\n"
                + "	products p,\n"
                + "	type_of_product t,\n"
                + "	`status` stt,\n"
                + "	supplier sup\n"
                + "WHERE\n"
                + "	(p.CODE_TYPE = t.CODE_TYPE)\n"
                + "AND (p.CODE_SUPPLIER = sup.CODE_SUPPLIER)\n"
                + "AND (p.CODE_PRODUCT = stt.CODE_PRODUCT)\n"
                + "AND ((p.`NAME` LIKE '%" + key + "%')\n"
                + "		OR (sup.`NAME` LIKE '%" + key + "%')\n"
                + "		OR (sup.`COUNTRY` LIKE '%" + key + "%')\n"
                + "		OR (t.`NAME` LIKE '%" + key + "%')\n"
                + "		OR (stt.PRICE = '" + key + "')\n"
                + "	);";
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

                Product temp = new Product(Ma_Thiet_bi, Ten_Thiet_bi, Ma_NSX, Ma_loai, So_Luong, Gia_Thanh, Anh_SP);

                list_result.add(temp);
            }
            con.close();
            s.close();
        } catch (Exception e) {
            System.out.println("error in function search_sp");
        }
        return list_result;
    }

//thêm & lấy sản phẩm
    public Product getNewestProd() {
        Product temp = new Product();
        String query = "SELECT * \n"
                + "FROM    PRODUCTS\n"
                + "WHERE   CODE_PRODUCT = (SELECT MAX(CODE_PRODUCT)  FROM PRODUCTS);";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(query);

            while (rs.next()) {
                String Ma_Thiet_bi = rs.getString(1);
                String Ten_Thiet_bi = rs.getString(2);
                String Ma_NSX = rs.getString(3);
                String Ma_loai = rs.getString(4);
                String Anh_SP = rs.getString(5);

                temp.setCode_product(Ma_Thiet_bi);
                temp.setName(Ten_Thiet_bi);
                temp.setCode_supplier(Ma_NSX);
                temp.setCode_type(Ma_loai);
                temp.setImage(Anh_SP);

            }
            con.close();
            s.close();
        } catch (Exception e) {
            System.out.println("error in function Get_sp");
        }
        return temp;
    }

    public Supplier getNewestSup() {
        Supplier sup = new Supplier();
        String query = "SELECT * \n"
                + "FROM    SUPPLIER\n"
                + "WHERE   CODE_SUPPLIER = (SELECT MAX(CODE_SUPPLIER)  FROM SUPPLIER);";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(query);

            while (rs.next()) {
                String Ma_NSX = rs.getString(1);
                String Ten_NSX = rs.getString(2);
                String Quoc_Gia = rs.getString(3);

                sup.setCode_Supplier(Ma_NSX);
                sup.setName_Supplier(Ten_NSX);
                sup.setCountry(Quoc_Gia);

            }

            con.close();
            s.close();
        } catch (Exception e) {
            System.out.println("error in function Get_NSX");
        }
        return sup;
    }

    public Type_Of_Product getNewestType() {
        Type_Of_Product type = new Type_Of_Product();
        String query = "SELECT * \n"
                + "FROM    TYPE_OF_PRODUCT\n"
                + "WHERE   CODE_TYPE = (SELECT MAX(CODE_TYPE)  FROM TYPE_OF_PRODUCT);";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(query);

            while (rs.next()) {
                String Ma_Loai = rs.getString(1);
                String Ten_Loai = rs.getString(2);
                String Don_Vi_Tinh = rs.getString(3);

                type.setCodeType(Ma_Loai);
                type.setNameType(Ten_Loai);
                type.setUnit(Don_Vi_Tinh);

            }
            con.close();
            s.close();
        } catch (Exception e) {
            System.out.println("error in function Get_sp");
        }
        return type;
    }

    public boolean insert(Type_Of_Product type) {
        return type.insert();
    }

    public ArrayList<Type_Of_Product> get_List_Type() {
        ArrayList<Type_Of_Product> list_result = new ArrayList<>();
        String sql = "SELECT * FROM TYPE_OF_PRODUCT ;";

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

                Type_Of_Product temp = new Type_Of_Product(Ma_Loai, Ten_Loai, Don_Vi_Tinh);
                list_result.add(temp);
            }
            con.close();
            s.close();
        } catch (Exception e) {
            System.out.println("error in function Get_Loaisp");
        }
        return list_result;
    }

    public boolean insert(Product product) {
        return product.insert();
    }

    public ArrayList<Product> get_List_Product() {
        ArrayList<Product> list_result = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS NATURAL JOIN STATUS ORDER BY NAME ASC";
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

                Product temp = new Product(Ma_Thiet_bi, Ten_Thiet_bi, Ma_NSX, Ma_loai, So_Luong, Gia_Thanh, Anh_SP);
                list_result.add(temp);
            }
            con.close();
            s.close();
        } catch (Exception e) {
            System.out.println("error in function Get_sp");
        }
        return list_result;
    }

    public boolean insert(Supplier sup) {
        return sup.insert();
    }

    public ArrayList<Supplier> get_List_Supplier() {
        ArrayList<Supplier> list_result = new ArrayList<>();
        String sql = "SELECT * FROM SUPPLIER ;";
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

                Supplier temp = new Supplier(Ma_NSX, Ten_NSX, Quoc_Gia);
                list_result.add(temp);
            }
            con.close();
            s.close();
        } catch (Exception e) {
            System.out.println("error in function Get_NSX");
        }
        return list_result;
    }
// hết phần thêm & lấy sản phẩm

// Sửa sản phẩm
    public boolean update(Product eidted_Sp) {
        return eidted_Sp.update();
    }
// Sửa loại sản phẩm:

    public boolean update(Type_Of_Product eidted_Sp) {
        return eidted_Sp.update();
    }
//hết phần sửa sản phẩm 
//xóa sản phẩm 

    public boolean Delete_product(Product delete_Sp) {
        return delete_Sp.delete();
    }

    public static void main(String[] args) {
        Manage_Product_CONTROLER control = new Manage_Product_CONTROLER();

        ArrayList<Product> result = control.Search("dây");
        for (Product result1 : result) {
            System.out.println(result.toString());
        }
//        }
    }
}
