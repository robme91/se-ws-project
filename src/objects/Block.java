package objects;

import org.newdawn.slick.geom.Rectangle;

/**
 * Created by tom on 13.01.17.
 */
public abstract class Block extends GameObject {
    public Block(int pos_x, int pos_y, int size, boolean isBlocking) {
        super(pos_x, pos_y, size, isBlocking);
        this.hitbox = new Rectangle(pos_x, pos_y, size, size);
    }
}
