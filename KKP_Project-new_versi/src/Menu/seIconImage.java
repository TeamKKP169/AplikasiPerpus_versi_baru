/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author hikigaya
 */
public class seIconImage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
Menu_Utama a = new Menu_Utama();
a.setVisible(true);
a.setLocationRelativeTo(null);
try{
File myFile = new File ("perintis.jpeg");
Image img = ImageIO.read(myFile);
a.setIconImage(img);
}       catch (IOException ex) {
            Logger.getLogger(seIconImage.class.getName()).log(Level.SEVERE, null, ex);
        }
// TODO code application logic here
    }
    
}
