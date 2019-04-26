/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import Database.MyConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Nguyen Hiep
 */
public class Staff implements CRUD {

    private String Name;
    private java.sql.Date Date_of_birth;
    private String Phone_number;
    private String Code_staff;
    private String Image;
    private int roll_call = 0;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    java.util.Date date = new java.util.Date();
    String date_roll_call = dateFormat.format(date);

    static int Luong_Co_Ban = 4000000;
    static int Phat_Di_Muon = 10000;
    static int Phat_Nghi_Co_Phep = 10000;
    static int Phat_Nghi_Khong_Phep = 50000;
    static String default_ava = "profile.png";

    MyConnect db = new MyConnect();
    Connection con = null;

    public Staff() {
    }

    // lấy nhân viên từ database ra cho vào đây
    public Staff(String Ho_Va_Ten, Date Ngay_Sinh, String So_Dien_Thoai, String Ma_Nhan_Vien, String Anh_Nhan_Vien) throws ParseException {
        this.Name = Ho_Va_Ten;
        this.Date_of_birth = Ngay_Sinh;
        this.Phone_number = So_Dien_Thoai;
        this.Code_staff = Ma_Nhan_Vien;
        this.Image = Anh_Nhan_Vien;
    }

    //Khởi tạo 1 nhân viên mới không ảnh
    public Staff(String Ho_Va_Ten, String Ngay_Sinh, String So_Dien_Thoai) throws ParseException {
        this.Code_staff = generate_CODE();
        this.Name = Ho_Va_Ten;

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(Ngay_Sinh);
        this.Date_of_birth = new java.sql.Date(date.getTime());

        this.Phone_number = So_Dien_Thoai;
        this.Image = default_ava;
    }

    //Khởi tạo 1 nhân viên mới có ảnh
    public Staff(String Ho_Va_Ten, String Ngay_Sinh, String So_Dien_Thoai, String Anh_Nhan_Vien) throws ParseException {
        this.Code_staff = generate_CODE();
        this.Name = Ho_Va_Ten;

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(Ngay_Sinh);
        this.Date_of_birth = new java.sql.Date(date.getTime());

        this.Phone_number = So_Dien_Thoai;
        this.Image = Anh_Nhan_Vien;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Anh_Nhan_Vien) {
        this.Image = Anh_Nhan_Vien;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Ho_Va_Ten) {
        this.Name = Ho_Va_Ten;
    }

    public int getRoll_call() {
        return roll_call;
    }

    public void setRoll_call(int roll_call) {
        this.roll_call = roll_call;
    }

    public Date getDate_of_birth() {
        return Date_of_birth;
    }

    public void setDate_of_birth(String Ngay_Sinh) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(Ngay_Sinh);
        this.Date_of_birth = new java.sql.Date(date.getTime());
    }

    public void setDate_of_birth(Date Ngay_Sinh) {
        this.Date_of_birth = Ngay_Sinh;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String So_Dien_Thoai) {
        this.Phone_number = So_Dien_Thoai;
    }

    public void setCode_staff(String Ma_Nhan_Vien) {
        this.Code_staff = Ma_Nhan_Vien;
    }

    public String getCode_staff() {
        return Code_staff;
    }
    //Hàm thêm danh sách điểm danh:

    public boolean insert_rollCall() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String sql_ = "INSERT INTO roll_call (CODE_STAFF,DATE, STATUS) VALUES ('" + this.getCode_staff() + "','" + dtf.format(localDate) + "','" + this.roll_call + "')";
        try {
            con = db.connect();
            Statement s = null;
            s = con.createStatement();
            s.executeUpdate(sql_);
            // ngắn kết nối

            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

//Hàm điểm danh    
    public boolean roll_call() {
        String sql = "UPDATE roll_call SET `STATUS`='" + this.roll_call + "' WHERE (CODE_STAFF = '" + this.getCode_staff() + "') AND (DATE = '" + date_roll_call + "')";
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
//"Chưa điểm danh", "Đúng giờ", "Đi muộn", "Nghỉ có phép", "Nghỉ không phép" }));

    public int count_timeslate() {
        int count = 0;
        String sql = "SELECT COUNT(STATUS) FROM roll_call WHERE (CODE_STAFF = '" + this.getCode_staff() + "') AND (`STATUS`='2')";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                count = rs.getInt(1);
            }
            con.close();
            s.close();
        } catch (Exception e) {
            System.out.println("error in function count_timeslate");
        }
        return count;

    }

    public int count_off() {
        int count = 0;
        String sql = "SELECT COUNT(STATUS) FROM roll_call WHERE (CODE_STAFF = '" + this.getCode_staff() + "') AND (`STATUS`='3')";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                count = rs.getInt(1);
            }
            con.close();
            s.close();
        } catch (Exception e) {
            System.out.println("error in function count_off");
        }
        return count;

    }

    public int count_offwithout() {
        int count = 0;
        String sql = "SELECT COUNT(STATUS) FROM roll_call WHERE (CODE_STAFF = '" + this.getCode_staff() + "') AND (`STATUS`='4')";
        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                count = rs.getInt(1);
            }
            con.close();
            s.close();
        } catch (Exception e) {
            System.out.println("error in function count_offwithout");
        }
        return count;

    }

    public int salary() {
        return Luong_Co_Ban - (this.count_off() * Phat_Nghi_Co_Phep + this.count_offwithout() * Phat_Nghi_Khong_Phep + this.count_timeslate() * Phat_Di_Muon);
    }

    public static void main(String[] args) {
        Staff a = new Staff();
        a.getDetail("STF-2888-5049");
        // System.out.println(a.getDate_of_birth()));
    }

    @Override
    public String toString() {
        return "Staff{" + "Name=" + Name + ", Date_of_birth=" + Date_of_birth + ", Phone_number=" + Phone_number + ", Code_staff=" + Code_staff + ", Image=" + Image + '}';
    }

    @Override

    public boolean insert() {

        String sql = "INSERT INTO STAFFS (CODE_STAFF,NAME,DATE_OF_BIRTH,PHONE_NUMBER,IMAGE)\n"
                + "VALUES ('" + this.getCode_staff() + "', '" + this.getName() + "', '" + this.getDate_of_birth()
                + "', '" + this.getPhone_number() + "', '" + this.getImage() + "');";
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
    public boolean update() {
        String sql = "UPDATE STAFFS\n"
                + "SET NAME = '" + this.getName() + "',\n"
                + " DATE_OF_BIRTH = '" + this.getDate_of_birth() + "',\n"
                + " PHONE_NUMBER = '" + this.getPhone_number() + "',\n"
                + " IMAGE = '" + this.getImage() + "'\n"
                + "WHERE\n"
                + "	CODE_STAFF = '" + this.getCode_staff() + "';";
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
        String sql = "DELETE FROM ROLL_CALL\n"
                + "WHERE CODE_STAFF = \"" + this.getCode_staff() + "\"; ";

        String sql_ = "DELETE FROM STAFFS\n"
                + "WHERE CODE_STAFF = \"" + this.getCode_staff() + "\"; ";
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
    public boolean getDetail(String id) {
        String sql = "SELECT * FROM staffs NATURAL JOIN roll_call WHERE CODE_STAFF = '" + id + "'";

        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                String Ma_Nhan_Vien = rs.getString(1);
                String Ho_Va_Ten = rs.getString(2);
                java.sql.Date Ngay_Sinh = rs.getDate(3);
                String So_Dien_Thoai = rs.getString(4);
                String Anh_Nhan_Vien = rs.getString(5);
                //Date date = rs.getDate(6);
                int status = rs.getInt(7);

                this.setCode_staff(Ma_Nhan_Vien);
                this.setName(Ho_Va_Ten);
                this.setDate_of_birth(Ngay_Sinh);
                this.setPhone_number(So_Dien_Thoai);
                this.setImage(Anh_Nhan_Vien);
                //this.setDate_roll_call(date);
                this.setRoll_call(status);
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
        String s = "STF-";
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
        String sql = "SELECT * FROM STAFFS WHERE CODE_STAFF = \"" + key + "\"";

        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);
            con.close();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println("error in function is_Available");
        }
        return false;
    }

    @Override
    public boolean getDetail() {
        String sql = "SELECT * FROM staffs NATURAL JOIN roll_call WHERE CODE_STAFF = '" + this.getCode_staff() + "'";

        try {
            con = db.connect();
            Statement s = null;
            ResultSet rs = null;
            s = con.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                String Ma_Nhan_Vien = rs.getString(1);
                String Ho_Va_Ten = rs.getString(2);
                java.sql.Date Ngay_Sinh = rs.getDate(3);
                String So_Dien_Thoai = rs.getString(4);
                String Anh_Nhan_Vien = rs.getString(5);
                //Date date = rs.getDate(6);
                int status = rs.getInt(7);

                this.setCode_staff(Ma_Nhan_Vien);
                this.setName(Ho_Va_Ten);
                this.setDate_of_birth(Ngay_Sinh);
                this.setPhone_number(So_Dien_Thoai);
                this.setImage(Anh_Nhan_Vien);
                //this.setDate_roll_call(date);
                this.setRoll_call(status);
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
