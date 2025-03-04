package cardsclasses.heroclasses;

import cardsclasses.minionclasses.MinionCard;
import constants.Constants;
import gameclasses.Game;

import java.util.ArrayList;

public class KingMudface extends HeroCard {
    public KingMudface(final int mana, final String name, final String description,
                       final ArrayList<String> colors, final int playerID) {
        super(mana, name, description, colors, playerID);
    }

    /**
     * @param targetRow targeted row
     * @return error codes:
     * -2: the targeted row is an enemy row
     * -3 : the HeroCard has used its ability this turn already
     * 0 : the ability was cast with success
     */
    @Override
    public int ability(final int targetRow, final Game game) {
        if (isAttackTurn()) {
            if (getPlayerID() == 1) {
                if (targetRow < Constants.MAX_ROW_PLAYER_1
                        && targetRow > Constants.MIN_ROW_PLAYER_1) {
                    raiseHealth(game.getTable().get(targetRow));
                    setAttackTurn(true);
                } else {
                    return Constants.ERROR_MINUS_2;
                }
            } else {
                if (targetRow < Constants.MAX_ROW_NR_PLAYER_2
                        && targetRow > Constants.MIN_ROW_NR_PLAYER_2) {
                    raiseHealth(game.getTable().get(targetRow));
                    setAttackTurn(true);
                } else {
                    return Constants.ERROR_MINUS_2;
                }
            }
            return 0;
        }
        return Constants.ERROR_MINUS_3;
    }

    /**
     * Raises the health value of every minion from the targeted row
     * @param row list of MinionCards from the targeted row
     */
    public void raiseHealth(final ArrayList<MinionCard> row) {
        for (MinionCard minion : row) {
            minion.setHealth(minion.getHealth() + 1);
        }
    }
}
