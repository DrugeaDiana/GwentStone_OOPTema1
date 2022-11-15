package CardsClasses.MinionClasses;

import java.util.ArrayList;

public class Goliath extends MinionCard {
    public Goliath(int mana, int ad, int hp, String description, ArrayList<String> colors, String name, int playerID) {
        super(mana, ad, hp, description, colors, name, playerID);
        setTank(true);
        if(playerID == 1){
            setRow(3);
        } else
            setRow(0);
    }
}
