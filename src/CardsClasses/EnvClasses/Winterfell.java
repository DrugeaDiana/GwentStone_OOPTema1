package cardsclasses.envclasses;

import cardsclasses.minionclasses.MinionCard;
import constants.Constants;
import gameclasses.Game;

import java.util.ArrayList;

public class Winterfell extends EnvironmentCard {
    public Winterfell(final int mana, final String name, final String description,
                      final ArrayList<String> colors, final int playerID) {
        super(mana, name, description, colors, playerID);
    }

    /**
     * Implements the specific ability of the card
     * @param targetRow index of the row from the table we want to use the card's ability on
     * @param game      variable for the game we're playing
     * @return error code (here -1)
     */
    @Override
    public int ability(final int targetRow, final Game game) {
        if (getPlayerID() == 1) {
            if (targetRow < Constants.MAX_ROW_NR_PLAYER_2
                    && targetRow >= Constants.MIN_ROW_NR_PLAYER_2) {
                freezeMinions(game, targetRow);
            } else {
                return Constants.ERROR_MINUS_1;
            }
        } else {
            if (targetRow < Constants.MAX_ROW_PLAYER_1 && targetRow > Constants.MIN_ROW_PLAYER_1) {
                freezeMinions(game, targetRow);
            } else {
                return Constants.ERROR_MINUS_1;
            }
        }
        return 0;
    }

    /**
     * Freezes all the minions from the targeted row
     * @param game the base game variable
     * @param targetRow the row we're targeting
     */
    public void freezeMinions(final Game game, final int targetRow) {
        ArrayList<MinionCard> rowMinions = game.getTable().get(targetRow);
        for (MinionCard minion : rowMinions) {
            minion.setFrozen(true);
            minion.setFrozenTurn(game.getTurnCounter());
        }
    }
}
