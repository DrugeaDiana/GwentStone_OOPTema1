package CardsClasses.HeroClasses;

import CardsClasses.Card;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("Hero")
@JsonPropertyOrder({"mana", "description", "colors", "name"})
@JsonIgnoreProperties({"type", "playerID", "CardType"})
public abstract class HeroCard extends Card {

    private int health = 30;
    public HeroCard(int mana, String name, String description, ArrayList<String> colors, int playerID) {
        super(mana, name, description, colors, "Hero", playerID);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    abstract void ability(int targetRow);
}
