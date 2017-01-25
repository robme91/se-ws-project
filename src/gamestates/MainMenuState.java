package gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import utils.GameUtils;

/**
 *
 * The Main Menu Screen.
 */
public class MainMenuState extends BasicGameState {

    /**The id of this state*/
    public static int MAIN_MENU_STATE_ID;
    /**The play button*/
    private Rectangle playBtn;
    /**The quit button*/
    private Rectangle quitBtn;
    /**The background image*/
    private Image splashScreen;

    /**
     * Creates an insatnce of this with given id.
     * @param stateId The id for this state.
     */
    public MainMenuState(final int stateId) {
        MAIN_MENU_STATE_ID = stateId;
    }

    @Override
    public int getID() {
        return MAIN_MENU_STATE_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        splashScreen = new Image("/res/img/splashscreen.png");
        playBtn = new Rectangle(275, 375, 250, 50);
        quitBtn = new Rectangle(275, 450, 250, 50);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws
            SlickException {
        g.drawImage(splashScreen, 0, 0);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws
            SlickException {
        if (GameUtils.clickedMouseInShape(container.getInput(), playBtn)) {
            game.enterState(LevelMenuState.LEVEL_MENU_STATE_ID);
        }
        if (GameUtils.clickedMouseInShape(container.getInput(), quitBtn)) {
            System.exit(0);
        }
    }
}
