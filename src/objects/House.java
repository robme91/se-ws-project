package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class for a (blocking) house
 */
public class House extends Block {
    /**
     * Creates a new house
     *
     * @param pos_x X index starting at 0 (will get transformed to coordinate after level-init)
     * @param pos_y Y index starting at 0 (will get transformed to coordinate after level-init)
     */
    public House(int pos_x, int pos_y) {
        super(pos_x, pos_y, true);
        try {
            this.image = new Image("/res/img/objects/house.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
