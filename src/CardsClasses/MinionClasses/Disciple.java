package cardsclasses.minionclasses;

import java.util.ArrayList;

public class Disciple extends MinionCard {
    public Disciple(final int mana, final int ad, final int hp, final String description,
                    final ArrayList<String> colors, final String name, final int playerID) {
        super(mana, ad, hp, description, colors, name, playerID);
        setTank(false);
        if (playerID == 1) {
            setRow(3);
            setMirroredRow(0);
        } else {
            setRow(0);
            setMirroredRow(3);
        }
    }

    /**
     * @param target targeted enemy card
     * @return error code
     * -1 for trying to use the ability on an enemy card
     * -2 for trying to use the ability while frozen
     * -4 for trying to use the ability two times per turn
     * 0 if the ability succeeds
     */
    public int ability(final MinionCard target, final boolean existingTanks) {
        if (!isAttackedTurn()) {
            if (!isFrozen()) {
                if (target.getPlayerID() == getPlayerID()) {
                    target.setHealth(target.getHealth() + 2);
                    setAttackedTurn(true);
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return -2;
            }
        } else {
            return -4;
        }
    }
}
