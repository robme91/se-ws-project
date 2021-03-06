package gamestates;

import level.AbstractLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import utils.GameUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This is a Menu screen for choosing the level you wanna play.
 * Grey levels are won and green levels are not won.
 * Reset this colors if all games are won.
 */
public class LevelMenuState extends BasicGameState {

    /**The id of this state*/
    public static int LEVEL_MENU_STATE_ID;
    /**The level class objects and their displaying shapes*/
    private Map<Class<? extends AbstractLevel>, Shape> levelDisplays = new HashMap<>();
    /**The background image of this screen*/
    private Image backgroundImage;

    /**
     * Creates a new instance of this with given id.
     * @param stateId The id for this state.
     */
    public LevelMenuState(final int stateId) {
        LEVEL_MENU_STATE_ID = stateId;
    }

    @Override
    public int getID() {
        return LEVEL_MENU_STATE_ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        createLevelShapes();
        backgroundImage = new Image("/res/img/levelmenu.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws
            SlickException {
        g.drawImage(backgroundImage, 0, 0);
        renderLevelsWithDescription(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws
            SlickException {
        instantiateClickedLevel(game, container.getInput());
    }

    /**
     * Set all levels to not won if all are won.
     */
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        if(!GameUtils.WON_LEVELS.isEmpty() && GameUtils.getNextLevel() == null){
            GameUtils.resetLevelWonStates();
        }
    }

    /**
     * Get all levels from level package but the AbstractLevel. Put each level with a appropriate
     * shape together in a map. The shapes get absolute positions and sizes.
     */
    private void createLevelShapes() {
        Reflections reflections = new Reflections("level", new SubTypesScanner(false));
        final Set<Class<? extends AbstractLevel>> levels = reflections.getSubTypesOf
                (AbstractLevel.class);
        final int initialMargin = 20;
        int counterX = initialMargin;
        final int width = getMaxLevelNameWidth();
        final int xIncrease = width + 20; // plus 20 to get a space between to level boxes
        int counterY = initialMargin;
        final int yIncrease = 50;
        final int height = 30;
        for (Class<? extends AbstractLevel> level : levels) {
            if (counterX >= GameUtils.GAME_WIDTH - width) {
                counterY += yIncrease;
                counterX = initialMargin;
            }
            if (counterY > GameUtils.GAME_HEIGHT - yIncrease) {
                throw new UnsupportedOperationException("Level menu is full." + " Please " +
                        "implement another level menu page to present more levels");
            }
            levelDisplays.put(level, new RoundedRectangle(counterX, counterY, width, height, 8));
            GameUtils.WON_LEVELS.put(level, false); // add initial all levels but no one is won at this time
            counterX += xIncrease;
        }
    }

    /**
     * Render all appropriate level shapes with the level name in it to given graphic context.
     *
     * @param g The graphic context to render in
     */
    private void renderLevelsWithDescription(Graphics g) {
        final int paddingX = 5;
        final int paddingY = 5;
        levelDisplays.forEach((level, shape) -> {
            if(GameUtils.isLevelWon(level)){
                g.setColor(Color.gray);
            }else{
                g.setColor(Color.green);
            }
            g.draw(shape);
            g.setColor(Color.white);
            g.drawString(level.getSimpleName(), shape.getX() + paddingX, shape.getY() + paddingY);
        });
    }

    /**
     * Create an instance of the clicked level.
     *
     * @param game  The game to set new game state
     * @param input The user input which includes the click
     */
    private void instantiateClickedLevel(StateBasedGame game, Input input) {
        levelDisplays.forEach((levelClass, shape) -> {
            if (GameUtils.clickedMouseInShape(input, shape)) {
                ((PlayingState) game.getState(PlayingState.PLAYING_STATE_ID)).setCurrentLevel
                        (levelClass);
                game.enterState(PlayingState.PLAYING_STATE_ID);

            }
        });
    }

    /**
     * Get the maximum width of the available level names and increases for 5 to get a padding
     * between the end of
     * the level name and the surrounding shape. The shapes in the menu should be as wide as this
     * returned value.
     *
     * @return The max length of the level names.
     */
    private int getMaxLevelNameWidth() {
        int maxWidth = 120;
        for (final Class<? extends AbstractLevel> level : levelDisplays.keySet()) {
            final int currentWidth = level.getSimpleName().length() * 10 + 3; // for each char
            // count 2 pixels
            if (currentWidth > maxWidth) {
                maxWidth = currentWidth;
            }
        }
        return maxWidth;
    }

}
