package cardsclasses.envclasses;

import cardsclasses.minionclasses.MinionCard;
import constants.Constants;
import gameclasses.Game;

import java.util.ArrayList;

public class HeartHound extends EnvironmentCard {
    public HeartHound(final int mana, final String name, final String description,
                      final ArrayList<String> colors, final int playerID) {
        super(mana, name, description, colors, playerID);
    }

    /**
     * @param targetRow index of the row from the table we want to use the card's ability on
     * @param game variable for the game we're playing
     * this function implements the "steal minion" functionality of this card's ability
     * @return error code
     * -2 : the player can't add the stolen card to their row
     */
    public int stealMinion(final int targetRow, final Game game) {
        ArrayList<MinionCard> rowMinions = game.getTable().get(targetRow);
        int hp = 0;
        int index = 0;
        for (int i = 0; i < rowMinions.size(); i++) {
            if (rowMinions.get(i).getHealth() > hp) {
                hp = rowMinions.get(i).getHealth();
                index = i;
            }
        }
        MinionCard stolen = rowMinions.get(index);
        if (game.getTable().get(stolen.getMirroredRow()).size() > Constants.MAX_NR_OF_CARDS) {
            return Constants.ERROR_MINUS_2;
        } else {
            MinionCard stolenCopy = new MinionCard(stolen);
            stolenCopy.setRow(stolen.getMirroredRow());
            stolenCopy.setMirroredRow(stolen.getRow());

            if (getPlayerID() == 1) {
                stolenCopy.setPlayerID(1);
            } else {
                stolenCopy.setPlayerID(2);
            }
            game.getTable().get(stolenCopy.getRow()).add(stolenCopy);
            rowMinions.remove(stolen);
            return 0;
        }
    }

    /**
     * @param targetRow index of the row from the table we want to use the card's ability on
     * @param game      variable for the game we're playing
     * implements the specific ability of this card
     * @return error code
     * -1 : the targeted row is not from the enemy player ( determined based on the attacker's
     * player ID: if the attacker is one -> enemy rows are 0 and 1
     * -2 : the player can't add the stolen card to their row
     *  0 : ability is cast with no problems
     */
    @Override
    public int ability(final int targetRow, final Game game) {
        if (getPlayerID() == 1) {
            if (targetRow < Constants.MAX_ROW_NR_PLAYER_2
                    && targetRow >= Constants.MIN_ROW_NR_PLAYER_2) {
                if (stealMinion(targetRow, game) == Constants.ERROR_MINUS_2) {
                    return Constants.ERROR_MINUS_2;
                }
            } else {
                return Constants.ERROR_MINUS_1;
            }
        } else {
            if (targetRow < Constants.MAX_ROW_PLAYER_1 && targetRow > Constants.MIN_ROW_PLAYER_1) {
                if (stealMinion(targetRow, game) == Constants.ERROR_MINUS_2) {
                    return Constants.ERROR_MINUS_2;
                }
            } else {
                return Constants.ERROR_MINUS_1;
            }
        }
        return 0;
    }
}
