package GameClasses;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class Debug {
    public void getPlayerDeck (int playerIdx, Player player, ArrayNode output) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        //System.out.println("ayo");
        //System.out.println(player.getCurrentDeck());
        node1.put("command", "getPlayerDeck");
        node1.put("playerIdx", playerIdx);
        node1.set("output", mapper.convertValue(player.getCurrentDeck().getCards(), JsonNode.class));
        output.add(node1);
    }

    public void getHero (int playerIdx, Player player, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerHero");
        node1.put("playerIdx", playerIdx);
        node1.set("output", mapper.convertValue(player.getHero(), JsonNode.class));
        output.add(node1);
    }

    public void getPlayerTurn(int counter, int startingPlayer, ArrayNode output) {
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
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerTurn");
        node1.put("output", playerTurn);
        output.add(node1);
    }
}
