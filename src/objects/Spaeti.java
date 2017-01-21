package objects;

import utils.GameUtils;

/**
 * Created by tom on 19.01.17.
 */
public class Spaeti extends House {

    private GameUtils.Direction direction;

    public Spaeti(int pos_x, int pos_y, GameUtils.Direction direction) {
        super(pos_x, pos_y);
        this.direction = direction;
    }

    public GameUtils.Direction getDirection() {
        return direction;
    }
}
