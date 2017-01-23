package gamestates;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Robin on 23.01.2017.
 * This represents the screen if player lost the game.
 */
public class GameFinishState extends BasicGameState {

    public static int Game_FINISH_STATE_ID;

    private Image backgroundImage;
    private boolean finishedSuccessful;

    public GameFinishState(final int stateId){
        this.Game_FINISH_STATE_ID = stateId;
    }

    @Override
    public int getID() {
        return Game_FINISH_STATE_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(backgroundImage, 0, 0);
        if(finishedSuccessful){
            g.setColor(Color.magenta);
            g.drawString("Saufen kannste! Glückwunsch. Hast jewonnen!",250, 100);
        }else{
            g.setColor(Color.red);
            g.drawString("Wat war denn dat? Haste verkackt wa?! Und nu?",250, 100);
        }
        g.setColor(Color.white);
        g.drawString("Restart Level (R)", 250, 150);
        g.drawString("Level Menu (L)", 250, 200);
        g.drawString("Main Menu (M)", 250, 250);
        g.drawString("Quit (Q)", 250, 300);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        final Input input = container.getInput();
        if(input.isKeyDown(Input.KEY_R)) {
            ((PlayingState) game.getState(PlayingState.PLAYING_STATE_ID)).isPaused(false);
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

    /**
     * Sets the image that shall be shown in losing screen in the background
     * @param img The image that shall be shown
     */
    public void setBackgroundImage(final Image img){
        img.setAlpha(0.6f);
        this.backgroundImage = img;
    }

    public void isFinishedSuccessful (final boolean successfull){
        this.finishedSuccessful = successfull;
    }
}
