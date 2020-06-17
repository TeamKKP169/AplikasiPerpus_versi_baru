/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;


import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;



/**
 *
 * @author hikigaya
 */
public class Data_buku extends javax.swing.JFrame {
    //private final Connection conn = koneksi.connect();
    Connection conn;
     private DefaultTableModel tabmode;
     private String logopo="/laporan/logokkp.jpeg"; 

    /**
     * Creates new form Data_buku
     */
    public Data_buku() {
        initComponents();
        setData();
        config();
      autonumber();
       lblid.setEditable(false);
        tabelbuku();
        tampilbox();
        kosong();
        lebarKolom();
        
        
        try{
         BufferedImage beam = ImageIO.read(getClass().getResource("SMK PERINTIS DEPOK.png"));
        setIconImage(beam);
        } catch (IOException ex) {
            Logger.getLogger(Data_buku.class.getName()).log(Level.SEVERE, null, ex);
        }
   this.setTitle("DATA BUKU");
    }

    private void kosong() {
       //lblid.setText("");
       txtjudul.setText("");
       txtpengarang.setText("");
       txtpenerbit.setText("");
       cmbkat.setSelectedItem("Kategori");
       txtdesc.setText("");
       txtstok.setText("");
       
    }
    protected void tabelbuku(){
        Object[] Baris ={"ID","JUDUL"," PENGARANG","PENERBIT","KATEGORI","ISBN","JUMLAH"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel1.setModel(tabmode);
        String sql = "select * from buku order by id_buku asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String data1 = hasil.getString("id_buku");
                String data2 = hasil.getString("judul");
                String data3 = hasil.getString("pengarang");
                String data4 = hasil.getString("penerbit");
                String data5 = hasil.getString("kategori");
                String data6 = hasil.getString("isbn");
                String data7 = hasil.getString("stok");
                
                String[] data={data1,data2,data3,data4,data5,data6,data7,};
                tabmode.addRow(data);
            }
        }      
        
        catch (Exception ex) {
        }
    }
private void tampilbox(){
try{
    String sql="select * from kategori_buku";
   
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
    String name = hasil.getString("nama_kategori");
    cmbkat.addItem(name);
}
}    catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, ex);
    }
    }
    

  private void lebarKolom(){
        TableColumn lebar_kolom;
        
        tabel1.setAutoResizeMode(tabel1.AUTO_RESIZE_OFF);
        
        lebar_kolom = tabel1.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(90);
        lebar_kolom = tabel1.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(400);
        lebar_kolom = tabel1.getColumnModel().getColumn(2);
        lebar_kolom.setPreferredWidth(130);
        lebar_kolom = tabel1.getColumnModel().getColumn(3);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = tabel1.getColumnModel().getColumn(4);
        lebar_kolom.setPreferredWidth(110);
        lebar_kolom = tabel1.getColumnModel().getColumn(5);
        lebar_kolom.setPreferredWidth(300);
        lebar_kolom = tabel1.getColumnModel().getColumn(6);
        lebar_kolom.setPreferredWidth(100);
        
    }
  
  private void hapus(){
   {
            String sql = "delete from buku where id_buku='"+lblid.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
                lblid.requestFocus();
                tabelbuku();
                kosong();
                 lebarKolom();
            }   
            
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e);
            }
        }
  }
  
  private void datasiswa(){
  Data_siswa a=new Data_siswa();
  a.setVisible(true);
  dispose();
  }
  
  
  
 
  
  private void print(){
  try{
  panelsatu.removeAll();
    panelsatu.repaint();
    panelsatu.revalidate();
  URL in=this.getClass().getResource("/laporan/laporan_data_buku.jasper");
  JasperReport report=(JasperReport)JRLoader.loadObject(in);
  Map parameter = new HashMap();
parameter.clear();

parameter.put("logo", this.getClass().getResourceAsStream(logopo));

JasperPrint jprint = JasperFillManager.fillReport(report, parameter, conn);
JRViewer v=new JRViewer(jprint);
 panelsatu.setLayout(new BorderLayout());
    panelsatu.add(v);
  
  }     catch (JRException ex) {
            Logger.getLogger(Data_buku.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  
  private void edit(){
    {
            String sql = "update buku set"
                    + " id_buku=?,"
                    + "judul=?,"
                    + "pengarang=?,"
                    + "penerbit=?,"
                    + "kategori=?,"
                    + "isbn=?,"
                    + "stok=?"
                    + "where id_buku='"+lblid.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, lblid.getText());
                stat.setString(2, txtjudul.getText());
                stat.setString(3, txtpengarang.getText());
                stat.setString(4, txtpenerbit.getText());
                
                stat.setString(5, (String) cmbkat.getSelectedItem());
                 stat.setString(6, txtdesc.getText());
                  stat.setString(7, txtstok.getText());
               
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            
                lblid.requestFocus();
             tabelbuku();
            kosong();
             lebarKolom();
            }   
            
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+ex);
            } 
            
            
        }
  }
  private void autonumber(){
 try{
                        //Connection conn = (Connection) new Koneksi();
			Statement state = conn.createStatement();
			String query = "SELECT MAX(id_buku) FROM buku"; //Mendapatkan nilai id terakhir
			
			ResultSet rs = state.executeQuery(query);
			if(rs.next())
			{
				int a = rs.getInt(1);
				lblid.setText(Integer.toString(a + 001));
			}
			rs.close();
			state.close();
  
  } catch (Exception ex) {
			System.out.println(ex);
		}
  
 }
  private void simpan(){
String sql = "insert into buku values (?,?,?,?,?,?,?)"; 
       
    try{
    PreparedStatement stat = conn.prepareStatement(sql);
    stat.setString(1, lblid.getText());
    stat.setString(2, txtjudul.getText());
    stat.setString(3, txtpengarang.getText());
    stat.setString(4, txtpenerbit.getText());    
    stat.setString(5, cmbkat.getSelectedItem().toString());
    stat.setString(6, txtdesc.getText());
    stat.setString(7, txtstok.getText());
     
    stat.executeUpdate();
    JOptionPane.showMessageDialog(null, "data berhasil disimpan");
    lblid.requestFocus();
    }
    catch (SQLException ex){
    JOptionPane.showMessageDialog(null, "data gagal disimpan "+ex);
    }

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
            File file = new File("server.log");
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
            File file = new File("user.log");
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
            File file = new File("pass.log");
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
            File file = new File("db.log");
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

        jDialog1 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panelsatu = new javax.swing.JPanel();
        jDialog2 = new javax.swing.JDialog();
        jLabel14 = new javax.swing.JLabel();
        txtserver = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        jLabel17 = new javax.swing.JLabel();
        txtdb = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel1 = new javax.swing.JTable();
        txtjudul = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtpengarang = new javax.swing.JTextField();
        txtpenerbit = new javax.swing.JTextField();
        cmbkat = new javax.swing.JComboBox<String>();
        jButton3 = new javax.swing.JButton();
        txtdesc = new javax.swing.JTextField();
        tcari = new javax.swing.JTextField();
        btncetak = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtstok = new javax.swing.JTextField();
        btnsave = new javax.swing.JButton();
        btnnew = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        lblid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        jDialog1.setLocationByPlatform(true);
        jDialog1.setMinimumSize(new java.awt.Dimension(1009, 700));
        jDialog1.setUndecorated(true);
        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 102, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("x");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 50, 40));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 40));

        jDialog1.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 970, 60));

        panelsatu.setBackground(new java.awt.Color(102, 102, 255));
        panelsatu.setLayout(new java.awt.CardLayout());
        jDialog1.getContentPane().add(panelsatu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 970, 550));

        jDialog2.setMinimumSize(new java.awt.Dimension(551, 421));
        jDialog2.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setText("Server");
        jDialog2.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 110, 30));
        jDialog2.getContentPane().add(txtserver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 220, 30));

        jLabel15.setText("User");
        jDialog2.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 110, 30));
        jDialog2.getContentPane().add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 30));

        jLabel16.setText("Password");
        jDialog2.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 110, 30));
        jDialog2.getContentPane().add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 220, 30));

        jLabel17.setText("Database");
        jDialog2.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 98, 30));
        jDialog2.getContentPane().add(txtdb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 220, 33));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1280, 700));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/next.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 30));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("x");
        jLabel9.setFocusable(false);
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 0, 40, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("DATA BUKU");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 130, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1260, 50));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabel1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabel1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1240, 200));
        jPanel2.add(txtjudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 350, 40));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("JUDUL BUKU");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));
        jPanel2.add(txtpengarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 350, 40));
        jPanel2.add(txtpenerbit, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 350, 40));

        cmbkat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kategori" }));
        jPanel2.add(cmbkat, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 140, 40));

        jButton3.setText("Tambah Kategori");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 180, 40));
        jPanel2.add(txtdesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, 350, 40));

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });
        jPanel2.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 250, 370, 40));

        btncetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/print.png"))); // NOI18N
        btncetak.setText("Print");
        btncetak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });
        jPanel2.add(btncetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 120, 40));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/print.png"))); // NOI18N
        jButton4.setText("Save as");
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 420, 120, 40));

        txtstok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtstokKeyPressed(evt);
            }
        });
        jPanel2.add(txtstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 520, 350, 40));

        btnsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/save.png"))); // NOI18N
        btnsave.setText("Simpan");
        btnsave.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnsave, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 570, 140, 40));

        btnnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/new.png"))); // NOI18N
        btnnew.setText("Refresh");
        btnnew.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewActionPerformed(evt);
            }
        });
        jPanel2.add(btnnew, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 570, 140, 40));

        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/edit.png"))); // NOI18N
        btnedit.setText("Edit");
        btnedit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        jPanel2.add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 120, 40));

        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/delete.png"))); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        jPanel2.add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 120, 40));
        jPanel2.add(lblid, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 130, 40));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("STOK");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("ISBN");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("KATEGORI");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("PENERBIT");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("PENGARANG");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search-icon.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, -1, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1260, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel1MousePressed
int bar = tabel1.getSelectedRow();
        String h = tabmode.getValueAt(bar, 0).toString();
        String a = tabmode.getValueAt(bar, 1).toString();
        String b = tabmode.getValueAt(bar, 2).toString();
        String c = tabmode.getValueAt(bar, 3).toString();
        String d = tabmode.getValueAt(bar, 4).toString();
        String e = tabmode.getValueAt(bar, 5).toString();
        String f = tabmode.getValueAt(bar, 6).toString();

        lblid.setText(h);
        txtjudul.setText(a);
        txtpengarang.setText(b);
        txtpenerbit.setText(c);
        cmbkat.setSelectedItem(d);
        txtdesc.setText(e);
        txtstok.setText(f);        // TODO add your handling code here:
    }//GEN-LAST:event_tabel1MousePressed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
simpan();  
kosong();
autonumber();
 
 tabelbuku();
 lebarKolom();
 //autonumber();
// TODO add your handling code here:
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewActionPerformed
kosong();  
 lebarKolom();// TODO add your handling code here:
    }//GEN-LAST:event_btnnewActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
  edit(); 
  kosong();
 tabelbuku();
 lebarKolom();
  autonumber();
// TODO add your handling code here:
    }//GEN-LAST:event_btneditActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
 hapus();
 kosong();
 tabelbuku();
 lebarKolom();
autonumber(); 
// TODO add your handling code here:
    }//GEN-LAST:event_btnhapusActionPerformed

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
tabmode.getDataVector().removeAllElements();
        tabmode.fireTableDataChanged();

        try {
           
            Statement statement = conn.createStatement();

            String sql = "select * from buku where id_buku like '%" 
                    + tcari.getText() + "%' or id_buku like'%" 
                    + tcari.getText() + "%' or judul like'" 
                    + tcari.getText() + "%' or pengarang like'%" 
                    + tcari.getText() + "%' or penerbit like'%" 
                    + tcari.getText() + "%' or kategori like'%" 
                    + tcari.getText() + "%' or isbn like'%"
                    + tcari.getText() + "%' " + "or stok like'%" 
                    + tcari.getText() + "%'";
            ResultSet resulSet = statement.executeQuery(sql);

            while (resulSet.next()) {
                Object[] o = new Object[7];
                o[0] = resulSet.getString("id_buku");
                o[1] = resulSet.getString("judul");
                 o[2] = resulSet.getString("pengarang");
                o[3] = resulSet.getString("penerbit");
                o[4] = resulSet.getString("kategori");
                o[5] = resulSet.getString("isbn");
                o[6] = resulSet.getString("stok");
              
                
                tabmode.addRow(o);
            }
            resulSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }         // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyReleased

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
jDialog1.setLocationRelativeTo(null);
       print();
           // printbuku();
      
        
        jDialog1.setVisible(true);  
// TODO add your handling code here:
    }//GEN-LAST:event_btncetakActionPerformed

    private void txtstokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstokKeyPressed
if (evt.getKeyCode()== KeyEvent.VK_ENTER){
String sql = "insert into buku values (?,?,?,?,?,?,?)"; 
       
    try{
    PreparedStatement stat = conn.prepareStatement(sql);
    stat.setString(1, lblid.getText());
    stat.setString(2, txtjudul.getText());
    stat.setString(3, txtpengarang.getText());
    stat.setString(4, txtpenerbit.getText());    
    stat.setString(5, cmbkat.getSelectedItem().toString());
    stat.setString(6, txtdesc.getText());
    stat.setString(7, txtstok.getText());
     
    stat.executeUpdate();
    JOptionPane.showMessageDialog(null, "data berhasil disimpan");
    lblid.requestFocus();
 autonumber();
 kosong();
 tabelbuku();
 lebarKolom();
    }
    catch (SQLException ex){
    JOptionPane.showMessageDialog(null, "data gagal disimpan "+ex);
    }   
}// TODO add your handling code here:
    }//GEN-LAST:event_txtstokKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
kategori a=new kategori();
a.setVisible(true);
dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        
 MessageFormat header = new MessageFormat("Report Print");
 MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try{

tabel1.print(JTable.PrintMode.NORMAL, header,footer);


} catch (java.awt.print.PrinterException e){
    System.err.format("gagal print", e.getMessage());
}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
Menu.Menu_Utama a=new Menu.Menu_Utama();
a.setVisible(true);
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
datasiswa();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
   jDialog1.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
   jDialog1.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

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
            java.util.logging.Logger.getLogger(Data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Data_buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnnew;
    private javax.swing.JButton btnsave;
    private javax.swing.JComboBox<String> cmbkat;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lblid;
    private javax.swing.JPanel panelsatu;
    private javax.swing.JTable tabel1;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField txtdb;
    private javax.swing.JTextField txtdesc;
    private javax.swing.JTextField txtjudul;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtpenerbit;
    private javax.swing.JTextField txtpengarang;
    private javax.swing.JTextField txtserver;
    private javax.swing.JTextField txtstok;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
