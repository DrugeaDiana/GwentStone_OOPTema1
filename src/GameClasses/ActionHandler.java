package gameclasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;

public class ActionHandler {
    /**
     * @param action the action taken from the input
     * @param output the output where we write in the json file
     * @param player the player that does the action
     * @param game the base game variable
     */
    public void checkAction(final ActionsInput action, final ArrayNode output,
                             final Player player, final Game game) {
        Debug debugCommands = new Debug();
        GameCommands gameCommands = new GameCommands();
        String command = action.getCommand();
        switch (command) {
            case "getPlayerDeck" -> {
                debugCommands.getPlayerDeck(player, output);
            }
            case "getPlayerHero" -> {
                debugCommands.getPlayerHero(player, output);
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
            case "useHeroAbility" -> {
                gameCommands.useHeroAbility(game, player, action.getAffectedRow(), output);
            }
            case "getPlayerOneWins" -> {
                debugCommands.getPlayerOneWins(game, output);
            }
            case "getPlayerTwoWins" -> {
                debugCommands.getPlayerTwoWins(game, output);
            }
            case "getTotalGamesPlayed" -> {
                debugCommands.getTotalGamesPlayed(game, output);
            }
            default -> { }
        }
    }
}
