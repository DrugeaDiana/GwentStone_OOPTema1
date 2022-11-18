package gameclasses;

import cardsclasses.Deck;
import cardsclasses.heroclasses.HeroCard;
import cardsclasses.Card;
import fileio.DecksInput;
import fileio.StartGameInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayerPreparer {
    /**
     * Prepares the list of decks that each player has from the data gotten from input
     * @param decksIn the input data
     * @param player the player which prepare the decks for
     * @return the list of decks the player can use in the entire set of games
     */
    public ArrayList<Deck> prepareDecks(final DecksInput decksIn, final Player player) {
        ArrayList<Card> deck;
        ArrayList<Deck> playerDecks = new ArrayList<>();
        Deck oneDeck;
        CardCreator cardCreator = new CardCreator();

        int playerID = player.getPlayerId();
        int deckNumber = decksIn.getNrDecks();
        int cardNumber = decksIn.getNrCardsInDeck();

        for (int i = 0; i < deckNumber; i++) {
            deck = new ArrayList<>();
            for (int j = 0; j < cardNumber; j++) {
                deck.add(cardCreator.create(decksIn.getDecks().get(i).get(j), playerID));
            }
            oneDeck = new Deck(deck);
            playerDecks.add(oneDeck);
        }
        return playerDecks;
    }

    /**
     * Prepares the deck that the player wants to use for this specific game
     * @param decksInput the input data
     * @param player the player themselves
     * @param deckIndex the index of the deck they're planning to use
     * @return the Deck they want to use
     */
    public Deck prepareCurrentDeck(final DecksInput decksInput, final Player player,
                                   final int deckIndex) {
        ArrayList<Card> deck = new ArrayList<>();
        CardCreator cardCreator = new CardCreator();
        int playerID = player.getPlayerId();
        int cardNumber = decksInput.getNrCardsInDeck();

        for (int i = 0; i < cardNumber; i++) {
            deck.add(cardCreator.create(decksInput.getDecks().get(deckIndex).get(i), playerID));
        }
        return new Deck(deck);
    }

    /**
     * Prepares the player for a new game
     * @param player the player we're updating the information for
     * @param start the input variable we're using to get the HeroCard and the deck they're using
     *              for this game
     * @param decksInput the input variable where we get the decks from
     */
    public void preparePlayer(final Player player, final StartGameInput start,
                              final DecksInput decksInput) {
        int playerIdx = player.getPlayerId();
        Random rnd = new Random(start.getShuffleSeed());

        int deckIndex;
        CardCreator creator = new CardCreator();
        HeroCard playerHero;
        if (playerIdx == 1) {
            deckIndex = start.getPlayerOneDeckIdx();
            playerHero = creator.createHero(start.getPlayerOneHero(), playerIdx);
        } else {
            deckIndex = start.getPlayerTwoDeckIdx();
            playerHero = creator.createHero(start.getPlayerTwoHero(), playerIdx);
        }

        player.setCurrentDeck(prepareCurrentDeck(decksInput, player, deckIndex));
        Collections.shuffle(player.getCurrentDeck().getCards(), rnd);
        ArrayList<Card> newHand = new ArrayList<>();
        player.setHand(newHand);
        player.setHero(playerHero);
        player.getHand().add(player.getCurrentDeck().getCards().get(0));
        player.getCurrentDeck().getCards().remove(0);
        player.setFinishTurn(false);
        player.setMana(1);
    }
}
