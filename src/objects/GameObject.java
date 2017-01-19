package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Created by tom on 13.01.17.
 * Abstract class for GameObject
 */
public abstract class GameObject {

    /**
     * center x
     */
    float pos_x;

    /**
     * center y
     */
    float pos_y;

    /**
     * center width if square or 2*radius
     */
    private int size;

    /**
     * if True, Characters can´t walk through or over it
     */
    private final boolean isBlocking;

    /**
     * Geometry that represents the hitbox
     */
    Shape hitbox;

    /**
     * Image to render
     */
    Image image;

    /**
     * @param pos_x      center x
     * @param pos_y      center y
     * @param size       width / 2*radius
     * @param isBlocking can Player walk over it?
     */
    public GameObject(float pos_x, float pos_y, int size, boolean isBlocking) {
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

    public float getPos_x() {
        return pos_x;
    }

    public float getPos_y() {
        return pos_y;
    }

    public void setPos_x(float x) {
        this.pos_x = x;
        this.hitbox.setCenterX(x - size);  // TODO We dont move blocks, but should this be only x?
    }

    public void setPos_y(float y) {
        this.pos_y = y;
        this.hitbox.setCenterY(y - size);  // TODO We dont move blocks, but should this be only x?
    }

    public void setLocation(float x, float y) {
        this.setPos_x(x);
        this.setPos_y(y);
    }

    /**
     * Gets called if this object is hit by GameObject
     * @param go object that hit this object
     */
    public void interact(GameObject go) { }

    public boolean isBlocking() {
        return isBlocking;
    }

    public Shape getHitbox() {
        return hitbox;
    }

    public Image getImage() {
        return this.image;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "GameObject{" + "pos_x=" + pos_x + ", pos_y=" + pos_y + ", size=" + size + ", " +
                "isBlocking=" + isBlocking + ", hitbox=" + hitbox + ", image=" + image + '}';
    }
}
