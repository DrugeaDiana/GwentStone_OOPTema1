package cardsclasses.heroclasses;

import cardsclasses.minionclasses.MinionCard;
import constants.Constants;
import gameclasses.Game;

import java.util.ArrayList;

public class EmpressThorina extends HeroCard {
    public EmpressThorina(final int mana, final String name, final String description,
                          final ArrayList<String> colors, final int playerID) {
        super(mana, name, description, colors, playerID);
    }

    /**
     * Implements the specific ability of this card
     * @param targetRow the row the player wants to use the ability on
     * @return error codes:
     * -1 : the targeted row is a friendly row
     * -3 : the HeroCard has used its ability this turn already
     * 0 : the ability was cast with success
     */
    @Override
    public int ability(final int targetRow, final Game game) {
        if (isAttackTurn()) {
            if (getPlayerID() == 1) {
                if (targetRow < Constants.MAX_ROW_NR_PLAYER_2
                        && targetRow > Constants.MIN_ROW_NR_PLAYER_2) {
                    removeMinionWithHighestHP(game.getTable().get(targetRow));
                    setAttackTurn(true);
                } else {
                    return Constants.ERROR_MINUS_1;
                }
            } else {
                if (targetRow < Constants.MAX_ROW_PLAYER_1
                        && targetRow > Constants.MIN_ROW_PLAYER_1) {
                    removeMinionWithHighestHP(game.getTable().get(targetRow));
                    setAttackTurn(true);
                } else {
                    return Constants.ERROR_MINUS_1;
                }
            }
            return 0;
        }
        return Constants.ERROR_MINUS_3;

    }

    /**
     * Kills the minion with the highest health value from the targeted row
     * @param row the cards from the target row of the table
     */
    public void removeMinionWithHighestHP(final ArrayList<MinionCard> row) {
        int health = 0;
        int remId = 0;
        for (MinionCard minionCard : row) {
            if (minionCard.getHealth() > health) {
                health = minionCard.getHealth();
            }
        }
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i).getHealth() == health) {
                remId = i;
                break;
            }
        }
        row.remove(remId);
    }
}
