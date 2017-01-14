package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by tom on 13.01.17.
 */
public class House extends Block {
    public House(int pos_x, int pos_y, int size, boolean isBlocking) {
        super(pos_x, pos_y, size, isBlocking);
        try {
            this.image = new Image("/res/img/objects/house.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
