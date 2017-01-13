package objects;

/**
 * Created by tom on 13.01.17.
 */
public abstract class House extends Block {
    public House(int pos_x, int pos_y, int size, boolean isBlocking) {
        super(pos_x, pos_y, size, isBlocking);
    }
}
