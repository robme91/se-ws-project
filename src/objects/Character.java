package objects;

import org.newdawn.slick.geom.Rectangle;

/**
 * Created by tom on 13.01.17.
 */
public abstract class Character extends GameObject {

    private float speed;

    private Enums.Direction direction = null;

    protected String name;

    public Character(int pos_x, int pos_y, boolean isBlocking, float speed) {
        super(pos_x, pos_y, 20, isBlocking);
        this.speed = speed;
        this.hitbox = new Rectangle(pos_x, pos_y, 20, 20);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setPos_x(float x) {
        this.pos_x = x;
        this.hitbox.setCenterX(x);
    }

    public void setPos_y(float y) {
        this.pos_y = y;
        this.hitbox.setCenterY(y);
    }


    public Enums.Direction getDirection() {
        return direction;
    }

    public void setDirection(Enums.Direction direction) {
        this.direction = direction;
    }
}
