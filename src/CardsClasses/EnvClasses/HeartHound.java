package cardsclasses.envclasses;

import cardsclasses.minionclasses.MinionCard;
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
        if (game.getTable().get(stolen.getMirroredRow()).size() > 4) {
            return -2;
        } else {
            MinionCard stolenCopy = new MinionCard(stolen);
            stolenCopy.setRow(stolen.getMirroredRow());
            stolenCopy.setMirroredRow(stolen.getRow());
            stolenCopy.setPlayerID(2);
            rowMinions.remove(stolen);
            game.getTable().get(2).add(stolenCopy);
            return 0;
        }
    }

    /**
     * @param targetRow index of the row from the table we want to use the card's ability on
     * @param game      variable for the game we're playing
     * implements the specific ability of this card
     * @return error code (here -1, -2, -4, 0)
     */
    @Override
    public int ability(final int targetRow, final Game game) {
        if (getPlayerID() == 1) {
            if (targetRow < 2 && targetRow > -1) {
                if (stealMinion(targetRow, game) == -2) {
                    return -2;
                }
            } else {
                return -1;
            }
        } else {
            if (targetRow < 4 && targetRow > 1) {
                if (stealMinion(targetRow, game) == -2) {
                    return -2;
                }
            } else {
                return -1;
            }
        }
        return 0;
    }
}
