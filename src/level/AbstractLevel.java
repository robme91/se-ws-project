package level;

import objects.Block;
import objects.NPC;
import objects.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 19.01.17.
 */
public abstract class AbstractLevel {

    private Player player;
    private final List<Block> blocks;
    private final List<NPC> npcs;
    private String levelName;
    private float initialLevelTime;
    private float remainingTime;

    public AbstractLevel() {
        this.blocks = new ArrayList<Block>();
        this.npcs = new ArrayList<NPC>();
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
