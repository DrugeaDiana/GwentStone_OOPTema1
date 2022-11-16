package cardsclasses;

import java.util.ArrayList;

public final class Deck {
    private ArrayList<Card> cards;

    /**
     * @return the list of cards from this Deck
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(final ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Deck(final ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * @param copyDeck the Deck to be copied into the new deck
     */
    public Deck(final Deck copyDeck) {
        this.cards = new ArrayList<>();
        this.cards.addAll(copyDeck.cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
