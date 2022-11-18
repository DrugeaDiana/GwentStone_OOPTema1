package gameclasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import constants.CommandStrings;
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
            case CommandStrings.PLAYER_DECK ->
                debugCommands.getPlayerDeck(player, output);
            case CommandStrings.PLAYER_HERO ->
                debugCommands.getPlayerHero(player, output);
            case CommandStrings.PLAYER_TURN ->
                debugCommands.getPlayerTurn(game.getTurnCounter(), game.getStartingPlayer(),
                                            output);
            case CommandStrings.END_TURN ->
                gameCommands.endPlayerTurn(game, player);
            case CommandStrings.PLACE_CARD ->
                gameCommands.placeCard(game, player, action.getHandIdx(), output);
            case CommandStrings.MANA ->
                debugCommands.getPlayerMana(player, output);
            case CommandStrings.CARDS_IN_HAND ->
                debugCommands.getPlayerHand(player, output);
            case CommandStrings.CARDS_ON_TABLE ->
                debugCommands.getCardsOnTable(game, output);
            case CommandStrings.ENV_CARD ->
                gameCommands.useEnvironmentCard(game, player, action.getHandIdx(),
                        action.getAffectedRow(), output);
            case CommandStrings.ENV_CARDS_IN_HAND ->
                debugCommands.getEnvironmentCardsInHand(player, output);
            case CommandStrings.CARD_AT_POSITION ->
                debugCommands.getCardAtPosition(game, output, action);
            case CommandStrings.FROZEN_CARDS ->
                debugCommands.getFrozenCardsOnTable(game, output);
            case CommandStrings.ATTACK ->
                gameCommands.cardAttack(game, action, output);
            case CommandStrings.ABILITY ->
                gameCommands.cardUsesAbility(game, action, output);
            case CommandStrings.ATTACK_A_HERO ->
                gameCommands.useAttackHero(game, action, player, output);
            case CommandStrings.HERO_ABILITY ->
                gameCommands.useHeroAbility(game, player, action.getAffectedRow(), output);
            case CommandStrings.WINS_1 ->
                debugCommands.getPlayerOneWins(game, output);
            case CommandStrings.WINS_2 ->
                debugCommands.getPlayerTwoWins(game, output);
            case CommandStrings.ALL_GAMES ->
                debugCommands.getTotalGamesPlayed(game, output);
            default -> { }
        }
    }
}
