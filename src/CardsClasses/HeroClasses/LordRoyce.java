package cardsclasses.heroclasses;

import cardsclasses.minionclasses.MinionCard;
import constants.Constants;
import gameclasses.Game;

import java.util.ArrayList;

public class LordRoyce extends HeroCard {
    public LordRoyce(final int mana, final String name, final String description,
                     final ArrayList<String> colors, final int playerID) {
        super(mana, name, description, colors, playerID);
    }

    /**
     * Implements the specific ability of each
     * @param targetRow targeted row
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
                    freezeCard(game.getTable().get(targetRow), game);
                    setAttackTurn(true);
                } else {
                    return Constants.ERROR_MINUS_1;
                }
            } else {
                if (targetRow < Constants.MAX_ROW_PLAYER_1
                        && targetRow > Constants.MIN_ROW_PLAYER_1) {
                    freezeCard(game.getTable().get(targetRow), game);
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
     * Freezes every minion from the targeted row
     * @param row list of MinionCards from the targeted row
     * @param game the base game variable
     */
    public void freezeCard(final ArrayList<MinionCard> row, final Game game) {
        int attack = 0;
        int index = 0;
        for (MinionCard minionCard : row) {
            if (minionCard.getAttackDamage() > attack) {
                attack = minionCard.getAttackDamage();
            }
        }
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i).getAttackDamage() == attack) {
                index = i;
                break;
            }
        }
        row.get(index).setFrozen(true);
        row.get(index).setFrozenTurn(game.getTurnCounter());
    }
}
