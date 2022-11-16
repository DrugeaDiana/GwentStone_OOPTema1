package cardsclasses.envclasses;

import cardsclasses.minionclasses.MinionCard;
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
            if (targetRow < 2 && targetRow > -1) {
                ArrayList<MinionCard> rowMinions = game.getTable().get(targetRow);
                for (MinionCard minion : rowMinions) {
                    minion.setHealth(minion.getHealth() - 1);
                }
                rowMinions.removeIf(MinionCard::isDead);
            } else {
                return -1;
            }
        } else {
            if (targetRow < 4 && targetRow > 1) {
                ArrayList<MinionCard> rowMinions = game.getTable().get(targetRow);
                for (MinionCard minion : rowMinions) {
                    minion.setHealth(minion.getHealth() - 1);
                }
                rowMinions.removeIf(MinionCard::isDead);
            } else {
                return -1;
            }
        }
        return 0;
    }
}
