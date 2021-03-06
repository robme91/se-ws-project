package level;

import objects.Block;
import objects.NPC;
import objects.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tom on 19.01.17.
 */
public abstract class AbstractLevel {

    protected Player player;
    protected final Set<Block> blocks;
    protected final Set<NPC> npcs;
    protected String levelName;
    protected float initialLevelTime;
    protected float remainingTime;

    public AbstractLevel() {
        this.blocks = new HashSet<Block>();
        this.npcs = new HashSet<NPC>();
    }

    public Player getPlayer() {
        return player;
    }

    public Set<Block> getBlocks() {
        return blocks;
    }

    public Set<NPC> getNpcs() {
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
