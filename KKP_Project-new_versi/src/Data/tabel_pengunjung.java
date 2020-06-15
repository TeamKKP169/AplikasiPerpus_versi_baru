/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import static Data.koneksi.connect;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;


/**
 *
 * @author hikigaya
 */
import java.awt.Window;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Scanner;
import net.sf.jasperreports.engine.util.JRLoader;
public class tabel_pengunjung extends javax.swing.JFrame {
 Connection conn;
     private DefaultTableModel tabmode;
      private String logopo="/laporan/logokkp.jpeg"; 
 
     
    /**
     * Creates new form tabel_pengunjung
     */
    public tabel_pengunjung() {
        initComponents();
        setData();
        config();
        datatabel();
         try{
         BufferedImage beam = ImageIO.read(getClass().getResource("SMK PERINTIS DEPOK.png"));
        setIconImage(beam);
        } catch (IOException ex) {
            Logger.getLogger(Pengunjung.class.getName()).log(Level.SEVERE, null, ex);
        }    
       this.setTitle("DATA PENGUNJUNG");
      
    }
   
   
    
    
private void hapusdata(){
 String sql = "delete from pengunjung ";
            try {
             
            Statement stat = conn.createStatement();
               stat.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "data terhapus semua");
                
                datatabel();
            }   
            
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e);
            }
         


}    
protected void datatabel(){
        Object[] Baris ={"KODE PENGUNJUNG","NIS"," NAMA"," NO HP"," TANGGAL BERKUNJUNG","WAKTU"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel.setModel(tabmode);
        String sql = "select * from pengunjung order by id asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String data1 = hasil.getString("id");
                String data2 = hasil.getString("nis");
                String data3 = hasil.getString("nama");
                String data4 = hasil.getString("telpon");
                String data5 = hasil.getString("tanggal");
                String data6 = hasil.getString("jam");
                
                String[] data={data1,data2,data3,data4,data5,data6,};
                tabmode.addRow(data);
            }
        }      
        
        catch (Exception e) {
        }
    }

 
  
 
private void print(){
  try{
  panelcetak.removeAll();
    panelcetak.repaint();
    panelcetak.revalidate();
  URL in=this.getClass().getResource("/laporan/pengunjung.jasper");
  JasperReport report=(JasperReport)JRLoader.loadObject(in);
  Map parameter = new HashMap();
parameter.clear();

parameter.put("logo", this.getClass().getResourceAsStream(logopo));

JasperPrint jprint = JasperFillManager.fillReport(report, parameter, conn);
JRViewer v=new JRViewer(jprint);
 panelcetak.setLayout(new BorderLayout());
 panelcetak.add(v);
  
  }     catch (JRException ex) {
            Logger.getLogger(Data_buku.class.getName()).log(Level.SEVERE, null, ex);
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

              cetak = new javax.swing.JFrame();
              panelcetak = new javax.swing.JPanel();
              jPanel3 = new javax.swing.JPanel();
              jLabel6 = new javax.swing.JLabel();
              jLabel7 = new javax.swing.JLabel();
              jLabel8 = new javax.swing.JLabel();
              jDialog1 = new javax.swing.JDialog();
              txtpass = new javax.swing.JPasswordField();
              txtserver = new javax.swing.JTextField();
              txtdb = new javax.swing.JTextField();
              jLabel22 = new javax.swing.JLabel();
              jLabel25 = new javax.swing.JLabel();
              jLabel24 = new javax.swing.JLabel();
              txtuser = new javax.swing.JTextField();
              jLabel23 = new javax.swing.JLabel();
              jPanel1 = new javax.swing.JPanel();
              jLabel1 = new javax.swing.JLabel();
              jLabel2 = new javax.swing.JLabel();
              jLabel3 = new javax.swing.JLabel();
              jLabel5 = new javax.swing.JLabel();
              jPanel2 = new javax.swing.JPanel();
              jScrollPane1 = new javax.swing.JScrollPane();
              tabel = new javax.swing.JTable();
              tcari = new javax.swing.JTextField();
              jButton2 = new javax.swing.JButton();
              jButton1 = new javax.swing.JButton();
              jLabel4 = new javax.swing.JLabel();
              jButton3 = new javax.swing.JButton();

              cetak.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              cetak.setTitle("CETAK DATA");
              cetak.setMinimumSize(new java.awt.Dimension(1000, 700));
              cetak.setUndecorated(true);
              cetak.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

              panelcetak.setBackground(new java.awt.Color(102, 102, 255));
              cetak.getContentPane().add(panelcetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 980, 600));

              jPanel3.setBackground(new java.awt.Color(102, 102, 255));
              jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

              jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
              jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
              jLabel6.setText("x");
              jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
                     public void mouseClicked(java.awt.event.MouseEvent evt) {
                            jLabel6MouseClicked(evt);
                     }
              });
              jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 60, 50));

              jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
              jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
              jLabel7.setText("CETAK DATA");
              jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 200, 50));

              jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back.png"))); // NOI18N
              jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
                     public void mouseClicked(java.awt.event.MouseEvent evt) {
                            jLabel8MouseClicked(evt);
                     }
              });
              jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

              cetak.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 980, 50));

              jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
              jDialog1.getContentPane().add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 220, 30));
              jDialog1.getContentPane().add(txtserver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 220, 30));
              jDialog1.getContentPane().add(txtdb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 220, 33));

              jLabel22.setText("Server");
              jDialog1.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 110, 30));

              jLabel25.setText("Database");
              jDialog1.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 98, 30));

              jLabel24.setText("Password");
              jDialog1.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 110, 30));
              jDialog1.getContentPane().add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 30));

              jLabel23.setText("User");
              jDialog1.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 110, 30));

              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setLocationByPlatform(true);
              setMinimumSize(new java.awt.Dimension(1280, 700));
              setUndecorated(true);
              setPreferredSize(new java.awt.Dimension(1280, 700));
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

              jPanel1.setBackground(new java.awt.Color(102, 102, 255));
              jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

              jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
              jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
              jLabel1.setText("x");
              jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                     public void mouseClicked(java.awt.event.MouseEvent evt) {
                            jLabel1MouseClicked(evt);
                     }
              });
              jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 10, 60, 30));

              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back.png"))); // NOI18N
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                     public void mouseClicked(java.awt.event.MouseEvent evt) {
                            jLabel2MouseClicked(evt);
                     }
              });
              jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

              jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/next.png"))); // NOI18N
              jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, 50));

              jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
              jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
              jLabel5.setText("DATA PENGUNJUNG");
              jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 200, 50));

              getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1260, 50));

              jPanel2.setBackground(new java.awt.Color(102, 102, 255));
              jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

              tabel.setModel(new javax.swing.table.DefaultTableModel(
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
              jScrollPane1.setViewportView(tabel);

              jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 1201, 220));

              tcari.addKeyListener(new java.awt.event.KeyAdapter() {
                     public void keyPressed(java.awt.event.KeyEvent evt) {
                            tcariKeyPressed(evt);
                     }
              });
              jPanel2.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, 430, 40));

              jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/delete.png"))); // NOI18N
              jButton2.setText("HAPUS DATA");
              jButton2.addActionListener(new java.awt.event.ActionListener() {
                     public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton2ActionPerformed(evt);
                     }
              });
              jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 450, 180, 50));

              jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/print.png"))); // NOI18N
              jButton1.setText("Save As");
              jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
              jButton1.addActionListener(new java.awt.event.ActionListener() {
                     public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton1ActionPerformed(evt);
                     }
              });
              jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 130, 50));

              jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search-icon.png"))); // NOI18N
              jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, 50, 40));

              jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/print.png"))); // NOI18N
              jButton3.setText("Print");
              jButton3.addActionListener(new java.awt.event.ActionListener() {
                     public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton3ActionPerformed(evt);
                     }
              });
              jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 300, 130, 50));

              getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1260, 620));

              pack();
              setLocationRelativeTo(null);
       }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
hapusdata();
datatabel();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyPressed
tabmode.getDataVector().removeAllElements();
        tabmode.fireTableDataChanged();

        try {
           
            Statement statement = conn.createStatement();

            String sql = "select * from pengunjung where id like '%" 
                    + tcari.getText() + "%' or id like'%" 
                    + tcari.getText() + "%' or nis like'" 
                    + tcari.getText() + "%' or nama like'%" 
                    + tcari.getText() + "%' or telpon like'%" 
                    
                     + tcari.getText() + "%' or tanggal like'%" 
                    + tcari.getText() + "%' " + "or jam like'%" 
                    + tcari.getText() + "%'";
            ResultSet resulSet = statement.executeQuery(sql);

            while (resulSet.next()) {
                Object[] o = new Object[6];
                o[0] = resulSet.getString("id");
                o[1] = resulSet.getString("nis");
                o[2] = resulSet.getString("nama");
                o[3] = resulSet.getString("telpon");
                o[4] = resulSet.getString("tanggal");
                o[5] = resulSet.getString("jam");
               
              
                tabmode.addRow(o);
            }
            resulSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
// TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 MessageFormat header = new MessageFormat("Report Print");
 MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try{

tabel.print(JTable.PrintMode.NORMAL, header,footer);


} catch (java.awt.print.PrinterException e){
    System.err.format("gagal print", e.getMessage());
}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
Menu.Menu_Utama a=new Menu.Menu_Utama();
a.setVisible(true);
dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

cetak.setLocationRelativeTo(null);
     print();
        cetak.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
cetak.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
    cetak.setVisible(false);      // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

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
            java.util.logging.Logger.getLogger(tabel_pengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tabel_pengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tabel_pengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tabel_pengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tabel_pengunjung().setVisible(true);
               
            }
        });
               
       
    }


       // Variables declaration - do not modify//GEN-BEGIN:variables
       private javax.swing.JFrame cetak;
       private javax.swing.JButton jButton1;
       private javax.swing.JButton jButton2;
       private javax.swing.JButton jButton3;
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
       private javax.swing.JLabel jLabel6;
       private javax.swing.JLabel jLabel7;
       private javax.swing.JLabel jLabel8;
       private javax.swing.JPanel jPanel1;
       private javax.swing.JPanel jPanel2;
       private javax.swing.JPanel jPanel3;
       private javax.swing.JScrollPane jScrollPane1;
       private javax.swing.JPanel panelcetak;
       private javax.swing.JTable tabel;
       private javax.swing.JTextField tcari;
       private javax.swing.JTextField txtdb;
       private javax.swing.JPasswordField txtpass;
       private javax.swing.JTextField txtserver;
       private javax.swing.JTextField txtuser;
       // End of variables declaration//GEN-END:variables
}
