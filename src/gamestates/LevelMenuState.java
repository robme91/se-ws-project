package gamestates;

import level.AbstractLevel;
import level.DemoLevel;
import level.TestLevel;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.omg.CORBA.Object;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import utils.GameUtils;

import java.util.*;

/**
 * Created by Robin on 08.01.2017.
 *
 * This is a Menu screen for choosing the level you wanna play.
 * TODO preview images inside the rectangels and above the rectangles the level name then.
 */
public class LevelMenuState extends BasicGameState{

    public static int LEVEL_MENU_STATE_ID;

    private Map<Class<? extends AbstractLevel>, Shape> levelDisplays = new HashMap<>();

    public LevelMenuState(final int stateId){
        LEVEL_MENU_STATE_ID = stateId;
    }

    public int getID() {
        return LEVEL_MENU_STATE_ID;
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        createLevelShapes();
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        renderLevelsWithDescription(g);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        instantiateClickedLevel(game, container.getInput());
    }

    /**
     * Get all levels from level package but the AbstractLevel. Put each level with a appropriate
     * shape together in a map. The shapes get absolute positions and sizes.
     */
    private void createLevelShapes(){
        Reflections reflections = new Reflections("level", new SubTypesScanner(false));
        final Set<Class<? extends AbstractLevel>> levels = reflections.getSubTypesOf(AbstractLevel.class);
        final int initialMargin = 20;
        int counterX = initialMargin;
        final int width = getMaxLevelNameWidth();
        final int xIncrease = width + 20; // plus 20 to get a space between to level boxes
        int counterY = initialMargin;
        final int yIncrease = 50;
        final int height = 30;
        for(Class<? extends AbstractLevel> level : levels){
            if(counterX >= GameUtils.GAME_WIDTH - width){
                counterY += yIncrease;
                counterX = initialMargin;
            }
            if(counterY > GameUtils.GAME_HEIGHT - yIncrease){
                throw new UnsupportedOperationException("Level menu is full." +
                        " Please implement another level menu page to present more levels");
            }
            levelDisplays.put(level, new RoundedRectangle(counterX, counterY, width, height, 8));
            counterX += xIncrease;
        }
    }

    /**
     * Render all appropriate level shapes with the level name in it to given graphic context.
     * @param g The graphic context to render in
     */
    private void renderLevelsWithDescription(Graphics g){
        final int paddingX = 5;
        final int paddingY = 5;
        levelDisplays.forEach((level,shape) ->{
            g.setColor(Color.green);
            g.draw(shape);
            g.setColor(Color.white);
            g.drawString(level.getSimpleName(), shape.getX() + paddingX, shape.getY() + paddingY);
        });
    }

    /**
     * Create an instance of the clicked level.
     * @param game The game to set new game state
     * @param input The user input which includes the click
     */
    private void instantiateClickedLevel(StateBasedGame game, Input input){
        levelDisplays.forEach((levelClass, shape) -> {
            if(GameUtils.clickedMouseInShape(input, shape)){
                try {
                    final AbstractLevel instLevel = levelClass.newInstance();
                    ((PlayingState) game.getState(PlayingState.PLAYING_STATE_ID)).setCurrentLevel(instLevel);
                    game.enterState(PlayingState.PLAYING_STATE_ID);
                } catch (InstantiationException e) {
                    System.err.println("Level could not be instantiated on click in level menu." +
                            " See below error message for more information.");
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    System.err.println("Level class could not be accessed on click in level menu." +
                            " See below error message for more information.");
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Get the maximum width of the available level names and increases for 5 to get a padding between the end of
     * the level name and the surrounding shape. The shapes in the menu should be as wide as this returned value.
     * @return The max length of the level names.
     */
    private int getMaxLevelNameWidth(){
        int maxWidth = 120;
        for(final Class<? extends AbstractLevel> level : levelDisplays.keySet()){
            final int currentWidth = level.getSimpleName().length() * 10 + 3; // for each char count 2 pixels
            if(currentWidth > maxWidth){
                maxWidth = currentWidth;
            }
        }
        return maxWidth;
    }

}
