/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Pinjam_Kembali;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hikigaya
 */
public class pengembalian_buku extends javax.swing.JFrame {
    Connection conn;
private DefaultTableModel tabmode;
String kembali;
String pulang;
    /**
     * Creates new form tesdatabalikbuku
     */
    public pengembalian_buku() {
        initComponents();
        setData();
        config();
        //datatable();
        kosong();
          tampil();
        txtstatus.setEditable(false);
        
        
          try{
         BufferedImage beam = ImageIO.read(getClass().getResource("SMK PERINTIS DEPOK.png"));
        setIconImage(beam);
        } catch (IOException ex) {
            Logger.getLogger(pengembalian_buku.class.getName()).log(Level.SEVERE, null, ex);
        }    
       this.setTitle("PENGEMBALIAN BUKU");
    }
protected void datatable(){
        Object[] Baris ={"Kode","NIS ","NAMA","ID BUKU","JUDUL BUKU","JUMLAH","TANGGAL PINJAM","TANGGAL KEMBALI","STATUS"};
        tabmode = new DefaultTableModel(null, Baris);
        jTable1.setModel(tabmode);
        String sql = "select * from pengembalian order by id_pinjam asc";
        try {
            
            java.sql.Statement stm = conn.createStatement();
            ResultSet hasil = stm.executeQuery(sql);
            while(hasil.next()){
                String data1 = hasil.getString("id_pinjam");
                String data2 = hasil.getString("npm");
                String data3 = hasil.getString("nama");
                String data4 = hasil.getString("id_buku");
                String data5 = hasil.getString("judul");
                String data6 = hasil.getString("jumlah");
                String data7 = hasil.getString("tgl_pinjam");
                String data8 = hasil.getString("tgl_balik");
                String data9 = hasil.getString("status");
                
                String[] data={
                    data1,data2,data3,data4,data5,data6,data7,data8,data9,};
                tabmode.addRow(data);
            }
        }      
        
        catch (Exception e) {
        }
    }
private void tampil(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Npm");
        model.addColumn("Nama");
        model.addColumn("Id_buku");
        model.addColumn("judul");
        model.addColumn("Jumlah");
        model.addColumn("Tgl Pinjam");
        model.addColumn("Tgl Kembali");
        
        model.addColumn("Status");
        try{
            String status = "Belum Kembali";
            String query = "select*from pengembalian where status = '"+status+"'";
            
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);
            
            while(data.next()){
                model.addRow(new Object [] {
                data.getString(1),
                data.getString(2),
                data.getString(3),
                data.getString(4),
                data.getString(5),
                data.getString(6),
                data.getString(7),
                data.getString(8),
            
                data.getString(9)});
            }
            jTable1.setModel(model);
        }
        catch(Exception b){
            
        }
    }
private void kosong(){
    txttelat.setEditable(true);
    
    tglkembali.setDate(null);
    tglkembali.setEnabled(false);  
    
    txtid.setText("ID Peminjaman");
    txtpinjam.setText("Tanggal Peminjaman");
    txtblk.setText("Tanggal Balik");
    txttelat.setText("Keterlambatan");
   
       txtid.setEditable(false);
       txtpinjam.setEditable(false);
       txtblk.setEditable(false);
       txttelat.setEditable(false);
      
    tglkembali.setEnabled(false);
    
  btnbatal.setEnabled(false);
  btnproses.setEnabled(false);
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

        jDialog1 = new javax.swing.JDialog();
        jLabel22 = new javax.swing.JLabel();
        txtserver = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        jLabel25 = new javax.swing.JLabel();
        txtdb = new javax.swing.JTextField();
        txtstatus = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtid = new javax.swing.JTextField();
        txtpinjam = new javax.swing.JTextField();
        txtblk = new javax.swing.JTextField();
        tglkembali = new com.toedter.calendar.JDateChooser();
        tcari = new javax.swing.JTextField();
        txttelat = new javax.swing.JTextField();
        btnproses = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setText("Server");
        jDialog1.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 110, 30));
        jDialog1.getContentPane().add(txtserver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 220, 30));

        jLabel23.setText("User");
        jDialog1.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 110, 30));
        jDialog1.getContentPane().add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 30));

        jLabel24.setText("Password");
        jDialog1.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 110, 30));
        jDialog1.getContentPane().add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 220, 30));

        jLabel25.setText("Database");
        jDialog1.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 98, 30));
        jDialog1.getContentPane().add(txtdb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 220, 33));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1280, 700));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtstatus.setText("sudah kembali");
        getContentPane().add(txtstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 479, 10, 0));

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/next.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 50, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("x");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, 40, 30));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("FORM PENGEMBALIAN BUKU");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 300, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1260, 50));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1210, 220));
        jPanel2.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 219, 50));
        jPanel2.add(txtpinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 219, 50));
        jPanel2.add(txtblk, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 219, 50));

        tglkembali.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglkembaliPropertyChange(evt);
            }
        });
        tglkembali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglkembaliKeyPressed(evt);
            }
        });
        jPanel2.add(tglkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 210, 50));

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });
        jPanel2.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 260, 370, 40));
        jPanel2.add(txttelat, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 140, 50));

        btnproses.setText("Proses");
        btnproses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprosesActionPerformed(evt);
            }
        });
        jPanel2.add(btnproses, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 220, 50));

        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/delete.png"))); // NOI18N
        btnbatal.setText("batal");
        btnbatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });
        jPanel2.add(btnbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 110, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search-icon.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 50, 60));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1260, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased

     if(tcari.getText().equals(""))
   {
 tampil();   
}
else{
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nis");
        model.addColumn("Nama");
        model.addColumn("Id_buku");
        model.addColumn("judul");
        model.addColumn("Jumlah");
        model.addColumn("Tgl Pinjam");
        model.addColumn("Tgl Kembali");
     
        model.addColumn("Status");
        try{
            String status="Belum Kembali";
            String query = "select * from pengembalian where npm = '"
                    +tcari.getText()
                    +"'"
                    + "&& status = '"+status+"'";
           
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);

            while(data.next()){
                model.addRow(new Object [] {
                    data.getString(1),
                    data.getString(2),
                    data.getString(3),
                    data.getString(4),
                    data.getString(5),
                    data.getString(6),
                    data.getString(7),
                    data.getString(8),
                    
                    data.getString(9)});
        }
        jTable1.setModel(model);
        }
        catch(Exception b){

        }    
        }       // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyReleased

    private void tglkembaliPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglkembaliPropertyChange
        if (tglkembali.getDate() != null) {
            SimpleDateFormat FormatTanggal = new SimpleDateFormat("dd MMMM yyyy");
            pulang = FormatTanggal.format(tglkembali.getDate());
        }
        try {
            DateFormat format = new SimpleDateFormat("dd MMMM yyyy");
            Date tanggalpinjam = format.parse(kembali);
            Date tanggalkembali = format.parse(pulang);
            long tanggalpinjam1 = tanggalpinjam.getTime();
            long tanggalkembali1 = tanggalkembali.getTime();
            long diff = tanggalkembali1 - tanggalpinjam1;
            long lama = diff / (24 * 60 * 60 * 1000);
            txttelat.setText(Long.toString(lama) + "");

            int denda;
            int telat = Integer.parseInt(txttelat.getText());
            denda = telat*1000;
            String tlt;
            tlt = String.valueOf(denda);
            if(telat<0){
                txttelat.setText("Keterlambatan");

                btnproses.setEnabled(false);
                btnbatal.setEnabled(true);
            }
            else if(tlt.equals("0")){

                btnproses.setEnabled(true);
                btnbatal.setEnabled(true);
            }
            else{

                btnproses.setEnabled(true);
                btnbatal.setEnabled(true);

            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tglkembaliPropertyChange

    private void tglkembaliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglkembaliKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglkembaliKeyPressed

    private void btnprosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprosesActionPerformed
        try {
            String status = "sudah kembali";
            String sql ="UPDATE pengembalian SET status = '"+txtstatus.getText()

            +"' WHERE id_pinjam = '"+txtid.getText()+"'";
           
           PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        try{

            String id= txtid.getText();

            String query = "select id_buku from pengembalian where id_pinjam = '"+id+"'";
            
           Statement stm = conn.createStatement();
          ResultSet data = stm.executeQuery(query);

            while(data.next()){
                String aidi = data.getString("id_buku");
                try{
                    String query1 = "select*from buku where id_buku = '"+aidi+"'";
                   
                    java.sql.Statement st = conn.createStatement();
                    java.sql.ResultSet data1 = st.executeQuery(query1);

                    while(data1.next()){
                        int baris = jTable1.getSelectedRow();
                        String jmlh= jTable1.getValueAt(baris, 5).toString();
                        int jumlah= Integer.parseInt(data1.getString(7));
                        int jumlahpinjam = Integer.parseInt(jmlh);
                        int total = jumlah+jumlahpinjam;
                        try {
                            String sql1 ="UPDATE buku SET stok = '"+total
                            +"' WHERE id_buku = '"+aidi+"'";
                            
                            java.sql.PreparedStatement pst=conn.prepareStatement(sql1);
                            pst.execute();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
                        }

                    }
                }
                catch(Exception b){
                    JOptionPane.showMessageDialog(null, b.getMessage());
                }
            }

        }
        catch(Exception b){

        }
        tampil();
        //datatable();
        kosong();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnprosesActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
 btnbatal.setEnabled(true);
 tglkembali.setEnabled(true);
       
        try{

            int baris = jTable1.getSelectedRow();
            String id= jTable1.getValueAt(baris, 0).toString();

            String query = "select*from pengembalian where id_pinjam = '"+id+"'";
            
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);

            while(data.next()){

                txtid.setText(data.getString(1));
                txtpinjam.setText(data.getString(7));
                txtblk.setText(data.getString(8));
                kembali = data.getString(8);
            }

        }
        catch(Exception b){

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
kosong();        // TODO add your handling code here:
    }//GEN-LAST:event_btnbatalActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
Menu.Menu_Utama a=new Menu.Menu_Utama();
a.setVisible(true);
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(pengembalian_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pengembalian_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pengembalian_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pengembalian_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pengembalian_buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnproses;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tcari;
    private com.toedter.calendar.JDateChooser tglkembali;
    private javax.swing.JTextField txtblk;
    private javax.swing.JTextField txtdb;
    private javax.swing.JTextField txtid;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtpinjam;
    private javax.swing.JTextField txtserver;
    private javax.swing.JTextField txtstatus;
    private javax.swing.JTextField txttelat;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
