package Data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import login.Menu_login;

/**
 *
 * @author hikigaya
 */
public class Pengunjung extends javax.swing.JFrame {
Connection conn;

public Timer t = null;
    private int count = 0;

    /**
     * Creates new form Pengunjung
     */
    public Pengunjung() {
        initComponents();
        setData();
        config();
        txtnama.setEditable(false);
        txttelpon.setEditable(false);
        txttanggal.setEditable(false);
        txtkd.setEditable(false);
        txtjam.setEditable(false);
        //txtnis.setEditable(false);
        txtnis.setText("");
        tanggal();
        autoNumber();
        refresh();
         getData();
         jDialog1.setLocationRelativeTo(this);
         setTanggal();
          setJam();
          setwaktu();
   
    t = new Timer(100, (ActionEvent e) -> {
            count +=5;
            jProgressBar1.setValue(count);
            if(jProgressBar1.getValue()<100){
                jProgressBar1.setValue(jProgressBar1.getValue()+ 1);
            }
            if(jProgressBar1.getValue()==100 ){
               
               jDialog1.setLocationRelativeTo(this);
                t.stop();
                
            } 
        });
        t.start();
    
    
        
     try{
         BufferedImage beam = ImageIO.read(getClass().getResource("SMK PERINTIS DEPOK.png"));
        setIconImage(beam);
        } catch (IOException ex) {
            Logger.getLogger(Pengunjung.class.getName()).log(Level.SEVERE, null, ex);
        }    
       this.setTitle("FORM Pengunjung");
    }
    
    private void login(){
 Menu_login a=new Menu_login();
a.setVisible(true);
dispose();
    }
    
  public void setTanggal(){
    java.util.Date skrg = new java.util.Date();
    java.text.SimpleDateFormat kal = new
    java.text.SimpleDateFormat("dd-MM-yyyy");
    jLabel9.setText(kal.format(skrg));
}

public void setJam(){
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            String nol_jam = "", nol_menit = "",nol_detik = "";
            java.util.Date dateTime = new java.util.Date();
            int nilai_jam = dateTime.getHours();
            int nilai_menit = dateTime.getMinutes();
            int nilai_detik = dateTime.getSeconds();
            if(nilai_jam <= 9) nol_jam= "0";
            if(nilai_menit <= 9) nol_menit= "0";
            if(nilai_detik <= 9) nol_detik= "0";
            String waktu = nol_jam + Integer.toString(nilai_jam);
            String menit = nol_menit + Integer.toString(nilai_menit);
            String detik = nol_detik + Integer.toString(nilai_detik);
            txtjam.setText(waktu+":"+menit+":"+detik+"");
        }
    };
    new Timer(1000, taskPerformer).start();
} 
public void setwaktu(){
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            String nol_jam = "", nol_menit = "",nol_detik = "";
            java.util.Date dateTime = new java.util.Date();
            int nilai_jam = dateTime.getHours();
            int nilai_menit = dateTime.getMinutes();
            int nilai_detik = dateTime.getSeconds();
            if(nilai_jam <= 9) nol_jam= "0";
            if(nilai_menit <= 9) nol_menit= "0";
            if(nilai_detik <= 9) nol_detik= "0";
            String waktu = nol_jam + Integer.toString(nilai_jam);
            String menit = nol_menit + Integer.toString(nilai_menit);
            String detik = nol_detik + Integer.toString(nilai_detik);
            jLabel10.setText(waktu+":"+menit+":"+detik+"");
        }
    };
    new Timer(1000, taskPerformer).start();
}  

    
  public void getData() {
        jLabel12.setText(txtkd.getText());
        jLabel13.setText(txtnis.getText());
       jLabel14.setText(txtnama.getText());
       jLabel15.setText(txttelpon.getText());
       jLabel16.setText(txttanggal.getText());
       jLabel11.setText(txtjam.getText());
         
       
  }
    
    public void tanggal(){
Calendar cal = new GregorianCalendar();
int day = cal.get(Calendar.DAY_OF_MONTH);
int month =cal.get(Calendar.MONTH);
int year = cal.get(Calendar.YEAR);




txttanggal.setText(day + "-" + 
        (month + 1) 
           + "-" + year);
}
    
private void datasiswa(){
Data_siswa a=new Data_siswa();
a.setVisible(true);
dispose();

}    
    
public void autoNumber() {
		try{
                        //Connection conn = (Connection) new Koneksi();
			Statement state = conn.createStatement();
			String query = "SELECT MAX(id) FROM pengunjung"; //Mendapatkan nilai id terakhir
			
			ResultSet rs = state.executeQuery(query);
			if(rs.next())
			{
				int a = rs.getInt(1);
				txtkd.setText(Integer.toString(a + 001));
			}
			rs.close();
			state.close();
  
  } catch (Exception ex) {
			System.out.println(ex);
		}
	}    

   private void refresh() {
      //  txtkd.setText("");
        txtnis.setText("");
        txtnama.setText("");
        txttelpon.setText("");
       
        }  
private void config(){
 Koneksi sd = new Koneksi();
        sd.setServer(txtserver.getText());
        sd.setDB(txtdb.getText());
        sd.setUser(txtuser.getText());
        sd.setPass(txtpass.getText());
        try{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://" 
                +sd.getServer()+"/" 
                +sd.getDB(),
                sd.getUser(),
                sd.getPass());
       JOptionPane.showMessageDialog(null, "connected....  ");
       
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, "connect....gagal  ");
        } 
} 

private void viewserver(){
String server;

        try{
            File file = new File("../server.log");
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                server = input.nextLine();
               
                System.out.println(" read file details : "+server);
               txtserver.setText(server);
               
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
               txtuser.setText(User);
               
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
               txtpass.setText(pass);
               
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
               txtdb.setText(db);
               
            }
            input.close();

        }catch(Exception e){
            e.printStackTrace();
        } 
}
private void setData(){
viewserver();
 viewUser();
 viewPass();
 viewdb();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        daftar = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDialog1 = new javax.swing.JDialog();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jDialog2 = new javax.swing.JDialog();
        jLabel22 = new javax.swing.JLabel();
        txtserver = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        jLabel25 = new javax.swing.JLabel();
        txtdb = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtnis = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txttelpon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttanggal = new javax.swing.JTextField();
        txtjam = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtkd = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        daftar.setMinimumSize(new java.awt.Dimension(400, 300));

        jLabel2.setText("Untuk Pendaftaran Hubungi Petugas Perpustakaan");

        jLabel3.setText("Selamat Berkunjung");

        javax.swing.GroupLayout daftarLayout = new javax.swing.GroupLayout(daftar.getContentPane());
        daftar.getContentPane().setLayout(daftarLayout);
        daftarLayout.setHorizontalGroup(
            daftarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daftarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(daftarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        daftarLayout.setVerticalGroup(
            daftarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daftarLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addGap(47, 47, 47)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        jDialog1.setMinimumSize(new java.awt.Dimension(355, 350));
        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setText("jLabel12");
        jDialog1.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 150, 30));

        jLabel13.setText("jLabel13");
        jDialog1.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 10, 30));

        jLabel14.setText("jLabel14");
        jDialog1.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 20, 20));

        jLabel15.setText("jLabel15");
        jDialog1.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 20, 20));

        jLabel16.setText("jLabel16");
        jDialog1.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 160, 30));

        jLabel7.setText("Kode Pengunjung");
        jDialog1.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        jLabel8.setText("Tanggal berkunjung");
        jDialog1.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        jButton4.setText("Close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 240, 40));

        jLabel11.setText("jLabel11");
        jDialog1.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, -1));

        jLabel17.setText("Waktu berkunjung");
        jDialog1.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        jProgressBar1.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jProgressBar1InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jDialog1.getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 240, 20));

        jDialog2.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setText("Server");
        jDialog2.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 110, 30));
        jDialog2.getContentPane().add(txtserver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 220, 30));

        jLabel23.setText("User");
        jDialog2.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 110, 30));
        jDialog2.getContentPane().add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 30));

        jLabel24.setText("Password");
        jDialog2.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 110, 30));
        jDialog2.getContentPane().add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 220, 30));

        jLabel25.setText("Database");
        jDialog2.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 98, 30));
        jDialog2.getContentPane().add(txtdb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 220, 33));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(685, 754));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("x");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 60, 30));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back.png"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, 30));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/next.png"))); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 60, 30));

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("FORM PENGUNJUNG");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 210, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 670, 50));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtnis.setText("masukan nis di sini !");
        txtnis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnisMouseClicked(evt);
            }
        });
        txtnis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnisKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnisKeyReleased(evt);
            }
        });
        jPanel2.add(txtnis, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 440, 49));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("NIS");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 80, 40));
        jPanel2.add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 440, 52));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("NAMA");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 70, 40));
        jPanel2.add(txttelpon, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 440, 50));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("TELPON");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 90, 40));
        jPanel2.add(txttanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 200, 51));
        jPanel2.add(txtjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 220, 50));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("TANGGAL");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 100, 40));
        jPanel2.add(txtkd, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 200, 40));

        jButton1.setText("DAFTAR ANGGOTA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 460, -1, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/save.png"))); // NOI18N
        jButton2.setText("SUBMIT");
        jButton2.setBorderPainted(false);
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 142, 50));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("jLabel10");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 140, 30));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("jLabel9");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 130, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 670, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtnisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnisKeyReleased
try{
            String query = "select nama,no_hp from siswa where nis = '"+txtnis.getText()+"'";
            
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);

            if(data.next()){
                txtnama.setText(data.getString("nama"));
                txttelpon.setText(data.getString("no_hp"));
            }
            else{
                txtnama.setText("");
                txttelpon.setText("");
            }
        }
        catch(Exception b){
            JOptionPane.showMessageDialog(null, b.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtnisKeyReleased

    private void txtnisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnisKeyPressed
         if (evt.getKeyCode()== KeyEvent.VK_ENTER)
         {
             
        String id = txtkd.getText();
        String Nis = txtnis.getText();
        String Nama = txtnama.getText();
        String Telpon = txttelpon.getText();
        String Tanggal = txttanggal.getText();
        String Kam= txtjam.getText();
        if(id.equals("")||
           Nis.equals("")||
           Nama.equals("")||
           Telpon.equals("")||
           Tanggal.equals("")||
           Kam.equals("")
                ){
            JOptionPane.showMessageDialog(null,"Data Tidak Tersedia ! Daftar Anggota Terlebih Dahulu ... ");
refresh();
        }
        
        else{
            try {
                String sql1 = "INSERT INTO pengunjung"
                + " VALUES ('"
                 +txtkd.getText()
                +"','" + txtnis.getText()
                + "','" + txtnama.getText()
                + "','" + txttelpon.getText()
                 + "','" +txttanggal.getText()
                        + "','" +txtjam.getText()
                + "')";
              
                java.sql.PreparedStatement pdt = conn.prepareStatement(sql1);
                pdt.execute();
                JOptionPane.showMessageDialog(null, "Selamat Datang Di Perpustakaan");
               // new datamahasiswa().setVisible(true);
              //  dispose();//
            
             autoNumber();
             refresh();
      //      jDialog1.setVisible(true);
    //        getData();
          try{
             int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menunjungi katalog buku?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
             int no =JOptionPane.showConfirmDialog(null, "Yakin ?","Konfirmasi ", JOptionPane.NO_OPTION);
        if(ok==0){
        
        new katalog_buku().setVisible(true);
        dispose();
        
        }
            
            
        if(no==1){
         jDialog1.setVisible(true);
        getData();
        dispose();
        
        }
            
            
       {
     //   new katalog_buku().setVisible(true);
      jDialog1.setVisible(true);
        getData();
       
       }
            
          }catch(Exception e){
               
       }
           
            } catch (Exception b) {
                JOptionPane.showMessageDialog(this, b.getMessage());
            }
        
        
        }     
         }// TODO add your handling code here:
    }//GEN-LAST:event_txtnisKeyPressed

    private void txtnisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnisMouseClicked
txtnis.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_txtnisMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
daftar.setLocationRelativeTo(null);
       
        daftar.setVisible(true);
//datasiswa a=new datasiswa();
//a.setVisible(true);
//dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 {
             
        String id = txtkd.getText();
        String Nis = txtnis.getText();
        String Nama = txtnama.getText();
        String Telpon = txttelpon.getText();
        String Tanggal = txttanggal.getText();
        String Jam = txtjam.getText();
        
        if(id.equals("")||
           Nis.equals("")||
           Nama.equals("")||
           Telpon.equals("")||
           Tanggal.equals("")||
                
           Jam.equals("")
                ){
            JOptionPane.showMessageDialog(null,"Data Tidak Tersedia ! Daftar Anggota Terlebih Dahulu ... ");
refresh();
        }
        
        else{
            try {
                String sql1 = "INSERT INTO pengunjung"
                + " VALUES ('"
                 +txtkd.getText()
                +"','" + txtnis.getText()
                + "','" + txtnama.getText()
                + "','" + txttelpon.getText()
                  + "','" +txttanggal.getText()      
                 + "','" +txtjam.getText()
                + "')";
                
                java.sql.PreparedStatement pdt = conn.prepareStatement(sql1);
                pdt.execute();
                JOptionPane.showMessageDialog(null, "Selamat Datang Di Perpustakaan");
               // new datamahasiswa().setVisible(true);
              //  dispose();//
             
             autoNumber();
             refresh();
             jDialog1.setVisible(true);
             getData();
            } catch (Exception b) {
                JOptionPane.showMessageDialog(this, b.getMessage());
            }
          
        
        }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 jDialog1.setVisible(false);
  
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jProgressBar1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jProgressBar1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jProgressBar1InputMethodTextChanged

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
login();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
//datasiswa();       // TODO add your handling code here:
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
login.Menu_login a=new login.Menu_login();
a.setVisible(true);
dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseClicked

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
            java.util.logging.Logger.getLogger(Pengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pengunjung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog daftar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField txtdb;
    private javax.swing.JTextField txtjam;
    private javax.swing.JTextField txtkd;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnis;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtserver;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txttelpon;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables

   
}
