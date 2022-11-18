package cardsclasses.heroclasses;

import cardsclasses.Card;
import constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import gameclasses.Game;

import java.util.ArrayList;

@JsonTypeName("Hero")
@JsonPropertyOrder({"mana", "description", "colors", "name"})
@JsonIgnoreProperties({"type", "playerID", "CardType", "attackTurn"})
public abstract class HeroCard extends Card {

    private int health = Constants.MAX_HERO_HP;
    private boolean attackTurn;

    public HeroCard(final int mana, final String name, final String description,
                    final ArrayList<String> colors, final int playerID) {
        super(mana, name, description, colors, "Hero", playerID);
        attackTurn = false;
    }

    /**
     * @return if the hero has used their ability this turn
     */
    public boolean isAttackTurn() {
        return !attackTurn;
    }

    /**
     * @param attackTurn sets if the hero has used their ability or not this turn
     */
    public void setAttackTurn(final boolean attackTurn) {
        this.attackTurn = attackTurn;
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
     * The function implements the ability of each hero
     * @param targetRow targeted row
     * @return error codes depending on the ability
     */
    public abstract int ability(int targetRow, Game game);
}
