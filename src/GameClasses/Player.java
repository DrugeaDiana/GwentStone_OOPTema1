package gameclasses;
import cardsclasses.Card;
import cardsclasses.Deck;
import cardsclasses.heroclasses.HeroCard;

import java.util.ArrayList;

public class Player {
    private boolean finishTurn;
    private int mana;
    private int wins;
    private final int playerId;
    private ArrayList<Deck> decks;
    private Deck currentDeck;
    private ArrayList<Card> hand = new ArrayList<>();
    private HeroCard hero;

    public Player(final int playerId, final ArrayList<Deck> decks) {
        this.playerId = playerId;
        this.decks = decks;
    }

    public Player(final int playerId) {
        this.playerId = playerId;
        this.mana = 1;
    }

    /**
     * Getter for finishTurn
     * @return if the player has finished their turn or not
     */
    public boolean isFinishTurn() {
        return finishTurn;
    }

    /**
     * Setter for finishTurn
     * @param finishTurn modifies if the player has finished their turn
     */
    public void setFinishTurn(final boolean finishTurn) {
        this.finishTurn = finishTurn;
    }

    /**
     * Getter for playerID
     * @return the ID of the player
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Getter for decks
     * @return the list of Decks the player has
     */
    public ArrayList<Deck> getDecks() {
        return decks;
    }

    /**
     * Setter for decks
     * @param decks the new list with the new decks
     */
    public void setDecks(final ArrayList<Deck> decks) {
        this.decks = decks;
    }

    /**
     * Getter for currentDeck
     * @return the currentDeck that the player is using
     */
    public Deck getCurrentDeck() {
        return currentDeck;
    }

    /**
     * Setter for currentDeck
     * @param currentDeck the new deck that the player is using
     */
    public void setCurrentDeck(final Deck currentDeck) {
        this.currentDeck = currentDeck;
    }

    /**
     * Getter for hand
     * @return the list of Cards that the player has in their hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Setter for hand
     * @param hand the new list of Cards that the player has in their hand
     */
    public void setHand(final ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * Getter for hero
     * @return the HeroCard that the player has
     */
    public HeroCard getHero() {
        return hero;
    }

    /**
     * Setter for hero
     * @param hero the new HeroCard that the player is using for the new game
     */
    public void setHero(final HeroCard hero) {
        this.hero = hero;
    }

    /**
     * Getter for mana
     * @return the amount of mana the player has
     */
    public int getMana() {
        return mana;
    }

    /**
     * Setter for mana
     * @param mana the new amount of mana the player has after a new turn/using cards
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * Getter for wins
     * @return the amount of games the player has won
     */
    public int getWins() {
        return wins;
    }

    /**
     * Setter for wins
     * @param wins the new amount of games the player has won
     */
    public void setWins(final int wins) {
        this.wins = wins;
    }
}
