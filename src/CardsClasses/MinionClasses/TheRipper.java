package cardsclasses.minionclasses;

import java.util.ArrayList;

public class TheRipper extends MinionCard {
    public TheRipper(final int mana, final int ad, final int hp, final String description,
                     final ArrayList<String> colors, final String name, final int playerID) {
        super(mana, ad, hp, description, colors, name, playerID);
        setTank(false);
        if (playerID == 1) {
            setRow(2);
            setMirroredRow(1);
        } else {
            setRow(1);
            setMirroredRow(2);
        }
    }

    /**
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
        if (!isAttackedTurn()) {
            if (!isFrozen()) {
                if (enemy.getPlayerID() != getPlayerID()) {
                    if (existingTanks) {
                        if (enemy.isTank()) {
                            enemy.setAttackDamage(enemy.getAttackDamage() - 2);
                            if (enemy.getAttackDamage() < 0) {
                                enemy.setAttackDamage(0);
                            }
                            setAttackedTurn(true);
                        } else {
                            return -3;
                        }
                    } else {
                        enemy.setAttackDamage(enemy.getAttackDamage() - 2);
                        if (enemy.getAttackDamage() < 0) {
                            enemy.setAttackDamage(0);
                        }
                        setAttackedTurn(true);
                    }
                } else {
                    return -1;
                }
            } else {
                return -2;
            }
            return 0;
        } else {
            return -4;
        }
    }
}
