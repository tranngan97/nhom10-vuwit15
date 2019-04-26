/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.MyConnect;
//import Model.Input_bill;
import Model.Item;
//import Model.Output_bill;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Hiep
 */
public class Manage_storage_CONTROLLER {

    MyConnect db = new MyConnect();
    Connection con = null;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    java.util.Date date = new java.util.Date();
    String date_input = dateFormat.format(date);
//Input_bill

//    public boolean insert(Output_bill bill) {
//        return bill.insert();
//    }

//    public boolean insert(Input_bill bill) {
//        return bill.insert();
//    }

    public boolean insert(Item item) {

        return item.insert();
    }

    public ArrayList<Item> getListItems(String id) {
        ArrayList<Item> list = new ArrayList<>();
        String query = "SELECT * FROM LIST_ITEMS WHERE CODE_BILL = '" + id + "'";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(query);

            while (rs.next()) {
                String code_bill = rs.getString(1);
                String code_product = rs.getString(2);
                String code_sup = rs.getString(3);
                String code_type = rs.getString(4);
                int quantity = rs.getInt(5);
                int price = rs.getInt(6);

                Item item = new Item(code_bill, code_product, code_sup, code_type, quantity, price);
                list.add(item);
            }
            con.close();
            s.close();
        } catch (Exception e) {
        }
        return list;
    }
}

//    public ArrayList<Output_bill> getListOutputBill() {
//        ArrayList<Output_bill> list_bill = new ArrayList<>();
//        String query = "SELECT * FROM OUTPUT_BILL ORDER BY `DATE` ASC";
//        try {
//            con = db.connect();
//            Statement s = null;
//            ResultSet rs = null;
//            s = con.createStatement();
//            rs = s.executeQuery(query);
//            ArrayList<Item> list_item = new ArrayList<>();
//            while (rs.next()) {
//                String code_input_bill = rs.getString(1);
//                String code_staff = rs.getString(2);
//                String name_cus = rs.getString(3);
//                Date date = rs.getDate(4);
//                Output_bill bill = new Output_bill(code_input_bill, code_staff, name_cus, date);
//                list_bill.add(bill);
//            }
//            con.close();
//            s.close();
//
//        } catch (Exception e) {
//        }
//        return list_bill;
//    }

//    public ArrayList<Input_bill> getListInputBill() {
//        ArrayList<Input_bill> list_bill = new ArrayList<>();
//        String query = "SELECT * FROM INPUT_BILL ORDER BY `DATE` ASC";
//        try {
//            con = db.connect();
//            Statement s = null;
//            ResultSet rs = null;
//            s = con.createStatement();
//            rs = s.executeQuery(query);
//            ArrayList<Item> list_item = new ArrayList<>();
//            while (rs.next()) {
//                String code_input_bill = rs.getString(1);
//                String code_staff = rs.getString(2);
//                Date date = rs.getDate(3);
//                Input_bill bill = new Input_bill(code_input_bill, code_staff, date);
//                list_bill.add(bill);
//            }
//            con.close();
//            s.close();
//
//        } catch (Exception e) {
//        }
//        return list_bill;
//    }
//
//    public boolean delete(Input_bill bill) {
//        ArrayList<Item> list = this.getListItems(bill.getCODE_INPUT_BILL());
//        for (Item list1 : list) {
//            list1.delete();
//        }
//        return bill.delete();
//    }
//
//    public boolean delete(Output_bill bill) {
//        ArrayList<Item> list = this.getListItems(bill.getCODE_OUTPUT_BILL());
//        for (Item list1 : list) {
//            list1.delete();
//        }
//        return bill.delete();
//    }
//
//    public ArrayList<Input_bill> search_InputBill(String key) {
//        ArrayList<Input_bill> result = new ArrayList<>();
//        String query = "SELECT * FROM INPUT_BILL WHERE DATE = '" + key + "'";
//        try {
//            con = db.connect();
//            Statement s = null;
//            ResultSet rs = null;
//            s = con.createStatement();
//            rs = s.executeQuery(query);
//            while (rs.next()) {
//                String code_input_bill = rs.getString(1);
//                String code_staff = rs.getString(2);
//                Date date = rs.getDate(3);
//                Input_bill bill = new Input_bill(code_input_bill, code_staff, date);
//                result.add(bill);
//            }
//            con.close();
//            s.close();
//
//        } catch (Exception e) {
//        }
//        return result;
//    }
//
//    public ArrayList<Output_bill> search_OutputBill(String key) {
//        ArrayList<Output_bill> result = new ArrayList<>();
//        String query = "SELECT * FROM OUTPUT_BILL WHERE (DATE = '" + key + "') OR (NAME_CUSTOMMER LIKE '%" + key + "%')";
//        try {
//            con = db.connect();
//            Statement s = null;
//            ResultSet rs = null;
//            s = con.createStatement();
//            rs = s.executeQuery(query);
//            while (rs.next()) {
//                String code_input_bill = rs.getString(1);
//                String code_staff = rs.getString(2);
//                String name_cus = rs.getString(3);
//                java.sql.Date date = rs.getDate(4);
//
//                Output_bill bill = new Output_bill(code_input_bill, code_staff, name_cus, date);
//                result.add(bill);
//            }
//            con.close();
//            s.close();
//
//        } catch (Exception e) {
//        }
//        return result;
//    }
//
//}
