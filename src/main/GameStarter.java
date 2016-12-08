package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Main Menu of the game and starts it
 * Created by Robin on 08.12.2016.
 */
public class GameStarter extends StateBasedGame {

    public static void main(String[] args){
        AppGameContainer app = null;
        try {
            app = new AppGameContainer(new GameStarter("No beer is ooch No option"));
            app.setDisplayMode(600, 600, false);
            app.setTargetFrameRate(60);
            app.setVSync(true);
            app.setShowFPS(false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public GameStarter(String name) {
        super(name);
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException {

    }
}
