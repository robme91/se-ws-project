package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Abstract class for GameObject
 */
public abstract class GameObject {

    /**
     * center X
     */
    float pos_x;

    /**
     * center Y
     */
    float pos_y;

    /**
     * Center width if hitbot is a square or diameter if hitbox is a circle
     */
    private int size;

    /**
     * if true, Characters can´t walk through or over it
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

    // how long does it take for the this GameObject to recover from damage?
    int rechargeDuration = 0;

    // Seconds until next interaction possible
    int interactionTimeout = 0;

    /**
     * @param pos_x      X index starting at 0 (will get transformed to coordinate after level-init)
     * @param pos_y      Y index starting at 0 (will get transformed to coordinate after level-init)
     * @param size       width or diameter
     * @param isBlocking can characters walk over it?
     */
    GameObject(float pos_x, float pos_y, int size, boolean isBlocking) {
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

    /**
     * Get center X position.
     *
     * @return center X position
     */
    public float getPos_x() {
        return pos_x;
    }

    /**
     * Get center Y position.
     *
     * @return center Y position
     */
    public float getPos_y() {
        return pos_y;
    }

    /**
     * Set center X position.
     *
     * @param x center X position
     */
    public void setPos_x(float x) {
        this.pos_x = x;
        this.hitbox.setCenterX(x);
    }

    /**
     * Set center Y position.
     *
     * @param y center Y position
     */
    public void setPos_y(float y) {
        this.pos_y = y;
        this.hitbox.setCenterY(y);
    }

    /**
     * Set center X and Y position.
     *
     * @param x center X position
     * @param y center Y position
     */
    public void setLocation(float x, float y) {
        this.setPos_x(x);
        this.setPos_y(y);
    }

    /**
     * Gets called if this object is hit by a GameObject
     *
     * @param go object that hit this object
     */
    public void interact(GameObject go) {
    }

    /**
     * Action to perform every second
     *
     * @param ms time since last call in ms (should be around 1000)
     */
    public void secondTick(int ms) {
        if (this.interactionTimeout > 0) {
            this.interactionTimeout--;
        }
    }

    /**
     * Check blocking state
     *
     * @return true if this object blocks others, false if not
     */
    public boolean isBlocking() {
        return isBlocking;
    }

    /**
     * Get hitbox
     *
     * @return hitbox
     */
    public Shape getHitbox() {
        return hitbox;
    }

    /**
     * Get image to render
     *
     * @return image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Get size
     *
     * @return size
     */
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "GameObject{" + "pos_x=" + pos_x + ", pos_y=" + pos_y + ", size=" + size + ", " +
                "isBlocking=" + isBlocking + ", hitbox=" + hitbox + ", image=" + image + '}';
    }
}
