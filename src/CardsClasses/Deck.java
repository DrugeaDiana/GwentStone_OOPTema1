package cardsclasses;

import java.util.ArrayList;

public final class Deck {
    private final ArrayList<Card> cards;

    public Deck(final ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * Getter for the list of cards in the deck
     * @return the list of cards from this Deck
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Copy Constructor for Deck
     * @param copyDeck the Deck to be copied into the new deck
     */
    public Deck(final Deck copyDeck) {
        this.cards = new ArrayList<>();
        this.cards.addAll(copyDeck.cards);
    }

}
