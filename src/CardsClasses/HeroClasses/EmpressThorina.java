package cardsclasses.heroclasses;

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
    void ability(final int targetRow) {
        if (getPlayerID() == 1) {
            if (targetRow < 2 && targetRow > -1) {
                System.out.println("e randu bun");
            } else {
                System.out.println("unde naiba dai");
            }
        } else {
            if (targetRow < 4 && targetRow > 1) {
                System.out.println("e randu bun");
            } else {
                System.out.println("unde dai boss");
            }
        }
    }
}
