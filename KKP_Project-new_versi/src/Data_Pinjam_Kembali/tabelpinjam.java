/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Pinjam_Kembali;


import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
public class tabelpinjam extends javax.swing.JFrame {
    Connection conn;
    private DefaultTableModel tabmode;
    private String logopo="/laporan2/logokkp.jpeg"; 

    /**
     * Creates new form tabelpinjam
     */
    public tabelpinjam() {
        initComponents();
        setData();
        config();
             kosong();
//        bersihdata();
       // tampil();
        datatable();
         try{
         BufferedImage beam = ImageIO.read(getClass().getResource("SMK PERINTIS DEPOK.png"));
        setIconImage(beam);
        } catch (IOException ex) {
            Logger.getLogger(tabelpinjam.class.getName()).log(Level.SEVERE, null, ex);
        }    
       this.setTitle("DATA PEMINJAMAN");
    }
    
    
    public void bersihdata()
    {
    int bar = tabmode.getRowCount();
    for(int a=0;a<bar;a++)
    {
    tabmode.removeRow(0);
    
    }
    
    
    
    }
    
    
    
   protected void datatable(){
        Object[] Baris ={"Kode","NIS ","NAMA","ID BUKU","JUDUL BUKU","JUMLAH","TANGGAL PINJAM","TANGGAL KEMBALI","STATUS"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel.setModel(tabmode);
        String sql = "select * from pinjaman order by id_pinjam asc";
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
   
   



    private void hapus(){
   {
           int baris = tabel.getSelectedRow();
        String id= tabel.getValueAt(baris, 0).toString();
        try {
            String sql ="delete from pinjaman where id_pinjam = '"+id+"'"; 
           
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Dicetak");
            datatable();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di cetak"+e.getMessage());
        }
        }
  }

private void kosong(){

txtnis.setText("");
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
            String query = "select*from pinjaman";
            
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);
            
            while(data.next()){
                model.addRow(new Object [] {data.getString(1),
                data.getString(2),
                data.getString(3),
                data.getString(4),
                data.getString(5),
                data.getString(6),
                data.getString(7),
                data.getString(8),
             
                data.getString(9)});
            }
            tabel.setModel(model);
        }
        catch(Exception b){
            
        }
    }






public void printpinjam(){
try{
 panelcetak.removeAll();
    panelcetak.repaint();
    panelcetak.revalidate();
    URL in=this.getClass().getResource("/laporan2/laporan_data_pinjam.jasper");
    JasperReport report=(JasperReport)JRLoader.loadObject(in);
    Map parameter = new HashMap();
parameter.clear();
parameter.put("npm", txtnis.getText());
parameter.put("logo", this.getClass().getResourceAsStream(logopo));
JasperPrint jprint = JasperFillManager.fillReport(report, parameter, conn);
JRViewer v=new JRViewer(jprint);
 panelcetak.setLayout(new BorderLayout());
    panelcetak.add(v);
}       catch (JRException ex) {
            Logger.getLogger(tabelpinjam.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel22 = new javax.swing.JLabel();
        txtserver = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        jLabel25 = new javax.swing.JLabel();
        txtdb = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        mainpanel = new javax.swing.JPanel();
        panelcetak = new javax.swing.JPanel();
        panelrefresh = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtnis = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tcari = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

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
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, 40, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/next.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 90, 30));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("DATA PEMINJAMAN");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 230, 50));

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
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 1180, 140));

        mainpanel.setBackground(new java.awt.Color(102, 102, 255));
        mainpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelcetak.setBackground(new java.awt.Color(51, 255, 204));
        mainpanel.add(panelcetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 460));

        panelrefresh.setBackground(new java.awt.Color(51, 204, 0));
        mainpanel.add(panelrefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 460));

        jPanel2.add(mainpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 700, 470));

        jButton1.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/new.png"))); // NOI18N
        jButton1.setText("   +Input Pinjaman  ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 230, 180, 40));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/delete.png"))); // NOI18N
        jButton4.setText("bersihdata");
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, 180, 40));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/new.png"))); // NOI18N
        jLabel7.setText("Refresh");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 330, 180, 40));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Print Berdasarkan Nis");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 370, 250, 30));
        jPanel2.add(txtnis, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 410, 180, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Search-icon.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 190, -1, 30));

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });
        jPanel2.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 410, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/print.png"))); // NOI18N
        jButton2.setText("Print");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 410, 130, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1260, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
     int bar = tabel.getSelectedRow();
        
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();
        
        txtnis.setText(b);
        

    }//GEN-LAST:event_tabelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new input_data_peminjaman().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
     tabmode.getDataVector().removeAllElements();
        tabmode.fireTableDataChanged();

        try {
            
            Statement statement = conn.createStatement();

            String sql = "select * from pinjaman where id_pinjam like '%" 
                    + tcari.getText() + "%' or id_pinjam like'%" 
                    + tcari.getText() + "%' or npm like'" 
                    + tcari.getText() + "%' or nama like'%" 
                    + tcari.getText() + "%' or id_buku like'%" 
                    + tcari.getText() + "%' or judul like'%" 
                    + tcari.getText() + "%' or jumlah like'%" 
                    + tcari.getText() + "%' or tgl_pinjam like'%" 
                     + tcari.getText() + "%' or tgl_balik like'%" 
                    + tcari.getText() + "%' " + "or status like'%" 
                    + tcari.getText() + "%'";
            ResultSet resulSet = statement.executeQuery(sql);

            while (resulSet.next()) {
                Object[] o = new Object[9];
                o[0] = resulSet.getString("id_pinjam");
                o[1] = resulSet.getString("npm");
                o[2] = resulSet.getString("nama");
                o[3] = resulSet.getString("id_buku");
                o[4] = resulSet.getString("judul");
                o[5] = resulSet.getString("jumlah");
                o[6] = resulSet.getString("tgl_pinjam");
                o[7] = resulSet.getString("tgl_balik");
                o[8] = resulSet.getString("status");
              
                tabmode.addRow(o);
            }
            resulSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }         // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
bersihdata();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
Menu.Menu_Utama a=new Menu.Menu_Utama();
a.setVisible(true);
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
tabelpinjam a=new tabelpinjam();
a.setVisible(true);
dispose();

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
       
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
  printpinjam();
        
        
        
// TODO add your handling code here:
         
        try {
            String sql ="delete from pinjaman "; 
            
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Dicetak");
            datatable();
            kosong();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di cetak"+e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(tabelpinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tabelpinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tabelpinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tabelpinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tabelpinjam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JPanel panelcetak;
    private javax.swing.JPanel panelrefresh;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField txtdb;
    private javax.swing.JTextField txtnis;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtserver;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
