package gamestates;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This represents a pause menu screen. can resume to the level or get to the other menus or leave the game.
 */
public class PauseMenuState extends BasicGameState {

    /**The id of this state*/
    public static int PAUSE_MENU_STATE_ID;

    /**The background image of this screen*/
    private Image backgroundImage;

    /**The whole game object representation*/
    private StateBasedGame game;

    /**
     * Creates a new instance of this with given id.
     * @param stateId The id of this state.
     */
    public PauseMenuState(final int stateId){
        PAUSE_MENU_STATE_ID = stateId;
    }

    @Override
    public int getID() {
        return PAUSE_MENU_STATE_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(backgroundImage, 0, 0);
        g.setColor(Color.white);
        g.drawString("Resume (R)", 250, 100);
        g.drawString("Level Menu (L)", 250, 150);
        g.drawString("Main Menu (M)", 250, 200);
        g.drawString("Quit (Q)", 250, 250);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

    /**
     * Sets the image that shall be shown in pause menu in the background
     * @param img The image that shall be shown
     */
    public void setBackgroundImage(final Image img){
        img.setAlpha(0.6f);
        this.backgroundImage = img;
    }

    @Override
    public void keyPressed(int key, char c) {
        //resume the game
        if(key == Input.KEY_R || key == Input.KEY_ESCAPE){
            this.game.enterState(PlayingState.PLAYING_STATE_ID);
        }
        // go to level menu
        if(key == Input.KEY_L) {
            ((PlayingState) this.game.getState(PlayingState.PLAYING_STATE_ID)).isPaused(false);
            this.game.enterState(LevelMenuState.LEVEL_MENU_STATE_ID);
        }
        // go to main menu
        if(key == Input.KEY_M){
            this.game.enterState(MainMenuState.MAIN_MENU_STATE_ID);
            ((PlayingState) this.game.getState(PlayingState.PLAYING_STATE_ID)).isPaused(false);
        }
        // exit game
        if(key == Input.KEY_Q){
            System.exit(0);
        }
    }
}
