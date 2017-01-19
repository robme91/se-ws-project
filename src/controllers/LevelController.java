package controllers;

import level.AbstractLevel;
import level.ILevel;
import org.newdawn.slick.Graphics;
import utils.GameUtils;

/**
 * Created by tom on 19.01.17.
 */
public class LevelController {

    AbstractLevel level;

    public LevelController(AbstractLevel level) {
        this.level = level;
        // TODO set initial block positions according to GameSize
        // TODO find street pictures
        // TODO Set initial random directions for NPC
    }

    public void drawOnGraphicsContext(Graphics g) {
        //TODO STUB
    }

    public void update(int delta) {
        // TODO STUB
        // TODO Call doEverySecond()
    }

    /**
     *
     * @return Intervall [0,1]
     */
    public float getRemainingTime() {
        //TODO STUB
        return 0f;
    }

    /**
     *
     * @return Intervall [0,1]
     */
    public float getRemainingBeer() {
        //TODO STUB
        return 0f;
    }

    public void setPlayerDirection(GameUtils.Direction direction) {
        //TODO STUB
    }

    /**
     * Gets executed every second
     */
    private void doEverySecond() {
        // TODO UPDATE KI
        // TODO SET REMAINING TIME
        // TODO Reduce Player beer level
        /*
        Try to see player within sightDistance (without blocking blocks in between.
        If so, then apply direction change with a probability of intelligence where:
        0 = do not listen to that daaaamn controller
        100 = always do what the controller sez
         */
    }
}
