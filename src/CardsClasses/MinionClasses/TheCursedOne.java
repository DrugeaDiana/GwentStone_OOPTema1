package CardsClasses.MinionClasses;

import java.util.ArrayList;

public class TheCursedOne extends MinionCard {
    public TheCursedOne(int mana, int ad, int hp, String description, ArrayList<String> colors, String name, int playerID) {
        super(mana, ad, hp, description, colors, name, playerID);
        setTank(false);
        if(playerID == 1){
            setRow(2);
        } else {
            setRow(1);
        }
    }

    public void ability(MinionCard enemy){
        if(!this.isFrozen()) {
            if(this.getPlayerID() != enemy.getPlayerID()) {
                int en_hp = enemy.getHealth();
                int en_ad = enemy.getAttackDamage();
                enemy.setAttackDamage(en_hp);
                enemy.setHealth(en_ad);
            } else {
                System.out.println("you can't attack your card");
            }
        } else {
            System.out.println("u're frozen my dude");
        }
    }
}
