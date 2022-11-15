package CardsClasses.MinionClasses;

import java.util.ArrayList;

public class Disciple extends MinionCard {
    public Disciple(int mana, int ad, int hp, String description, ArrayList<String> colors, String name, int playerID) {
        super(mana, ad, hp, description, colors, name, playerID);
        setTank(false);
        if(playerID == 1) {
            setRow(2);
        } else
            setRow(1);
    }

    void ability(MinionCard target) {
        if(!this.isFrozen()) {
            if (target.getPlayerID() == this.getPlayerID()) {
                target.setHealth(target.getHealth() + 2);
            } else {
                System.out.println("no");
            }
        } else {
            System.out.println("it's chilling");
        }
    }
}
