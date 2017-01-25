package gamestates;

import level.AbstractLevel;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import utils.GameUtils;

/**
 * This represents the screen if player loses or wins the game.
 * Offers an option menu to decide what to do next.
 */
public class GameFinishState extends BasicGameState {

    /**The id of this state*/
    public static int Game_FINISH_STATE_ID;

    /**The background image of this screen*/
    private Image backgroundImage;
    /**True if player won the game. Must be set by playing state.*/
    private boolean finishedSuccessful;

    /**The representing whole game object*/
    private StateBasedGame game;
    /**If the player won the game, this must be set, to load the playing state with this level*/
    private Class<? extends AbstractLevel> nextLevel = null;

    /**
     * Create an Insatnce of this with given id.
     * @param stateId The state id.
     */
    public GameFinishState(final int stateId){
        this.Game_FINISH_STATE_ID = stateId;
    }

    @Override
    public int getID() {
        return Game_FINISH_STATE_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(backgroundImage, 0, 0);
        if(finishedSuccessful){
            g.setColor(Color.magenta);
            g.drawString("Saufen kannste! Glückwunsch. Hast jewonnen!",250, 100);
            g.setColor(Color.white);
            if(nextLevel == null){
                g.drawString("You won all levels. Play from scratch (ENTER)", 250, 150);
            }else{
                g.drawString("Play next level (ENTER)", 250, 150);
            }
        }else{
            g.setColor(Color.red);
            g.drawString("Wat war denn dat? Haste verkackt wa?! Und nu?",250, 150);
        }
        g.setColor(Color.white);
        g.drawString("Restart Level (R)", 250, 200);
        g.drawString("Level Menu (L)", 250, 250);
        g.drawString("Main Menu (M)", 250, 300);
        g.drawString("Quit (Q)", 250, 350);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    /**
     * Sets the next level on entering this screen, if the player won the game.
     */
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        if(finishedSuccessful){
            GameUtils.setLevelWon(((PlayingState) game.getState(PlayingState.PLAYING_STATE_ID)).getCurrentLevel());
            nextLevel = GameUtils.getNextLevel();
        }
    }

    /**
     * Listens on key events. Do key depending calls then.
     */
    @Override
    public void keyPressed(int key, char c) {
        if(key == Input.KEY_ENTER) {
            PlayingState playing = (PlayingState) game.getState(PlayingState.PLAYING_STATE_ID);
            if(nextLevel == null){
                GameUtils.resetLevelWonStates();
                playing.setCurrentLevel(GameUtils.getNextLevel());
                playing.isPaused(false);
            }else{
                playing.setCurrentLevel(nextLevel);
                playing.isPaused(false);
            }
            game.enterState(PlayingState.PLAYING_STATE_ID);
        }else if(key == Input.KEY_R) {
            ((PlayingState) game.getState(PlayingState.PLAYING_STATE_ID)).isPaused(false);
            game.enterState(PlayingState.PLAYING_STATE_ID);
        }else if(key == Input.KEY_L) {
            ((PlayingState) game.getState(PlayingState.PLAYING_STATE_ID)).isPaused(false);
            game.enterState(LevelMenuState.LEVEL_MENU_STATE_ID);
        }else if(key == Input.KEY_M){
            game.enterState(MainMenuState.MAIN_MENU_STATE_ID);
            ((PlayingState) game.getState(PlayingState.PLAYING_STATE_ID)).isPaused(false);
        }else if(key == Input.KEY_Q){
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

    /**
     * Set true if the level was finished successful otherwise its finished unsucceessful
     * @param successful True if player won the level
     */
    public void isFinishedSuccessful (final boolean successful){
        this.finishedSuccessful = successful;
    }
}
