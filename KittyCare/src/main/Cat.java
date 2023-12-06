package main;

import java.util.*;

public class Cat {
    public int tiredness = 0, happiness = 0, catcoins = 10, fullness = 0;
    public boolean favoritechosen = false;
    String[] items = {"Pet", "Brush", "Laser", "Salmon", "Beef", "Shrimp", "Milk"};
    public String favitem;
    gamePlay gp;

    public Cat(gamePlay gp){
        this.gp = gp;
        assignFavs();
    }
    public void assignFavs(){
        Random random = new Random();
        int pick = random.nextInt(6);
        favitem = items[pick];
    }
}
