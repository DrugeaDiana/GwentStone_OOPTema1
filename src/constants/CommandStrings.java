package constants;

public final class CommandStrings {
    public static final String PLAYER_DECK =  "getPlayerDeck";
    public static final String PLAYER_HERO = "getPlayerHero";
    public static final String PLAYER_TURN = "getPlayerTurn";
    public static final String END_TURN = "endPlayerTurn";
    public static final String PLACE_CARD = "placeCard";
    public static final String MANA = "getPlayerMana";
    public static final String CARDS_IN_HAND = "getCardsInHand";
    public static final String CARDS_ON_TABLE = "getCardsOnTable";
    public static final String ENV_CARD = "useEnvironmentCard";
    public static final String ENV_CARDS_IN_HAND = "getEnvironmentCardsInHand";
    public static final String CARD_AT_POSITION = "getCardAtPosition";
    public static final String FROZEN_CARDS = "getFrozenCardsOnTable";
    public static final String ATTACK = "cardUsesAttack";
    public static final String ABILITY = "cardUsesAbility";
    public static final String ATTACK_A_HERO = "useAttackHero";
    public static final String HERO_ABILITY = "useHeroAbility";
    public static final String WINS_1 = "getPlayerOneWins";
    public static final String WINS_2 = "getPlayerTwoWins";
    public static final String ALL_GAMES = "getTotalGamesPlayed";

    private CommandStrings() { }
}
