/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.MyConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import Model.Staff;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 *
 */
public class Manage_Staff_CONTROLLER {

    MyConnect db = new MyConnect();
    Connection con = null;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    java.util.Date date = new java.util.Date();
    String date_roll_call = dateFormat.format(date);

    public Manage_Staff_CONTROLLER() {
        add_rollcall_date();
    }

//Kiểm tra ngày hôm nay đã thêm danh sách điểm danh chưa
    public boolean is_add_rollcall_date(String id) {
        String sql = "SELECT * FROM roll_call WHERE (DATE = '" + date_roll_call + "') AND (CODE_STAFF = '" + id + "')";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                con.close();
                s.close();
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("error in function is_add_rollcall_date");
        }

        return false;
    }
// Nếu hôm nay chưa thêm danh sách điểm danh thì thêm vào:

    public boolean add_rollcall_date() {
        ArrayList<Staff> list = this.get_List_Staff();
        for (Staff temp : list) {
            if (!is_add_rollcall_date(temp.getCode_staff())) {
                temp.setRoll_call(0);
                temp.insert_rollCall();
            }
        }
        return true;
    }
// tính lương;

    public int salary(Staff temp) {
        return temp.salary();
    }
// thêm nhân viên:

    public boolean Insert_Staff(Staff new_NV) {
        return (new_NV.insert() && add_rollcall_date());
    }
// Lấy danh sách các nhân viên:

    public ArrayList<Staff> get_List_Staff() {

        ArrayList<Staff> result = new ArrayList<>();
        String sql = "SELECT * FROM staffs ORDER BY `NAME` ASC";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                String Ma_Nhan_Vien = rs.getString(1);
                String Ho_Va_Ten = rs.getString(2);
                Date Ngay_Sinh = rs.getDate(3);
                String So_Dien_Thoai = rs.getString(4);
                String Anh_Nhan_Vien = rs.getString(5);
                Staff temp = new Staff(Ho_Va_Ten, Ngay_Sinh, So_Dien_Thoai, Ma_Nhan_Vien, Anh_Nhan_Vien);

                int roll_call = 0;

                String sql_ = "SELECT STATUS FROM ROLL_CALL WHERE (CODE_STAFF = '" + Ma_Nhan_Vien + "') AND (DATE = '" + date_roll_call + "') ";
                try {
                    Statement s_ = null;
                    ResultSet rs_ = null;
                    s_ = con.createStatement();
                    rs_ = s_.executeQuery(sql_);
                    while (rs_.next()) {
                        roll_call = rs_.getInt(1);
                    }
                } catch (Exception e) {
                    System.out.println("error here");
                }
                temp.setRoll_call(roll_call);

                result.add(temp);
            }
            con.close();
            s.close();
        } catch (Exception e) {
            System.out.println("error in function Get_NV list");
        }
        return result;
    }
// sửa thông tin nhân viên:

    public boolean Update_Staff(Staff edited_NV) {
        return edited_NV.update();
    }
// Xóa nhân viên:

    public boolean delete_Staff(Staff delete_NV) {
        return delete_NV.delete();
    }
// Tính lướng nhân viên:
}
