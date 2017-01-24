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

    /**
     * Sight distance in blocks
     */
    private int sightDistance = 0;

    /**
     * Probabilty of npc to move according to what it saw. (0-100)
     */
    private int intelligence = 0;

    /**
     * What should npc attack?
     */
    private Enums.AttackTarget attackTarget;

    /**
     * How much "damage" does npc cast?
     */
    private int attackValue;

    /**
     * Creates a new NPC Object.
     * If there is no image within /res/img/objects/ that matches "name.lowercase()".png a
     * default image is used.
     *
     * @param pos_x         Center positon X
     * @param pos_y         Center position Y
     * @param speed         Speed (0-100)
     * @param name          Name (also used to find image ressource)
     * @param sightDistance How far can it look? (blocks)
     * @param intelligence  Probability of acting on perception (0-100)
     * @param attackTarget  Which Player feature to attack?
     * @param attackValue   How much damage to cast?
     */
    public NPC(
            int pos_x, int pos_y, float speed, String name, int sightDistance, int intelligence, Enums.AttackTarget attackTarget, int attackValue) {
        super(pos_x, pos_y, true, speed);
        this.sightDistance = sightDistance;
        this.intelligence = GameUtils.clamp(0, 100, intelligence);
        this.attackTarget = attackTarget;
        this.attackValue = attackValue;
        this.name = name;
        this.rechargeDuration = 2;
        try {
            this.image = new Image("/res/img/objects/" + name.toLowerCase() + ".png");
        } catch (Exception e) {
            System.err.println("No image ressource found for NPC name " + name + "!");
            try {
                this.image = new Image("/res/img/objects/npc_default.png");
            } catch (SlickException e1) {
                e1.printStackTrace();
            }

        }
        this.setDirection(getRandomDirection());
    }

    /**
     * Processes attacks if hit by player or direction change if hit by blocking object.
     *
     * @param go object that hit this object
     */
    @Override
    public void interact(GameObject go) {
        if (go.isBlocking()) {
            this.setDirection(getRandomDirection());
        }
        if (interactionTimeout == 0) {
            if (go.getClass().equals(Player.class)) {
                Player p = (Player) go;
                if (attackTarget == Enums.AttackTarget.DRINK) {
                    p.setBeerLevel(p.getBeerLevel() - attackValue);
                    this.interactionTimeout = this.rechargeDuration;
                }
                if (attackTarget == Enums.AttackTarget.PLAYER_SPEED) {
                    p.attackSpeed(attackValue);
                }
            }
        }
        super.interact(go);
    }

    /**
     * Returns random direction
     *
     * @return random direction
     */
    private Enums.Direction getRandomDirection() {
        return Enums.Direction.values()[new Random().nextInt(4)];
    }


    /**
     * Get sight distance
     *
     * @return sight distance
     */
    public int getSightDistance() {
        return sightDistance;
    }

    /**
     * Get intelligence
     *
     * @return intelligence
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * Get attack target
     *
     * @return attack target
     */
    public Enums.AttackTarget getAttackTarget() {
        return attackTarget;
    }

    /**
     * Get attack value
     *
     * @return attack value
     */
    public int getAttackValue() {
        return attackValue;
    }
}
