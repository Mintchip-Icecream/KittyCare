package main;

import java.awt.*;

public class gamePlay implements Runnable{
    final int originalTitleSize = 16; // 16x16 tile
    final int scale = 3;
    final int tileSize = originalTitleSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenRow; //576 pixels wide
    final int screenHeight = tileSize * maxScreenCol;// 768 pixels tall
    public static final Color Pastel_Pink = new Color(255, 232, 247);//creates new color
    Thread gameThread;
    public UI ui = new UI(this);
    DrawingPanel panel = new DrawingPanel(screenWidth, screenHeight);
    Graphics2D g2;
    KeyHandler keyH = new KeyHandler();
    Cat cat = new Cat(this);
    ChoiceHandler choice = new ChoiceHandler(this,keyH);
    Animations animate = new Animations(this);
    public int internalTimer = 0;
    public int animationTimer = 0;
    public int secondaryTimer = 9091; // for 30fps game, this should take 9091 for 5 minutes to pass and the game to finish
    public boolean immediateshutdown = false;


    public gamePlay() {
        g2 = panel.getGraphics();
        panel.setBackground(Pastel_Pink);
        panel.addKeyListener(keyH);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() { // IMPORTANT: THIS IS THE GAMELOOP

        while(gameThread != null && secondaryTimer >= 0 && cat.happiness <=100 && immediateshutdown == false){
            // Step 1: UPDATE (updating information such character animations or progress)
            update();


            // Step 2: DRAW (making a new screen with updated information )
            rePaint();
            internalTimer++;
            animationTimer++;
            secondaryTimer--;
        }
        g2.setColor(Color.black);
        g2.setFont(ui.normalFont);
        if (immediateshutdown == true){g2.drawString("Cat got sick, but you ended with " + cat.happiness + " points",10, screenHeight/2);}
        else if (cat.happiness >= 100){g2.drawString("Congrats! You win and ended with " + cat.happiness + " points",10, screenHeight/2);}
        else if (secondaryTimer >=0){g2.drawString("Game finished, you ended with " + cat.happiness + " points",10, screenHeight/2);}

    }
    public void update() {
        choice.update();
    }
    public void rePaint(){

        // main.UI (should be the last draw method called);
        animate.draw(g2);
        ui.draw(g2);
        panel.sleep(33); // Controls for fps, currently 30fps
        panel.clear(); //DISPOSES EACH TIME SCREEN IS DRAWN TO DRAW A NEW SCREEN


    }
}

