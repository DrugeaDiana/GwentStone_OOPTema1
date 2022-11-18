package gameclasses;

import cardsclasses.envclasses.EnvironmentCard;
import cardsclasses.heroclasses.HeroCard;
import cardsclasses.minionclasses.MinionCard;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import constants.CommandStrings;
import constants.Constants;
import constants.ErrorStrings;
import fileio.ActionsInput;
import fileio.Coordinates;

public class GameCommands {
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Ends the turn of a player
     * @param game the base game variable
     * @param player the player who finished their turn
     */
    public void endPlayerTurn(final Game game, final Player player) {
        game.setTurnCounter(game.getTurnCounter() + 1);
        game.setNewTurn(true);
        player.setFinishTurn(true);
    }

    /**
     * Places a MinionCard from the hand of the player on the table
     * @param game the base game variable
     * @param player the player that's doing the action
     * @param handIdx the index of the card that they want to place
     * @param output the output where we save the data to later on put in the json file
     */
    public void placeCard(final Game game, final Player player,
                          final int handIdx, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.PLACE_CARD);
        node.put("handIdx", handIdx);
        if (player.getHand().get(handIdx).getType().equals("Minion")) {
            MinionCard minion = (MinionCard) player.getHand().get(handIdx);
            int rowId = minion.getRow();
            if (game.getTable().get(rowId).size() <= Constants.MAX_NR_OF_CARDS) {
                if (player.getMana() >= minion.getMana()) {
                    game.getTable().get(rowId).add(minion);
                    player.getHand().remove(handIdx);
                    player.setMana(player.getMana() - minion.getMana());
                } else {
                    node.put("error", ErrorStrings.NO_MANA_MINION);
                    output.add(node);
                }
            } else {
                node.put("error", ErrorStrings.FULL_ROW);
                output.add(node);
            }
        } else {
            node.put("error", ErrorStrings.NO_PLACING_ENV);
            output.add(node);
        }
    }

    /**
     * Uses an Environment Card from the hand of the player
     * @param game the base game variable
     * @param player the player that does the action
     * @param handIdx the index of the card the player wants to use
     * @param targetRow the row they're targeting
     * @param output the variable we save the output data
     */
    public void useEnvironmentCard(final Game game, final Player player, final int handIdx,
                                   final int targetRow, final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.ENV_CARD);
        node.put("handIdx", handIdx);
        node.put("affectedRow", targetRow);
        if (player.getHand().get(handIdx).getType().equals("Environment")) {
            EnvironmentCard environmentCard = (EnvironmentCard) player.getHand().get(handIdx);
            if (player.getMana() >= environmentCard.getMana()) {
                int answer = environmentCard.ability(targetRow, game);
                switch (answer) {
                    case Constants.ERROR_MINUS_1 -> {
                        node.put("error", ErrorStrings.NOT_ENEMY_ROW);
                        output.add(node);
                    }
                    case Constants.ERROR_MINUS_2 -> {
                        node.put("error", ErrorStrings.NO_STEAL);
                        output.add(node);
                    }
                    case 0 -> {
                        player.setMana(player.getMana() - environmentCard.getMana());
                        player.getHand().remove(environmentCard);
                    }
                    default -> {
                    }
                }
            } else {
                node.put("error", ErrorStrings.NO_MANA_ENV);
                output.add(node);
            }

        } else {
            node.put("error", ErrorStrings.NOT_ENV);
            output.add(node);
        }
    }

    /**
     * Tells a card on the table that belongs to the player to attack another card that
     * belongs to the enemy
     * @param game the base game variable
     * @param action the ActionsInput variable where we get the coordinates of the cards
     *               on the table
     * @param output the variable where we save the data for the output
     */
    public void cardAttack(final Game game, final ActionsInput action, final ArrayNode output) {
        Coordinates enemyCoord = action.getCardAttacked();
        MinionCard enemy = game.getTable().get(enemyCoord.getX()).get(enemyCoord.getY());
        Coordinates cardCoord = action.getCardAttacker();
        MinionCard atacker = game.getTable().get(cardCoord.getX()).get(cardCoord.getY());
        int id = enemy.getPlayerID();
        boolean existingTanks = existingTank(game, id);
        int answer = atacker.attack(enemy, existingTanks);

        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.ATTACK);
        node.set("cardAttacker", mapper.convertValue(cardCoord, JsonNode.class));
        node.set("cardAttacked", mapper.convertValue(enemyCoord, JsonNode.class));
        switch (answer) {
            case Constants.ERROR_MINUS_1 -> {
                node.put("error", ErrorStrings.NOT_ENEMY);
                output.add(node);
            }
            case Constants.ERROR_MINUS_2 -> {
                node.put("error", ErrorStrings.FROZEN);
                output.add(node);

            }
            case Constants.ERROR_MINUS_3 -> {
                node.put("error", ErrorStrings.NOT_TANK);
                output.add(node);
            }
            case Constants.ERROR_MINUS_4 -> {
                node.put("error", ErrorStrings.DOUBLE_ACTION);
                output.add(node);
            }
            case 0 -> {
                if (enemy.isDead()) {
                    game.getTable().get(enemyCoord.getX()).remove(enemyCoord.getY());
                }
            }
            default -> { }
        }
    }

    /**
     * Checks if there's a tank on the enemy's rows
     * @param game the base game variable
     * @param id the ID of the enemy
     * @return wether or not there's a tank on the enemy's rows
     */
    public boolean existingTank(final Game game, final int id) {
        boolean existingTanks = false;
        if (id == 1) {
            for (int i = Constants.MIN_ROW_PLAYER_1 + 1; i < Constants.MAX_ROW_PLAYER_1; i++) {
                for (MinionCard minion : game.getTable().get(i)) {
                    if (minion.isTank()) {
                        existingTanks = true;
                        break;
                    }
                }
            }
        } else {
            for (int i = Constants.MIN_ROW_NR_PLAYER_2 + 1; i < Constants.MAX_ROW_NR_PLAYER_2;
                 i++) {
                for (MinionCard minion : game.getTable().get(i)) {
                    if (minion.isTank()) {
                        existingTanks = true;
                        break;
                    }
                }
            }
        }
        return existingTanks;
    }

    /**
     * Tells a card that the player controls to use their ability
     * @param game the base game variable
     * @param action the ActionsInput variable from where we get the coordinates needed
     * @param output the variable we save the data for the output
     */
    public void cardUsesAbility(final Game game, final ActionsInput action,
                                final ArrayNode output) {
        Coordinates enemyCoord = action.getCardAttacked();
        MinionCard enemy = game.getTable().get(enemyCoord.getX()).get(enemyCoord.getY());
        Coordinates cardCoord = action.getCardAttacker();
        MinionCard atacker = game.getTable().get(cardCoord.getX()).get(cardCoord.getY());
        int id = enemy.getPlayerID();
        boolean existingTanks = existingTank(game, id);
        int answer = atacker.ability(enemy, existingTanks);

        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.ABILITY);
        node.set("cardAttacker", mapper.convertValue(cardCoord, JsonNode.class));
        node.set("cardAttacked", mapper.convertValue(enemyCoord, JsonNode.class));
        switch (answer) {
            case Constants.ERROR_MINUS_1 -> {
                if (atacker.getName().equals("Disciple")) {
                    node.put("error", ErrorStrings.NOT_FRIENDLY);
                } else {
                    node.put("error", ErrorStrings.NOT_ENEMY);
                }
                output.add(node);
            }
            case Constants.ERROR_MINUS_2 -> {
                node.put("error", ErrorStrings.FROZEN);
                output.add(node);
            }
            case Constants.ERROR_MINUS_3 -> {
                node.put("error", ErrorStrings.NOT_TANK);
                output.add(node);
            }
            case Constants.ERROR_MINUS_4 -> {
                node.put("error", ErrorStrings.DOUBLE_ACTION);
                output.add(node);
            }
            case 0 -> {
                if (atacker.getName().equals("The Cursed One")) {
                    if (enemy.isDead()) {
                        game.getTable().get(enemyCoord.getX()).remove(enemyCoord.getY());
                    }
                }
            }
            default -> { }
        }
    }

    /**
     * Tells a MinionCard on the table to attack the enemy's HeroCard
     * @param game the base game variable
     * @param action the ActionsInput variable from where we get the coordinates needed
     * @param player the player that controls the HeroCard
     * @param output the variable where we save the data for output
     */
    public void useAttackHero(final Game game, final ActionsInput action, final Player player,
                              final ArrayNode output) {
        HeroCard enemy;
        int id;
        if (player.getPlayerId() == 1) {
            enemy = game.getPlayer2().getHero();
            id = 2;
        } else {
            enemy = game.getPlayer1().getHero();
            id = 1;
        }

        Coordinates cardCoord = action.getCardAttacker();
        MinionCard atacker = game.getTable().get(cardCoord.getX()).get(cardCoord.getY());
        boolean existingTanks = existingTank(game, id);
        int answer = atacker.attackHero(enemy, existingTanks);

        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", CommandStrings.ATTACK_A_HERO);
        node1.set("cardAttacker", mapper.convertValue(cardCoord, JsonNode.class));

        switch (answer) {
            case Constants.ERROR_MINUS_2 -> {
                node1.put("error", ErrorStrings.FROZEN);
                output.add(node1);
            }
            case Constants.ERROR_MINUS_3 -> {
                node1.put("error", ErrorStrings.NOT_TANK);
                output.add(node1);
            }
            case Constants.ERROR_MINUS_4 -> {
                node1.put("error", ErrorStrings.DOUBLE_ACTION);
                output.add(node1);
            }
            case 0 -> {
                if (enemy.getHealth() <= 0) {
                    ObjectNode node2 = mapper.createObjectNode();
                    if (id == 1) {
                        node2.put("gameEnded", "Player two killed the enemy hero.");
                    } else {
                        node2.put("gameEnded", "Player one killed the enemy hero.");
                    }
                    player.setWins(player.getWins() + 1);
                    game.setGameNumbers(game.getGameNumbers() + 1);
                    output.add(node2);
                }
            }
            default -> { }
        }
    }

    /**
     * Tells the player's HeroCard to use their specific ability
     * @param game the base game variable
     * @param player the player that uses the HeroCard
     * @param targetRow the row where they want to use the ability
     * @param output the variable where we save the data for output
     */
    public void useHeroAbility(final Game game, final Player player, final int targetRow,
                               final ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", CommandStrings.HERO_ABILITY);
        node.put("affectedRow", targetRow);
        HeroCard playerHero = player.getHero();
        if (player.getMana() >= playerHero.getMana()) {
            int answer = playerHero.ability(targetRow, game);
            switch (answer) {
                case Constants.ERROR_MINUS_1 -> {
                    node.put("error", ErrorStrings.NOT_ENEMY_ROW_HERO);
                    output.add(node);
                }
                case Constants.ERROR_MINUS_2 -> {
                    node.put("error", ErrorStrings.NOT_FRIENDLY_ROW_HERO);
                    output.add(node);
                }
                case Constants.ERROR_MINUS_3 -> {
                    node.put("error", ErrorStrings.DOUBLE_ACTION_HERO);
                    output.add(node);
                }
                case 0 ->
                    player.setMana(player.getMana() - playerHero.getMana());
                default -> { }
            }

        } else {
            node.put("error", ErrorStrings.NO_MANA_HERO);
            output.add(node);
        }
    }
}
