package main;

import gamestates.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import utils.GameUtils;

/**
 * Starts the game and sets the surrounding conditions.
 * Initialises all game states.
 */
public class GameStarter extends StateBasedGame {

    /**
     * Creates an instance of this with given name.
     * Adds all available states/screens to the game
     * @param name The name of the game.
     */
    public GameStarter(String name) {
        super(name);
        this.addState(new MainMenuState(0));
        this.addState(new PlayingState(1));
        this.addState(new LevelMenuState(2));
        this.addState(new PauseMenuState(3));
        this.addState(new GameFinishState(4));
    }

    /**
     * Starts the game and set frame conditions like framerate and title icon.
     * @param args no arguments for this application start.
     */
    public static void main(String[] args){
        AppGameContainer app = null;
        try {
            app = new AppGameContainer(new GameStarter("No beer is ooch No option"));
            //TODO größe evtl. auslagern in util klasse wo all solche größen und zahlend rin stehen
            // größe erstmal so gewählt damit ein Spielfeld hinein passt, aber auch ein rand drum herum ist für Zeitleiste etc.
            app.setDisplayMode(GameUtils.GAME_WIDTH, GameUtils.GAME_HEIGHT, false);
            app.setTargetFrameRate(60);
            app.setVSync(true);
            app.setShowFPS(false);
            app.setIcon("res/img/objects/spaeti_on.png");
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialises all available states and enter the menu state as start screen.
     * @param gameContainer The container where the game is running.
     * @throws SlickException thrown if an error occurs.
     */
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        //init all states
        this.getState(MainMenuState.MAIN_MENU_STATE_ID).init(gameContainer, this);
        this.getState(PlayingState.PLAYING_STATE_ID).init(gameContainer,this);
        this.getState(LevelMenuState.LEVEL_MENU_STATE_ID).init(gameContainer, this);
        this.getState(PauseMenuState.PAUSE_MENU_STATE_ID).init(gameContainer, this);
        this.getState(GameFinishState.Game_FINISH_STATE_ID).init(gameContainer, this);
        // call startup screen
        this.enterState(MainMenuState.MAIN_MENU_STATE_ID);
    }
}
