package gameclasses;

import cardsclasses.Card;
import cardsclasses.minionclasses.MinionCard;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;

import java.io.IOException;
import java.util.ArrayList;

public class Debug {
    private final ObjectMapper mapper = new ObjectMapper();
    public void getPlayerDeck (final int playerIdx, final Player player,
                               final ArrayNode output) throws IOException {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerDeck");
        node1.put("playerIdx", playerIdx);
        node1.set("output", mapper.convertValue(player.getCurrentDeck().getCards(),
                JsonNode.class));
        output.add(node1);
    }

    public void getPlayerHero (final int playerIdx, final Player player, final ArrayNode output) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerHero");
        node1.put("playerIdx", playerIdx);
        node1.set("output", mapper.convertValue(player.getHero(), JsonNode.class));
        output.add(node1);
    }

    public void getCardsOnTable(Game game, ArrayNode output) {

        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getCardsOnTable");
        node.set("output", mapper.convertValue(game.getTable(), JsonNode.class));
        output.add(node);
    }

    public void getPlayerTurn(final int counter, final int startingPlayer,
                              final ArrayNode output) {
        int playerTurn;
        if (counter % 2 == 0) {
            if(startingPlayer == 1) {
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

    public void getPlayerMana(final Player player, final ArrayNode output){
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerMana");
        node1.put("playerIdx", player.getPlayerId());
        node1.put("output", player.getMana());
        output.add(node1);
    }

    public void getPlayerHand(final Player player, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getCardsInHand");
        node.put("playerIdx", player.getPlayerId());
        node.set("output", mapper.convertValue(player.getHand(), JsonNode.class));
        output.add(node);
    }

    public void getEnvironmentCardsInHand(final Player player, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getEnvironmentCardsInHand");
        node.put("playerIdx", player.getPlayerId());
        ArrayList<Card> envCards = new ArrayList<>();
        for(Card card : player.getHand()){
            if (card.getType().equals("Environment")) {
                envCards.add(card);
            }
        }
        node.set("output", mapper.convertValue(envCards, JsonNode.class));
        output.add(node);
    }

    public void getCardAtPosition(final Game game, final ArrayNode output,
                                  final ActionsInput action) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "getCardAtPosition");
        int x = action.getX();
        int y = action.getY();
        node.set("output", mapper.convertValue(game.getTable().get(x).get(y), JsonNode.class));
        output.add(node);
    }

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

}
