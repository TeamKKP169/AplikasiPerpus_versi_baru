/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;


import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
public class kategori extends javax.swing.JFrame {
    Connection conn; 
    private DefaultTableModel tabmode;

    /**
     * Creates new form kategori
     */
    public kategori() {
        initComponents();
        setData();
        config();
        datatable();
         refresh();
         autoNumber();
         txtid.setEditable(false);
         
          try{
         BufferedImage beam = ImageIO.read(getClass().getResource("SMK PERINTIS DEPOK.png"));
        setIconImage(beam);
        } catch (IOException ex) {
            Logger.getLogger(kategori.class.getName()).log(Level.SEVERE, null, ex);
        }    
       this.setTitle("KATEGORI BUKU");
    }
    
      protected void datatable(){
        Object[] Baris ={"ID","Kategori buku"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel.setModel(tabmode);
        String sql = "select * from kategori_buku order by id asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String data1 = hasil.getString("id");
                String data2 = hasil.getString("nama_kategori");
                
                
                String[] data={data1,data2,};
                tabmode.addRow(data);
            }
        }      
        
        catch (Exception e) {
        }
    }
 private void refresh() {
        txtid.setText("");
        txtkategori.setText("");
       
       
        
    }
 
 public void autoNumber() {
		try{
                        //Connection conn = (Connection) new Koneksi();
			Statement state = conn.createStatement();
			String query = "SELECT MAX(id) FROM kategori_buku"; //Mendapatkan nilai id terakhir
			
			ResultSet rs = state.executeQuery(query);
			if(rs.next())
			{
				int a = rs.getInt(1);
				txtid.setText(Integer.toString(a + 001));
			}
			rs.close();
			state.close();
  
  } catch (Exception ex) {
			System.out.println(ex);
		}
	}  
 
 private void save(){
     
            String sql = "insert into kategori_buku values(?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtid.getText());
                stat.setString(2, txtkategori.getText());
                
                
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
               
                txtid.requestFocus();
              
            }   
            
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
            } 
            autoNumber();
            datatable();
            refresh();
            autoNumber();
 }
 
 private void edit(){
   {
           
            String sql = "update kategori_buku set"
                    + " id=?,"
                   
                    + "nama_kategori=?"
                    + "where id='"+txtid.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtid.getText());
                stat.setString(2, txtkategori.getText());
              
                
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
               
                txtid.requestFocus();
              
            }   
            
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+e);
            } 
            datatable();
            autoNumber();
            refresh();
        }
 }
 
 private void hapus(){
  {
            String sql = "delete from kategori_buku where id='"+txtid.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                refresh();
                txtid.requestFocus();
                datatable();
                autoNumber();
                refresh();
            }   
            
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e);
            }
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

        jDialog1 = new javax.swing.JDialog();
        jLabel25 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        txtserver = new javax.swing.JTextField();
        txtdb = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        BTNSIMPAN = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        BTNEDIT = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtkategori = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setText("Database");
        jDialog1.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 98, 30));

        jLabel23.setText("User");
        jDialog1.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 110, 30));
        jDialog1.getContentPane().add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 30));

        jLabel24.setText("Password");
        jDialog1.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 110, 30));
        jDialog1.getContentPane().add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 220, 30));
        jDialog1.getContentPane().add(txtserver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 220, 30));
        jDialog1.getContentPane().add(txtdb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 220, 33));

        jLabel22.setText("Server");
        jDialog1.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 110, 30));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(491, 613));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        jScrollPane2.setViewportView(jScrollPane1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 430, 240));

        BTNSIMPAN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/save.png"))); // NOI18N
        BTNSIMPAN.setText("SIMPAN");
        BTNSIMPAN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTNSIMPAN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BTNSIMPAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSIMPANActionPerformed(evt);
            }
        });
        jPanel1.add(BTNSIMPAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 130, 50));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/delete.png"))); // NOI18N
        jButton3.setText("HAPUS");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, 120, 50));

        BTNEDIT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/edit.png"))); // NOI18N
        BTNEDIT.setText("EDIT");
        BTNEDIT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BTNEDIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNEDITActionPerformed(evt);
            }
        });
        jPanel1.add(BTNEDIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 120, 50));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/new.png"))); // NOI18N
        jButton4.setText("BARU");
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 130, 50));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("ID");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 32));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Kategori");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, 32));

        txtkategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtkategoriKeyPressed(evt);
            }
        });
        jPanel1.add(txtkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 220, 29));
        jPanel1.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 220, 32));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 540, 550));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("x");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 50, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/next.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 30));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("KATEGORI");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 130, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 540, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BTNSIMPANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSIMPANActionPerformed
        // TODO add your handling code here:
         
        
         save();
    }//GEN-LAST:event_BTNSIMPANActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
         int bar = tabel.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
   
        
        txtid.setText(a);
        txtkategori.setText(b);
        
        
    }//GEN-LAST:event_tabelMouseClicked

    private void BTNEDITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNEDITActionPerformed
        // TODO add your handling code here:
   edit();
    }//GEN-LAST:event_BTNEDITActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       hapus();
       autoNumber();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
txtid.setText("");
txtkategori.setText("");
autoNumber();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
dispose();
Data_buku a=new Data_buku();
a.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void txtkategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkategoriKeyPressed
 if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        save();  }      // TODO add your handling code here:
    }//GEN-LAST:event_txtkategoriKeyPressed

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
            java.util.logging.Logger.getLogger(kategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new kategori().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNEDIT;
    private javax.swing.JButton BTNSIMPAN;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField txtdb;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtkategori;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtserver;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
