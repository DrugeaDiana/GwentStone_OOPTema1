package cardsclasses.minionclasses;

import java.util.ArrayList;

public class Warden extends MinionCard {
    public Warden(final int mana, final int ad, final int hp, final String description,
                  final ArrayList<String> colors, final String name, final int playerID) {
        super(mana, ad, hp, description, colors, name, playerID);
        setTank(true);
        if (playerID == 1) {
            setRow(2);
            setMirroredRow(1);
        } else {
            setRow(1);
            setMirroredRow(2);
        }
    }
}
