/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login;
import java.sql.*;
import java.sql.DriverManager;
/**
 *
 * @author hikigaya
 */
class Koneksi {
   private String server;
    private String db;
    private String user;
    private String pass;
    
    public void setServer(String pserver){
    this.server = pserver;
    }
   public void setDB(String db){
   this.db = db;
   }
    public void setUser(String user){
    this.user = user;
    
    }
    public void setPass(String pass){
    this.pass= pass;
    }
    public String getServer(){
    return server;
    
    }
   public String getDB(){
   return db;
   }
    public String getUser(){
    return user;
    }
    public String getPass(){
    return pass;
    }     
}
