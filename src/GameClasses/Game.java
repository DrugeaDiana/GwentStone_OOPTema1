package gameclasses;

import cardsclasses.minionclasses.MinionCard;
import com.fasterxml.jackson.databind.node.ArrayNode;
import constants.Constants;
import fileio.ActionsInput;
import fileio.Input;
import java.util.ArrayList;

public class Game {

    private final Player player1;
    private final Player player2;
    private int gameNumbers;
    private final ArrayNode output;
    private final Input inputData;
    private int startingPlayer;
    private int turnCounter;
    private boolean newTurn;
    private ArrayList<ArrayList<MinionCard>> table;

    /**
     * Getter for player1
     * @return player1 variable (data corresponding with the player with ID 1)
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Getter for player2
     * @return player2 variable (data corresponding with the player with ID 2)
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Getter for gameNumbers
     * @return the number of games played
     */
    public int getGameNumbers() {
        return gameNumbers;
    }

    /**
     * Setter for gameNumbers
     * @param gameNumbers the new value of the amount of games played (usually old value + 1)
     */
    public void setGameNumbers(final int gameNumbers) {
        this.gameNumbers = gameNumbers;
    }

    /**
     * Getter for startingPlayer
     * @return get the ID of the player that starts this game
     */
    public int getStartingPlayer() {
        return startingPlayer;
    }

    /**
     * Getter for turnCounter
     * @return how many turns have passed so far
     */
    public int getTurnCounter() {
        return turnCounter;
    }

    /**
     * Setter for turnCounter
     * @param turnCounter the new value of the amount of turns that passed so far (usually
     *                    old value + 1)
     */
    public void setTurnCounter(final int turnCounter) {
        this.turnCounter = turnCounter;
    }

    /**
     * Getter for table
     * @return the matrix with the cards placed on the table
     */
    public ArrayList<ArrayList<MinionCard>> getTable() {
        return table;
    }

    /**
     * Setter for newTurn
     * @param newTurn if it's a new turn or not
     */
    public void setNewTurn(final boolean newTurn) {
        this.newTurn = newTurn;
    }

    public Game(final Input inputData, final ArrayNode output) {
        this.output = output;
        this.inputData = inputData;
        player1 = new Player(1);
        player2 = new Player(2);
        PlayerPreparer prep = new PlayerPreparer();
        player1.setDecks(prep.prepareDecks(inputData.getPlayerOneDecks(), player1));
        gameNumbers = 0;
        player2.setDecks(prep.prepareDecks(inputData.getPlayerTwoDecks(), player2));
    }

    /**
     * Initializes the matrix used to store the cards placed on the table
     */
    public void tableInit() {
        table = new ArrayList<>();
        ArrayList<MinionCard> row1 = new ArrayList<>();
        ArrayList<MinionCard> row2 = new ArrayList<>();
        ArrayList<MinionCard> row3 = new ArrayList<>();
        ArrayList<MinionCard> row4 = new ArrayList<>();
        table.add(row1);
        table.add(row2);
        table.add(row3);
        table.add(row4);
    }

    /**
     * The main logic of a game
     * @param gameIndex the index of the game in the Input.games
     */
    public void playGame(final int gameIndex) {
        tableInit();
        newTurn = false;
        PlayerPreparer prep = new PlayerPreparer();
        prep.preparePlayer(player1, inputData.getGames().get(gameIndex).getStartGame(),
                inputData.getPlayerOneDecks());
        prep.preparePlayer(player2, inputData.getGames().get(gameIndex).getStartGame(),
                inputData.getPlayerTwoDecks());
        startingPlayer = inputData.getGames().get(gameIndex).getStartGame().getStartingPlayer();

        int counter;
        turnCounter = 1;
        ActionHandler handler = new ActionHandler();
        ArrayList<ActionsInput> actions = inputData.getGames().get(gameIndex).getActions();

        int playerIdx;
        int pastPlayer = startingPlayer;
        int roundCounter = 1;
        for (counter = 0; counter < inputData.getGames().get(gameIndex).getActions().size();
             counter++) {
            if (newTurn) {
                if (player1.isFinishTurn() && player2.isFinishTurn()) {
                    if (player2.getCurrentDeck().getCards().size() > 0) {
                        player2.getHand().add(player2.getCurrentDeck().getCards().get(0));
                        player2.getCurrentDeck().getCards().remove(0);
                    }
                    if (player1.getCurrentDeck().getCards().size() > 0) {
                        player1.getHand().add(player1.getCurrentDeck().getCards().get(0));
                        player1.getCurrentDeck().getCards().remove(0);
                    }

                    roundCounter++;
                    if (roundCounter < Constants.MAX_MANA_PER_TURN) {
                        player1.setMana(player1.getMana() + roundCounter);
                        player2.setMana(player2.getMana() + roundCounter);
                    } else {
                        player1.setMana(player1.getMana() + Constants.MAX_MANA_PER_TURN);
                        player2.setMana(player2.getMana() + Constants.MAX_MANA_PER_TURN);
                    }

                    player1.setFinishTurn(false);
                    player2.setFinishTurn(false);
                    for (ArrayList<MinionCard> minionCards : table) {
                        for (MinionCard minion : minionCards) {
                            minion.setAttackedTurn(false);
                        }
                    }
                    player1.getHero().setAttackTurn(false);
                    player2.getHero().setAttackTurn(false);
                }

                for (ArrayList<MinionCard> minionCards : table) {
                    for (MinionCard minion : minionCards) {
                        if (turnCounter - minion.getFrozenTurn() == 2) {
                            minion.setFrozen(false);
                        }
                    }
                }
                if (pastPlayer == 1) {
                    pastPlayer = 2;
                } else {
                    pastPlayer = 1;
                }
                newTurn = false;
            }
            playerIdx = actions.get(counter).getPlayerIdx();
            if (playerIdx == 0) {
                playerIdx = pastPlayer;
            }

            if (playerIdx == 1) {
                handler.checkAction(actions.get(counter), output, this.getPlayer1(), this);
            } else {
                handler.checkAction(actions.get(counter), output, this.getPlayer2(), this);
            }
        }
    }
}
