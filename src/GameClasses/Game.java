package GameClasses;

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

    private int startingPlayer;

    //something for the game table itself

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

    public void playGame (int game_index) throws IOException {
        PlayerPreparer prep = new PlayerPreparer();
        prep.preparePlayer(player1, inputData.getGames().get(game_index).getStartGame());
        prep.preparePlayer(player2, inputData.getGames().get(game_index).getStartGame());
        startingPlayer = inputData.getGames().get(game_index).getStartGame().getStartingPlayer();
        boolean ongoing = true;
        int counter = 0;
        int turnCounter = 1;
        System.out.println(player1.getCurrentDeck());
        System.out.println(player2.getCurrentDeck());
        ActionHandler handler = new ActionHandler();
        ArrayList<ActionsInput> actions = inputData.getGames().get(game_index).getActions();
        //System.out.println(actions);
        while(ongoing && counter < inputData.getGames().get(game_index).getActions().size()){
            int playerIdx = actions.get(counter).getPlayerIdx();
            if (playerIdx == 1) {
                handler.checkAction(actions.get(counter), output, this.getPlayer1(), turnCounter, startingPlayer);
            } else {
                handler.checkAction(actions.get(counter), output, this.getPlayer2(), turnCounter, startingPlayer);
            }
            if(players.get(0).getHero().getHealth() < 0) {
                ongoing = false;
            }
            counter++;
        }
    }
}
