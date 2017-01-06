package gamestates;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import utils.GameUtils;

/**
 * Created by Robin on 05.01.2017
 *
 * The Main Menu Screen
 */
public class MainMenuState extends BasicGameState{

    public static final int MAIN_MENU_STATE_ID = 0;

    private Rectangle playBtn;
    private Image playBtnFill;
    private String welcomeText;

    public int getID() {
        return MAIN_MENU_STATE_ID;
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        playBtn = new RoundedRectangle(300, 210, 90, 30, 8);
        playBtnFill = new Image("res/img/play_button.png");
        welcomeText = "Welcome to No Beer is oooch no Option (NBNO). Ready to get drunk?";
    }

    String mPos = "";
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.texture(playBtn, playBtnFill, true);
        g.setColor(Color.white);
        g.drawString(mPos, 20, 10);
        g.drawString(welcomeText, 100, 50);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        mPos = "xPos: " + Mouse.getX() + " ,yPos: " + Mouse.getY();
        if(GameUtils.clickedMouseInShape(container.getInput(), playBtn)){
            game.enterState(PlayingSate.PLAYING_STATE_ID);
        }
    }
}
