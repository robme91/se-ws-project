package utils;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

/**
 * Created by Robin on 05.01.2017.
 *
 * This util class includes information about the game like size etc.
 * It also contains helper methods for click handling and so on
 */
public class GameUtils {

    /**The size of the game. NOT the size of the gamefield*/
    public static final int GAME_WIDTH = 850;
    public static final int GAME_HEIGHT = 690;


    /**
     * True if if mouse is clicked in given shape
     * @param input
     * @param shape
     * @return
     */
    public static boolean clickedMouseInShape(final Input input, final Shape shape){
        if(input.isMouseButtonDown(0)){
            final int xPos = Mouse.getX();
            final int yPos = Mouse.getY();
            /*TODO iwas mit y logic stimmt nicht benimmt sich so als ob es unten wäre
                wsl muss die game size mit eingerechnet werden
                oder iwas anderes is falsch 
            */
            if((xPos > shape.getX() && xPos < shape.getX() + shape.getWidth())
                    && (yPos > shape.getY() && yPos < shape.getY() + shape.getHeight())){
                return true;
            }
        }
        return false;
    }

    /* isMouseInShape() ... und auf die fenstergröße des gesamten spiels zugreifen*/

}
