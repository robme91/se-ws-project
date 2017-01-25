package utils;

import level.AbstractLevel;
import objects.Enums;
import objects.Street;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robin on 05.01.2017.
 * <p>
 * This util class includes information about the game like size etc.
 * It also contains helper methods for click handling and so on
 */
public class GameUtils {

    /**
     * The size of the game. NOT the size of the gamefield
     */
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 690;

    /**
     * The size of the game field/world
     */
    public static final int GAME_FIELD_WIDTH = 800;
    public static final int GAME_FIELD_HEIGHT = 640;


    public static final int GAME_STARTET_POS_X = 250;
    public static final int GAME_STARTET_POS_Y = 300;

    /**
     * All levels those are possible to play. With a value if they are already won once.
     * So if you play next level, you get a non-won level to play.
     */
    public static Map<Class<? extends AbstractLevel>, Boolean> WON_LEVELS = new HashMap<>();

    /**
     * True if if mouse is clicked in given shape
     *
     * @param input The input in the gamecontainer
     * @param shape The shape where the click shall be checked
     * @return True if mouse clicked inside the given shape
     */
    public static boolean clickedMouseInShape(final Input input, final Shape shape) {
        if (input.isMouseButtonDown(0)) {
            final int xPos = Mouse.getX();
            final int yPos = Mouse.getY();
            final float yUpBorder = GAME_HEIGHT - shape.getY();
            final float yDownBorder = GAME_HEIGHT - shape.getY() - shape.getHeight();
            if ((xPos > shape.getX() && xPos < shape.getX() + shape.getWidth()) && (yPos >
                    yDownBorder && yPos < yUpBorder)) {
                return true;
            }
        }
        return false;
    }
    /* isMouseInShape() ... und auf die fenstergröße des gesamten spiels zugreifen*/

    /**
     * Takes a dX and dY (starting at 0) and tells in which discrete direction
     * this vector points to.
     * For example does x = -100, and y = 0 point to the left.
     *
     * @return Most satisfing direction
     */
    public static Enums.Direction getDirectionFromXY(float x, float y) {
        if (Math.abs(x) >= Math.abs(y)) {
            if (x < 0) {
                return Enums.Direction.LEFT;
            } else {
                return Enums.Direction.RIGHT;
            }
        } else {
            if (y < 0) {
                return Enums.Direction.UP;
            } else {
                return Enums.Direction.DOWN;
            }
        }
    }

    /**
     * All image resources have their front at the top.
     * Input direction here and get rotation angle.
     *
     * @param direction which direction should we rotate to?
     * @return Angle in degrees
     */
    public static float getImageRotationFromDirection(Enums.Direction direction) {
        float rotation;
        switch (direction) {
            case RIGHT:
                rotation = 90f;
                break;
            case DOWN:
                rotation = 180f;
                break;
            case LEFT:
                rotation = 270f;
                break;
            default:
                rotation = 0f;
        }
        return rotation;
    }

    /**
     * Clamp method
     *
     * @param min   min value
     * @param max   max value
     * @param value value
     * @return value, or min or max
     */
    public static float clamp(float min, float max, float value) {
        return Math.min(max, Math.max(min, value));
    }

    /**
     * Clamp method
     *
     * @param min   min value
     * @param max   max value
     * @param value value
     * @return value, or min or max
     */
    public static int clamp(int min, int max, int value) {
        return Math.min(max, Math.max(min, value));
    }

    /**
     * Sets the street type of street objects inplace.
     * This only works before map initialization, because it relies on index coordinates.
     *
     * @param s         Street
     * @param streetMap Bitmap
     */
    public static void setStreetType(Street s, boolean[][] streetMap) {
        int x = (int) s.getPos_x();
        int y = (int) s.getPos_y();
        boolean T;
        boolean L;
        boolean R;
        boolean B;
        try {
            T = streetMap[x][y - 1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            T = false;
        }
        try {
            L = streetMap[x - 1][y];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            L = false;
        }
        try {
            R = streetMap[x + 1][y];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            R = false;
        }
        try {
            B = streetMap[x][y + 1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            B = false;
        }

        if (T && L && R && B) {/*4CROSS*/
            s.setDirection(Enums.Direction.UP);
            s.setStreetType(Enums.StreetType.CROSS4);
        }
        if (T && L && R && !B) {/*3 - 0*/
            s.setDirection(Enums.Direction.UP);
            s.setStreetType(Enums.StreetType.CROSS3);
        }
        if (T && L && !R && B) {/*3 - 270*/
            s.setDirection(Enums.Direction.LEFT);
            s.setStreetType(Enums.StreetType.CROSS3);
        }
        if (T && L && !R && !B) {/*bend 180*/
            s.setDirection(Enums.Direction.DOWN);
            s.setStreetType(Enums.StreetType.BEND);
        }
        if (T && !L && R && B) {/*3 - 90*/
            s.setDirection(Enums.Direction.RIGHT);
            s.setStreetType(Enums.StreetType.CROSS3);
        }
        if (T && !L && R && !B) {/*bend 270*/
            s.setDirection(Enums.Direction.LEFT);
            s.setStreetType(Enums.StreetType.BEND);
        }
        if (T && !L && !R && B) {/*straight - 0*/
            s.setDirection(Enums.Direction.UP);
            s.setStreetType(Enums.StreetType.STRAIGHT);
        }
        if (T && !L && !R && !B) {/*1 - 0*/
            s.setDirection(Enums.Direction.UP);
            s.setStreetType(Enums.StreetType.END);
        }
        if (!T && L && R && B) {/*3 - 180*/
            s.setDirection(Enums.Direction.DOWN);
            s.setStreetType(Enums.StreetType.CROSS3);
        }
        if (!T && L && R && !B) {/*straight - 90*/
            s.setDirection(Enums.Direction.RIGHT);
            s.setStreetType(Enums.StreetType.STRAIGHT);
        }
        if (!T && L && !R && B) {/*bend - 90*/
            s.setDirection(Enums.Direction.RIGHT);
            s.setStreetType(Enums.StreetType.BEND);
        }
        if (!T && L && !R && !B) {/*1 - 270*/
            s.setDirection(Enums.Direction.LEFT);
            s.setStreetType(Enums.StreetType.END);
        }
        if (!T && !L && R && B) {/*bend - 0*/
            s.setDirection(Enums.Direction.UP);
            s.setStreetType(Enums.StreetType.BEND);
        }
        if (!T && !L && R && !B) {/*1 - 90*/
            s.setDirection(Enums.Direction.RIGHT);
            s.setStreetType(Enums.StreetType.END);
        }
        if (!T && !L && !R && B) {/*1 - 180*/
            s.setDirection(Enums.Direction.DOWN);
            s.setStreetType(Enums.StreetType.END);
        }
        if (!T && !L && !R && !B) {/*0*/
            s.setDirection(Enums.Direction.UP);
            s.setStreetType(Enums.StreetType.SOLO);
        }
    }

    /**
     * Sets the status of the given level to won, so it wouldn't be taken if the player chose next level.
     * @param level The level that was won.
     */
    public static void setLevelWon(Class level){
        WON_LEVELS.replace(level, true);
    }

    public static boolean isLevelWon(Class level){
        return WON_LEVELS.get(level);
    }

    /**
     * Get the next not-won level.
     * @return The class object of the next level in the map, that is not won.
     *      May return null if all levels are won.
     */
    public static Class getNextLevel(){
        Class nextLevel = null;
        for(Map.Entry<Class<? extends AbstractLevel>, Boolean> tupel: WON_LEVELS.entrySet()){
            if(!tupel.getValue()){
                nextLevel = tupel.getKey();
            }
        }
        return nextLevel;
    }

    /**
     * Reset all level states to isWon==false, to start from scratch.
     */
    public static void resetLevelWonStates(){
        for(Map.Entry<Class<? extends AbstractLevel>, Boolean> tupel: WON_LEVELS.entrySet()){
            tupel.setValue(false);
        }
    }
}
