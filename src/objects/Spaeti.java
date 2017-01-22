package objects;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import utils.GameUtils;

/**
 * Created by tom on 19.01.17.
 */
public class Spaeti extends House {

    private Enums.Direction direction;

    /**
     * Seconds until next interaction possible
     */
    private int interactionTimeout = 0;

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

    @Override
    public void interact(GameObject go) {
        if (interactionTimeout == 0) {
            if (go.getClass().equals(Player.class)) {
                Line target = new Line(this.getPos_x(), this.getPos_y(), go.getPos_x(), go
                        .getPos_y());
                Enums.Direction touchDirection = GameUtils.getDirectionFromXY(target.getDX(),
                        target.getDY());
                if (touchDirection == this.direction) {
                    this.interactionTimeout = 10;
                }
            }
            super.interact(go);
        }
    }

    @Override
    public Image getImage() {
        if (interactionTimeout == 0) {
            image.setAlpha(1f);
            return super.getImage();
        } else {
            float a = 1f - ((float) this.interactionTimeout / 10f);
            this.image.setAlpha(a);
            return super.getImage();
        }
    }

    @Override
    public void secondTick(int ms) {
        if (this.interactionTimeout > 0) {
            this.interactionTimeout--;
        }
    }

    public Enums.Direction getDirection() {
        return direction;
    }

}
