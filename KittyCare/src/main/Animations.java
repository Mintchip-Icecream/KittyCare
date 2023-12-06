package main;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Animations {
    public BufferedImage idle1, idle2, happy1, happy2, bowl, lick1, lick2, angry1, angry2, hand1, laser1, laser2, laser3, laser4, brush, salmon, beef, milk, shrimp;
    public BufferedImage sleep1, sleep2, sleep3;
    gamePlay gp;
    Graphics2D g2;
    UI ui;
    public boolean nonIdleAnimation = false;
    Font normalFont;
    public Animations(gamePlay gp) {
        this.gp = gp;
        try {
            normalFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/font/x12y16pxMaruMonica.ttf")).deriveFont(36f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/res/font/x12y16pxMaruMonica.ttf")));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2){
        g2.setFont(gp.ui.normalFont);
        this.g2 =g2;
        switch (gp.choice.at) {
            case a1: //PLAY
                switch (gp.choice.as){
                    case as1:
                        headpat();
                        break;
                    case as2:
                        brushing();
                        break;
                    case as3:
                        laserpointer();
                        break;
                    default: superidle(); g2.drawString("Pro Tip: Laser reduces fullness!", 10,30);
                } break;
            case a2: //FEED
                switch(gp.choice.as) {
                    case as1: //Salmon
                            defaultbowls(); salmon(); licking();

                        break;
                    case as2://Beef
                            defaultbowls(); beef(); licking();
                        break;
                    case as3://Tempura
                        defaultbowls(); shrimp(); licking();
                        break;
                    case as4://Milk
                        milkbowls(); licking();
                        break;
                    default: superidle(); g2.drawString("Pro Tip: If you're full, take a nap!", 10,30);
                }
                break;
            case a3: //REST
                switch(gp.choice.as) {
                    case as1: //1 HR SLEEP
                        sleeping(); defaultbowls();
                        break;
                    case as2://2 HR SLEEP
                        sleeping(); defaultbowls();
                        break;
                    case as3://3 HR SLEEP
                        sleeping();defaultbowls();
                        break;
                    default: superidle();
                }
                break;
            case a4: //GAMES
                switch(gp.choice.as) {
                    case as1: //Bowls
                        bowlGame();
                        break;
                    default: superidle();
                }
                break;
        }
        if (gp.choice.isUsable == true) {superidle();}

    }

    public void laserpointer(){
        try{
            laser1 = ImageIO.read(new File("src/res/idleCat/laserpointer1.png"));
            laser2 = ImageIO.read(new File("src/res/idleCat/laserpointer2.png"));
            laser3 = ImageIO.read(new File("src/res/idleCat/idleCat1.png"));
            laser4 = ImageIO.read(new File("src/res/idleCat/idleCat2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
        if (gp.animationTimer <= 15) {
            g2.drawImage(laser1, (gp.screenWidth / 2), (gp.screenHeight / 2) - 128, gp.tileSize * 4, gp.tileSize * 4, null);
            g2.drawImage(laser3, (gp.screenWidth / 2)-150, (gp.screenHeight / 2) - 96, gp.tileSize * 4, gp.tileSize * 4, null);
        } else if (gp.animationTimer <= 30) {
            g2.drawImage(laser2, (gp.screenWidth / 2), (gp.screenHeight / 2) - 128, gp.tileSize * 4, gp.tileSize * 4, null);
            g2.drawImage(laser4, (gp.screenWidth / 2)-120, (gp.screenHeight / 2) - 96, gp.tileSize * 4, gp.tileSize * 4, null);
        } else {
            gp.animationTimer = 0;
        }
    }
    public void idleAnimation(){
        try{
            idle1 = ImageIO.read(new File("src/res/idleCat/idleCat1.png"));
            idle2 = ImageIO.read(new File("src/res/idleCat/idleCat3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        if (gp.animationTimer <= 15) {
            g2.drawImage(idle1, (gp.screenWidth / 2) - 96, (gp.screenHeight / 2) - 96, gp.tileSize * 4, gp.tileSize * 4, null);
        } else if (gp.animationTimer <= 30) {
            g2.drawImage(idle2, (gp.screenWidth / 2) - 96, (gp.screenHeight / 2) - 96, gp.tileSize * 4, gp.tileSize * 4, null);
        } else {
            gp.animationTimer = 0;
        }
    }
    public void superidle(){
        idleAnimation();defaultbowls();
    }
    public void defaultbowls(){
        try{
            bowl = ImageIO.read(new File("src/res/food/bowl1.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        g2.drawImage(bowl, (gp.screenWidth*3/ 4) - 64, (gp.screenHeight / 2) +15, gp.tileSize +30, gp.tileSize+30, null);
    }
    public void licking(){
        try{
            lick1 = ImageIO.read(new File("src/res/lick/lick.png"));
            lick2 = ImageIO.read(new File("src/res/lick/lick2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        if (gp.animationTimer <= 15) {
            g2.drawImage(lick1, (gp.screenWidth / 2) - 64, (gp.screenHeight / 2) - 110, gp.tileSize * 4, gp.tileSize * 4, null);

        } else if (gp.animationTimer <= 30) {
            g2.drawImage(lick2, (gp.screenWidth / 2) - 64, (gp.screenHeight / 2) - 110, gp.tileSize * 4, gp.tileSize * 4, null);
        } else {
            gp.animationTimer = 0;
        }
    }
    public void milkbowls(){
        try{
            milk = ImageIO.read(new File("src/res/food/milkBowl.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        g2.drawImage(milk, (gp.screenWidth*3/ 4) - 64, (gp.screenHeight / 2) +15, gp.tileSize +30, gp.tileSize+30, null);
    }
    public void drinkMilk(){
        if (gp.cat.catcoins >= 3 && gp.cat.fullness <= 5){
            milkbowls();
            licking();
        } else{
            superidle();
        }
    }

    public void salmon(){
        try{
            salmon = ImageIO.read(new File("src/res/food/salmon.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        g2.drawImage(salmon, (gp.screenWidth*3/ 4) - 54, (gp.screenHeight / 2) , gp.tileSize, gp.tileSize, null);
    }
    public void eatsalmon(){
        if (gp.cat.catcoins >= 4){
        defaultbowls(); salmon(); licking();
        } else{
            superidle();
        }
    }
    public void beef(){
        try{
            beef = ImageIO.read(new File("src/res/food/beef.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        g2.drawImage(beef, (gp.screenWidth*3/ 4) - 54, (gp.screenHeight / 2) , gp.tileSize, gp.tileSize, null);
    }
    public void eatBeef(){
        if (gp.cat.catcoins >= 6){
            defaultbowls(); beef(); licking();
        } else{
            superidle();
        }
    }
    public void shrimp(){
        try{
            shrimp = ImageIO.read(new File("src/res/food/shrimp.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        g2.drawImage(shrimp, (gp.screenWidth*3/ 4) - 54, (gp.screenHeight / 2) , gp.tileSize, gp.tileSize, null);
    }
    public void eatShrimp(){
        if (gp.cat.catcoins >= 2){
            defaultbowls(); shrimp(); licking();
        } else{
            superidle();
        }
    }

    public void happinessAnimation(){
        try{
            happy1 = ImageIO.read(new File("src/res/idleCat/idleCat4.png"));
            happy2 = ImageIO.read(new File("src/res/idleCat/idleCat5.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        if (gp.animationTimer <= 15) {
            g2.drawImage(happy1, (gp.screenWidth / 2) - 96, (gp.screenHeight / 2) - 96, gp.tileSize * 4, gp.tileSize * 4, null);

        } else if (gp.animationTimer <= 30) {
            g2.drawImage(happy2, (gp.screenWidth / 2) - 96, (gp.screenHeight / 2) - 96, gp.tileSize * 4, gp.tileSize * 4, null);
        } else {
            gp.animationTimer = 0;
        }

    }
    public void angryAnimation(){
        try{
            angry1 = ImageIO.read(new File("src/res/idleCat/idleCat6.png"));
            angry2 = ImageIO.read(new File("src/res/idleCat/idleCat7.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        if (gp.animationTimer <= 15) {
            g2.drawImage(angry1, (gp.screenWidth / 2) - 96, (gp.screenHeight / 2) - 96, gp.tileSize * 4, gp.tileSize * 4, null);

        } else if (gp.animationTimer <= 30) {
            g2.drawImage(angry2, (gp.screenWidth / 2) - 96, (gp.screenHeight / 2) - 96, gp.tileSize * 4, gp.tileSize * 4, null);
        } else {
            gp.animationTimer = 0;
        }
    }
    public void hands(){
        try{
            hand1 = ImageIO.read(new File("src/res/idleCat/hand.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        if (gp.animationTimer <= 15) {

            g2.drawImage(hand1, (gp.screenWidth / 2) - 16, (gp.screenHeight / 2) - 135, gp.tileSize * 2, gp.tileSize * 2, null);

        } else if (gp.animationTimer <= 30) {
            g2.drawImage(hand1, (gp.screenWidth / 2) - 15, (gp.screenHeight / 2) - 130, gp.tileSize * 2, gp.tileSize * 2, null);
        } else {
            gp.animationTimer = 0;
        }
    }
    public void brush(){
        try{
            brush = ImageIO.read(new File("src/res/idleCat/brush.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        if (gp.animationTimer <= 10) {
            g2.drawImage(brush, (gp.screenWidth / 2) -25, (gp.screenHeight / 2) -15 , gp.tileSize +30, gp.tileSize +30, null);
        } else if (gp.animationTimer <= 20) {
            g2.drawImage(brush, (gp.screenWidth / 2) - 40, (gp.screenHeight / 2) - 25 , gp.tileSize +30, gp.tileSize +30, null);
        } else if (gp.animationTimer <=30){
            g2.drawImage(brush, (gp.screenWidth / 2) - 50, (gp.screenHeight / 2)  , gp.tileSize +30, gp.tileSize +30, null);
        } else{
            gp.animationTimer = 0;
        }
    }
    public void headpat(){
         happinessAnimation(); hands();
    }
    public void brushing(){
        happinessAnimation(); brush();
    }
    public void fourbowls() {
        try{
            bowl = ImageIO.read(new File("src/res/food/bowl1.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        for(int i = 0; i <=4; i++) {
            g2.drawImage(bowl,((gp.screenWidth/4)*i)+30, 550, (gp.tileSize*2), (gp.tileSize*2), null);
        }
    }
    public void sleeping(){
        try{
            sleep1 = ImageIO.read(new File("src/res/sleep/sleep1.png"));
            sleep2= ImageIO.read(new File("src/res/sleep/sleep2.png"));
            sleep3= ImageIO.read(new File("src/res/sleep/sleep3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        if (gp.animationTimer <= 10) {
            g2.drawImage(sleep1, (gp.screenWidth / 2)-110, (gp.screenHeight / 2) -96, gp.tileSize*4, gp.tileSize*4, null);
        } else if (gp.animationTimer <= 20) {
            g2.drawImage(sleep2, (gp.screenWidth / 2)-110 , (gp.screenHeight / 2) -96 , gp.tileSize *4, gp.tileSize*4, null);
        } else if (gp.animationTimer <=30){
            g2.drawImage(sleep3, (gp.screenWidth / 2) -110, (gp.screenHeight / 2) -96 , gp.tileSize *4, gp.tileSize *4, null);
        } else{
            gp.animationTimer = 0;
        }
    }
    public void bowlGame(){
        fourbowls();
        if(gp.choice.gameON == true) {
            if (gp.choice.gameWon == true) {
                g2.drawString("Winner!", 10, 30);
                catWorL();
            } else if (gp.choice.gameLoss == true) {
                Random random = new Random();
                int p = gp.choice.winningNumber;
                catWorL();
                g2.drawString("Sorry, Bowl" + p + " was the winner...", 10, 30);
            } else{g2.drawString("One bowl has 5 coins!", 10, 30);}
        }
    }

    public void catWorL() {
        if (gp.choice.gameWon == true){
            happinessAnimation();
        } else if (gp.choice.gameLoss == true){
            angryAnimation();

        }
    }
}
