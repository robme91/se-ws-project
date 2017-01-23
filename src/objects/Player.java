package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utils.GameUtils;

/**
 * Created by tom on 13.01.17.
 * <p>
 * This class represents the Player of the game.
 */
public class Player extends Character {

    private float beerLevel;
    private float drinkSpeed;
    private float speedReduction = 0f;

    public Player(int pos_x, int pos_y, float speed, float beerLevel, float drinkSpeed) {
        super(pos_x, pos_y, true, speed);
        this.rechargeDuration = 5;
        if (beerLevel < 1 || beerLevel > 100) {
            throw new IllegalArgumentException("beerLevel must be between 1 and 100");
        }
        if (drinkSpeed < 1 || drinkSpeed > 100) {
            throw new IllegalArgumentException("drinkSpeed must be between 1 and 100");
        }
        this.beerLevel = beerLevel;
        this.drinkSpeed = drinkSpeed;
        try {
            this.image = new Image("/res/img/objects/player.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact(GameObject go) {
        if (go.isBlocking()) {
            this.setDirection(null);
        }
        super.interact(go);
    }

    @Override
    public void secondTick(int ms) {
        this.setBeerLevel(this.getBeerLevel() - this.drinkSpeed * ((float) ms / 1000f));
        if (interactionTimeout == 0) {
            speedReduction = 0f;
        }
        super.secondTick(ms);
    }

    public float getBeerLevel() {
        return beerLevel;
    }

    public void setBeerLevel(float beerLevel) {
        this.beerLevel = GameUtils.clamp(0f, 100f, beerLevel);
    }

    public void attackSpeed(int percentage) {
        this.interactionTimeout = this.rechargeDuration;
        this.speedReduction = super.getSpeed() * ((float) percentage / 100f);
    }

    @Override
    public float getSpeed() {
        return super.getSpeed() - speedReduction;

    }

    public float getDrinkSpeed() {
        return drinkSpeed;
    }

    public void setDrinkSpeed(float drinkSpeed) {
        this.drinkSpeed = drinkSpeed;
    }
}
