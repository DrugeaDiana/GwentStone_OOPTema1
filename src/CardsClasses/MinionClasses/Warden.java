package cardsclasses.minionclasses;

import constants.Constants;

import java.util.ArrayList;

public class Warden extends MinionCard {
    public Warden(final int mana, final int ad, final int hp, final String description,
                  final ArrayList<String> colors, final String name, final int playerID) {
        super(mana, ad, hp, description, colors, name, playerID);
        setTank(true);
        if (playerID == 1) {
            setRow(Constants.FRONT_ROW_PLAYER_1);
            setMirroredRow(Constants.FRONT_ROW_PLAYER_2);
        } else {
            setRow(Constants.FRONT_ROW_PLAYER_2);
            setMirroredRow(Constants.FRONT_ROW_PLAYER_1);
        }
    }
}
