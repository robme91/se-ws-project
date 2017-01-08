package gamestates;

import level.DummyLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import utils.GameUtils;

/**
 * Created by Robin on 08.01.2017.
 *
 * This is a Menu screen for choosing the level you wanna play.
 * TODO preview images inside the rectangels and above the rectangles the level name then.
 */
public class LevelMenuState extends BasicGameState{

    public static int LEVEL_MENU_STATE_ID;

    private Rectangle dummyLevel;

    public LevelMenuState(final int stateId){
        LEVEL_MENU_STATE_ID = stateId;
    }

    public int getID() {
        return LEVEL_MENU_STATE_ID;
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        dummyLevel = new RoundedRectangle(100, 100, 120, 30, 8);
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setColor(Color.green);
        g.draw(dummyLevel);
        g.drawString(DummyLevel.LEVEL_NAME, 105, 105);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(GameUtils.clickedMouseInShape(container.getInput(), dummyLevel)){
            ((PlayingState) game.getState(PlayingState.PLAYING_STATE_ID)).setCurrentLevel(new DummyLevel());
            game.enterState(PlayingState.PLAYING_STATE_ID);
        }
    }
}