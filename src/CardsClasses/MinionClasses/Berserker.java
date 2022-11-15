package CardsClasses.MinionClasses;

import java.util.ArrayList;

public class Berserker extends MinionCard {
    public Berserker(int mana, int ad, int hp, String description, ArrayList<String> colors, String name, int playerID) {
        super(mana, ad, hp, description, colors, name, playerID);
        setTank(false);
        if(playerID == 1){
            setRow(3);
        } else
            setRow(0);
    }
}
