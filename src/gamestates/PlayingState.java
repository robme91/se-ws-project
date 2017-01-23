package gamestates;

import controllers.LevelController;
import level.AbstractLevel;
import objects.Enums;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
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

    /*Set to true if quit menu shall popup*/
    private boolean quit = false;

//    private Player player;

    /**This progress bar shows how much time is left*/
    private Rectangle gameTimeBar;
    private boolean isTimeRunning = false;
    private boolean isLevelSucceeded = false;

    //just for dev
    private String playerPosDisplay = "";


    public PlayingState(final int stateId){
        PLAYING_STATE_ID = stateId;
    }

    public int getID() {
        return PLAYING_STATE_ID;
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
//        player = new RoundedRectangle(playerPosX, playerPosY,20, 20, 3);
        gameTimeBar = new Rectangle(300, GameUtils.GAME_FIELD_HEIGHT + 10, 400, 20);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        if(currentLevel == null){
            game.enterState(MainMenuState.MAIN_MENU_STATE_ID);
        }else{
            this.levelController = new LevelController(currentLevel);
        }
        //TODO später umziehen nachdem der Spielstart iwie bestätigt wurde
        isTimeRunning = true;
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        levelController.drawOnGraphicsContext(g);

        g.setColor(Color.white);

//        //gamefield separator
//        g.drawLine(0, GameUtils.GAME_FIELD_HEIGHT, GameUtils.GAME_FIELD_WIDTH, GameUtils.GAME_FIELD_HEIGHT);
//        g.drawLine(GameUtils.GAME_FIELD_WIDTH, GameUtils.GAME_FIELD_HEIGHT, GameUtils.GAME_FIELD_WIDTH, 0);

        //render gameinfos
        g.setColor(Color.green);
        g.drawRect(gameTimeBar.getX(), gameTimeBar.getY(), 400, 20);
        g.fill(gameTimeBar);

        //render level finished
        if(isLevelSucceeded){
            g.setColor(Color.white);
            g.drawString("You Win!", GameUtils.GAME_FIELD_WIDTH/2, GameUtils.GAME_FIELD_HEIGHT/2);
        }

        //render quit menu
        if(quit){
            g.setColor(Color.white);
            g.drawString("Resume (R)", 250, 100);
            g.drawString("Level Menu (L)", 250, 150);
            g.drawString("Main Menu (M)", 250, 200);
            g.drawString("Quit (Q)", 250, 250);
            if(!quit){
                g.clear();
            }
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        playerPosDisplay = "xPos: " + levelController.getPlayer().getPos_x() + " ,yPos: " + levelController.getPlayer().getPos_y();
        final Input input = container.getInput();

        //Speed of player is calculated in the delta so you get a nice result
//        final float playerSpeed = currentLevel.getPlayer().getSpeed() * delta * .1f;
//        final float gameTimeSpeed = currentLevel.getTime() * delta * .1f;
        final float gameTimeSpeed = 0.001f * delta * .1f;  // TODO FIX ME, dont be a static value
        if(isTimeRunning){
            if(gameTimeBar.getWidth() > 1){
                gameTimeBar.setWidth(gameTimeBar.getWidth() - gameTimeSpeed);
            }else{
                gameTimeBar.setWidth(1);
                isLevelSucceeded = true;
            }
        }

        /*movement of the player*/
        //up
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

        //escape or resume game logic
        if(input.isKeyDown(Input.KEY_ESCAPE)){
            levelController.pause();
            quit = true;
        }
        if(quit){
            if(input.isKeyDown(Input.KEY_R)) {
                quit = false;
                levelController.play();
            }else if(input.isKeyDown(Input.KEY_L)) {
                quit = false;
                game.enterState(LevelMenuState.LEVEL_MENU_STATE_ID);
            }else if(input.isKeyDown(Input.KEY_M)){
                quit = false;
                game.enterState(MainMenuState.MAIN_MENU_STATE_ID);
            }else if(input.isKeyDown(Input.KEY_Q)){
                System.exit(0);
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public void setCurrentLevel(AbstractLevel currentLevel) {
        this.currentLevel = currentLevel;
    }
}
