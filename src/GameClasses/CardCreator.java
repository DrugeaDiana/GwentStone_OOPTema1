package GameClasses;
import CardsClasses.*;
import CardsClasses.EnvClasses.*;
import CardsClasses.HeroClasses.*;
import CardsClasses.MinionClasses.*;
import fileio.CardInput;


public class CardCreator {

    //int mana, int ad, int hp, String description, ArrayList<String> colors, String name, int playerID
    public Card create(CardInput source, int playerID) {
        String name = source.getName();
        Card card = null;
        switch (name) {
            case "Berserker" -> {
                card = new Berserker(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            }
            case "Goliath" -> {
                card = new Goliath(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            }
            case "Sentinel" -> {
                card = new Sentinel(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            }
            case "Warden" -> {
                card = new Warden(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            }
            case "The Ripper" -> {
                card = new TheRipper(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            }
            case "Miraj" -> {
                card = new Miraj(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            }
            case "The Cursed One" -> {
                card = new TheCursedOne(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            }
            case "Disciple" -> {
                card = new Disciple(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            }
            case "Firestorm" -> {
                card = new Firestorm(source.getMana(), source.getName(), source.getDescription(),
                        source.getColors(), playerID);
            }
            case "Winterfell" -> {
                card = new Winterfell(source.getMana(), source.getName(), source.getDescription(),
                        source.getColors(), playerID);
            }
            case "Heart Hound" -> {
                card = new HeartHound(source.getMana(), source.getName(), source.getDescription(),
                        source.getColors(), playerID);
            }
        }
        return card;
    }

    public HeroCard createHero(CardInput source, int playerIdx) {
        String name = source.getName();
        HeroCard card;
        switch (name) {
            case "Empress Thorina" -> {
                card = new EmpressThorina(source.getMana(), source.getName(), source.getDescription(),
                        source.getColors(), playerIdx);
            }
            case "General Kocioraw" -> {
                card = new GeneralKocioraw(source.getMana(), source.getName(), source.getDescription(),
                        source.getColors(), playerIdx);
            }
            case "King Mudface" -> {
                card = new KingMudface(source.getMana(), source.getName(), source.getDescription(),
                        source.getColors(), playerIdx);
            }
            case "Lord Royce" -> {
                card = new LordRoyce(source.getMana(), source.getName(), source.getDescription(),
                        source.getColors(), playerIdx);
            }
            default -> card = null;
        }
        //System.out.println(card);
        return card;
    }
}
