package main;

import gamestates.LevelMenuState;
import gamestates.MainMenuState;
import gamestates.PlayingState;
import level.ILevel;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import utils.GameUtils;

/**
 * Main Menu of the game and starts it
 * Created by Robin on 08.12.2016.
 */
public class GameStarter extends StateBasedGame {

    public GameStarter(String name) {
        super(name);
        this.addState(new MainMenuState(0));
        this.addState(new PlayingState(1));
        this.addState(new LevelMenuState(2));
    }

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
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException {
        //init all states
        this.getState(MainMenuState.MAIN_MENU_STATE_ID).init(gameContainer, this);
        this.getState(PlayingState.PLAYING_STATE_ID).init(gameContainer,this);
        this.getState(LevelMenuState.LEVEL_MENU_STATE_ID).init(gameContainer, this);
        // call startup screen
        this.enterState(MainMenuState.MAIN_MENU_STATE_ID);
    }
}
