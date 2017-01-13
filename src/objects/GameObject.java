package objects;

import org.newdawn.slick.geom.Shape;

/**
 * Created by tom on 13.01.17.
 */
public abstract class GameObject {

    private int pos_x;
    private int pos_y;
    private int size;
    private boolean isBlocking;
    private Shape hitbox;

    public GameObject(int pos_x, int pos_y, int size, boolean isBlocking) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.size = size;
        this.isBlocking = isBlocking;
    }

    public boolean checkIfHitBy(Shape s) {
        return s.intersects(this.hitbox);
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public Shape getHitbox() {
        return hitbox;
    }
}
