package CardsClasses.MinionClasses;

import CardsClasses.Card;
import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;

@JsonPropertyOrder({"mana", "attackDamage", "health", "description", "colors", "name"})
@JsonIgnoreProperties({"tank", "frozen", "row", "playerID", "type"})
public class MinionCard extends Card {
    private int health;
    private int attackDamage;
    private boolean tank;
    private boolean frozen;
    private int row;
    public MinionCard(int mana, int ad, int hp, String description, ArrayList<String> colors, String name, int playerID) {
        super(mana,name,description, colors, "Minion", playerID);
        this.health = hp;
        this.attackDamage = ad;
        this.frozen = false;
    }

    public void attack(MinionCard enemy) {
        if(!isFrozen()) {
            if (enemy.getPlayerID() != getPlayerID()) {
                enemy.setHealth(enemy.getHealth() - this.getAttackDamage());
                System.out.println("bonk");
            } else {
                System.out.println("u can't attack friends </3");
            }
        } else {
            System.out.println("u're frozen");
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int hp) {
        this.health = hp;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int ad) {
        this.attackDamage = ad;
    }

    public boolean isTank() {
        return tank;
    }

    public void setTank(boolean tank) {
        this.tank = tank;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }



}
