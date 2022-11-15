package CardsClasses;

import CardsClasses.MinionClasses.*;
import CardsClasses.HeroClasses.*;
import CardsClasses.EnvClasses.*;
import GameClasses.*;
import checker.CheckerConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class MainTestingCards {
    public static void main(String[] args) throws IOException {
//        ArrayList<String> colors = new ArrayList<>();
//        colors.add("blue");
//        colors.add("red");
//        colors.add("yellow");
//
//        DecksInput input = new DecksInput();
//        ArrayList<ArrayList<CardInput>> deck = new ArrayList<>();
//        CardInput card1 = new CardInput();
//        card1.setMana(1);
//        card1.setAttackDamage(1);
//        card1.setHealth(7);
//        card1.setDescription("Standard card: A warrior who is never afraid of battle, no matter the costs");
//        card1.setColors(colors);
//        card1.setName("Goliath");
//
//        CardInput card2 = new CardInput();
//        card2.setMana(1);
//        card2.setAttackDamage(0);
//        card2.setHealth(1);
//        card2.setDescription("Nobody suspects the Spanish Inquisition");
//        card2.setColors(colors);
//        card2.setName("The Cursed One");
//
//        CardInput card3 = new CardInput();
//        card3.setMana(1);
//        card3.setDescription("Iti fur mintea mancati-as");
//        card3.setColors(colors);
//        card3.setName("Heart Hound");
//
//        CardInput card4 = new CardInput();
//        card4.setMana(1);
//        card4.setDescription("bate vantul foarte inflacarat");
//        card4.setColors(colors);
//        card4.setName("Firestorm");
//
//        CardInput card5 = new CardInput();
//        card5.setMana(1);
//        card5.setAttackDamage(2);
//        card5.setHealth(2);
//        card5.setDescription("Fear HIM");
//        card5.setColors(colors);
//        card5.setName("Berserker");
//
//        CardInput card6 = new CardInput();
//        card6.setMana(1);
//        card6.setAttackDamage(0);
//        card6.setHealth(1);
//        card6.setDescription("cursed BoiTM");
//        card6.setColors(colors);
//        card6.setName("The Cursed One");
//
//        ArrayList<CardInput> cards_1 = new ArrayList<>();
//        cards_1.add(card1);
//        cards_1.add(card2);
//        cards_1.add(card3);
//        deck.add(cards_1);
//
//        ArrayList<CardInput> cards_2 = new ArrayList<>();
//        cards_2.add(card4);
//        cards_2.add(card5);
//        cards_2.add(card6);
//        deck.add(cards_2);
//
//        input.setDecks(deck);
//        input.setNrCardsInDeck(3);
//        input.setNrDecks(2);

        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File("input/test01_game_start.json"),
                Input.class);

        Player player = new Player(1);
        PlayerPreparer preparer = new PlayerPreparer();

        player.setDecks(preparer.prepareDecks(inputData.getPlayerOneDecks(), player));
        System.out.println(player);
        preparer.preparePlayer(player, inputData.getGames().get(0).getStartGame());

        System.out.println(player.getHero());

        Debug testing = new Debug();


        ArrayNode output = objectMapper.createArrayNode();

//        testing.getPlayerDeck(1, player, output);
//        testing.getPlayerDeck(2, player, output);
//        testing.getHero(1, player, output);
//
        Game game = new Game(inputData, output);
        game.playGame(0);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File("testing.json"), output);


    }
}
