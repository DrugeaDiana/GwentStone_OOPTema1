package cardsclasses.heroclasses;

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
    void ability(final int targetRow) {
        if (getPlayerID() == 1) {
            if (targetRow < 4 && targetRow > 1) {
                System.out.println("e randu bun");
            } else {
                System.out.println("unde naiba dai");
            }
        } else {
            if (targetRow < 2 && targetRow > -1) {
                System.out.println("e randu bun");
            } else {
                System.out.println("unde dai boss");
            }
        }
    }
}
