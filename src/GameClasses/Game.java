package gameclasses;

import cardsclasses.minionclasses.MinionCard;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.Input;

import java.io.IOException;
import java.util.ArrayList;

public class Game {

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    private Player player1;

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    private Player player2;
    private int game_numbers;
    private ArrayNode output;
    private Input inputData;

    private ArrayList<Player> players;

    public int getStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    private int startingPlayer;

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public ArrayList<ArrayList<MinionCard>> getTable() {
        return table;
    }

    public void setTable(ArrayList<ArrayList<MinionCard>> table) {
        this.table = table;
    }

    private ArrayList<ArrayList<MinionCard>> table;

    private int turnCounter;

    public boolean isNewturn() {
        return newturn;
    }

    public void setNewturn(boolean newturn) {
        this.newturn = newturn;
    }

    private boolean newturn;


    public Game (Input inputData, ArrayNode output){
        this.output = output;
        this.inputData = inputData;
        player1 = new Player(1);
        player2 = new Player(2);
        PlayerPreparer prep = new PlayerPreparer();
        player1.setDecks(prep.prepareDecks(inputData.getPlayerOneDecks(), player1));
        game_numbers = inputData.getGames().size();
        player2.setDecks(prep.prepareDecks(inputData.getPlayerTwoDecks(), player2));
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
    }

    public void tableInit(){
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

    public void playGame (int game_index) throws IOException {
        tableInit();
        newturn = false;
        PlayerPreparer prep = new PlayerPreparer();
        prep.preparePlayer(player1, inputData.getGames().get(game_index).getStartGame());
        prep.preparePlayer(player2, inputData.getGames().get(game_index).getStartGame());
        startingPlayer = inputData.getGames().get(game_index).getStartGame().getStartingPlayer();
        boolean ongoing = true;
        int counter = 0;
        turnCounter = 1;
        System.out.println(player1.getCurrentDeck());
        System.out.println(player2.getCurrentDeck());
        ActionHandler handler = new ActionHandler();
        ArrayList<ActionsInput> actions = inputData.getGames().get(game_index).getActions();
        //System.out.println(actions);
        int playerIdx = startingPlayer;
        int pastPlayer = startingPlayer;
        int roundCounter = 1;
        while(ongoing && counter < inputData.getGames().get(game_index).getActions().size()){

            if(newturn) {
                if(player1.isFinishTurn() && player2.isFinishTurn()) {
                    if (player2.getCurrentDeck().getCards().size() > 0) {
                        player2.getHand().add(player2.getCurrentDeck().getCards().get(0));
                        player2.getCurrentDeck().getCards().remove(0);
                    }
                    //
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
                }
                for (ArrayList<MinionCard> minionCards : table) {
                    for (MinionCard minion : minionCards) {
                        if(turnCounter - minion.getFrozenTurn() == 2) {
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
            if(players.get(0).getHero().getHealth() < 0) {
                ongoing = false;
            }
            counter++;

        }
    }
}
