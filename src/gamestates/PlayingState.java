package gamestates;

import controllers.LevelController;
import controllers.StatusBarController;
import level.AbstractLevel;
import objects.Enums;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import utils.GameUtils;

/**
 * Created by Robin on 05.01.2017.
 *
 * This is the Screen where the real game is played
 */
public class PlayingState extends BasicGameState{

    public static int PLAYING_STATE_ID;

    private LevelController levelController;

    /**The level that is chosen to play*/
    private AbstractLevel currentLevel;

    /*Set to true if pause menu shall popup*/
    private boolean isPaused = false;
    private boolean isLevelSucceeded = false;


    private StatusBarController statusBarController;

    public PlayingState(final int stateId){
        PLAYING_STATE_ID = stateId;
    }

    public int getID() {
        return PLAYING_STATE_ID;
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        if(currentLevel == null){
            game.enterState(MainMenuState.MAIN_MENU_STATE_ID);
        }else if(!isPaused){
            this.levelController = new LevelController(currentLevel);
            this.statusBarController = new StatusBarController(levelController);
        }else{
            container.resume();
        }
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        levelController.drawOnGraphicsContext(g);
        statusBarController.drawOnGraphicsContext(g, 0f, GameUtils.GAME_HEIGHT - 50);

        //render level finished
        if(isLevelSucceeded){
            g.setColor(Color.white);
            g.drawString("You Win!", GameUtils.GAME_FIELD_WIDTH/2, GameUtils.GAME_FIELD_HEIGHT/2);
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        final Input input = container.getInput();

        /*movement of the player*/
        if (input.isKeyDown(Input.KEY_UP)) {
            levelController.setPlayerDirection(Enums.Direction.UP);
        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            levelController.setPlayerDirection(Enums.Direction.DOWN);
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            levelController.setPlayerDirection(Enums.Direction.LEFT);
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            levelController.setPlayerDirection(Enums.Direction.RIGHT);
        }

        // let level update itself
        levelController.update(delta);

        //pause menu logic
        if(!isPaused){
            if(input.isKeyDown(Input.KEY_ESCAPE)){
                container.pause();
                levelController.pause();
                isPaused = true;
                Image pauseImage = new Image(GameUtils.GAME_WIDTH, GameUtils.GAME_HEIGHT);
                container.getGraphics().copyArea(pauseImage, 0, 0);
                ((PauseMenuState) game.getState(PauseMenuState.PAUSE_MENU_STATE_ID)).setBackgroundImage(pauseImage);
                game.enterState(PauseMenuState.PAUSE_MENU_STATE_ID);
            }
        }else{
            if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_DOWN)
                    || input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT)) {
                isPaused = false;
                levelController.play();
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public void setCurrentLevel(AbstractLevel currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * Set false if game shall not be paused anymore
     * @param paused
     */
    public void isPaused(final boolean paused){
        this.isPaused = paused;
    }
}
