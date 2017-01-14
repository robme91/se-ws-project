package objects;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by tom on 13.01.17.
 */
public abstract class Character extends GameObject {

    private float speed;

    public Character(int pos_x, int pos_y, int size, boolean isBlocking, float speed) {
        super(pos_x, pos_y, size, isBlocking);
        this.speed = speed;
        this.hitbox = new Circle(pos_x, pos_y, size / 2);
    }

    public float getSpeed() {
        return speed;
    }
}
