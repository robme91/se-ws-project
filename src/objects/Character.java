package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import utils.GameUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for a character like a npc or the player
 */
public abstract class Character extends GameObject {

    /**
     * Speed of motion, from 0 to 100
     */
    private float speed;

    /**
     * Current direction
     */
    private Enums.Direction direction = null;

    /**
     *
     */
    protected String name;

    /**
     * Images for all possible directions
     */
    private Map<Enums.Direction, Image> directedImages = null;

    public Character(int pos_x, int pos_y, boolean isBlocking, float speed) {
        super(pos_x, pos_y, 20, isBlocking);
        this.speed = speed;
        this.hitbox = new Rectangle(pos_x, pos_y, 20, 20);
    }

    /**
     * Get the motion speed (0-100)
     *
     * @return speed (0-100)
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Set the motion speed (0-100)
     *
     * @param speed
     */
    public void setSpeed(float speed) {
        speed = GameUtils.clamp(0, 100, speed);
        this.speed = speed;
    }

    /**
     * Get current direction of character
     *
     * @return current Direction
     */
    public Enums.Direction getDirection() {
        return direction;
    }

    /**
     * Set current direction of character
     *
     * @param direction new direction
     */
    public void setDirection(Enums.Direction direction) {
        this.direction = direction;
        // if first call, generate directed images
        if (this.directedImages == null) {
            fillDirectedImages();
        }
        // make sure that images does not change when character stops
        if (direction != null) {
            this.image = directedImages.get(direction);
        }
    }

    /**
     * Since Images get set within concrete class, we have to fill the Map at first query.
     * This method does this.
     */
    private void fillDirectedImages() {
        // create directed images
        this.directedImages = new HashMap<>(5);
        this.directedImages.put(null, this.image);
        for (Enums.Direction d : Enums.Direction.values()) {
            Image img = this.image.copy();
            img.rotate(GameUtils.getImageRotationFromDirection(d));
            this.directedImages.put(d, img);
        }
    }

}
