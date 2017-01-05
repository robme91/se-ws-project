package gamestates;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
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

    public int getID() {
        return MAIN_MENU_STATE_ID;
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        playBtn = new Rectangle(50, 50 , 200, 120);
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setColor(Color.green);
        g.fill(playBtn);
        g.setColor(Color.white);
        g.drawString("PLAY", 90, 75);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(GameUtils.clickedMouseInShape(container.getInput(), playBtn)){
            game.enterState(PlayingSate.PLAYING_STATE_ID);
        }
    }
}
