package cardsclasses.heroclasses;

import cardsclasses.minionclasses.MinionCard;
import constants.Constants;
import gameclasses.Game;
import java.util.ArrayList;

public class GeneralKocioraw extends HeroCard {
    public GeneralKocioraw(final int mana, final String name, final String description,
                           final ArrayList<String> colors, final int playerID) {
        super(mana, name, description, colors, playerID);
    }

    /**
     * Implements the specific ability of this hero
     * @param targetRow targeted row
     * @return error codes:
     * -2 : the targeted row is an enemy row
     * -3 : the HeroCard has used its ability this turn already
     * 0 : the ability was cast with success
     */
    @Override
    public int ability(final int targetRow, final Game game) {
        if (isAttackTurn()) {
            if (getPlayerID() == 1) {
                if (targetRow < Constants.MAX_ROW_PLAYER_1
                        && targetRow > Constants.MIN_ROW_PLAYER_1) {
                    raiseAttack(game.getTable().get(targetRow));
                    setAttackTurn(true);
                } else {
                    return Constants.ERROR_MINUS_2;
                }
            } else {
                if (targetRow < Constants.MAX_ROW_NR_PLAYER_2
                        && targetRow > Constants.MIN_ROW_NR_PLAYER_2) {
                    raiseAttack(game.getTable().get(targetRow));
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
     * Raises the attack of every minion from the targeted row
     * @param row lists of MinionCards from the targeted row
     */
    public void raiseAttack(final ArrayList<MinionCard> row) {
        for (MinionCard minion : row) {
            minion.setAttackDamage(minion.getAttackDamage() + 1);
        }
    }
}
