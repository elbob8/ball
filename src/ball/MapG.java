
package ball;

import java.awt.*;

/**
 *
 * @author Elbob
 */
public class MapG {
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    
    public MapG(int row , int clo){
        map = new int [row][clo];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                map[i][j] = 1;
            }
        }
        brickWidth = 540/clo; //540 / 7
        brickHeight = 150/row; //150 / 3
    }
    
    public void draw(Graphics2D g2){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] > 0){
                    //brick
                    g2.setColor(Color.BLACK);
                    g2.fillRect(j*brickWidth+88,i*brickHeight+50,brickWidth,brickHeight);
                    //background brick
                    g2.setStroke(new BasicStroke(3));
                    g2.setColor(Color.WHITE);
                    g2.drawRect(j*brickWidth+88,i*brickHeight+50,brickWidth,brickHeight);
                }
            }
        }
    }
    public void setBrickValue(int value,int row,int clo){
        map[row][clo] = value;
    }
}
