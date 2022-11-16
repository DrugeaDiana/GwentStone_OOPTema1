package cardsclasses.heroclasses;

import cardsclasses.minionclasses.MinionCard;
import gameclasses.Game;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;

public class KingMudface extends HeroCard {
    public KingMudface(final int mana, final String name, final String description,
                       final ArrayList<String> colors, final int playerID) {
        super(mana, name, description, colors, playerID);
    }

    /**
     * @param targetRow targeted row
     * The function implements the ability of each hero
     */
    @Override
    public int ability(final int targetRow, final Game game) {
        if (!isAttackTurn()) {
            if (getPlayerID() == 1) {
                if (targetRow < 4 && targetRow > 1) {
                    System.out.println("e randu bun");
                    raiseHealth(game.getTable().get(targetRow));
                    setAttackTurn(true);
                } else {
                    System.out.println("unde naiba dai");
                    return -2;
                }
            } else {
                if (targetRow < 2 && targetRow > -1) {
                    System.out.println("e randu bun");
                    raiseHealth(game.getTable().get(targetRow));
                    setAttackTurn(true);
                } else {
                    System.out.println("unde dai boss");
                    return -2;
                }
            }
            return 0;
        }
        return -3;
    }

    public void raiseHealth(final ArrayList<MinionCard> row) {
        for (MinionCard minion : row) {
            minion.setHealth(minion.getHealth() + 1);
        }
    }
}
