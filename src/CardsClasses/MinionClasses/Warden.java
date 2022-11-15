package CardsClasses.MinionClasses;

import java.util.ArrayList;

public class Warden extends MinionCard {
    public Warden(int mana, int ad, int hp, String description, ArrayList<String> colors, String name, int playerID) {
        super(mana, ad, hp, description, colors, name, playerID);
        setTank(true);
        if(playerID == 1) {
            setRow(3);
        } else
            setRow(0);
    }
}
