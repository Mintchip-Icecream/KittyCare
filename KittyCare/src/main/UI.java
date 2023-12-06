package main;

import main.*;

import java.awt.*;
import java.io.*;

public class UI {
    gamePlay gp;
    Graphics2D g2;
    Font normalFont;
    Font normalFontreduced;
    public static final Color Pastel_Pink = new Color(255, 232, 247); //creates new color
    public boolean nonDefaultState = false;
    String[] action = {"Action 1", "Action 2", "Action 3", "Action 4"};
    String[] keyLegend = {"D", "F", "J", "K"};

    ChoiceHandler choice;

    public UI(gamePlay gp){
        this.gp=gp;
        {
            try {
                normalFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/font/x12y16pxMaruMonica.ttf")).deriveFont(36f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/res/font/x12y16pxMaruMonica.ttf")));
            } catch (FontFormatException | IOException e) {
                throw new RuntimeException(e);
            }
            try {
                normalFontreduced = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/font/x12y16pxMaruMonica.ttf")).deriveFont(24f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/res/font/x12y16pxMaruMonica.ttf")));
            } catch (FontFormatException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void draw(Graphics2D g2){
        this.g2 =g2;
        g2.setFont(normalFont);
        g2.setColor(Color.black);
        for (int i = 0; i<4; i++) {
            g2.fillRect(gp.screenWidth/2, 650, gp.tileSize*3, gp.tileSize*3);
        }
        g2.drawRect(0, -10, gp.screenWidth, 60);
        stats();

        for (int i = 0; i<4; i++) { //drawing the outline for the buttons
            g2.setColor(Color.black);
            g2.drawRect(((gp.screenWidth/4)*i)-1,649, gp.tileSize*3+1, gp.tileSize*3+1);
            g2.setColor(Pastel_Pink);
            g2.fillRect((gp.screenWidth/4)*i, 650, (gp.tileSize*3)-1, (gp.tileSize*3)-1);
            g2.setColor(Color.BLACK);
            g2.drawString(keyLegend[i], ((gp.screenWidth/4)*i)+55, 750);
        }

        if (nonDefaultState == true){ // container for all of the different choices
            reassignButtons();
           // emergency();
            g2.setFont(normalFont);
            if (gp.choice.at == ChoiceHandler.actiontype.a2){
                g2.setFont(normalFontreduced);
                for (int i = 0; i<4; i++) {
                    g2.setColor(Color.BLACK);
                    g2.drawString(action[i],((gp.screenWidth/4)*i)+35, 700);
                }
            }
            for (int i = 0; i<4; i++) {
                g2.setColor(Color.BLACK);
                g2.drawString(action[i],((gp.screenWidth/4)*i)+35, 700);
            }

        } else { // where the default options on the screen are displayed
            action[0] = "Play";
            action[1] = "Feed";
            action[2] = "Rest";
            action[3] = "Games";
            g2.setFont(normalFont);
            for (int i = 0; i<4; i++) {
                g2.setColor(Color.BLACK);
                g2.drawString(action[i],((gp.screenWidth/4)*i)+35, 700);
            }
            g2.setFont(normalFontreduced);
            g2.drawString("Make money through games, babysit before the owners are home!", 10, 30);
        }

    }
    public void reassignButtons(){ //Reassignment for buttons
        switch(gp.choice.at){
            case a1: action[0] = "Pet";action[1] = "Brush";action[2] = "Laser";action[3] = "";break;
            case a2: action[0] = "Salmon($5)";action[1] = "Beef($6)";action[2] = "Shrimp($3)";action[3] = "Dairy($2)";break;
            case a3: action[0] = "1 HR";action[1] = "2 HR";action[2] = "3 HR";action[3] = "";break;
            case a4: action[0] = "Bowls";action[1] = "";action[2] = "";action[3] = "";
            switch (gp.choice.as){
                case as1: action[0] = "Bowl 1";action[1] = "Bowl 2";action[2] = "Bowl 3";action[3] = "Bowl 4";break;
            }
            break;

        }
    }
    public void emergency(){
        g2.setColor(Color.red);
        g2.setFont(normalFontreduced);
        while (gp.choice.isUsable == false);
        String reccomendation = "";
        String reccomendation2 = "";
        if (gp.cat.fullness == 4){
            reccomendation = "don't eat beef";
        } else if (gp.cat.fullness == 5){
            reccomendation = "player Laser or sleep soon";
        }
        if (gp.cat.tiredness >= 13){
            reccomendation2 = "don't do any activities";
        }
        if (gp.cat.fullness >= 4 && gp.cat.tiredness >13) {
            g2.drawString("She's nearly sick, " + reccomendation + " and " + reccomendation2, 10, 30);
        } else if (gp.cat.fullness >= 4|| gp.cat.tiredness >= 13){
            g2.drawString("She's nearly sick, " + reccomendation + " and " + reccomendation2, 10, 30);
        }
    }
    public void stats(){
        g2.setColor(Color.BLACK);
        int hoursleft = (gp.secondaryTimer/30)/60;
        int minutesleft = (gp.secondaryTimer/30)%60;

        g2.drawString("Tiredness: " + gp.cat.tiredness + "/15", 10, 90);
        g2.drawString("Happiness: " + gp.cat.happiness + "/100", 10, 150);
        g2.drawString("CatCoins: " + gp.cat.catcoins, gp.screenWidth-170, 90);
        g2.drawString("Fullness: " + gp.cat.fullness + "/5", 10, 210);
        if (minutesleft <=9) {
            g2.drawString("Hours Left " + hoursleft + ":0" + minutesleft, 10, 270);
        }else {g2.drawString("Hours Left " + hoursleft + ":" + minutesleft, 10, 270);}
    }
}
