package cardsclasses.minionclasses;

import cardsclasses.Card;
import cardsclasses.heroclasses.HeroCard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;

@JsonPropertyOrder({"mana", "attackDamage", "health", "description", "colors", "name"})
@JsonIgnoreProperties({"tank", "frozen", "row", "playerID", "type", "dead",
                        "mirroredRow", "attackedTurn", "frozenTurn"})
public class MinionCard extends Card {
    private int health;

    /**
     * @return the row where the card can be placed if it's stolen
     */
    public int getMirroredRow() {
        return mirroredRow;
    }

    /**
     * @param mirroredRow the row where the card can be placed if it's stolen
     */
    public void setMirroredRow(final int mirroredRow) {
        this.mirroredRow = mirroredRow;
    }

    private int mirroredRow;
    private int attackDamage;
    private boolean tank;
    private boolean frozen;

    /**
     * @return the turn when the card was frozen
     */
    public int getFrozenTurn() {
        return frozenTurn;
    }

    /**
     * @param frozenTurn set the turn when the card got frozen
     */
    public void setFrozenTurn(final int frozenTurn) {
        this.frozenTurn = frozenTurn;
    }

    private int frozenTurn;
    private int row;

    /**
     * @return if the card attacked/used ability this turn or not
     */
    public boolean isAttackedTurn() {
        return attackedTurn;
    }

    /**
     * @param attackedTurn set it to true if the card attacked/used ability this turn
     */
    public void setAttackedTurn(final boolean attackedTurn) {
        this.attackedTurn = attackedTurn;
    }

    private boolean attackedTurn = false;

    /**
     * @return if the card is dead or not
     */
    public boolean isDead() {
        return health <= 0;
    }

    public MinionCard(final int mana, final int ad, final int hp, final String description,
                      final ArrayList<String> colors, final String name, final int playerID) {
        super(mana, name, description, colors, "Minion", playerID);
        this.health = hp;
        this.attackDamage = ad;
        this.frozen = false;
    }


    public MinionCard(final MinionCard copy) {
        super(copy);
        this.health = copy.health;
        this.attackDamage = copy.attackDamage;
        this.frozen = copy.frozen;
        this.row = copy.row;
        this.tank = copy.tank;
    }

    /**
     * @param enemy targeted card
     * @param existingTanks if there's a tank on the row where the enemy is or not
     * @return error code
     * -3 if you're attacking a non-tank card when there's a tank on the row
     * -1 if the target is a friendly card
     * -2 if the card that's trying to attack is frozen
     */
    public int attack(final MinionCard enemy, final boolean existingTanks) {
        if (!attackedTurn) {
            if (!isFrozen()) {
                if (enemy.getPlayerID() != getPlayerID()) {
                    if (existingTanks) {
                        if (enemy.isTank()) {
                            enemy.setHealth(enemy.getHealth() - this.getAttackDamage());
                            attackedTurn = true;
                        } else {
                            return -3;
                        }
                    } else {
                        enemy.setHealth(enemy.getHealth() - this.getAttackDamage());
                        attackedTurn = true;
                    }
                } else {
                    return -1;
                }
            } else {
                return -2;
            }
            return 0;
        } else {
            return -4;
        }
    }

    public int attackHero(HeroCard enemy, boolean existingTanks) {
        if (!attackedTurn) {
            if (!isFrozen()) {
                if (enemy.getPlayerID() != getPlayerID()) {
                    if (existingTanks) {
                            return -3;
                    } else {
                        enemy.setHealth(enemy.getHealth() - this.getAttackDamage());
                        attackedTurn = true;
                    }
                } else {
                    return -1;
                }
            } else {
                return -2;
            }
            return 0;
        } else {
            return -4;
        }
    }

    public int ability(final MinionCard enemy, boolean existingTanks) {
        return 0;
    }

    /**
     * @return the health of the card
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param hp set the new health value (in case the card it's attacked/healed)
     */
    public void setHealth(final int hp) {
        this.health = hp;
    }

    /**
     * @return return the attack of this card
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * @param ad sets the new attack of the card
     */
    public void setAttackDamage(final int ad) {
        this.attackDamage = ad;
    }

    /**
     * @return if the card is a tank or not
     */
    public boolean isTank() {
        return tank;
    }

    /**
     * @param tank sets if the card is a tank or not
     */
    public void setTank(final boolean tank) {
        this.tank = tank;
    }

    /**
     * @return the row where you can place the card
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row where the card can be placed
     */
    public void setRow(final int row) {
        this.row = row;
    }

    /**
     * @return if the card is frozen or not
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * @param frozen the state of the card
     */
    public void setFrozen(final boolean frozen) {
        this.frozen = frozen;
    }



}
