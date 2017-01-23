package controllers;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by tom on 23.01.17.
 */
public class StatusBarController {

    private LevelController levelController;
    private Image statusBar;

    public StatusBarController(LevelController levelController) {
        this.levelController = levelController;
        try {
            statusBar = new Image("/res/img/statusbar.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void drawOnGraphicsContext(Graphics g, float x, float y) {
        g.drawImage(statusBar, x, y);
    }
}
