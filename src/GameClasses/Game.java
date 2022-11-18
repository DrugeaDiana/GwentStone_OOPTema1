package gameclasses;

import cardsclasses.minionclasses.MinionCard;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
    private boolean newturn;
    private ArrayList<ArrayList<MinionCard>> table;

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getGameNumbers() {
        return gameNumbers;
    }

    public void setGameNumbers(final int gameNumbers) {
        this.gameNumbers = gameNumbers;
    }

    public int getStartingPlayer() {
        return startingPlayer;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setTurnCounter(final int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public ArrayList<ArrayList<MinionCard>> getTable() {
        return table;
    }

    public void setNewturn(final boolean newturn) {
        this.newturn = newturn;
    }

    public Game (final Input inputData, final ArrayNode output){
        this.output = output;
        this.inputData = inputData;
        player1 = new Player(1);
        player2 = new Player(2);
        PlayerPreparer prep = new PlayerPreparer();
        player1.setDecks(prep.prepareDecks(inputData.getPlayerOneDecks(), player1));
        gameNumbers = 0;
        player2.setDecks(prep.prepareDecks(inputData.getPlayerTwoDecks(), player2));
    }

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

    public void playGame (final int game_index) {
        tableInit();
        newturn = false;
        PlayerPreparer prep = new PlayerPreparer();
        prep.preparePlayer(player1, inputData.getGames().get(game_index).getStartGame(),
                inputData.getPlayerOneDecks());
        prep.preparePlayer(player2, inputData.getGames().get(game_index).getStartGame(),
                inputData.getPlayerTwoDecks());
        startingPlayer = inputData.getGames().get(game_index).getStartGame().getStartingPlayer();

        int counter;
        turnCounter = 1;
        ActionHandler handler = new ActionHandler();
        ArrayList<ActionsInput> actions = inputData.getGames().get(game_index).getActions();

        int playerIdx;
        int pastPlayer = startingPlayer;
        int roundCounter = 1;
        for( counter = 0; counter < inputData.getGames().get(game_index).getActions().size(); counter++){
            if(newturn) {
                if(player1.isFinishTurn() && player2.isFinishTurn()) {
                    if (player2.getCurrentDeck().getCards().size() > 0) {
                        player2.getHand().add(player2.getCurrentDeck().getCards().get(0));
                        player2.getCurrentDeck().getCards().remove(0);
                    }

                    if (player1.getCurrentDeck().getCards().size() > 0) {
                        player1.getHand().add(player1.getCurrentDeck().getCards().get(0));
                        player1.getCurrentDeck().getCards().remove(0);
                    }
                    roundCounter++;
                    if (roundCounter <= 9) {
                        player1.setMana(player1.getMana() + roundCounter);
                        player2.setMana(player2.getMana() + roundCounter);
                    } else {
                        player1.setMana(player1.getMana() + 10);
                        player2.setMana(player2.getMana() + 10);
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
                if(pastPlayer == 1) {
                    pastPlayer = 2;
                } else {
                    pastPlayer = 1;
                }
                newturn = false;

            }
            playerIdx = actions.get(counter).getPlayerIdx();
            if(playerIdx == 0) {
                playerIdx = pastPlayer;
            }

            if(playerIdx == 1) {
                handler.checkAction(actions.get(counter), output, this.getPlayer1(), this);
            } else {
                handler.checkAction(actions.get(counter), output, this.getPlayer2(), this);
            }
        }
    }
}
