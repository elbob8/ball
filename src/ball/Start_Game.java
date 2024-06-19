package ball;


/**
 *
 * @author Elbob
 */

import Tools.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.WindowConstants.*;


public class Start_Game extends JFrame{
    //
    JPanel home;
    JLabel title;
    MyButton button_start,button_out,button_about;
    private BufferedImage Img_bowling;
    //
    public Start_Game(){
        setTitle("Ball");
        try {
            setIconImage(ImageIO.read(this.getClass().getResource("/Tools/images/wink.png")));
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(rootPane, "icon app not found");
             }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(700, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        
        importImg();
        
            home = new JPanel();
            home.setSize(getWidth(), getHeight());
            home.setLayout(null);
            home.setBackground(Color.decode("#000000"));
            add(home);
            
            title = new JLabel("Ball");
            title.setForeground(Color.GREEN);
            title.setFont(new Font("Ink Free",Font.BOLD,50));
            title.setBounds(250, 10, 200, 50);
            title.setHorizontalAlignment(JLabel.CENTER);
            home.add(title);
            
            ImageIcon icon_bowling = new ImageIcon(Img_bowling);
            
            button_start = new MyButton("Start");
            button_start.setIcon(icon_bowling);
            button_start.setBounds(250, 170, 200, 50);
            button_start.setRadius(20);
            button_start.setToolTipText("Play!");
            button_start.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Game();
            }
            });
            home.add(button_start,BorderLayout.CENTER);
            
            
            button_about = new MyButton("about");
            button_about.setBounds(250, 240, 200, 50);
            button_about.setRadius(20);
            button_about.setToolTipText("Information From Game");
            button_about.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new About_Game();
            }
            });
            home.add(button_about,BorderLayout.CENTER);
            
            button_out = new MyButton("Exit");
            button_out.setBounds(250, 310, 200, 50);
            button_out.setRadius(20);
            button_out.setToolTipText("Exit in Game");
            button_out.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            });
            home.add(button_out,BorderLayout.CENTER);
            
        
    }
     private void importImg(){
        InputStream bowling = getClass().getResourceAsStream("/res/bowling.png");
		try {
                        Img_bowling = ImageIO.read(bowling);
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
    }
    public static void main(String[] args) {
        new Start_Game();
    }
}
