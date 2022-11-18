package gameclasses;

import cardsclasses.Card;
import cardsclasses.minionclasses.MinionCard;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import java.util.ArrayList;

public class Debug {
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * @param player the player
     * @param output the variable where we keep the output to later put in json file
     */
    public void getPlayerDeck(final Player player,
                               final ArrayNode output) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerDeck");
        node1.put("playerIdx", player.getPlayerId());
        node1.set("output", mapper.convertValue(player.getCurrentDeck().getCards(),
                JsonNode.class));
        output.add(node1);
    }

    /**
     * @param player the player we want to show information about
     * @param output where we store the output data
     */
    public void getPlayerHero(final Player player, final ArrayNode output) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerHero");
        node1.put("playerIdx", player.getPlayerId());
        node1.set("output", mapper.convertValue(player.getHero(), JsonNode.class));
        output.add(node1);
    }

    /**
     * @param game the base game variable
     * @param output the variable where we store the output data
     */
    public void getCardsOnTable(final Game game, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getCardsOnTable");
        node.set("output", mapper.convertValue(game.getTable(), JsonNode.class));
        output.add(node);
    }

    /**
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
        node1.put("command", "getPlayerTurn");
        node1.put("output", playerTurn);
        output.add(node1);
    }

    /**
     * @param player the player we want to get information from
     * @param output variable that stores output data
     */
    public void getPlayerMana(final Player player, final ArrayNode output) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerMana");
        node1.put("playerIdx", player.getPlayerId());
        node1.put("output", player.getMana());
        output.add(node1);
    }

    /**
     * @param player the player we want to get information from
     * @param output variable that stores output data
     */
    public void getPlayerHand(final Player player, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getCardsInHand");
        node.put("playerIdx", player.getPlayerId());
        node.set("output", mapper.convertValue(player.getHand(), JsonNode.class));
        output.add(node);
    }

    /**
     * @param player the player we want to get information from
     * @param output variable that stores output data
     */
    public void getEnvironmentCardsInHand(final Player player, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getEnvironmentCardsInHand");
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
     * @param game the base game variable
     * @param output the variable where we store output data
     * @param action the action variable (where we have coordinates saved)
     */
    public void getCardAtPosition(final Game game, final ArrayNode output,
                                  final ActionsInput action) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getCardAtPosition");
        int x = action.getX();
        int y = action.getY();
        node.set("output", mapper.convertValue(game.getTable().get(x).get(y), JsonNode.class));
        output.add(node);
    }

    /**
     * @param game the base game variable
     * @param output the variable where we store the output data
     */
    public void getFrozenCardsOnTable(final Game game, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getFrozenCardsOnTable");
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
     * @param game the base game variable
     * @param output the variable where we store the output data
     */
    public void getPlayerOneWins(final Game game, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getPlayerOneWins");
        node.put("output", game.getPlayer1().getWins());
        output.add(node);
    }

    /**
     * @param game the base game variable
     * @param output the variable where we store the output data
     */
    public void getPlayerTwoWins(final Game game, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getPlayerTwoWins");
        node.put("output", game.getPlayer2().getWins());
        output.add(node);
    }

    /**
     * @param game the base game variable
     * @param output the variable where we store the output data
     */
    public void getTotalGamesPlayed(final Game game, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getTotalGamesPlayed");
        node.put("output", game.getGameNumbers());
        output.add(node);
    }

}
