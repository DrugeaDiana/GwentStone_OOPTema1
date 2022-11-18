package gameclasses;

import cardsclasses.envclasses.EnvironmentCard;
import cardsclasses.heroclasses.HeroCard;
import cardsclasses.minionclasses.MinionCard;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import fileio.Coordinates;

import java.util.ArrayList;

public class GameCommands {
    private ObjectMapper mapper = new ObjectMapper();

    public void endPlayerTurn(Game game, Player player){
        game.setTurnCounter(game.getTurnCounter()+ 1);
        game.setNewturn(true);
        player.setFinishTurn(true);
    }

    public void placeCard(Game game, Player player, int handIdx, ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        if (player.getHand().get(handIdx).getType().equals("Minion")) {
            MinionCard minion = (MinionCard) player.getHand().get(handIdx);
            int rowId = minion.getRow();

            if (game.getTable().get(rowId).size() < 5) {
                if (player.getMana() >= minion.getMana()) {
                    game.getTable().get(rowId).add(minion);
                    player.getHand().remove(handIdx);
                    player.setMana(player.getMana() - minion.getMana());
                } else {
                    node.put("command", "placeCard");
                    node.put("handIdx", handIdx);
                    node.put("error", "Not enough mana to place card on table.");
                    output.add(node);
                }
            } else {
                node.put("command", "placeCard");
                node.put("handIdx", handIdx);
                node.put("error", "Cannot place card on table since row is full.");
                output.add(node);
            }
        } else {
            node.put("command", "placeCard");
            node.put("handIdx", handIdx);
            node.put("error", "Cannot place environment card on table.");
            output.add(node);
        }
    }

    public void useEnvironmentCard(Game game, Player player, int handIdx, int targetRow, ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "useEnvironmentCard");
        node.put("handIdx", handIdx);
        node.put("affectedRow", targetRow);
        if (player.getHand().get(handIdx).getType().equals("Environment")) {
            EnvironmentCard environmentCard = (EnvironmentCard) player.getHand().get(handIdx);
            if (player.getMana() >= environmentCard.getMana()) {
                int answer = environmentCard.ability(targetRow, game);
                switch (answer) {
                    case -1 -> {
                        node.put("error", "Chosen row does not belong to the enemy.");
                        output.add(node);
                    }
                    case -2 -> {
                        node.put("error", "Cannot steal enemy card since the player's row is full.");
                        output.add(node);
                    }
                    case 0 -> {
                        player.setMana(player.getMana() - environmentCard.getMana());
                        player.getHand().remove(environmentCard);
                    }
                }
            } else {
                node.put("error", "Not enough mana to use environment card.");
                output.add(node);
            }

        } else {
            node.put("error", "Chosen card is not of type environment.");
            output.add(node);
        }
    }

    public void cardAttack(Game game, ActionsInput action, ArrayNode output) {
        Coordinates enemyCoord = action.getCardAttacked();
        MinionCard enemy = game.getTable().get(enemyCoord.getX()).get(enemyCoord.getY());
        Coordinates cardCoord = action.getCardAttacker();
        MinionCard atacker = game.getTable().get(cardCoord.getX()).get(cardCoord.getY());
        int id = enemy.getPlayerID();
        boolean existingTanks = existingTank(game, id);
        int answer = atacker.attack(enemy, existingTanks);

        ObjectNode node = mapper.createObjectNode();
        node.put("command", "cardUsesAttack");
        node.set("cardAttacker", mapper.convertValue(cardCoord, JsonNode.class));
        node.set("cardAttacked", mapper.convertValue(enemyCoord, JsonNode.class));
        switch (answer) {
            case -1 -> {
                node.put("error", "Attacked card does not belong to the enemy.");
                output.add(node);
            }
            case -2 -> {
                node.put("error", "Attacker card is frozen.");
                output.add(node);

            }
            case -3 -> {
                node.put("error", "Attacked card is not of type 'Tank'.");
                output.add(node);
            }
            case -4 -> {
                node.put("error", "Attacker card has already attacked this turn.");
                output.add(node);
            }
            case 0 -> {
                if (enemy.isDead()) {
                    game.getTable().get(enemyCoord.getX()).remove(enemyCoord.getY());
                }
            }
        }
    }

    public boolean existingTank(Game game, int id) {

        boolean existingTanks = false;
        if (id == 1) {
            for (int i = 2; i < 4; i++) {
                for (MinionCard minion : game.getTable().get(i)) {
                    if (minion.isTank()) {
                        existingTanks = true;
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < 2; i++) {
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

    public void cardUsesAbility(Game game, ActionsInput action, ArrayNode output) {
        Coordinates enemyCoord = action.getCardAttacked();
        MinionCard enemy = game.getTable().get(enemyCoord.getX()).get(enemyCoord.getY());
        Coordinates cardCoord = action.getCardAttacker();
        MinionCard atacker = game.getTable().get(cardCoord.getX()).get(cardCoord.getY());
        int id = enemy.getPlayerID();
        boolean existingTanks = existingTank(game, id);
        int answer = atacker.ability(enemy, existingTanks);

        ObjectNode node = mapper.createObjectNode();
        node.put("command", "cardUsesAbility");
        node.set("cardAttacker", mapper.convertValue(cardCoord, JsonNode.class));
        node.set("cardAttacked", mapper.convertValue(enemyCoord, JsonNode.class));
        switch (answer) {
            case -1 -> {
                if (atacker.getName().equals("Disciple")) {
                    node.put("error", "Attacked card does not belong to the current player.");
                } else {
                    node.put("error", "Attacked card does not belong to the enemy.");
                }
                output.add(node);
            }
            case -2 -> {
                node.put("error", "Attacker card is frozen.");
                output.add(node);
            }
            case -3 -> {
                node.put("error", "Attacked card is not of type 'Tank'.");
                output.add(node);
            }
            case -4 -> {
                node.put("error", "Attacker card has already attacked this turn.");
                output.add(node);
            }
            case 0 -> {
                if (atacker.getName().equals("The Cursed One")) {
                    if (enemy.isDead()) {
                        game.getTable().get(enemyCoord.getX()).remove(enemyCoord.getY());
                    }
                }
            }
            default -> {}
        }
    }

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
        node1.put("command", "useAttackHero");
        node1.set("cardAttacker", mapper.convertValue(cardCoord, JsonNode.class));

        switch (answer) {
            case -2 -> {
                node1.put("error", "Attacker card is frozen.");
                output.add(node1);

            }
            case -3 -> {
                node1.put("error", "Attacked card is not of type 'Tank'.");
                output.add(node1);
            }
            case -4 -> {
                node1.put("error", "Attacker card has already attacked this turn.");
                output.add(node1);
            }
            case 0 -> {
                if(enemy.getHealth() <= 0) {
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
            default -> {}
        }

    }

    public void useHeroAbility(Game game, Player player, int targetRow, ArrayNode output) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", "useHeroAbility");
        node.put("affectedRow", targetRow);
        HeroCard playerHero = player.getHero();
        if(player.getMana() >= playerHero.getMana()) {
            int answer = playerHero.ability(targetRow, game);
            switch (answer) {
                case -1 -> {
                    node.put("error", "Selected row does not belong to the enemy.");
                    output.add(node);
                }
                case -2 -> {
                    node.put("error", "Selected row does not belong to the current player.");
                    output.add(node);
                }
                case -3 -> {
                    node.put("error", "Hero has already attacked this turn.");
                    output.add(node);
                }
                case 0 -> {
                    player.setMana(player.getMana() - playerHero.getMana());
                }
            }

        } else {
            node.put("error", "Not enough mana to use hero's ability.");
            output.add(node);
        }
    }
}
