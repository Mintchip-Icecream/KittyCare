package main;

import java.awt.event.*;
import java.util.*;


public class ChoiceHandler {
    gamePlay gp;
    KeyHandler keyH;

    public enum actiontype {
        a1, a2, a3, a4, anone
    }
    actiontype at;

    public enum actionsecondary {
        as1, as2, as3, as4, asnone
    }
    actionsecondary as;
    public boolean gameWon = false;
    public boolean gameON = false;
    public boolean gameLoss = false;
    public boolean isUsable = true;
    public boolean switcher = false;

    public ChoiceHandler(gamePlay gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        as = actionsecondary.asnone;
        ga = gameAction.ganone;
        at = actiontype.anone;
    }
    public enum gameAction{
        ga1, ga2, ga3, ga4, ganone
    }
    gameAction ga;
    Random random = new Random();
    public int winningNumber;

    public void update() {
        if (gameON == true && gp.internalTimer > 10) {
            if (keyH.a1Pressed == true && isUsable) {
                ga = gameAction.ga1;
                gp.internalTimer = 0;
                winningNumber = random.nextInt(1,4);
                keyH.a1Pressed = false;
            } else if (keyH.a2Pressed == true && isUsable) {
                ga = gameAction.ga2;
                gp.internalTimer = 0;
                winningNumber = random.nextInt(1,4);
                keyH.a2Pressed = false;
            } else if (keyH.a3Pressed == true && isUsable) {
                ga = gameAction.ga3;
                gp.internalTimer = 0;
                winningNumber = random.nextInt(1,4);
                keyH.a3Pressed = false;
            } else if (keyH.a4Pressed == true && isUsable) {
                ga = gameAction.ga4;
                gp.internalTimer = 0;
                winningNumber = random.nextInt(1,4);
                keyH.a4Pressed = false;
            }
            inputPerformed();

        } else if (gp.ui.nonDefaultState == true && gp.internalTimer > 10) {
            if (keyH.a1Pressed == true) {
                as = actionsecondary.as1;
                gp.internalTimer = 0;
                keyH.a1Pressed = false;
            } else if (keyH.a2Pressed == true) {
                as = actionsecondary.as2;
                gp.internalTimer = 0;
                keyH.a2Pressed = false;
            } else if (keyH.a3Pressed == true) {
                as = actionsecondary.as3;
                gp.internalTimer = 0;
                keyH.a3Pressed = false;
            } else if (keyH.a4Pressed == true) {
                as = actionsecondary.as4;
                gp.internalTimer = 0;
                keyH.a4Pressed = false;
            }
            inputPerformed();

        } else if (isUsable && gp.internalTimer > 10) {
            if (keyH.a1Pressed == true) {
                at = actiontype.a1;
                keyH.a1Pressed = false;
                gp.internalTimer = 0;
                gp.ui.nonDefaultState = true;
            } else if (keyH.a2Pressed == true) {
                at = actiontype.a2;
                keyH.a2Pressed = false;
                gp.internalTimer = 0;
                gp.ui.nonDefaultState = true;
            } else if (keyH.a3Pressed == true) {
                at = actiontype.a3;
                keyH.a3Pressed = false;
                gp.internalTimer = 0;
                gp.ui.nonDefaultState = true;
            } else if (keyH.a4Pressed == true) {
                at = actiontype.a4;
                keyH.a4Pressed = false;
                gp.internalTimer = 0;
                gp.ui.nonDefaultState = true;
            }
        }
        if (gp.cat.tiredness > 15 || gp.cat.fullness > 5){
            gp.immediateshutdown = true;
        }
    }


    public void draw() {
    }

    public void inputPerformed() { //where all of the different key input scenarios go
        switch (at) {
            case a1: //PLAY
                switch (as){
                   case as1:
                       if (isUsable) {
                           if (gp.cat.favitem == "Pet") {
                               gp.cat.happiness += 10;
                               gp.cat.favoritechosen = true;
                           }
                           gp.cat.tiredness += 4;
                           gp.cat.happiness +=7;
                           gp.secondaryTimer -= 155;
                       }
                       endAnimation();
                       break;
                    case as2:
                        if (isUsable) {
                            if (gp.cat.favitem == "Brush") {
                                gp.cat.happiness += 10;
                                gp.cat.favoritechosen = true;
                            }
                            gp.cat.tiredness += 3;
                            gp.cat.happiness +=5;
                            gp.secondaryTimer -= 155;
                        }
                        endAnimation();
                        break;
                    case as3:
                        if (isUsable) {
                            if (gp.cat.favitem == "Laser") {
                                gp.cat.happiness += 14;
                                gp.cat.favoritechosen = true;
                            }
                            gp.cat.fullness -=1;
                            gp.cat.tiredness += 4;
                            gp.cat.happiness +=7;
                            gp.secondaryTimer -= 155;
                        }
                        endAnimation();
                        break;
               } break;
            case a2: //FEED
                switch(as) {
                    case as1: //Salmon
                        if (gp.cat.catcoins >= 5 && gp.cat.fullness <= 5) {
                            if (isUsable) {
                                if (gp.cat.favitem == "Salmon") {
                                    gp.cat.happiness += 20;
                                    gp.cat.favoritechosen = true;
                                }
                                gp.cat.catcoins -= 5;
                                gp.cat.fullness += 1;
                                gp.cat.tiredness += 3;
                                gp.cat.happiness += 10;
                                gp.secondaryTimer -= 155;
                            }
                        }
                        endAnimation();
                        break;
                    case as2://Beef
                        if (gp.cat.catcoins >= 6) {
                            if (isUsable) {
                                if (gp.cat.favitem == "Beef") {
                                    gp.cat.happiness += 25;
                                    gp.cat.favoritechosen = true;
                                }
                                gp.cat.catcoins -= 6;
                                gp.cat.fullness += 2;
                                gp.cat.tiredness -= 5;
                                gp.cat.happiness += 15;
                                gp.secondaryTimer -= 155;
                            }
                        }
                        endAnimation();
                        break;
                    case as3://Tempura
                        if (gp.cat.catcoins >= 3) {
                            if (isUsable) {
                                if (gp.cat.favitem == "Shrimp") {
                                    gp.cat.happiness += 12;
                                    gp.cat.favoritechosen = true;
                                }
                                gp.cat.catcoins -= 3;
                                gp.cat.fullness += 1;
                                gp.cat.tiredness += 2;
                                gp.cat.happiness += 6;
                                gp.secondaryTimer -= 155;
                            }
                        }
                        endAnimation();
                        break;
                    case as4://Milk
                        if (gp.cat.catcoins >= 2) {
                            if (isUsable) {
                                if (gp.cat.favitem == "Milk") {
                                    gp.cat.happiness += 10;
                                    gp.cat.favoritechosen = true;
                                }
                                gp.cat.catcoins -= 2;
                                gp.cat.fullness += 1;
                                gp.cat.tiredness -= 1;
                                gp.cat.happiness += 5;
                                gp.secondaryTimer -= 155;
                            }
                        }
                        endAnimation();
                        break;
                }
                break;
            case a3: //REST
                switch(as) {
                    case as1: //1 HR SLEEP
                        if (isUsable) {
                            gp.cat.fullness -= 2;
                            gp.cat.tiredness -= 2;
                            gp.cat.happiness += 4;
                            gp.secondaryTimer -= 1818;
                        }
                        endAnimation();
                        break;
                    case as2://2 HR SLEEP
                        if (isUsable) {
                            gp.cat.fullness -= 4;
                            gp.cat.tiredness -= 4;
                            gp.cat.happiness += 10;
                            gp.secondaryTimer -= 3636;
                        }
                        endAnimation();
                        break;
                    case as3://3 HR SLEEP
                        if (isUsable) {
                            gp.cat.fullness -= 5;
                            gp.cat.tiredness -= 5;
                            gp.cat.happiness += 20;
                            gp.secondaryTimer -= 5454.6;
                        }
                        endAnimation();
                        break;
                }
                break;

            case a4://GAMES
                switch(as) {
                    case as1: //Bowls
                        bowlsgame();
                        break;
                }
                break;
        }

    }
    public void endAnimation(){ //resets to default states
        isUsable = false;
        if (gp.internalTimer >= 120) {
            gp.ui.nonDefaultState = false;
            gp.cat.favoritechosen = false;
            as = actionsecondary.asnone;
            at = actiontype.anone;
            ga = gameAction.ganone;
            isUsable = true;
            gameWon = false;
            gameLoss = false;
            gameON = false;
        }
    }
    public void bowlsgame() {
        gameON = true;
        if (ga != gameAction.ganone) {
            if (winningNumber == 2 && isUsable) {
                gameWon = true;
                gp.cat.catcoins += 5;
                gp.secondaryTimer -= 300;
            } else if (winningNumber != 2 && isUsable) {
                gameLoss = true;
                gp.secondaryTimer -= 300;
            }
            endAnimation();
        }
    }
}


