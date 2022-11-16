package cardsclasses.minionclasses;

import java.util.ArrayList;

public class Sentinel extends MinionCard {

    public Sentinel(final int mana, final int ad, final int hp, final String description,
                    final ArrayList<String> colors, final String name, final int playerID) {
        super(mana, ad, hp, description, colors, name, playerID);
        setTank(false);
        if (playerID == 1) {
            setRow(3);
            setMirroredRow(0);
        } else {
            setRow(0);
            setMirroredRow(3);
        }
    }
}
