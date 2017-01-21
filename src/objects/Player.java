package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by tom on 13.01.17.
 *
 * This class represents the Player of the game.
 */
public class Player extends Character {

    private float beerLevel;
    private float drinkSpeed;

    public Player(int pos_x, int pos_y, float speed, float drinkSpeed) {
        super(pos_x, pos_y, true, speed);
        this.beerLevel = 1f; // TODO change me
        this.drinkSpeed = 1f; //TODO change me
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


    public float getBeerLevel() {
        return beerLevel;
    }

    public void setBeerLevel(float beerLevel) {
        this.beerLevel = beerLevel;
    }
}
