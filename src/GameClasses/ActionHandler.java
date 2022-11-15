package GameClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;

import java.io.IOException;
import java.util.ArrayList;

public class ActionHandler {

    public void checkAction (ActionsInput action, ArrayNode output, Player player, int turnCounter, int startingPlayer) throws IOException {
        int playerIndex = player.getPlayerId();
        Debug debugCommands = new Debug();
       // System.out.println(action.getCommand());
       // System.out.println("testing the rest of shit");
        String command = action.getCommand();
        switch (command) {
            case "getPlayerDeck" -> {
                //System.out.println("o comanda");
                debugCommands.getPlayerDeck(playerIndex, player, output);
            }
            case "getPlayerHero" -> {
                //System.out.println("a doua comanda");
                debugCommands.getHero(playerIndex, player, output);
            }
            case "getPlayerTurn" -> {
                //System.out.println("a treia comanda");
                debugCommands.getPlayerTurn(turnCounter, startingPlayer, output);
            }
            //default -> {
                //System.out.println("something happened");
            //}
        }



    }

}
