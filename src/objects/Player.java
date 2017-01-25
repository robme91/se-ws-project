package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utils.GameUtils;

/**
 * This class represents the Player of the game.
 */
public class Player extends Character {

    private float beerLevel;
    private float drinkSpeed;
    private float speedReduction = 0f;

    /**
     * Creates a new Player
     *
     * @param pos_x      X index starting at 0 (will get transformed to coordinate after level-init)
     * @param pos_y      Y index starting at 0 (will get transformed to coordinate after level-init)
     * @param speed      speed of motion
     * @param beerLevel  initial beerLevel ranging from 1 to 100
     * @param drinkSpeed loss of beer per second
     */
    public Player(int pos_x, int pos_y, float speed, float beerLevel, float drinkSpeed) {
        super(pos_x, pos_y, true, speed);
        this.rechargeDuration = 5;
        this.beerLevel = GameUtils.clamp(1f, 100f, beerLevel);
        this.drinkSpeed = GameUtils.clamp(1f, 100f, drinkSpeed);
        try {
            this.image = new Image("/res/img/objects/player.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles stopping if it hits an blocking object
     *
     * @param go object that hit this object
     */
    @Override
    public void interact(GameObject go) {
        if (go.isBlocking()) {
            this.setDirection(null);
        }
        super.interact(go);
    }

    /**
     * Handles beer reduction
     *
     * @param ms time since last call in ms (should be around 1000)
     */
    @Override
    public void secondTick(int ms) {
        this.setBeerLevel(this.getBeerLevel() - this.drinkSpeed * ((float) ms / 1000f));
        if (interactionTimeout == 0) {
            speedReduction = 0f;
        }
        super.secondTick(ms);
    }

    /**
     * Get current beer level
     *
     * @return beer level (0-100)
     */
    public float getBeerLevel() {
        return beerLevel;
    }

    /**
     * Set current beer level
     *
     * @param beerLevel
     */
    public void setBeerLevel(float beerLevel) {
        this.beerLevel = GameUtils.clamp(0f, 100f, beerLevel);
    }

    /**
     * Attack the speed of the player
     *
     * @param percentage how strong is the reduction? (0-100)
     */
    public void attackSpeed(int percentage) {
        this.interactionTimeout = this.rechargeDuration;
        percentage = GameUtils.clamp(0, 100, percentage);
        this.speedReduction = super.getSpeed() * ((float) percentage / 100f);
    }

    /**
     * Get current speed with possible speed reduction.
     *
     * @return current speed
     */
    @Override
    public float getSpeed() {
        return super.getSpeed() - speedReduction;
    }

    /**
     * Get drink speed
     *
     * @return drink speed
     */
    public float getDrinkSpeed() {
        return drinkSpeed;
    }

    /**
     * Set drink speed
     *
     * @param drinkSpeed drink speed in beer per second (1-100)
     */
    public void setDrinkSpeed(float drinkSpeed) {
        this.drinkSpeed = GameUtils.clamp(1, 100, drinkSpeed);
    }

}
