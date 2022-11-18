package constants;

public final class ErrorStrings {
    public static final String NOT_ENEMY = "Attacked card does not belong to the enemy.";
    public static final String NOT_FRIENDLY = "Attacked card does not belong to the "
            + "current player.";
    public static final String FROZEN = "Attacker card is frozen.";
    public static final String NOT_TANK = "Attacked card is not of type 'Tank'.";
    public static final String DOUBLE_ACTION = "Attacker card has already attacked this turn.";
    public static final String NOT_ENV = "Chosen card is not of type environment.";
    public static final String NO_MANA_ENV = "Not enough mana to use environment card.";
    public static final String NO_STEAL = "Cannot steal enemy card since the "
            + "player's row is full.";
    public static final String NOT_ENEMY_ROW = "Chosen row does not belong to the enemy.";
    public static final String FULL_ROW = "Cannot place card on table since row is full.";
    public static final String NO_PLACING_ENV = "Cannot place environment card on table.";
    public static final String NO_MANA_MINION = "Not enough mana to place card on table.";
    public static final String NOT_ENEMY_ROW_HERO = "Selected row does not belong to the enemy.";
    public static final String NOT_FRIENDLY_ROW_HERO = "Selected row does not belong to the "
            + "current player.";
    public static final String DOUBLE_ACTION_HERO = "Hero has already attacked this turn.";
    public static final String NO_MANA_HERO = "Not enough mana to use hero's ability.";
    public static final String NO_CARD = "No card available at that position.";

    private ErrorStrings() { }
}
