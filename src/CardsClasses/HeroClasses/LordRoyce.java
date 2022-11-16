package cardsclasses.heroclasses;

import cardsclasses.minionclasses.MinionCard;
import gameclasses.Game;

import java.util.ArrayList;

public class LordRoyce extends HeroCard {
    public LordRoyce(final int mana, final String name, final String description,
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
                if (targetRow < 2 && targetRow > -1) {
                    System.out.println("e randu bun");
                    freezeCard(game.getTable().get(targetRow), game);
                    setAttackTurn(true);
                } else {
                    System.out.println("unde naiba dai");
                    return -1;
                }
            } else {
                if (targetRow < 4 && targetRow > 1) {
                    System.out.println("e randu bun");
                    freezeCard(game.getTable().get(targetRow), game);
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

    public void freezeCard(final ArrayList<MinionCard> row, Game game) {
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
