package objects;

import org.newdawn.slick.geom.Rectangle;

/**
 * Class for a block
 */
public class Block extends GameObject {
    /**
     * Creates a new block Instance without any image.
     *
     * @param pos_x      X index starting at 0 (will get transformed to coordinate after level-init)
     * @param pos_y      Y index starting at 0 (will get transformed to coordinate after level-init)
     * @param isBlocking can characters walk over it?
     */
    public Block(int pos_x, int pos_y, boolean isBlocking) {
        super(pos_x, pos_y, 32, isBlocking);
        this.hitbox = new Rectangle(pos_x - 16, pos_y - 16, 32, 32);
    }
}
