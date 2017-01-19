package level;

import objects.Character;
import objects.Player;
import org.newdawn.slick.Graphics;

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

    public void drawOnGraphicContext(Graphics g);

    public void update(int delta);
}
