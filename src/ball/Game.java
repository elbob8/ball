package ball;

/**
 *
 * @author Elbob
 */
public class Game implements Runnable{

    private final frame gameWindow;
    private final Game_Panel gamePanel;
    private Thread gameThread; //gameLoop
    private final int FPS_SET = 120;
    
    public Game(){
        gamePanel = new Game_Panel();
        gameWindow = new frame(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        
        //nano//
        //حساب الوقت المطلوب بين كل أطارر //
        
        //قسمة عدد النانوثانية في الثانية علي معدل الاطار
        double timePerFrame = 1000000000.0 / FPS_SET;
        // تعين الوقت الاطار الاخير
        long lastFrame = System.nanoTime();
        // فحص الوقت الحالي
        long now = System.nanoTime();
        
        //mills//
        
        int frame = 0;
        // فحص اخر وقت للفريم
        long lastCheck = System.currentTimeMillis();
        
        while(true){
            now = System.nanoTime();
            //اذا مر الوقت الكافي لعرض اطار جديد
            if(now - lastFrame >= timePerFrame){
                //اعادة رسم اللوحة
                gamePanel.repaint();
                //تحديث وقت الاطار الاخير
                lastFrame = now;
                //زيادة عدد الاطارات المعروضة
                frame++;
            }
            
        //كل ثانية يقوم الكود ب
        if(System.currentTimeMillis() - lastCheck >= 1000){
            lastCheck = System.currentTimeMillis();
            //طباعة الاطار في الثانية
            System.out.println("FPS : " + frame );
            //ثم اعادة تعين عدد الاطارات الي صفر للعد جديد
            frame = 0;
            }
        }
        
    }
}
