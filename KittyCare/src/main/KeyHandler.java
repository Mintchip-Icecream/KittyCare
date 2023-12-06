package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean a1Pressed, a2Pressed, a3Pressed, a4Pressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_D){
            a1Pressed = true;
        }
        if(code == KeyEvent.VK_F){
            a2Pressed = true;
        }
        if(code == KeyEvent.VK_J){
            a3Pressed = true;
        }
        if(code == KeyEvent.VK_K){
            a4Pressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_D){
            a1Pressed = false;
        }
        if(code == KeyEvent.VK_F){
            a2Pressed = false;
        }
        if(code == KeyEvent.VK_J){
            a3Pressed = false;
        }
        if(code == KeyEvent.VK_K){
            a4Pressed = false;
        }
    }

}
