package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by tom on 24.01.17.
 */
public class Park extends Block {
    public Park(int pos_x, int pos_y) {
        super(pos_x, pos_y, false);
        try {
            this.image = new Image("/res/img/objects/park.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
