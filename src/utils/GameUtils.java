package utils;

import objects.Enums;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

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
    public static final int GAME_WIDTH = 850;
    public static final int GAME_HEIGHT = 690;

    /**
     * The size of the game field/world
     */
    public static final int GAME_FIELD_WIDTH = 800;
    public static final int GAME_FIELD_HEIGHT = 640;


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

    /* isMouseInShape() ... und auf die fenstergröße des gesamten spiels zugreifen*/
}
