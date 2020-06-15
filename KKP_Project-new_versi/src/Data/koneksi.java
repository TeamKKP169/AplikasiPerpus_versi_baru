package Data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hikigaya
 */
public class koneksi{
 public static Connection conn;
    public static Connection connect() { 
try {
 String url = "jdbc:mysql://localhost:3308/app_bookll";
conn = (Connection) DriverManager.getConnection (url, "root","@@##bagas");


}catch (SQLException ex) {
        Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
}
        return conn;
    }
}