package objects;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import utils.GameUtils;

/**
 * Class for Spaeti to drink beer
 */
public class Spaeti extends House {

    /**
     * Door direction, Spaeti can only be accessed from this direction
     */
    private Enums.Direction direction;

    /**
     * Image if Spaeti is open
     */
    private Image image_on;

    /**
     * Image if Spaeti is closed
     */
    private Image image_off;

    /**
     * Creates a new Spaeti
     *
     * @param pos_x     center X
     * @param pos_y     center Y
     * @param direction direction of door
     */
    public Spaeti(int pos_x, int pos_y, Enums.Direction direction) {
        super(pos_x, pos_y);
        this.direction = direction;
        this.rechargeDuration = 15;
        try {
            this.image_on = new Image("/res/img/objects/spaeti_on.png");
            this.image_off = new Image("/res/img/objects/spaeti_off.png");
            this.image_on.rotate(GameUtils.getImageRotationFromDirection(direction));
            this.image_off.rotate(GameUtils.getImageRotationFromDirection(direction));
            this.image = image_on;
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles player beer recharge
     *
     * @param go object that hit this object
     */
    @Override
    public void interact(GameObject go) {
        if (interactionTimeout == 0) {
            if (go.getClass().equals(Player.class)) {
                Line target = new Line(this.getPos_x(), this.getPos_y(), go.getPos_x(), go.getPos_y());
                Enums.Direction touchDirection = GameUtils.getDirectionFromXY(target.getDX(), target.getDY());
                if (touchDirection == this.direction) {
                    this.interactionTimeout = this.rechargeDuration;
                    Player p = (Player) go;
                    p.setBeerLevel(p.getBeerLevel() + 10);
                    this.image = image_off;
                }
            }
            super.interact(go);
        }
    }

    /**
     * Handles image switching between off and on
     *
     * @param ms time since last call in ms (should be around 1000)
     */
    @Override
    public void secondTick(int ms) {
        if (this.interactionTimeout == 0) {
            this.image = image_on;
        }
        super.secondTick(ms);
    }

    /**
     * Get direction of door
     *
     * @return direction of Spaeti door
     */
    public Enums.Direction getDirection() {
        return direction;
    }

}
