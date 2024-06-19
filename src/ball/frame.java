
package ball;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Elbob
 */
public class frame extends JFrame {
    
    public frame(Game_Panel gamePanel){
        
        this.setTitle("Ball");
        try {
            this.setIconImage(ImageIO.read(this.getClass().getResource("/Tools/images/wink.png")));
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(rootPane, "icon app not found");
         }
        this.setSize(700, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(gamePanel);
        this.setVisible(true);
        
        
    }
    
}
