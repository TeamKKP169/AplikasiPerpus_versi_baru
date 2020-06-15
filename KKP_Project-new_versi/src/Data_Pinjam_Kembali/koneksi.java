package Data_Pinjam_Kembali;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hikigaya
 */
public class koneksi {
    private static Connection mysqlconfig;
    public static Connection koneksi()throws SQLException{
        try {
            String url="jdbc:mysql://localhost:3308/app_book"; //url database
            String user="root"; //user database
            String pass="@@##bagas"; //password database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig=DriverManager.getConnection(url, user, pass);            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"Tidak Connect ke Database"); //perintah menampilkan error pada koneksi
        }
        return mysqlconfig;
    }    
}
