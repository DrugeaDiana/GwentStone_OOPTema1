package gameclasses;
import cardsclasses.*;
import cardsclasses.envclasses.*;
import cardsclasses.heroclasses.*;
import cardsclasses.minionclasses.*;
import fileio.CardInput;


public class CardCreator {
    /**
     * @param source the card (Minion/Environment) taken from the input file
     * @param playerID the ID of the player that's using the card
     * @return the Minion/Environment card that's been newly created
     */
    public Card create(final CardInput source, final int playerID) {
        String name = source.getName();
        Card card = null;
        switch (name) {
            case "Berserker" -> {
                card = new Berserker(source.getMana(), source.getAttackDamage(),
                        source.getHealth(), source.getDescription(), source.getColors(),
                        source.getName(), playerID);
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
                card = new TheRipper(source.getMana(), source.getAttackDamage(),
                        source.getHealth(), source.getDescription(), source.getColors(),
                        source.getName(), playerID);
            }
            case "Miraj" -> {
                card = new Miraj(source.getMana(), source.getAttackDamage(), source.getHealth(),
                        source.getDescription(), source.getColors(), source.getName(), playerID);
            }
            case "The Cursed One" -> {
                card = new TheCursedOne(source.getMana(), source.getAttackDamage(),
                        source.getHealth(), source.getDescription(), source.getColors(),
                        source.getName(), playerID);
            }
            case "Disciple" -> {
                card = new Disciple(source.getMana(), source.getAttackDamage(),
                        source.getHealth(), source.getDescription(), source.getColors(),
                        source.getName(), playerID);
            }
            case "Firestorm" -> {
                card = new Firestorm(source.getMana(), source.getName(),
                        source.getDescription(), source.getColors(), playerID);
            }
            case "Winterfell" -> {
                card = new Winterfell(source.getMana(), source.getName(),
                        source.getDescription(), source.getColors(), playerID);
            }
            case "Heart Hound" -> {
                card = new HeartHound(source.getMana(), source.getName(), source.getDescription(),
                        source.getColors(), playerID);
            }
            default -> {
                card = null;
            }
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
            case "Empress Thorina" -> {
                card = new EmpressThorina(source.getMana(), source.getName(),
                        source.getDescription(), source.getColors(), playerIdx);
            }
            case "General Kocioraw" -> {
                card = new GeneralKocioraw(source.getMana(), source.getName(),
                        source.getDescription(), source.getColors(), playerIdx);
            }
            case "King Mudface" -> {
                card = new KingMudface(source.getMana(), source.getName(),
                        source.getDescription(), source.getColors(), playerIdx);
            }
            case "Lord Royce" -> {
                card = new LordRoyce(source.getMana(), source.getName(),
                        source.getDescription(), source.getColors(), playerIdx);
            }
            default -> card = null;
        }
        return card;
    }
}
