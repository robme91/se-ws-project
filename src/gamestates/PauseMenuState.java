package gamestates;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Robin on 23.01.2017.
 * This represents a pause menu screen. can resume to the level or get to the other menus or leave the game.
 */
public class PauseMenuState extends BasicGameState {

    public static int PAUSE_MENU_STATE_ID;

    private Image backgroundImage;

    /*To avoid that press escape returns to game to fast*/
    private int counter = 0;

    public PauseMenuState(final int stateId){
        PAUSE_MENU_STATE_ID = stateId;
    }

    @Override
    public int getID() {
        return PAUSE_MENU_STATE_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

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
        final Input input = container.getInput();

        counter += 1;
        // workaround, to do enter on escape driectly to playingState
        if(counter > 20){
            if(input.isKeyDown(Input.KEY_R) || input.isKeyDown(Input.KEY_ESCAPE)) {
                counter = 0;
                game.enterState(PlayingState.PLAYING_STATE_ID);
            }else if(input.isKeyDown(Input.KEY_L)) {
                ((PlayingState) game.getState(PlayingState.PLAYING_STATE_ID)).isPaused(false);
                game.enterState(LevelMenuState.LEVEL_MENU_STATE_ID);
            }else if(input.isKeyDown(Input.KEY_M)){
                game.enterState(MainMenuState.MAIN_MENU_STATE_ID);
                ((PlayingState) game.getState(PlayingState.PLAYING_STATE_ID)).isPaused(false);
            }else if(input.isKeyDown(Input.KEY_Q)){
                System.exit(0);
            }
        }
    }

    /**
     * Sets the image that shall be shown in pause menu in the background
     * @param img The image that shall be shown
     */
    public void setBackgroundImage(final Image img){
        img.setAlpha(0.6f);
        this.backgroundImage = img;
    }
}
