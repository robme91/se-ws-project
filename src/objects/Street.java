package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by tom on 14.01.17.
 */
public class Street extends Block {
    public Street(int pos_x, int pos_y, int size, boolean isBlocking) {
        super(pos_x, pos_y, size, isBlocking);
        try {
            this.image = new Image("/res/img/objects/street.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
