package cardsclasses.envclasses;

import cardsclasses.Card;
import gameclasses.Game;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("Environment")
public abstract class EnvironmentCard extends Card {
    public EnvironmentCard(final int mana, final String name, final String description,
                           final ArrayList<String> colors, final int playerID) {
        super(mana, name, description, colors, "Environment", playerID);
    }

    /**
     * Implements the ability of each card
     * @param targetRow index of the row from the table we want to use the card's ability on
     * @param game variable for the game we're playing
     * @return an int code to know what error we have
     * -1 : wrong row targeted
     * -2 (for HeartHound) : player's row is full
     * 0 : the ability was used properly
     */
    public abstract int ability(int targetRow, Game game);

}
