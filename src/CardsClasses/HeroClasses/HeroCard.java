package cardsclasses.heroclasses;

import cardsclasses.Card;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("Hero")
@JsonPropertyOrder({"mana", "description", "colors", "name"})
@JsonIgnoreProperties({"type", "playerID", "CardType"})
public abstract class HeroCard extends Card {

    private int health = 30;
    public HeroCard(final int mana, final String name, final String description,
                    final ArrayList<String> colors, final int playerID) {
        super(mana, name, description, colors, "Hero", playerID);
    }

    /**
     * @return the health of the hero
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health sets the health of the hero
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * @param targetRow targeted row
     * The function implements the ability of each hero
     */
    abstract void ability(int targetRow);
}
