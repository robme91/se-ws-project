package gamestates;

import controllers.LevelController;
import controllers.StatusBarController;
import objects.Enums;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import utils.GameUtils;

/**
 *
 * This is the Screen where the real game is played
 */
public class PlayingState extends BasicGameState{
    /**The id of this state*/
    public static int PLAYING_STATE_ID;
    /**The playing and level controlling object*/
    private LevelController levelController;
    /**Controls the beer and time bar*/
    private StatusBarController statusBarController;
    /**The level that is chosen to play*/
    private Class currentLevel;
    /**Set to true if pause menu shall popup*/
    private boolean isPaused = false;
    /**the game container which is used in this game*/
    private GameContainer container;
    /**Represents the whole game*/
    private StateBasedGame game;
    /**The image that is passed to other states, as a background image*/
    private Image pauseImage;

    /**
     * Creates an instance of this.
     * @param stateId
     */
    public PlayingState(final int stateId){
        PLAYING_STATE_ID = stateId;
    }

    @Override
    public int getID() {
        return PLAYING_STATE_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.container = container;
        this.game = game;
        this.pauseImage = new Image(GameUtils.GAME_WIDTH, GameUtils.GAME_HEIGHT);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        if(currentLevel == null){
            game.enterState(MainMenuState.MAIN_MENU_STATE_ID);
        }else if(!isPaused){ // if game is not paused but this state is entered, create new instances to get a restart
            this.levelController = new LevelController(currentLevel);
            this.statusBarController = new StatusBarController(levelController);
            levelController.pause();
            isPaused = true;
        }else{
            container.resume();
        }
        if(container instanceof AppGameContainer){
            ((AppGameContainer) container).setTitle(currentLevel.getSimpleName());
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        levelController.drawOnGraphicsContext(g);
        statusBarController.drawOnGraphicsContext(g, 0f, GameUtils.GAME_HEIGHT - 50);

        if(isPaused){
            g.setColor(Color.cyan);
            g.drawString("Press any arrow key to start!", GameUtils.GAME_STARTET_POS_X, GameUtils.GAME_STARTET_POS_Y);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(levelController.isPlayerDead()){
            container.getGraphics().copyArea(this.pauseImage, 0, 0);
            ((GameFinishState) game.getState(GameFinishState.Game_FINISH_STATE_ID)).setBackgroundImage(this.pauseImage);
            ((GameFinishState) game.getState(GameFinishState.Game_FINISH_STATE_ID)).isFinishedSuccessful(false);
            game.enterState(GameFinishState.Game_FINISH_STATE_ID);
        }else if(levelController.isTimeUp()){
            container.getGraphics().copyArea(this.pauseImage, 0, 0);
            ((GameFinishState) game.getState(GameFinishState.Game_FINISH_STATE_ID)).setBackgroundImage(this.pauseImage);
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
    }

    @Override
    public void keyPressed(int key, char c) {
        // pause menu logic
        if (key == Input.KEY_ESCAPE) {
            levelController.pause();
            isPaused = true;
            this.container.getGraphics().copyArea(this.pauseImage, 0, 0);
            ((PauseMenuState) game.getState(PauseMenuState.PAUSE_MENU_STATE_ID)).setBackgroundImage(this.pauseImage);
            game.enterState(PauseMenuState.PAUSE_MENU_STATE_ID);
        }
        if (isPaused) {
            if (key == Input.KEY_UP || key == Input.KEY_DOWN
                    || key == Input.KEY_LEFT || key == Input.KEY_RIGHT) {
                isPaused = false;
                levelController.play();
            }
        }
    }

    /**
     * Sets the current level that will be played then
     * @param currentLevel
     */
    public void setCurrentLevel(Class currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * Gets the current level in the playing state
     * @return The class object of the current level.
     */
    public Class getCurrentLevel(){
        return this.currentLevel;
    }

    /**
     * Set false if game shall not be paused anymore
     * @param paused
     */
    public void isPaused(final boolean paused){
        this.isPaused = paused;
    }
}
