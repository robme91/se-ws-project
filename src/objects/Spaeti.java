package objects;


/**
 * Created by tom on 19.01.17.
 */
public class Spaeti extends House {

    private Enums.Direction direction;

    public Spaeti(int pos_x, int pos_y, Enums.Direction direction) {
        super(pos_x, pos_y);
        this.direction = direction;
    }

    public Enums.Direction getDirection() {
        return direction;
    }
}
