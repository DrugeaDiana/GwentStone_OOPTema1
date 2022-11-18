package cardsclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;

@JsonPropertyOrder({"mana", "attackDamage", "health", "description", "colors", "name"})
@JsonIgnoreProperties({"tank", "frozen", "row", "playerID", "type", "CardType"})
public abstract class Card {
    private final int mana;
    private final String name;
    private final String description;
    private final ArrayList<String> colors;
    private final String type;
    private int playerID;

    public Card() {
        this.mana = 0;
        this.name = null;
        this.description = null;
        this.colors = null;
        this.type = null;
    }

    public Card(final Card copyCard) {
        this.mana = copyCard.getMana();
        this.name = copyCard.getName();
        this.description = copyCard.getDescription();
        this.colors = new ArrayList<>();
        this.colors.addAll(copyCard.getColors());
        this.type = copyCard.getType();
        this.playerID = copyCard.getPlayerID();
    }

    public Card(final int mana, final String name, final String description,
                final ArrayList<String> colors, final String type, final int playerID) {
        this.mana = mana;
        this.name = name;
        this.description = description;
        this.colors = colors;
        this.type = type;
        this.playerID = playerID;
    }

    /**
     * @return mana needed to place the card
     */
    public int getMana() {
        return mana;
    }

    /**
     * @return name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * @return description of the card
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return colors of the card
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     * @return type of the card
     */
    public String getType() {
        return type;
    }

    /**
     * @return ID of the player that has the card in their hand/deck
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * @param playerID saves the ID of the player that has the card in their hand/deck
     */
    public void setPlayerID(final int playerID) {
        this.playerID = playerID;
    }

}
