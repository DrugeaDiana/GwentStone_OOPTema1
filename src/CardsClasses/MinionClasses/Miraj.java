package cardsclasses.minionclasses;

import constants.Constants;

import java.util.ArrayList;

public class Miraj extends MinionCard {
    public Miraj(final int mana, final int ad, final int hp, final String description,
                 final ArrayList<String> colors, final String name, final int playerID) {
        super(mana, ad, hp, description, colors, name, playerID);
        setTank(false);
        if (playerID == 1) {
            setRow(Constants.FRONT_ROW_PLAYER_1);
            setMirroredRow(Constants.FRONT_ROW_PLAYER_2);
        } else {
            setRow(Constants.FRONT_ROW_PLAYER_2);
            setMirroredRow(Constants.FRONT_ROW_PLAYER_1);
        }
    }

    /**
     * Implements the ability of this card
     * @param enemy target of the ability
     * @param existingTanks if there's a tank on the row with the targeted card
     * @return error code
     * -1 for trying to use the ability on a friendly card
     * -2 for trying to use the ability while frozen
     * -3 for trying to use the ability on a non-tank
     * -4 for trying to use the ability two times per turn
     * 0 if the ability succeeds
     */
    public int ability(final MinionCard enemy, final boolean existingTanks) {
        if (isAttackedTurn()) {
            if (!isFrozen()) {
                if (enemy.getPlayerID() != getPlayerID()) {
                    if (existingTanks) {
                        if (enemy.isTank()) {
                            swapHP(enemy);
                            setAttackedTurn(true);
                        } else {
                            return Constants.ERROR_MINUS_3;
                        }
                    } else {
                        swapHP(enemy);
                        setAttackedTurn(true);
                    }
                } else {
                    return Constants.ERROR_MINUS_1;
                }
            } else {
                return Constants.ERROR_MINUS_2;
            }
            return 0;
        } else {
            return Constants.ERROR_MINUS_4;
        }
    }

    /**
     * Swaps the health of this card with the targeted card
     * @param enemy the enemy to swap the health value with
     */
    public void swapHP(final MinionCard enemy) {
        int enHp = enemy.getHealth();
        int cardHp = getHealth();
        enemy.setHealth(cardHp);
        setHealth(enHp);
    }
}
