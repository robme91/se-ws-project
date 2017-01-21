package objects;

import org.newdawn.slick.geom.Circle;
import utils.GameUtils;

/**
 * Created by tom on 13.01.17.
 */
public abstract class Character extends GameObject {

    private float speed;

    private GameUtils.Direction direction = null;

    protected String name;

    public Character(int pos_x, int pos_y, int size, boolean isBlocking, float speed) {
        super(pos_x, pos_y, size, isBlocking);
        this.speed = speed;
        this.hitbox = new Circle(pos_x, pos_y, size / 2);
    }

    public Character(int pos_x, int pos_y, float speed) {
        super(pos_x, pos_y, 20, true);
    }

    public void gotBlockedBy(GameObject go) {

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

    public GameUtils.Direction getDirection() {
        return direction;
    }

    public void setDirection(GameUtils.Direction direction) {
        this.direction = direction;
    }
}
