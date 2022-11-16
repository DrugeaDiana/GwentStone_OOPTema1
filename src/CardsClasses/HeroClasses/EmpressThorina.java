package cardsclasses.heroclasses;

import cardsclasses.minionclasses.MinionCard;
import gameclasses.Game;

import java.util.ArrayList;

public class EmpressThorina extends HeroCard {
    public EmpressThorina(final int mana, final String name, final String description,
                          final ArrayList<String> colors, final int playerID) {
        super(mana, name, description, colors, playerID);
    }

    /**
     * @param targetRow the row the player wants to use the ability on
     */
    @Override
    public int ability(final int targetRow, final Game game) {
        if (!isAttackTurn()) {
            if (getPlayerID() == 1) {
                if (targetRow < 2 && targetRow > -1) {
                    removeMinionWithHighestHP(game.getTable().get(targetRow));
                    setAttackTurn(true);
                } else {
                    System.out.println("unde naiba dai");
                    return -1;
                }
            } else {
                if (targetRow < 4 && targetRow > 1) {
                    removeMinionWithHighestHP(game.getTable().get(targetRow));
                    setAttackTurn(true);
                } else {
                    System.out.println("unde dai boss");
                    return -1;
                }
            }
            return 0;
        }
        return -3;

    }

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
