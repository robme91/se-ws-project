package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class for a (non-blocking) park
 */
public class Park extends Block {
    /**
     * Creates a new park
     *
     * @param pos_x Center X
     * @param pos_y Center Y
     */
    public Park(int pos_x, int pos_y) {
        super(pos_x, pos_y, false);
        try {
            this.image = new Image("/res/img/objects/park.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
