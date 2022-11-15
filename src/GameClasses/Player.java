package GameClasses;
import CardsClasses.*;
import CardsClasses.HeroClasses.HeroCard;
import fileio.*;


import java.util.ArrayList;

public class Player {
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    private int playerId;

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    private ArrayList<Deck> decks;

    public Deck getCurrentDeck() {
        return currentDeck;
    }

    public void setCurrentDeck(Deck currentDeck) {
        this.currentDeck = currentDeck;
    }

    private Deck currentDeck;

    public Deck getShuffledDeck() {
        return shuffledDeck;
    }

    public void setShuffledDeck(Deck shuffledDeck) {
        this.shuffledDeck = new Deck(shuffledDeck);
    }

    private Deck shuffledDeck;

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    private ArrayList<Card> hand = new ArrayList<>();

    public HeroCard getHero() {
        return hero;
    }

    public void setHero(HeroCard hero) {
        this.hero = hero;
    }

    private HeroCard hero;
    private int mana;
    private int games;
    private int nrDecks;

    public Player(int playerId, ArrayList<Deck> decks) {
        this.playerId = playerId;
        this.decks = decks;
    }

    public Player(int playerId){
        this.playerId = playerId;
    };

    @Override
    public String toString() {
        return "Player{" +
                "decks=" + decks +
                '}';
    }
}
