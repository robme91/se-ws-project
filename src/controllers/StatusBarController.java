package controllers;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import utils.GameUtils;


/**
 * Controlls the StatusBar on the bottom of the game window
 */
public class StatusBarController {

    /**
     * LevelController that delivers the StatusBar data
     */
    private LevelController levelController;
    /**
     * Background Image
     */
    private Image statusBar;

    /**
     * Green Color for beer bar
     */
    private final Color beerBarColorGREEN = new Color(125, 205, 11);

    /**
     * Yellow Color for beer bar
     */
    private final Color beerBarColorYELLOW = new Color(212, 200, 13);

    /**
     * Red Color for beer bar
     */
    private final Color beerBarColorRED = new Color(212, 69, 12);

    /**
     * Blue Color for time bar
     */
    private final Color timeBarColorBLUE = new Color(4, 100, 181);


    /**
     * Creates a new StatusBarController
     *
     * @param levelController levelController to get the data from
     */
    public StatusBarController(LevelController levelController) {
        this.levelController = levelController;
        try {
            statusBar = new Image("/res/img/statusbar.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the status bar onto a given Graphic context at a specific position
     *
     * @param g Graphic context
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void drawOnGraphicsContext(Graphics g, float x, float y) {
        Color oldColor = g.getColor();
        g.drawImage(statusBar, x, y);

        final float startBeerBar = x + 48f;
        final float endBeerBar = x + 393f;
        final float startTimeBar = x + 448f;
        final float endTimeBar = x + 793f;
        final float barMinY = y + 12f;
        final float barMaxY = y + 37f;

        final float beerLevel = GameUtils.clamp(0f, 1f, levelController
                .getRemainingBeerPercentage());
        final float timeLevel = GameUtils.clamp(0f, 1f, levelController
                .getRemainingTimePercentage());

        Color beerBarColor = beerBarColorGREEN;
        Color timeBarColor = timeBarColorBLUE;

        if (beerLevel < 0.5f) {
            beerBarColor = beerBarColorYELLOW;
        }
        if (beerLevel < 0.2f) {
            beerBarColor = beerBarColorRED;
        }
        if (beerLevel <= 0f) {
            beerBarColor = Color.transparent;
        }
        if (timeLevel <= 0f) {
            timeBarColor = Color.transparent;
        }

        final Shape beerBar = new Rectangle(startBeerBar, barMinY, (endBeerBar - startBeerBar) *
                beerLevel, barMaxY - barMinY);
        final Shape timeBar = new Rectangle(startTimeBar, barMinY, (endTimeBar - startTimeBar) *
                timeLevel, barMaxY - barMinY);

        g.setColor(beerBarColor);
        g.fill(beerBar);
        g.draw(beerBar);

        g.setColor(timeBarColor);
        g.fill(timeBar);
        g.draw(timeBar);
        g.setColor(oldColor);  // reset graphics context to old color
    }
}
