package gamestates;

import controllers.LevelController;
import controllers.StatusBarController;
import level.AbstractLevel;
import objects.Enums;
import org.newdawn.slick.*;
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
            levelController.pause();
            isPaused = true;
        }else{
            container.resume();
        }
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        levelController.drawOnGraphicsContext(g);
        statusBarController.drawOnGraphicsContext(g, 0f, GameUtils.GAME_HEIGHT - 50);

        if(isPaused){
            g.setColor(Color.cyan);
            g.drawString("Press any arrow key to start!", GameUtils.GAME_STARTET_POS_X, GameUtils.GAME_STARTET_POS_Y);
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(levelController.isPlayerDead()){
            Image pauseImg = pauseGameMakeImg(container);
            ((GameFinishState) game.getState(GameFinishState.Game_FINISH_STATE_ID)).setBackgroundImage(pauseImg);
            ((GameFinishState) game.getState(GameFinishState.Game_FINISH_STATE_ID)).isFinishedSuccessful(false);
            game.enterState(GameFinishState.Game_FINISH_STATE_ID);
        }else if(levelController.isTimeUp()){
            Image pauseImg = pauseGameMakeImg(container);
            ((GameFinishState) game.getState(GameFinishState.Game_FINISH_STATE_ID)).setBackgroundImage(pauseImg);
            ((GameFinishState) game.getState(GameFinishState.Game_FINISH_STATE_ID)).isFinishedSuccessful(true);
            game.enterState(GameFinishState.Game_FINISH_STATE_ID);
        }

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
                Image pauseImg = pauseGameMakeImg(container);
                ((PauseMenuState) game.getState(PauseMenuState.PAUSE_MENU_STATE_ID)).setBackgroundImage(pauseImg);
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

    /**
     * pause the game and return an image from the current game field.
     * @param container The container which must be paused and hold the graphics
     * @return
     * @throws SlickException
     */
    private Image pauseGameMakeImg(GameContainer container) throws SlickException {
        container.pause();
        levelController.pause();
        isPaused = true;
        Image pauseImage = new Image(GameUtils.GAME_WIDTH, GameUtils.GAME_HEIGHT);
        container.getGraphics().copyArea(pauseImage, 0, 0);
        return pauseImage;
    }
}
