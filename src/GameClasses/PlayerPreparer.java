package gameclasses;
import cardsclasses.heroclasses.*;
import cardsclasses.*;
import fileio.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayerPreparer {

    public PlayerPreparer(){};
    public ArrayList<Deck> prepareDecks(DecksInput decksIn, Player player) {
        ArrayList<Card> deck;
        ArrayList<Deck> playerDecks = new ArrayList<>();
        Deck oneDeck;
        CardCreator cardCreator = new CardCreator();

        int playerID = player.getPlayerId();
        int deckNumber = decksIn.getNrDecks();
        int cardNumber = decksIn.getNrCardsInDeck();

        for(int i = 0; i < deckNumber; i++) {
            deck = new ArrayList<>();
            for(int j = 0; j < cardNumber; j++) {
                String cardName = decksIn.getDecks().get(i).get(j).getName();
                deck.add(cardCreator.create(decksIn.getDecks().get(i).get(j), playerID));
            }
            oneDeck = new Deck(deck);
            playerDecks.add(oneDeck);
        }
        return playerDecks;
    }

    public Deck prepareCurrentDeck(DecksInput decksInput, Player player, int deckIndex) {
        ArrayList<Card> deck = new ArrayList<>();
        CardCreator cardCreator = new CardCreator();
        int playerID = player.getPlayerId();
        int cardNumber = decksInput.getNrCardsInDeck();

        for (int i = 0; i < cardNumber; i++) {
            deck.add(cardCreator.create(decksInput.getDecks().get(deckIndex).get(i), playerID));
        }
        return new Deck(deck);
    }

    public void preparePlayer(Player player, StartGameInput start, DecksInput decksInput) {
        int playerIdx = player.getPlayerId();
        Random rnd = new Random(start.getShuffleSeed());

        if (playerIdx == 1) {
            int deck_index = start.getPlayerOneDeckIdx();
            player.setCurrentDeck(prepareCurrentDeck(decksInput, player, deck_index));
            Collections.shuffle(player.getCurrentDeck().getCards(), rnd);
            CardCreator creator = new CardCreator();
            HeroCard playerHero = creator.createHero(start.getPlayerOneHero(), playerIdx);
            player.setHero(playerHero);
            ArrayList<Card> newHand = new ArrayList<>();
            player.setHand(newHand);
            player.getHand().add(player.getCurrentDeck().getCards().get(0));
            player.getCurrentDeck().getCards().remove(0);
            player.setFinishTurn(false);
            player.setMana(1);
        } else {
            int deck_index = start.getPlayerTwoDeckIdx();
            player.setCurrentDeck(prepareCurrentDeck(decksInput, player, deck_index));
            Collections.shuffle(player.getCurrentDeck().getCards(), rnd);
            CardCreator creator = new CardCreator();
            HeroCard playerHero = creator.createHero(start.getPlayerTwoHero(), playerIdx);
            player.setHero(playerHero);
            ArrayList<Card> newHand = new ArrayList<>();
            player.setHand(newHand);
            player.getHand().add(player.getCurrentDeck().getCards().get(0));
            player.getCurrentDeck().getCards().remove(0);
            player.setFinishTurn(false);
            player.setMana(1);
        }
    }

}
