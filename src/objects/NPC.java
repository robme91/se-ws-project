package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utils.GameUtils;

import java.util.Random;

/**
 * Created by tom on 13.01.17.
 * Non Player Character
 */
public class NPC extends Character {
    public NPC(int pos_x, int pos_y, int size, boolean isBlocking, float speed) {
        super(pos_x, pos_y, size, isBlocking, speed);
        try {
            this.image = new Image("/res/img/objects/npc.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.setDirection(GameUtils.Direction.values()[new Random().nextInt(4)]);
    }

    public NPC(int pos_x, int pos_y, int size, boolean isBlocking, float speed, GameUtils.Direction direction) {
        super(pos_x, pos_y, size, isBlocking, speed);
        this.setDirection(direction);
    }

    @Override
    public void interact(GameObject go) {
        if (go.isBlocking()) {
            this.setDirection(GameUtils.Direction.values()[new Random().nextInt(4)]);
        }
        if (go.getClass().equals(Player.class)) {
            System.out.println("PLAYER GOT HIT BY AN NPC!");
        }
        super.interact(go);
    }

}
