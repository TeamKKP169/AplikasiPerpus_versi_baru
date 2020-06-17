/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Pinjam_Kembali;


import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author hikigaya
 */
public class input_data_peminjaman extends javax.swing.JFrame {
    Connection conn;
String pinjam;
String balik;
 DefaultTableModel tabel1 = new DefaultTableModel();
 DefaultTableModel tabel2 = new DefaultTableModel();
    /**
     * Creates new form datapinjam
     */
    public input_data_peminjaman() {
        initComponents();
        setData();
        config();
        txtnm.setEditable(false);
    txtjdl.setEditable(false);
    refresh();
    
    autonumber();
    autonumber2();
      try{
         BufferedImage beam = ImageIO.read(getClass().getResource("SMK PERINTIS DEPOK.png"));
        setIconImage(beam);
        } catch (IOException ex) {
            Logger.getLogger(input_data_peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }    
       this.setTitle("PEMINJAMAN");
    }
private void loadKategori() {
        tabel1.addColumn("NIS");
        tabel1.addColumn("NAMA");
        jTable1.setModel(tabel1);
        TableColumn lebar_kolom;
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        lebar_kolom = jTable1.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable1.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);
        tabel1.getDataVector().removeAllElements();
        tabel1.fireTableDataChanged();
        try {           
            java.sql.Statement stm = conn.createStatement();
            String query_bukaTabel = "select nis,nama from siswa ";
          ResultSet line_result = stm.executeQuery(query_bukaTabel);
            while (line_result.next()) {
                Object[] getO = new Object[2];
                getO[0] = line_result.getString("nis");
                getO[1] = line_result.getString("nama");
                tabel1.addRow(getO);                
            }
            line_result.close();
            stm.close();
        } catch (Exception e) {
        }
    }


private void loadKategori1() {
        tabel2.addColumn("ID");
        tabel2.addColumn("JUDUL BUKU");
        tabelbuku.setModel(tabel2);
        TableColumn lebar_kolom;
        tabelbuku.setAutoResizeMode(tabelbuku.AUTO_RESIZE_OFF);
        lebar_kolom = tabelbuku.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = tabelbuku.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);
        tabel2.getDataVector().removeAllElements();
        tabel2.fireTableDataChanged();
        try {           
            java.sql.Statement stm = conn.createStatement();
            String query_bukaTabel = "select id_buku, judul from buku";
          ResultSet line_result = stm.executeQuery(query_bukaTabel);
            while (line_result.next()) {
                Object[] getO = new Object[2];
                getO[0] = line_result.getString("id_buku");
                getO[1] = line_result.getString("judul");
                tabel2.addRow(getO);                
            }
            line_result.close();
            stm.close();
        } catch (Exception e) {
        }
    }
 private void refresh() {
     
        txtnpm.setText("");
        txtnm.setText("");
        txtidbk.setText("");
        txtjdl.setText("");
        txtjmlh.setText("");
        tgl.setDate(null);
        tglbalik.setDate(null);
             
    }
 
private void autonumber(){
 try{                      
Statement state = conn.createStatement();
String query = "SELECT MAX(id_pinjam) FROM pinjaman"; //Mendapatkan nilai id terakhir			
ResultSet rs = state.executeQuery(query);
if(rs.next())
{
int a = rs.getInt(1);
txtid.setText(Integer.toString(a + 001));
}
rs.close();
state.close();
  }catch(Exception e){
  
  }
}


private void autonumber2(){
try{
                       
			Statement state = conn.createStatement();
			String query = "SELECT MAX(id_pinjam) FROM pengembalian"; //Mendapatkan nilai id terakhir
			
			ResultSet rs = state.executeQuery(query);
			if(rs.next())
			{
				int a = rs.getInt(1);
				txtid.setText(Integer.toString(a + 001));
			}
			rs.close();
			state.close();
  
  }catch(Exception e){
  
  }

}

private void add(){
if(tgl.getDate().equals("")&&tglbalik.getDate().equals("")){
            JOptionPane.showMessageDialog(null,"Tidak Boleh Ada yang kosong");
        }else{
    try{
        
        String query1 = "INSERT INTO pinjaman VALUES "
                                +"('" + txtid.getText()
                                +"','"+txtnpm.getText()
                                + "','" + txtnm.getText() 
                                + "','" + txtidbk.getText()
                                + "','" + txtjdl.getText()
                                + "','" + txtjmlh.getText()
                                + "','" + pinjam
                                + "','" + balik                          
                                + "','" + "Belum Kembali"+ "')";                             
                                java.sql.PreparedStatement mts = conn.prepareStatement(query1);
                                mts.execute();
                                JOptionPane.showMessageDialog(null, "Peminjaman  berhasil");  
    }catch(Exception e){
        
    }   
            
}
}

private void prosesbuku(){
    try{
     String aidi1;
     aidi1=txtidbk.getText();
     String query = "select*from buku where id_buku = '"+aidi1+"'";   
     Statement stm = conn.createStatement();
     ResultSet data = stm.executeQuery(query);
     while(data.next()){
      String jmlh = txtjmlh.getText();
    String stk = data.getString(7);
     int stok = Integer.parseInt(stk);
    int jumlah = Integer.parseInt(jmlh);
    if(stok == 0){
     JOptionPane.showMessageDialog(null,"Stok Buku Telah Kosong!");
    }
    else if(jumlah>stok){
    JOptionPane.showMessageDialog(null,"Jumlah Buku Tidak Cukup!");
                        }
     }
    }catch(Exception e){
    
    
    }
  
}


private void prosesbuku2(){
try{
                                    String query2 = "select*from buku where id_buku = '"+txtidbk.getText()+"'";
                                  
                                    java.sql.Statement st = conn.createStatement();
                                    java.sql.ResultSet data2 = st.executeQuery(query2);

                                    while(data2.next()){
                                        int jumlah1= Integer.parseInt(data2.getString(7));
                                        int jumlahpinjam = Integer.parseInt(txtjmlh.getText());
                                        int total = jumlah1-jumlahpinjam;
                                        try {
                                            String sql3 ="UPDATE buku SET stok = '"+total
                                            +"' WHERE id_buku = '"+txtidbk.getText()+"'";
                                           
                                            java.sql.PreparedStatement pst=conn.prepareStatement(sql3);
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

private void tambahdatapinjam(){
add();
prosesbuku();
prosesbuku2();

}

private void tambahdatapengembalian(){
 pengembalian();

}

private void pengembalian(){
if(tgl.getDate().equals("")&&tglbalik.getDate().equals("")){
            JOptionPane.showMessageDialog(null,"Tidak Boleh Ada yang kosong");
        }else{
    try{
        
        String query1 = "INSERT INTO pengembalian VALUES "
                                +"('" + txtid.getText()
                                +"','"+txtnpm.getText()
                                + "','" + txtnm.getText() 
                                + "','" + txtidbk.getText()
                                + "','" + txtjdl.getText()
                                + "','" + txtjmlh.getText()
                                + "','" + pinjam
                                + "','" + balik                          
                                + "','" + "Belum Kembali"+ "')";                             
                                java.sql.PreparedStatement mts = conn.prepareStatement(query1);
                                mts.execute();
                                //JOptionPane.showMessageDialog(null, "Peminjaman  berhasil");  
    }catch(Exception e){
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        tcari = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jDialog2 = new javax.swing.JDialog();
        tcari1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelbuku = new javax.swing.JTable();
        jDialog3 = new javax.swing.JDialog();
        jLabel22 = new javax.swing.JLabel();
        txtserver = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        jLabel25 = new javax.swing.JLabel();
        txtdb = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtjmlh = new javax.swing.JTextField();
        txtjdl = new javax.swing.JTextField();
        txtidbk = new javax.swing.JTextField();
        txtnm = new javax.swing.JTextField();
        txtnpm = new javax.swing.JTextField();
        tglbalik = new com.toedter.calendar.JDateChooser();
        tgl = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btntambah = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        txtid = new javax.swing.JLabel();

        jDialog1.setMinimumSize(new java.awt.Dimension(400, 350));

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton5.setText("KEMBALI");

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });

        jLabel8.setText("CARI");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel8)))
                        .addGap(0, 79, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tcari, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap())
        );

        jDialog2.setMinimumSize(new java.awt.Dimension(400, 350));

        tcari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcari1KeyReleased(evt);
            }
        });

        jLabel9.setText("CARI");

        jButton7.setText("KEMBALI");

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane3MousePressed(evt);
            }
        });

        tabelbuku.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelbuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelbukuMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelbukuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelbuku);

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7)
                            .addGroup(jDialog2Layout.createSequentialGroup()
                                .addComponent(tcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel9)))
                        .addGap(0, 79, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tcari1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap())
        );

        jDialog3.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setText("Server");
        jDialog3.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 110, 30));
        jDialog3.getContentPane().add(txtserver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 220, 30));

        jLabel23.setText("User");
        jDialog3.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 110, 30));
        jDialog3.getContentPane().add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 30));

        jLabel24.setText("Password");
        jDialog3.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 110, 30));
        jDialog3.getContentPane().add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 220, 30));

        jLabel25.setText("Database");
        jDialog3.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 98, 30));
        jDialog3.getContentPane().add(txtdb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 220, 33));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(737, 700));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/back.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/next.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 30));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("x");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 40, 30));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("FORM PEMINJAMAN");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 210, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 720, 50));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("TANGGAL PINJAM");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("TANGGAL KEMBALI");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("NIS");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("NAMA");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("ID BUKU");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("JUDUL BUKU");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("JUMLAH");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        txtjmlh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtjmlhMouseClicked(evt);
            }
        });
        txtjmlh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjmlhActionPerformed(evt);
            }
        });
        txtjmlh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtjmlhKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtjmlhKeyPressed(evt);
            }
        });
        jPanel2.add(txtjmlh, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, 310, 40));
        jPanel2.add(txtjdl, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 310, 40));

        txtidbk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtidbkMouseClicked(evt);
            }
        });
        txtidbk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidbkActionPerformed(evt);
            }
        });
        txtidbk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtidbkKeyReleased(evt);
            }
        });
        jPanel2.add(txtidbk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 310, 40));

        txtnm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnmKeyReleased(evt);
            }
        });
        jPanel2.add(txtnm, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 310, 40));

        txtnpm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnpmMouseClicked(evt);
            }
        });
        txtnpm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnpmKeyReleased(evt);
            }
        });
        jPanel2.add(txtnpm, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 310, 40));

        tglbalik.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglbalikPropertyChange(evt);
            }
        });
        jPanel2.add(tglbalik, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 310, 40));

        tgl.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglPropertyChange(evt);
            }
        });
        jPanel2.add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 41, 310, 40));

        jButton3.setText("...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 40, 40));

        jButton4.setText("...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 40, 40));

        btntambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/save.png"))); // NOI18N
        btntambah.setText("TAMBAH");
        btntambah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });
        jPanel2.add(btntambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 140, 50));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/new.png"))); // NOI18N
        jButton6.setText("REFRESH");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 490, 140, 50));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Documents-icon.png"))); // NOI18N
        jButton8.setText("Cek Data");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 150, 50));
        jPanel2.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 70, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 720, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtnpmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnpmMouseClicked
        txtnpm.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnpmMouseClicked

    private void txtnpmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnpmKeyReleased
        try{
            String query = "select nama from siswa where nis = '"+txtnpm.getText()+"'";
            
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);

            if(data.next()){
                txtnm.setText(data.getString("nama"));
            }
            else{
                txtnm.setText("Jika npm benar makan Nama akan tercantum");
            }
        }
        catch(Exception b){
            JOptionPane.showMessageDialog(null, b.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnpmKeyReleased

    private void txtnmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnmKeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmKeyReleased

    private void txtidbkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtidbkMouseClicked
        txtidbk.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidbkMouseClicked

    private void txtidbkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidbkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidbkActionPerformed

    private void txtidbkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidbkKeyReleased
        try{
            String query = "select judul from buku where id_buku = '"+txtidbk.getText()+"'";
            
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet data = stm.executeQuery(query);

            if(data.next()){
                txtjdl.setText(data.getString("judul"));
            }
            else{
                txtjdl.setText("Judul");
            }
        }
        catch(Exception b){
            JOptionPane.showMessageDialog(null, b.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidbkKeyReleased

    private void txtjmlhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtjmlhMouseClicked
        txtjmlh.setText(null);
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjmlhMouseClicked

    private void txtjmlhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjmlhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjmlhActionPerformed

    private void txtjmlhKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjmlhKeyTyped
        char c = evt.getKeyChar();
        if (! ((Character.isDigit(c) ||
            (c == KeyEvent.VK_BACK_SPACE) ||
            (c == KeyEvent.VK_DELETE))
        )
        )
        {
            evt.consume();
        }            // TODO add your handling code here:
    }//GEN-LAST:event_txtjmlhKeyTyped

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
tambahdatapinjam();
tambahdatapengembalian();
refresh();
autonumber();
autonumber2();
        // TODO add your handling code here:
    }//GEN-LAST:event_btntambahActionPerformed

    private void tglPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglPropertyChange
if (tgl.getDate() != null) {
     SimpleDateFormat FormatTanggal = new SimpleDateFormat("dd MMMM yyyy");
     pinjam = FormatTanggal.format(tgl.getDate());
}        // TODO add your handling code here:
    }//GEN-LAST:event_tglPropertyChange

    private void tglbalikPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglbalikPropertyChange
    if (tglbalik.getDate() != null) {
     SimpleDateFormat FormatTanggal = new SimpleDateFormat("dd MMMM yyyy");
     balik = FormatTanggal.format(tglbalik.getDate());
}        // TODO add your handling code here:
    }//GEN-LAST:event_tglbalikPropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
jDialog1.setLocationRelativeTo(null);
        loadKategori();
        jDialog1.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
tabel1.getDataVector().removeAllElements();
        tabel1.fireTableDataChanged();

        try {
           
                java.sql.Statement stm = conn.createStatement();

            String sql = "select * from siswa where nis like '%" 
                    + tcari.getText() + "%' or nis like'%" 
                    + tcari.getText() + "%' or nama like'" 
                    
                    + tcari.getText() + "%'";
            ResultSet resulSet = stm.executeQuery(sql);

            while (resulSet.next()) {
                Object[] o = new Object[2];
                o[0] = resulSet.getString("Nis");
                o[1] = resulSet.getString("Nama");
                
                
                tabel1.addRow(o);
            }
            resulSet.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyReleased

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
String a = 
jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
txtnpm.setText(a);
String b = 
 jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();   
 txtnm.setText(b);
 jDialog1.setVisible(false);  
        
// TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tcari1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcari1KeyReleased
tabel2.getDataVector().removeAllElements();
        tabel2.fireTableDataChanged();

        try {
           
                java.sql.Statement stm = conn.createStatement();

           
            String sql = "select * from buku where id_buku like '%" 
                    + tcari1.getText() + "%' or id_buku like'%" 
                    + tcari1.getText() + "%' or judul like'" 
                   
                    + tcari1.getText() + "%'";
            ResultSet resulSet = stm.executeQuery(sql);

            while (resulSet.next()) {
                Object[] o = new Object[2];
                o[0] = resulSet.getString("id_buku");
                o[1] = resulSet.getString("judul");
                
                
                tabel2.addRow(o);
            }
            resulSet.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }          // TODO add your handling code here:
    }//GEN-LAST:event_tcari1KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jDialog2.setLocationRelativeTo(null);
        loadKategori1();
        jDialog2.setVisible(true);           // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabelbukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelbukuMouseClicked
    // TODO add your handling code here:
    }//GEN-LAST:event_tabelbukuMouseClicked

    private void tabelbukuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelbukuMousePressed
        String c = 
tabelbuku.getValueAt(tabelbuku.getSelectedRow(), 0).toString();
txtidbk.setText(c);
String d = 
 tabelbuku.getValueAt(tabelbuku.getSelectedRow(), 1).toString();   
 txtjdl.setText(d);
 jDialog2.setVisible(false);  // TODO add your handling code here:
    }//GEN-LAST:event_tabelbukuMousePressed

    private void jScrollPane3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MousePressed
       // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane3MousePressed

    private void txtjmlhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjmlhKeyPressed
 if (evt.getKeyCode()== KeyEvent.VK_ENTER){
tambahdatapinjam();
tambahdatapengembalian();

autonumber();
autonumber2();
refresh();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjmlhKeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
tabelpinjam a=new tabelpinjam();
a.setVisible(true);
dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
Menu.Menu_Utama a=new Menu.Menu_Utama();
a.setVisible(true);
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel12MouseClicked

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
            java.util.logging.Logger.getLogger(input_data_peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(input_data_peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(input_data_peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(input_data_peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new input_data_peminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btntambah;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tabelbuku;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tcari1;
    private com.toedter.calendar.JDateChooser tgl;
    private com.toedter.calendar.JDateChooser tglbalik;
    private javax.swing.JTextField txtdb;
    private javax.swing.JLabel txtid;
    private javax.swing.JTextField txtidbk;
    private javax.swing.JTextField txtjdl;
    private javax.swing.JTextField txtjmlh;
    private javax.swing.JTextField txtnm;
    private javax.swing.JTextField txtnpm;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtserver;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
