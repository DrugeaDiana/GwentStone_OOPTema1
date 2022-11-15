package CardsClasses.HeroClasses;

import java.util.ArrayList;

public class LordRoyce extends HeroCard{
    public LordRoyce(int mana, String name, String description, ArrayList<String> colors, int playerID) {
        super(mana, name, description, colors, playerID);
    }

    @Override
    void ability(int targetRow) {
        if(getPlayerID() == 1) {
            if(targetRow < 2 && targetRow > -1) {
                System.out.println("e randu bun");
            } else {
                System.out.println("unde naiba dai");
            }
        } else {
            if(targetRow < 4 && targetRow > 1) {
                System.out.println("e randu bun");
            } else {
                System.out.println("unde dai boss");
            }
        }
    }
}
