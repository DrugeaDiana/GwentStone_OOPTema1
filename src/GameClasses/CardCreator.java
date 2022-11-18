package gameclasses;

import cardsclasses.Card;
import cardsclasses.envclasses.Firestorm;
import cardsclasses.envclasses.HeartHound;
import cardsclasses.envclasses.Winterfell;
import cardsclasses.heroclasses.EmpressThorina;
import cardsclasses.heroclasses.GeneralKocioraw;
import cardsclasses.heroclasses.HeroCard;
import cardsclasses.heroclasses.KingMudface;
import cardsclasses.heroclasses.LordRoyce;
import cardsclasses.minionclasses.Berserker;
import cardsclasses.minionclasses.Goliath;
import cardsclasses.minionclasses.Sentinel;
import cardsclasses.minionclasses.Warden;
import cardsclasses.minionclasses.TheRipper;
import cardsclasses.minionclasses.TheCursedOne;
import cardsclasses.minionclasses.Miraj;
import cardsclasses.minionclasses.Disciple;
import constants.CardNames;
import fileio.CardInput;


public class CardCreator {
    /**
     * @param source the card (Minion/Environment) taken from the input file
     * @param playerID the ID of the player that's using the card
     * @return the Minion/Environment card that's been newly created
     */
    public Card create(final CardInput source, final int playerID) {
        String name = source.getName();
        Card card;
        switch (name) {
            case CardNames.BERSK ->
                card = new Berserker(source.getMana(), source.getAttackDamage(),
                        source.getHealth(), source.getDescription(), source.getColors(),
                        source.getName(), playerID);
            case CardNames.GOLIATH ->
                card = new Goliath(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            case CardNames.SENTI ->
                card = new Sentinel(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            case CardNames.WARDEN ->
                card = new Warden(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            case CardNames.RIPP ->
                card = new TheRipper(source.getMana(), source.getAttackDamage(),
                        source.getHealth(), source.getDescription(), source.getColors(),
                        source.getName(), playerID);
            case CardNames.MIRAJ ->
                card = new Miraj(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            case CardNames.CURSED ->
                card = new TheCursedOne(source.getMana(), source.getAttackDamage(),
                        source.getHealth(), source.getDescription(), source.getColors(),
                        source.getName(), playerID);
            case CardNames.DISCIPLE ->
                card = new Disciple(source.getMana(), source.getAttackDamage(),
                        source.getHealth(), source.getDescription(), source.getColors(),
                        source.getName(), playerID);
            case CardNames.FIRE ->
                card = new Firestorm(source.getMana(), source.getName(),
                        source.getDescription(), source.getColors(), playerID);
            case CardNames.WINTER ->
                card = new Winterfell(source.getMana(), source.getName(),
                        source.getDescription(), source.getColors(), playerID);
            case CardNames.HEART ->
                card = new HeartHound(source.getMana(), source.getName(), source.getDescription(),
                        source.getColors(), playerID);
            default ->
                card = null;
        }
        return card;
    }

    /**
     * @param source the Card taken from the input file
     * @param playerIdx the ID of the player
     * @return the new HeroCard created in the process
     */
    public HeroCard createHero(final CardInput source, final int playerIdx) {
        String name = source.getName();
        HeroCard card;
        switch (name) {
            case CardNames.EMPRESS ->
                card = new EmpressThorina(source.getMana(), source.getName(),
                    source.getDescription(), source.getColors(), playerIdx);
            case CardNames.GENERAL ->
                card = new GeneralKocioraw(source.getMana(), source.getName(),
                        source.getDescription(), source.getColors(), playerIdx);
            case CardNames.KING ->
                card = new KingMudface(source.getMana(), source.getName(),
                        source.getDescription(), source.getColors(), playerIdx);
            case CardNames.LORD ->
                card = new LordRoyce(source.getMana(), source.getName(),
                        source.getDescription(), source.getColors(), playerIdx);
            default ->
                card = null;
        }
        return card;
    }
}
