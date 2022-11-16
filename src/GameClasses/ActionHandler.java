package gameclasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;

import java.io.IOException;

public class ActionHandler {

    public void checkAction (final ActionsInput action, final ArrayNode output,
                             final Player player, final Game game) throws IOException {
        int playerIndex = player.getPlayerId();
        Debug debugCommands = new Debug();
        GameCommands gameCommands = new GameCommands();
        String command = action.getCommand();
        switch (command) {
            case "getPlayerDeck" -> {
                debugCommands.getPlayerDeck(playerIndex, player, output);
            }
            case "getPlayerHero" -> {
                debugCommands.getPlayerHero(playerIndex, player, output);
            }
            case "getPlayerTurn" -> {
                debugCommands.getPlayerTurn(game.getTurnCounter(), game.getStartingPlayer(),
                                            output);
            }
            case "endPlayerTurn" -> {
                gameCommands.endPlayerTurn(game, player);
            }
            case "placeCard" -> {
                gameCommands.placeCard(game, player, action.getHandIdx(), output);
            }
            case "getPlayerMana" -> {
                debugCommands.getPlayerMana(player, output);
            }
            case "getCardsInHand" -> {
                debugCommands.getPlayerHand(player, output);
            }
            case "getCardsOnTable" -> {
                debugCommands.getCardsOnTable(game, output);
            }
            case "useEnvironmentCard" -> {
                gameCommands.useEnvironmentCard(game, player, action.getHandIdx(),
                        action.getAffectedRow(), output);
            }
            case "getEnvironmentCardsInHand" -> {
                debugCommands.getEnvironmentCardsInHand(player, output);
            }
            case "getCardAtPosition" -> {
                debugCommands.getCardAtPosition(game, output, action);
            }
            case "getFrozenCardsOnTable" -> {
                debugCommands.getFrozenCardsOnTable(game, output);
            }
            case "cardUsesAttack" -> {
                gameCommands.cardAttack(game, action, output);
            }
            case "cardUsesAbility" -> {
                gameCommands.cardUsesAbility(game, action, output);
            }
            case "useAttackHero" -> {
                gameCommands.useAttackHero(game, action, player, output);
            }
            default -> {}
        }
    }
}
