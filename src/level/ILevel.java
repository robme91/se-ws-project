package level;

import characters.Player;

/**
 * Created by Robin on 08.01.2017.
 * Helper because so you don't need every level in gamestarter
 * TODO wie heißt das pattern?
 */
public interface ILevel {

    /**
     * Get the player of this level.
     * @return The player used in this level.
     */
    Player getPlayer();

    /**
     * The time the player must survive in this level.
     * @return The gametime in seconds
     */
    float getTime();
}
