package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utils.GameUtils;

/**
 * Created by tom on 14.01.17.
 */
public class Street extends Block {

    // used for rotating street image
    private Enums.Direction direction;

    private Enums.StreetType streetType;

    public Street(int pos_x, int pos_y) {
        super(pos_x, pos_y, false);
        try {
            this.image = new Image("/res/img/objects/street.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets street direction for rendering
     * Must get called before setStreetType()!
     *
     * @param direction for rotating the original asset
     */
    public void setDirection(Enums.Direction direction) {
        this.direction = direction;
    }

    public void setStreetType(Enums.StreetType streetType) {
        this.streetType = streetType;
        try {
            switch (streetType) {
                case SOLO:
                    this.image = new Image("/res/img/objects/street_0.png");
                    break;
                case STRAIGHT:
                    this.image = new Image("/res/img/objects/street_straight.png");
                    break;
                case END:
                    this.image = new Image("/res/img/objects/street_1.png");
                    break;
                case BEND:
                    this.image = new Image("/res/img/objects/street_2.png");
                    break;
                case CROSS3:
                    this.image = new Image("/res/img/objects/street_3.png");
                    break;
                case CROSS4:
                    this.image = new Image("/res/img/objects/street_4.png");
                    break;
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.image.rotate(GameUtils.getImageRotationFromDirection(direction));
    }
}
