import main.*;

import java.awt.*;

public class PlayingWithDrawingPanel {
    public static void main(String[] args) {
        int width = 600, height = 400;
        DrawingPanel panel = new DrawingPanel(width, height);
        Graphics g = panel.getGraphics();
        g.setColor(Color.RED);
        int step = 20;
        int size = step * 3;
        for (int x = 0; x <= height - size; x += step) {
            panel.clear();
            g.drawRect(x, x, size, size);
            panel.sleep(1);
        }
//        g.drawOval(234, 77, 100, 100);
    }
}
