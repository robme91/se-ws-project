package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import utils.GameUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 13.01.17.
 */
public abstract class Character extends GameObject {

    private float speed;

    private Enums.Direction direction = null;

    protected String name;

    private Map<Enums.Direction, Image> directedImages = null;

    public Character(int pos_x, int pos_y, boolean isBlocking, float speed) {
        super(pos_x, pos_y, 20, isBlocking);
        this.speed = speed;
        this.hitbox = new Rectangle(pos_x, pos_y, 20, 20);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setPos_x(float x) {
        this.pos_x = x;
        this.hitbox.setCenterX(x);
    }

    public void setPos_y(float y) {
        this.pos_y = y;
        this.hitbox.setCenterY(y);
    }

    @Override
    public Image getImage() {

        return super.getImage();
    }

    public Enums.Direction getDirection() {
        return direction;
    }

    public void setDirection(Enums.Direction direction) {
        this.direction = direction;

        // if first call, generate directed images
        if (this.directedImages == null) {
            fillDirectedImages();
        }
        // make sure that images does not change when character stops
        if (direction != null) {
            this.image = directedImages.get(direction);
        }
    }

    /**
     * Since Images get set within concrete class, we have to fill the Map at first query.
     */
    private void fillDirectedImages() {
        // create directed images
        this.directedImages = new HashMap<>(5);
        this.directedImages.put(null, this.image);
        for (Enums.Direction d : Enums.Direction.values()) {
            Image img = this.image.copy();
            img.rotate(GameUtils.getImageRotationFromDirection(d));
            this.directedImages.put(d, img);
        }
    }

}
