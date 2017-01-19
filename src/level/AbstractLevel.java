package level;

import objects.Block;
import objects.NPC;
import objects.Player;

import java.util.List;

/**
 * Created by tom on 19.01.17.
 */
public abstract class AbstractLevel {

    Player player;
    List<Block> blocks;
    List<NPC> npcs;
    String levelName;
    float initialLevelTime;
    float remainingTime;

    public AbstractLevel() {
    }

    public Player getPlayer() {
        return player;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<NPC> getNpcs() {
        return npcs;
    }

    public String getLevelName() {
        return levelName;
    }

    public float getInitialLevelTime() {
        return initialLevelTime;
    }

    public float getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(float remainingTime) {
        this.remainingTime = remainingTime;
    }
}
