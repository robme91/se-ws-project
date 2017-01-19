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

    private int sightDistance = 0;
    private int intelligence = 0;

    public NPC(int pos_x, int pos_y, int size, boolean isBlocking, float speed) {
        super(pos_x, pos_y, size, isBlocking, speed);
        try {
            this.image = new Image("/res/img/objects/npc.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.setDirection(GameUtils.Direction.values()[new Random().nextInt(4)]);
    }

    public NPC(int pos_x, int pos_y, int size, boolean isBlocking, float speed, String name) {
        super(pos_x, pos_y, size, isBlocking, speed);
        try {
            this.image = new Image("/res/img/objects/" + name.toLowerCase() + ".png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.setDirection(GameUtils.Direction.values()[new Random().nextInt(4)]);
    }

    public NPC(int pos_x, int pos_y, int size, boolean isBlocking, float speed, String name, int sightDistance, int intelligence) {
        super(pos_x, pos_y, size, isBlocking, speed);
        this.sightDistance = sightDistance;
        this.intelligence = intelligence;
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

    public int getSightDistance() {
        return sightDistance;
    }

    public int getIntelligence() {
        return intelligence;
    }
}
