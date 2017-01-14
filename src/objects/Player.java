package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by tom on 13.01.17.
 *
 * This class represents the Player of the game.
 */
public class Player extends Character {
    public Player(int pos_x, int pos_y, int size, boolean isBlocking, float speed) {
        super(pos_x, pos_y, size, isBlocking, speed);
        try {
            this.image = new Image("/res/img/objects/player.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact(GameObject go) {
        if (go.isBlocking()) {
            this.setDirection(null);
        }
        super.interact(go);
    }
}
