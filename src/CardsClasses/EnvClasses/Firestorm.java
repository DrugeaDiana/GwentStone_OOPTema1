package cardsclasses.envclasses;

import cardsclasses.minionclasses.MinionCard;
import constants.Constants;
import gameclasses.Game;

import java.util.ArrayList;

public class Firestorm extends EnvironmentCard {
    public Firestorm(final int mana, final String name, final String description,
                     final ArrayList<String> colors, final int playerID) {
        super(mana, name, description, colors, playerID);
    }

    /**
     * @param targetRow index of the row from the table we want to use the card's ability on
     * @param game      variable for the game we're playing
     * @return error code (here only -1)
     */
    @Override
    public int ability(final int targetRow, final Game game) {
        if (getPlayerID() == 1) {
            if (targetRow < Constants.MAX_ROW_NR_PLAYER_2
                    && targetRow > Constants.MIN_ROW_NR_PLAYER_2) {
                dmgAndRemoveDead(game, targetRow);
            } else {
                return Constants.ERROR_MINUS_1;
            }
        } else {
            if (targetRow < Constants.MAX_ROW_PLAYER_1
                    && targetRow > Constants.MIN_ROW_PLAYER_1) {
                dmgAndRemoveDead(game, targetRow);
            } else {
                return Constants.ERROR_MINUS_1;
            }
        }
        return 0;
    }

    /**
     * @param game the base game variable
     * @param targetRow the row we're targeting
     */
    public void dmgAndRemoveDead(final Game game, final int targetRow) {
        ArrayList<MinionCard> rowMinions = game.getTable().get(targetRow);
        for (MinionCard minion : rowMinions) {
            minion.setHealth(minion.getHealth() - 1);
        }
        rowMinions.removeIf(MinionCard::isDead);
    }
}
