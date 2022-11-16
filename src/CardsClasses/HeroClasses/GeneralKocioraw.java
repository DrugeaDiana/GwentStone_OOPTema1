package cardsclasses.heroclasses;

import cardsclasses.minionclasses.MinionCard;
import gameclasses.Game;

import java.util.ArrayList;

public class GeneralKocioraw extends HeroCard {
    public GeneralKocioraw(final int mana, final String name, final String description,
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
                    raiseAttack(game.getTable().get(targetRow));
                    setAttackTurn(true);
                } else {
                    System.out.println("unde naiba dai");
                    return -2;
                }
            } else {
                if (targetRow < 2 && targetRow > -1) {
                    System.out.println("e randu bun");
                    raiseAttack(game.getTable().get(targetRow));
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

    public void raiseAttack(final ArrayList<MinionCard> row) {
        for (MinionCard minion : row) {
            minion.setAttackDamage(minion.getAttackDamage() + 1);
        }
    }
}
