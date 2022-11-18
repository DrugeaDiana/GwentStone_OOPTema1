package gameclasses;

import cardsclasses.Card;
import cardsclasses.minionclasses.MinionCard;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import constants.CommandStrings;
import constants.ErrorStrings;
import fileio.ActionsInput;
import java.util.ArrayList;

public class Debug {
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Prints in the output the player's current deck
     * @param player the player
     * @param output the variable where we keep the output to later put in json file
     */
    public void getPlayerDeck(final Player player,
                               final ArrayNode output) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", CommandStrings.PLAYER_DECK);
        node1.put("playerIdx", player.getPlayerId());
        node1.set("output", mapper.convertValue(player.getCurrentDeck().getCards(),
                JsonNode.class));
        output.add(node1);
    }

    /**
     * Prints in the output information about the player's HeroCard
     * @param player the player we want to show information about
     * @param output where we store the output data
     */
    public void getPlayerHero(final Player player, final ArrayNode output) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", CommandStrings.PLAYER_HERO);
        node1.put("playerIdx", player.getPlayerId());
        node1.set("output", mapper.convertValue(player.getHero(), JsonNode.class));
        output.add(node1);
    }

    /**
     * Prints in the output the cards that are on the table at the time the function is called
     * @param game the base game variable
     * @param output the variable where we store the output data
     */
    public void getCardsOnTable(final Game game, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.CARDS_ON_TABLE);
        node.set("output", mapper.convertValue(game.getTable(), JsonNode.class));
        output.add(node);
    }

    /**
     * Prints in the output the ID of the player that's currently active
     * @param counter the number of turns spent
     * @param startingPlayer the player that starts the game
     * @param output variable where we save output data
     */
    public void getPlayerTurn(final int counter, final int startingPlayer,
                              final ArrayNode output) {
        int playerTurn;
        if (counter % 2 == 0) {
            if (startingPlayer == 1) {
                playerTurn = 2;
            } else {
                playerTurn = 1;
            }
        } else {
            playerTurn = startingPlayer;
        }
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", CommandStrings.PLAYER_TURN);
        node1.put("output", playerTurn);
        output.add(node1);
    }

    /**
     * Prints in the output how much mana the player has
     * @param player the player we want to get information from
     * @param output variable that stores output data
     */
    public void getPlayerMana(final Player player, final ArrayNode output) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", CommandStrings.MANA);
        node1.put("playerIdx", player.getPlayerId());
        node1.put("output", player.getMana());
        output.add(node1);
    }

    /**
     * Prints in the output the cards that the player has in their hand
     * @param player the player we want to get information from
     * @param output variable that stores output data
     */
    public void getPlayerHand(final Player player, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.CARDS_IN_HAND);
        node.put("playerIdx", player.getPlayerId());
        node.set("output", mapper.convertValue(player.getHand(), JsonNode.class));
        output.add(node);
    }

    /**
     * Prints in the output the Environment cards the player has in their hand
     * @param player the player we want to get information from
     * @param output variable that stores output data
     */
    public void getEnvironmentCardsInHand(final Player player, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.ENV_CARDS_IN_HAND);
        node.put("playerIdx", player.getPlayerId());
        ArrayList<Card> envCards = new ArrayList<>();
        for (Card card : player.getHand()) {
            if (card.getType().equals("Environment")) {
                envCards.add(card);
            }
        }
        node.set("output", mapper.convertValue(envCards, JsonNode.class));
        output.add(node);
    }

    /**
     * Prints in the output the card that can be found at the given coordinates on the table
     * @param game the base game variable
     * @param output the variable where we store output data
     * @param action the action variable (where we have coordinates saved)
     */
    public void getCardAtPosition(final Game game, final ArrayNode output,
                                  final ActionsInput action) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.CARD_AT_POSITION);
        int x = action.getX();
        int y = action.getY();
        node.put("x", x);
        node.put("y", y);
        if (game.getTable().size() > x) {
            if (game.getTable().get(x).size() > y) {
                node.set("output", mapper.convertValue(game.getTable().get(x).get(y),
                        JsonNode.class));
            } else {
                node.put("output", ErrorStrings.NO_CARD);
            }
        } else {
            node.put("output", ErrorStrings.NO_CARD);
        }

        output.add(node);
    }

    /**
     * Prints in the output all the frozen cards that are on the table
     * @param game the base game variable
     * @param output the variable where we store the output data
     */
    public void getFrozenCardsOnTable(final Game game, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.FROZEN_CARDS);
        ArrayList<MinionCard> frozenCards = new ArrayList<>();
        for (ArrayList<MinionCard> minionCards : game.getTable()) {
            for (MinionCard minion : minionCards) {
                if (minion.isFrozen()) {
                    frozenCards.add(minion);
                }
            }
        }
        node.set("output", mapper.convertValue(frozenCards, JsonNode.class));
        output.add(node);
    }

    /**
     * Prints in the output how many games did player1 win
     * @param game the base game variable
     * @param output the variable where we store the output data
     */
    public void getPlayerOneWins(final Game game, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.WINS_1);
        node.put("output", game.getPlayer1().getWins());
        output.add(node);
    }

    /**
     * Prints in the output how many games did player2 win
     * @param game the base game variable
     * @param output the variable where we store the output data
     */
    public void getPlayerTwoWins(final Game game, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.WINS_2);
        node.put("output", game.getPlayer2().getWins());
        output.add(node);
    }

    /**
     * Prints in the output how many games have been played so far
     * @param game the base game variable
     * @param output the variable where we store the output data
     */
    public void getTotalGamesPlayed(final Game game, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.ALL_GAMES);
        node.put("output", game.getGameNumbers());
        output.add(node);
    }

}
