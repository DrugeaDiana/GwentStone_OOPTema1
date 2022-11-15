package CardsClasses;

import CardsClasses.EnvClasses.EnvironmentCard;
import CardsClasses.HeroClasses.HeroCard;
import CardsClasses.MinionClasses.MinionCard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;

//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        property = "CardType")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = MinionCard.class, name = "Minion"),
//        @JsonSubTypes.Type(value = EnvironmentCard.class, name = "Environment"),
//        //@JsonSubTypes.Type(value = HeroCard.class, name = "Hero")
//})
@JsonPropertyOrder({"mana", "attackDamage", "health", "description", "colors", "name"})
@JsonIgnoreProperties({"tank", "frozen", "row", "playerID", "type", "CardType"})
public abstract class Card {
    final int mana;
    final String name;
    final String description;
    final ArrayList<String> colors;

    final String type;

//    private int health;
//    private int attackDamage;
//    private boolean tank;
//    private boolean frozen;

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    private int playerID;

    public Card() {
        this.mana = 0;
        this.name = null;
        this.description = null;
        this.colors = null;
        this.type = null;
    }

    public Card(Card copy_card){
        this.mana = copy_card.getMana();
        this.name = copy_card.getName();
        this.description = copy_card.getDescription();
        this.colors = new ArrayList<String>();
        this.colors.addAll(copy_card.getColors());
        this.type = copy_card.getType();
        this.playerID = copy_card.getPlayerID();
    };

    public Card(int mana, String name, String description, ArrayList<String> colors, String type, int playerID) {
        this.mana = mana;
        this.name = name;
        this.description = description;
        this.colors = colors;
        this.type = type;
        this.playerID = playerID;
    }

    public int getMana() {
        return mana;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public String getType() {
        return type;
    }


}
