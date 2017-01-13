package level;

import objects.Player;
import utils.GameUtils;

/**
 * Created by Robin on 08.01.2017.
 * This is a dummy level class for dev. All other level classes musst be generated by codegenerator.
 */
public class DummyLevel implements ILevel{

    public static final String LEVEL_NAME = "Dummy Level";

    /*The player for this level*/
    private Player player;
    /*The time player have to survive in this level in s*/
    private float time;

    //TODO field/world, enemies

    /*creates this level. here all the input of the text file must be used for crating this level*/
    public DummyLevel(){
        player = new Player(GameUtils.GAME_FIELD_WIDTH/2, GameUtils.GAME_FIELD_HEIGHT/2, 1);
        time = 0.2f;
    }


    public Player getPlayer() {
        return player;
    }

    public float getTime() {
        return time;
    }
}
