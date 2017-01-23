package controllers;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import utils.GameUtils;


/**
 * Created by tom on 23.01.17.
 */
public class StatusBarController {

    private LevelController levelController;
    private Image statusBar;
    private final float startBeerBar = 48f;
    private final float endBeerBar = 393f;
    private final float startTimeBar = 448f;
    private final float endTimeBar = 793f;
    private final float barMinY = 12f;
    private final float barMaxY = 37f;
    private final Color beerBarColorGREEN = new Color(125, 205, 11);
    private final Color beerBarColorYELLOW = new Color(212, 200, 13);
    private final Color beerBarColorRED = new Color(212, 69, 12);
    private final Color timeBarColorBLUE = new Color(4, 100, 181);

    public StatusBarController(LevelController levelController) {
        this.levelController = levelController;
        try {
            statusBar = new Image("/res/img/statusbar.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void drawOnGraphicsContext(Graphics g, float x, float y) {
        Color oldColor = g.getColor();
        g.drawImage(statusBar, x, y);
        float beerLevel = GameUtils.clamp(0f, 1f, levelController.getRemainingBeerPercentage());
        float timeLevel = GameUtils.clamp(0f, 1f, levelController.getRemainingTimePercentage());

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

        Shape beerBar = new Rectangle(x + startBeerBar, y + barMinY, (endBeerBar - startBeerBar)
                * beerLevel, barMaxY - barMinY);
        Shape timeBar = new Rectangle(x + startTimeBar, y + barMinY, (endTimeBar - startTimeBar)
                * timeLevel, barMaxY - barMinY);

        g.setColor(beerBarColor);
        g.fill(beerBar);
        g.draw(beerBar);

        g.setColor(timeBarColor);
        g.fill(timeBar);
        g.draw(timeBar);
        g.setColor(oldColor);  // reset graphics context to old color
    }
}
