/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Hieu
 */
public class MyConnect {

    private final String className = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/quanlycuahang?useUnicode=yes&characterEncoding=UTF-8";
    private final String user = "root";
    private final String pass = "root";

    public java.sql.Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(className);
        return DriverManager.getConnection(url, user, pass);
    }
}
