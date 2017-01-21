package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.Random;

/**
 * Created by tom on 13.01.17.
 * Non Player Character
 */
public class NPC extends Character {

    private int sightDistance = 0;
    private int intelligence = 0;
    private Enums.AttackTarget attackTarget;
    private int attackValue;

    public NPC(
            int pos_x, int pos_y, float speed, String name, int sightDistance, int intelligence,
            Enums.AttackTarget attackTarget, int attackValue) {
        super(pos_x, pos_y, true, speed);
        this.sightDistance = sightDistance;
        this.intelligence = intelligence;
        this.attackTarget = attackTarget;
        this.attackValue = attackValue;
        this.name = name;
        try {
            this.image = new Image("/res/img/objects/" + name.toLowerCase() + ".png");
        } catch (SlickException e) {
            e.printStackTrace();
            System.err.println("No image ressource found for NPC name " + name + "!");
        }
        this.setDirection(getRandomDirection());
    }

    @Override
    public void interact(GameObject go) {
        if (go.isBlocking()) {
            this.setDirection(getRandomDirection());
        }
        if (go.getClass().equals(Player.class)) {
            System.out.println("PLAYER GOT HIT BY AN NPC!");
        }
        super.interact(go);
    }

    private Enums.Direction getRandomDirection() {
        return Enums.Direction.values()[new Random().nextInt(4)];
    }

    public int getSightDistance() {
        return sightDistance;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public Enums.AttackTarget getAttackTarget() {
        return attackTarget;
    }

    public int getAttackValue() {
        return attackValue;
    }
}
