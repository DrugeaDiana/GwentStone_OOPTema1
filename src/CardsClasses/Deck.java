package CardsClasses;

import java.util.ArrayList;

public class Deck {
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    private ArrayList<Card> cards;

    public Deck(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Deck(Deck copy_deck){
        this.cards = new ArrayList<>();
        this.cards.addAll(copy_deck.cards);
    }

    @Override
    public String toString() {
        return "Deck\n{" +
                "cards=" + cards +
                '}';
    }
}
