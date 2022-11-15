package CardsClasses.EnvClasses;

import CardsClasses.Card;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("Environment")
public abstract class EnvironmentCard extends Card {
    public EnvironmentCard(int mana, String name, String description, ArrayList<String> colors, int playerID) {
        super(mana, name, description, colors, "Environment", playerID);
    }

    abstract void ability(int TargetRow);

}
