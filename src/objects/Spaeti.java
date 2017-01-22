package objects;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utils.GameUtils;

/**
 * Created by tom on 19.01.17.
 */
public class Spaeti extends House {

    private Enums.Direction direction;

    public Spaeti(int pos_x, int pos_y, Enums.Direction direction) {
        super(pos_x, pos_y);
        this.direction = direction;

        try {
            this.image = new Image("/res/img/objects/spaeti.png");
            this.image.rotate(GameUtils.getImageRotationFromDirection(direction));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Enums.Direction getDirection() {
        return direction;
    }

}
