package gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Robin on 05.01.2017.
 *
 * This is the Screen where the real game is played
 */
public class PlayingSate extends BasicGameState{

    public static final int PLAYING_STATE_ID = 1;

    public int getID() {
        return PLAYING_STATE_ID;
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        final Input input = container.getInput();
        if(input.isKeyDown(Input.KEY_ESCAPE)){
            game.enterState(MainMenuState.MAIN_MENU_STATE_ID);
        }
    }
}
