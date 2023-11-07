package main;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
public class GamePanel extends JPanel implements Runnable{
    // Screen settings
    final int originalTitleSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize=originalTitleSize * scale; // 48x48 tile
    final int maxScreenRow=16;
    final int maxScreenCol=12;
    final int screenWidth = tileSize * maxScreenCol; //768 pixels tall
    final int screenHeight = tileSize * maxScreenRow;// 576 pixels wide

    public static final Color Pastel_Pink = new Color(255, 232, 247); //creates new color

    Thread gameThread; //allows for game to continuously run without stop, such as creating animations

    public GamePanel() { //setup for the screen window
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Pastel_Pink); // Sets Color of Screen
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() { // IMPORTANT: THIS IS THE GAMELOOP



    }
}