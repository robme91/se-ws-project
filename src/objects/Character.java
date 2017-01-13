package objects;

/**
 * Created by tom on 13.01.17.
 */
public abstract class Character extends GameObject {
    public Character(int pos_x, int pos_y, int size, boolean isBlocking) {
        super(pos_x, pos_y, size, isBlocking);
    }
}
