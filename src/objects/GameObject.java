package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Created by tom on 13.01.17.
 * Abstract class for GameObject
 */
public abstract class GameObject {

    /**
     * center x
     */
    protected int pos_x;

    /**
     * center y
     */
    protected int pos_y;

    /**
     * center width if square or 2*radius
     */
    protected int size;

    /**
     * if True, Characters can´t walk through or over it
     */
    protected boolean isBlocking;

    /**
     * Geometry that represents the hitbox
     */
    protected Shape hitbox;

    /**
     * @param pos_x      center x
     * @param pos_y      center y
     * @param size       width / 2*radius
     * @param isBlocking can Player walk over it?
     */

    protected Image image;

    public GameObject(int pos_x, int pos_y, int size, boolean isBlocking) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.size = size;
        this.isBlocking = isBlocking;
    }

    /**
     * Checks if this object is touched by another Shape
     *
     * @param s Shape to check
     * @return True if Shape intersects
     */
    public boolean checkIfHitBy(Shape s) {
        return s.intersects(this.hitbox);
    }

    public int getPos_x() { return pos_x; }

    public int getPos_y() { return pos_y; }

    public void setPos_x(int x) {
        this.pos_x = x;
        this.hitbox.setCenterX(x);
    }

    public void setPos_y(int y) {
        this.pos_y = y;
        this.hitbox.setCenterY(y);
    }

    public void setLocation(float x, float y) {
        this.setPos_x(Math.round(x));
        this.setPos_y(Math.round(y));
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public Shape getHitbox() {
        return hitbox;
    }

    public Image getImage() { return this.image; }

    public int getSize() {
        return size;
    }
}
