/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hikigaya
 */
public class Menu extends javax.swing.JFrame {
Connection conn;
private Statement stmt;
    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
       getdata();
        //setData1();
    }


private void setrver(){
    String text = tserver.getText();
        System.out.println(text);
        tserver.setText(text);
    try { 
        Files.write(Paths.get("../server.log"), text.getBytes());
    } catch (IOException ex) {
        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }

private void setuser(){
 String text = tuser.getText();
        System.out.println(text);
        tuser.setText(text);
    try { 
        Files.write(Paths.get("../user.log"), text.getBytes());
    } catch (IOException ex) {
        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
    }
}

private void setpass(){
String text = tpass.getText();
        System.out.println(text);
        tpass.setText(text);
    try { 
        Files.write(Paths.get("../pass.log"), text.getBytes());
    } catch (IOException ex) {
        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
    }
}


private void setdatabase(){
String text = tdb.getText();
        System.out.println(text);
        tdb.setText(text);
    try { 
        Files.write(Paths.get("../db.log"), text.getBytes());
    } catch (IOException ex) {
        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
    }
        
}

private void create(){
setrver();
setuser();
setpass();
setdatabase();
}


private void viewserver(){
String server;

        try{
            File file = new File("../server.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                server = input.nextLine();
               
                System.out.println(" read file details : "+server);
               txtserver1.setText(server);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        } 
}

private void viewUser(){
String User;

        try{
            File file = new File("../user.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                User = input.nextLine();
               
                System.out.println(" read file details : "+User);
               txtuser1.setText(User);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        } 
}

private void viewdb(){
String db;

        try{
            File file = new File("../db.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                db = input.nextLine();
               
                System.out.println(" read file details : "+db);
               txtdbase.setText(db);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        } 
}

private void viewPass(){
String pass;

        try{
            File file = new File("../pass.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                pass = input.nextLine();
               
                System.out.println(" read file details : "+pass);
               txtpass1.setText(pass);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        } 
}

private void getdata(){
viewserver();
viewUser();
viewPass();
viewdb();
}

private void config(){
 Koneksi sd = new Koneksi();
        sd.setServer(txtserver1.getText());
        sd.setDB(txtdbase.getText());
        sd.setUser(txtuser1.getText());
        sd.setPass(txtpass1.getText());
        try{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://" 
                +sd.getServer()+"/" 
                +sd.getDB(),
                sd.getUser(),
                sd.getPass());
       JOptionPane.showMessageDialog(null, "connect sukses ");
       
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
} 


private void buat(){
     try{
Class.forName("com.mysql.jdbc.Driver");
System.out.println("Berhasil Koneksi");

}
catch(ClassNotFoundException eg){
System.out.println("gagal koneksi"+eg);
}
 String a,b,c;
 a="jdbc:mysql://";
 b=tserver.getText();
 c="/";
String url =  ""+a+""+b+""+c+"";
String user;
user=tuser.getText();
String pass;
pass=tpass.getText();
try{
conn = DriverManager.getConnection(url,user,pass);
System.out.println("Berhasil Koneksi Database");
txthasil.setText("Berhasil Koneksi Database");
stmt = conn.createStatement();
String d,e,f;
d="CREATE";
e="DATABASE";
f=tdb.getText();
    String sql = ""+d+" "+e+" "+f+"";

stmt.executeUpdate(sql);
System.out.println("Database berhasil dibuat...");
txthasil.setText("\nHasil Output !   "+"\n1   Database Berhasil dibuat"+"\n2   Nama DataBase  = "+f+"\n\n");
stmt.close();
conn.close();
}
catch (SQLException ex){
System.out.println("Gagal Koneksi Database"+ex);
txthasil.setText("\n1  Koneksi"+"\nBerhasil Koneksi"+"\n2  Database sudah ada"+"\n"+ex);
}

}
private void insert(){
try{
Class.forName("com.mysql.jdbc.Driver");
System.out.println("Berhasil Koneksi");

}
catch(ClassNotFoundException eg){
System.out.println("gagal koneksi"+eg);
}
 String a,b,c,d;
 a="jdbc:mysql://";
 b=tserver.getText();
 c="/";
 d=tdb.getText();
String url =  ""+a+""+b+""+c+""+d+"";
String user;
user=tuser.getText();
String pass;
pass=tpass.getText();
String DB;
DB=tdb.getText();

try{
Connection conn = null;
    Statement stmt = null;

    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection(url, user, pass);
    System.out.println("Deleting database...");
    stmt = conn.createStatement();

    conn = DriverManager.getConnection(url, user, pass);

    stmt = conn.createStatement();

    String sql = "INSERT INTO login VALUES ('admin', 'admin', 'admin', 'admin123', 'admin')";
    stmt.executeUpdate(sql);
           sql = "INSERT INTO login VALUES ('user', 'user', 'user', 'user123', 'user')";
    stmt.executeUpdate(sql);
  
    stmt.executeUpdate(sql);
    stmt.close();
    conn.close();
}catch(Exception e){
}

}



private void buattabeldatabase(){
try{
Class.forName("com.mysql.jdbc.Driver");
System.out.println("Berhasil Koneksi");

}
catch(ClassNotFoundException eg){
System.out.println("gagal koneksi"+eg);
}
 String a,b,c,d;
 a="jdbc:mysql://";
 b=tserver.getText();
 c="/";
 d=tdb.getText();
String url =  ""+a+""+b+""+c+""+d+"";
String user;
user=tuser.getText();
String pass;
pass=tpass.getText();
String DB;
DB=tdb.getText();

try{
    Connection conn = null;
    Statement stmt = null;

    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection(url, user, pass);
    System.out.println("nymbung dulu database...");
    stmt = conn.createStatement();

    conn = DriverManager.getConnection(url, user, pass);

    stmt = conn.createStatement();
  
    String sql1="CREATE TABLE buku"
            + "(id_buku INTEGER not NULL, "
            + " judul VARCHAR(200), " 
            + " pengarang VARCHAR(100), "
            + " penerbit VARCHAR(100), "
            + " kategori VARCHAR(50), "
            + " isbn INTEGER, "
            + " stok INTEGER, " 
            + " PRIMARY KEY ( id_buku ))";
    
    String sql2="CREATE TABLE kategori_buku"
            + "(id INTEGER not NULL, "
            + " nama_kategori VARCHAR(100), " 
            + " PRIMARY KEY ( id ))";
    
    String sql3="CREATE TABLE login"
            + "(id VARCHAR(50), "
            + " nama_depan VARCHAR(100), " 
            + " nama_belakang VARCHAR(100), "
            + " password VARCHAR(50), "
            + " hak VARCHAR(50), " 
            + " PRIMARY KEY ( id ))";
    
    String sql4="CREATE TABLE pengembalian"
            + "(id_pinjam INTEGER not NULL, "
            + " npm VARCHAR(100), " 
            + " nama VARCHAR(100), "
            + " id_buku INTEGER, "
            + " judul VARCHAR(200), "
            + " jumlah INTEGER, "
            + " tgl_pinjam VARCHAR(50), "
            + " tgl_balik VARCHAR(50), "
            + " status VARCHAR(50), "
            + " PRIMARY KEY ( id_pinjam ))";
    
    String sql5="CREATE TABLE pinjaman"
            + "(id_pinjam INTEGER not NULL, "
            + " npm VARCHAR(100), " 
            + " nama VARCHAR(100), "
            + " id_buku INTEGER, "
            + " judul VARCHAR(200), "
            + " jumlah INTEGER, "
            + " tgl_pinjam VARCHAR(50), "
            + " tgl_balik VARCHAR(50), "
            + " status VARCHAR(50), "
            + " PRIMARY KEY ( id_pinjam ))";
    
    String sql6="CREATE TABLE pengunjung"
            + "(id INTEGER not NULL, "
            + " nis VARCHAR(100), " 
            + " nama VARCHAR(100), "
            + " telpon VARCHAR(50), "
            + " tanggal VARCHAR(50), "
            + " jam VARCHAR(50), "           
            + " PRIMARY KEY ( id ))";
    
    String sql7="CREATE TABLE siswa"
            + "(id INTEGER not NULL, "
            + " nis VARCHAR(100), " 
            + " nama VARCHAR(100), "
            + " alamat VARCHAR(50), "
            + " no_hp VARCHAR(50), "
            + " jenis_kelamin VARCHAR(50), " 
            + " kelas VARCHAR(50), "   
            + " PRIMARY KEY ( id ))";
    stmt.executeUpdate(sql1);
    stmt.executeUpdate(sql2);
    stmt.executeUpdate(sql3);
    stmt.executeUpdate(sql4);
    stmt.executeUpdate(sql5);
    stmt.executeUpdate(sql6);
    stmt.executeUpdate(sql7);
    System.out.println("Created table in given database...");
    stmt.close();
    conn.close();
 }catch(Exception e){}

}

private void viserver(){
String Server;

        try{
            File file = new File("../server.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                Server = input.nextLine();
               
                System.out.println(" read file details : "+Server);
               tserver.setText(Server);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        }     
}
private void viuser(){
String User;

        try{
            File file = new File("../user.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                User = input.nextLine();
               
                System.out.println(" read file details : "+User);
               tuser.setText(User);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        } 
}
private void vipass(){
String pass;

        try{
            File file = new File("../pass.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                pass = input.nextLine();
               
                System.out.println(" read file details : "+pass);
               tpass.setText(pass);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        } 
}
private void db(){
String DB;

        try{
            File file = new File("../db.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                DB = input.nextLine();
               
                System.out.println(" read file details : "+DB);
               tdb.setText(DB);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        } 
}

private void Server(){
String Server;

        try{
            File file = new File("../server.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                Server = input.nextLine();
               
                System.out.println(" read file details : "+Server);
               txtserver.setText(Server);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        } 
}

private void User(){
String User;

        try{
            File file = new File("../user.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                User = input.nextLine();
               
                System.out.println(" read file details : "+User);
               txtuser.setText(User);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        }
}

private void DB(){
String DB;

        try{
            File file = new File("../db.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                DB = input.nextLine();
               
                System.out.println(" read file details : "+DB);
               txtdb.setText(DB);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        } 
}

private void PASS(){
String pass;

        try{
            File file = new File("../pass.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                pass = input.nextLine();
               
                System.out.println(" read file details : "+pass);
               txtpass.setText(pass);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        } 
}

private void getView(){
Server();
User();
DB();
PASS();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jLabel14 = new javax.swing.JLabel();
        tabb2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        tserver = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tuser = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tpass = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        tdb = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txthasil = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        mainpanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtserver = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        txtdb = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        hasil = new javax.swing.JTextArea();
        jButton8 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtserver1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtuser1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtpass1 = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtdbase = new javax.swing.JTextField();
        btnconnect = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        button1 = new java.awt.Button();
        jPanel5 = new javax.swing.JPanel();
        button2 = new java.awt.Button();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(822, 531));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_logo/SMK PERINTIS DEPOK.png"))); // NOI18N
        jTabbedPane2.addTab("Logo", jLabel14);

        jTabbedPane1.addTab("File", jTabbedPane2);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("Server");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 30));
        jPanel6.add(tserver, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 370, 30));

        jLabel11.setText("User");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 110, 30));
        jPanel6.add(tuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 370, 30));

        jLabel12.setText("Password");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 110, 30));
        jPanel6.add(tpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 370, 30));

        jLabel13.setText("Database");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 98, 30));
        jPanel6.add(tdb, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 370, 33));

        jButton2.setText("Create Database");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, -1, 40));

        txthasil.setColumns(20);
        txthasil.setRows(5);
        jScrollPane1.setViewportView(txthasil);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 270, 240));

        jButton4.setText("view");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, 40));

        jButton5.setText("refresh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        tabb2.addTab("Membuat Database", jPanel6);

        mainpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Server");
        mainpanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 110, 30));
        mainpanel.add(txtserver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 220, 30));

        jLabel4.setText("User");
        mainpanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 110, 30));
        mainpanel.add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 30));

        jLabel3.setText("Password");
        mainpanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 110, 30));
        mainpanel.add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 220, 30));

        jLabel1.setText("Database");
        mainpanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 98, 30));
        mainpanel.add(txtdb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 220, 33));

        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        mainpanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, 30));

        jButton3.setText("view");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        mainpanel.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, -1, -1));

        jButton6.setText("refresh");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        mainpanel.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        hasil.setColumns(20);
        hasil.setRows(5);
        jScrollPane2.setViewportView(hasil);

        mainpanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 410, 180));

        jButton8.setText("refresh");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        mainpanel.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, -1, -1));

        tabb2.addTab("Edit Koneksi", mainpanel);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Server");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 110, 30));
        jPanel4.add(txtserver1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 220, 30));

        jLabel6.setText("User");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 110, 30));
        jPanel4.add(txtuser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 220, 30));

        jLabel7.setText("Password");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 110, 30));
        jPanel4.add(txtpass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 220, 30));

        jLabel8.setText("Database");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 98, 30));
        jPanel4.add(txtdbase, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 33));

        btnconnect.setText("Connect");
        btnconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconnectActionPerformed(evt);
            }
        });
        jPanel4.add(btnconnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jButton7.setText("refresh");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        tabb2.addTab("Tes Koneksi", jPanel4);

        jTabbedPane1.addTab("Tools", tabb2);
        jTabbedPane1.addTab("Help", jTabbedPane5);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 43, 820, 430));

        button1.setBackground(new java.awt.Color(51, 51, 51));
        button1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("Login");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        getContentPane().add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 483, 164, -1));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        button2.setLabel("x");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel5.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, 40, 40));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Sistem Informasi Perpustakaaan");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 520, 40));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
String text = txtserver.getText();
        System.out.println(text);
        txtserver.setText(text);
    try { 
        Files.write(Paths.get("../server.log"), text.getBytes());
    } catch (IOException ex) {
        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
    }       
 String aa = txtuser.getText();
        System.out.println(aa);
        txtuser.setText(aa);
    try { 
        Files.write(Paths.get("../user.log"), aa.getBytes());
    } catch (IOException ex) {
        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
    }   
String bb = txtpass.getText();
        System.out.println(bb);
        txtpass.setText(bb);
    try { 
        Files.write(Paths.get("../pass.log"), bb.getBytes());
    } catch (IOException ex) {
        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
    }
  String cc = txtdb.getText();
        System.out.println(cc);
        txtdb.setText(cc);
    try { 
        Files.write(Paths.get("../db.log"), cc.getBytes());
    } catch (IOException ex) {
        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
    }  
    hasil.setText("Data sudah terupdate");
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconnectActionPerformed
config();        // TODO add your handling code here:
    }//GEN-LAST:event_btnconnectActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_button2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
Menu_login a=new Menu_login();
a.setVisible(true);
dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
buat();
buattabeldatabase();
create();
insert();
txthasil.setText("Database dan tabel sudah di buat ");
// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
getView();
 
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
getdata();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
Menu a=new Menu();


// TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

       private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
Menu a=new Menu();
a.setVisible(true);
dispose();
               
// TODO add your handling code here:
       }//GEN-LAST:event_jButton5ActionPerformed

       private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
Menu a=new Menu();
a.setVisible(true);
dispose();              // TODO add your handling code here:
       }//GEN-LAST:event_jButton6ActionPerformed

       private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
  Menu a=new Menu();
a.setVisible(true);
dispose();             // TODO add your handling code here:
       }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
hasil.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnconnect;
    private java.awt.Button button1;
    private java.awt.Button button2;
    private javax.swing.JTextArea hasil;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JTabbedPane tabb2;
    private javax.swing.JTextField tdb;
    private javax.swing.JPasswordField tpass;
    private javax.swing.JTextField tserver;
    private javax.swing.JTextField tuser;
    private javax.swing.JTextField txtdb;
    private javax.swing.JTextField txtdbase;
    private javax.swing.JTextArea txthasil;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JPasswordField txtpass1;
    private javax.swing.JTextField txtserver;
    private javax.swing.JTextField txtserver1;
    private javax.swing.JTextField txtuser;
    private javax.swing.JTextField txtuser1;
    // End of variables declaration//GEN-END:variables
}
