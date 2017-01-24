package objects;

/**
 * Enums
 */
public class Enums {

    /**
     * Enum to specify the attack target of an npc
     */
    public enum AttackTarget {
        DRINK, PLAYER_SPEED
    }

    /**
     * Enum for directions
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Enum for stret types
     */
    public enum StreetType {
        SOLO, STRAIGHT, END, BEND, CROSS3, CROSS4
    }
}
