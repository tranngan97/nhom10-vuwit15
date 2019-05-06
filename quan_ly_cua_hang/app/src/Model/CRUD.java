/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Nguyen Hiep
 */
public interface CRUD {
    
    //Thêm bản ghi vào database
    boolean insert();

    //Cập nhập bản ghi vào database
    boolean update();
    
    //Xóa bản ghi khỏi database
    boolean delete();
    
    //Lấy dữ liệu bản ghi từ database
    boolean getDetail(String id);
    
    //Lấy dữ liệu bản ghi từ database
    boolean getDetail();
    
    //Tạo ID cho đối tượng
    String generate_CODE();
    
    boolean is_Available(String key);
}
