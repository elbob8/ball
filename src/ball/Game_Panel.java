
package ball;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Elbob
 */
public class Game_Panel extends JPanel implements KeyListener {
    
    private int xball = 150,yball = 200 ;
    private float ballXdir = 2f,ballYdir = 2f;
    private int playerX = 300;
    private boolean play = false;
    private int totalBricks;
    private MapG map;
    private int Score;
    private Color color = new Color(150,20,90);
    private final Random random;
    int v1 = 3,v2 = 7,x1,x2;
    int level = 1;
    boolean win = false;
    
    public Game_Panel(){
        random = new Random();
        map = new MapG(v1, v2);
        totalBricks = v1 * v2;
        addKeyListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        //startGame
        start();
        
        //map
        map.draw(g2);
        
        //ball
        g2.setColor(color);
        g2.fillOval(xball, yball, 25, 25);
        
        // when you won
        if(totalBricks <= 0){
            play = false;
            win = true;
            ballXdir = 0;
            ballYdir = 0;
            g2.setColor(Color.red);
            g2.setFont(new Font("serif",Font.BOLD,30));
            g2.drawString("You Won: ", 260, 300);
            
            g2.setFont(new Font("serif",Font.BOLD,20));
            g2.drawString("Press Space to Next Level", 230, 350);
        }
        
        //Score
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Ink Free",Font.BOLD,25));
        g2.drawString("Score : "+Score, 10,30);
        
        //Level
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Ink Free",Font.BOLD,30));
        g2.drawString("Level : "+level, 550,30);
            
        //player
        g2.setColor(color);
        g2.fillRect(playerX, 500, 80, 25);
        g2.fillOval(playerX-20, 500, 40, 25);
        g2.fillOval(playerX+60, 500, 40, 25);
        
        //game Over
        if(yball > 570){
            play = false;
            win = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g2.setFont(new Font("serif",Font.BOLD,40));
            g2.drawString("GameOver , Score: "+Score, 170, 300);
            
            g2.setFont(new Font("serif",Font.BOLD,20));
            g2.drawString("Press Enter to Restart", 250, 350);
        }
        
    }
    public void start(){
        if(play){
            if(new Rectangle(xball,yball,25,25).intersects(new Rectangle(playerX, 500, 100, 25))){
                ballYdir =- ballYdir;
                color = getRndColor();
            }
            
            
   After_Collision_ReturnToPlayer : for(int i = 0; i < map.map.length; i++){
                for(int j = 0; j < map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                        int brickX = j*map.brickWidth + 80;
                        int brickY = i*map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;
                        
                        Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);                       
                        Rectangle ballRect = new Rectangle(xball,yball,25,25);
                        Rectangle brickRect = rect;
                        
                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            Score+=5;
                            
                            if(xball + 19 <= brickRect.x || xball + 1 >= brickRect.x + brickRect.width){
                                ballXdir =- ballXdir;   
                            }else{
                                ballYdir =- ballYdir;
                            }
                            break After_Collision_ReturnToPlayer;
                        }
                    }
                }
            }
            
            
            xball += ballXdir;
            yball += ballYdir;
            
            if(xball < 0 ){
                ballXdir =- ballXdir;
            }
            if(yball < 0 ){
                ballYdir =- ballYdir;
            }
            if(xball > 670){
                ballXdir =- ballXdir;
            }

        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
              if(playerX >= 600){
                    playerX = 600;
                }else{
                    play = true;
                    playerX += 100;
                }
                break;
            case KeyEvent.VK_LEFT:
              if(playerX < 10){
                    playerX = 10;
                }else{
                    play = true;
                    playerX -= 100;
                }
                break;
            case KeyEvent.VK_ENTER:
                if(!win){
                    if(!play){
                        play = true;
                        xball = 150;
                        yball = 200;
                        ballXdir = 2f;
                        ballYdir = 2f;
                        playerX = 300;
                        Score = 0;
                        map = new MapG(v1, v2);
                        totalBricks = v1 * v2;
                    }
                }
                break;
            case KeyEvent.VK_SPACE:
                if(win){
                    if(!play){
                        play = true;
                        xball = 150;
                        yball = 200;
                        ballXdir = 2f;
                        ballYdir = 2f;
                        playerX = 300;
                        Score = 0;
                        v1++;
                        v2++;
                        level++;
                        x1 = v1;
                        x2 = v2;
                        map = new MapG(x1,x2);
                        totalBricks = x1 * x2;
                    }
                }
                break;
        }
        
    }
    
    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r,g,b);
    }
    @Override
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    
}
