package cardsclasses.minionclasses;

import constants.Constants;

import java.util.ArrayList;

public class Berserker extends MinionCard {
    public Berserker(final int mana, final int ad, final int hp, final String description,
                     final ArrayList<String> colors, final String name, final int playerID) {
        super(mana, ad, hp, description, colors, name, playerID);
        setTank(false);
        if (playerID == 1) {
            setRow(Constants.BACK_ROW_PLAYER_1);
            setMirroredRow(Constants.BACK_ROW_PLAYER_2);
        } else {
            setRow(Constants.BACK_ROW_PLAYER_2);
            setMirroredRow(Constants.BACK_ROW_PLAYER_1);
        }
    }
}
