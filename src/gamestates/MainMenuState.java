package gamestates;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Robin on 05.01.2017
 *
 * The Main Menu Screen
 */
public class MainMenuState extends BasicGameState{

    public static final int MAIN_MENU_STATE_ID = 0;

    public int getID() {
        return MAIN_MENU_STATE_ID;
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setColor(Color.green);
        g.fillRect(50, 50 , 200, 120);
        g.setColor(Color.white);
        g.drawString("PLAY", 90, 75);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        //TODO create util class for math stuff like isMouseInButtonArea() or so
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
        if((xPos > 50 && xPos < 250) && (yPos > 430 && yPos < 550)){
            final Input input = container.getInput();
            if(input.isMouseButtonDown(0)){
                game.enterState(PlayingSate.PLAYING_STATE_ID);
            }
        }
    }
}
