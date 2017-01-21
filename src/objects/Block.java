package objects;

import org.newdawn.slick.geom.Rectangle;

/**
 * Created by tom on 13.01.17.
 */
public class Block extends GameObject {
    public Block(int pos_x, int pos_y, boolean isBlocking) {
        super(pos_x, pos_y, 32, isBlocking);
        this.hitbox = new Rectangle(pos_x - 16, pos_y - 16, 32, 32);
    }
}
