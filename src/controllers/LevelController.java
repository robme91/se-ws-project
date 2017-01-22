package controllers;

import level.AbstractLevel;
import objects.Block;
import objects.Character;
import objects.Enums;
import objects.GameObject;
import objects.NPC;
import objects.Player;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import utils.GameUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tom on 19.01.17.
 */
public class LevelController {

    // the level
    private AbstractLevel level;

    // all level characters including player
    private List<Character> characters = new ArrayList<Character>();

    // all blocking blocks out of level
    private List<Block> blockingBlocks = new ArrayList<Block>();

    // Needed for doEverySecond()
    private int msSinceLastSecond = 0;

    private boolean IS_PAUSED = false;

    /**
     * @param level
     */
    public LevelController(AbstractLevel level) {
        if (level == null) {
            throw new IllegalArgumentException("Level must not be null!");
        }
        this.level = level;
        initializeLevel(level);
        characters.addAll(level.getNpcs());
        characters.add(level.getPlayer());
        for (Block b : level.getBlocks()) {
            if (b.isBlocking()) {
                this.blockingBlocks.add(b);
            }
        }

        // TODO find street pictures
    }

    /**
     * @param delta
     */
    public void update(int delta) {
        if (!IS_PAUSED) {
            moveCharacters(this.characters, delta);
            msSinceLastSecond += delta;
            if (msSinceLastSecond > 1000) {
                doEverySecond(msSinceLastSecond);
                msSinceLastSecond -= 1000;
            }
        }
    }

    /**
     * @param g
     */
    public void drawOnGraphicsContext(Graphics g) {
        for (Block b : this.level.getBlocks()) {
            drawImageOnGraphicsContext(g, b);
        }

        for (NPC npc : this.level.getNpcs()) {
            drawImageOnGraphicsContext(g, npc);
        }
        drawImageOnGraphicsContext(g, this.level.getPlayer());
    }

    /**
     * Pauses the game
     */
    public void pause() {
        this.IS_PAUSED = true;
    }

    /**
     * Runs the game
     */
    public void play() {
        this.IS_PAUSED = false;
    }

    /**
     * Transforms the initial block indices into pixel coordinates.
     *
     * @param level AbstractLevel Instance
     */
    private void initializeLevel(AbstractLevel level) {
        float blockSize = 32f;
        this.level.setRemainingTime(this.level.getInitialLevelTime());
        for (Block b : this.level.getBlocks()) {
            b.setPos_x((b.getPos_x() + 1) * blockSize - (blockSize / 2));
            b.setPos_y((b.getPos_y() + 1) * blockSize - (blockSize / 2));
        }
        for (NPC n : this.level.getNpcs()) {
            n.setPos_x((n.getPos_x() + 1) * blockSize - (blockSize / 2));
            n.setPos_y((n.getPos_y() + 1) * blockSize - (blockSize / 2));
        }
        Player p = this.level.getPlayer();
        p.setPos_x((p.getPos_x() + 1) * blockSize - (blockSize / 2));
        p.setPos_y((p.getPos_y() + 1) * blockSize - (blockSize / 2));
    }

    /**
     * @param g
     * @param go
     */
    private void drawImageOnGraphicsContext(Graphics g, GameObject go) {
        if (go.getImage() != null) {
            g.drawImage(go.getImage(), go.getPos_x() - go.getSize() / 2, go.getPos_y() - go
                    .getSize() / 2);
        } else {
            //TODO draw error image with size of game object
        }
    }

    /**
     * @param characters
     * @param delta
     */
    private void moveCharacters(List<Character> characters, int delta) {
        for (Character c : characters) {
            if (c.getDirection() != null) {
                float oldX = c.getPos_x();
                float oldY = c.getPos_y();
                float newX = oldX;
                float newY = oldY;
                float xFactor = 0f;
                float yFactor = 0f;
                float factor = delta * .002f * c.getSpeed();
                switch (c.getDirection()) {
                    case UP:
                        yFactor = -1f;
                        break;
                    case DOWN:
                        yFactor = 1f;
                        break;
                    case LEFT:
                        xFactor = -1f;
                        break;
                    case RIGHT:
                        xFactor = 1f;
                        break;
                }
                int steps = ((int) factor) + 1; //make sure that there is at least 1 step
                float stepFactor = factor / (float) steps;
                for (int i = 1; i <= steps; i++) {
                    newX += xFactor * stepFactor;
                    newY += yFactor * stepFactor;
                    // check borders
                    if (newX - c.getSize() / 2 <= 0 || newX + c.getSize() / 2 >= GameUtils
                            .GAME_FIELD_WIDTH || newY - c.getSize() / 2 <= 0 || newY + c.getSize
                            () / 2 >= GameUtils.GAME_FIELD_HEIGHT) {
                    /*
                     * A dummy block is needed since something has to get processed by the
                     * interact method. The interact method is neccessary because of the different
                     * behavior of characters when hitting things. A Player just stops, while
                     * an NPC changes the direction.
                     */
                        c.interact(new Block(0, 0, true));
                        break;
                    }
                    // set new location and test if this is legal
                    c.setLocation(newX, newY);
                    List<GameObject> hitObjects = whichBlocksDoesCharacterHit(c);
                    if (hitObjects != null) {
                        for (GameObject go : hitObjects) {
                            go.interact(c);
                            c.interact(go);
                        }
                        c.setPos_x(newX - xFactor * stepFactor);
                        c.setPos_y(newY - yFactor * stepFactor);
                        break;
                    }
                }
            }
        }
    }

    /**
     * @param c
     * @return
     */
    private List<GameObject> whichBlocksDoesCharacterHit(Character c) {
        List<GameObject> hitObjects = new ArrayList<GameObject>();
        for (GameObject block : blockingBlocks) {
            if (block.isBlocking()) {
                if (block.checkIfHitBy(c.getHitbox())) {
                    hitObjects.add(block);
                }
            }
        }
        for (GameObject character : characters) {  // TODO REMOVE REDUNDANCY
            if (character.isBlocking() && character != c) {
                if (character.checkIfHitBy(c.getHitbox())) {
                    hitObjects.add(character);
                }
            }
        }
        if (hitObjects.isEmpty()) {
            return null;
        } else {
            return hitObjects;
        }
    }


    /**
     * How many time (in percent) is left?
     * @return Intervall [0,100]
     */
    public float getRemainingTimePercentage() {
        return this.level.getRemainingTime() / this.level.getInitialLevelTime();
    }

    /**
     * How many beer (in percent) has the player left?
     * @return Intervall [0,100]
     */
    public float getRemainingBeerPercentage() {
        return getPlayer().getBeerLevel();
    }

    /**
     * @return player object
     */
    public Player getPlayer() {
        return level.getPlayer();
    }

    /**
     * @param direction
     */
    public void setPlayerDirection(Enums.Direction direction) {
        this.level.getPlayer().setDirection(direction);
    }

    /**
     * Gets executed every second
     */
    private void doEverySecond(int msSinceLastSecond) {
        updateAI();
        for (GameObject c : characters) {
            c.secondTick(msSinceLastSecond);
        }
        for (GameObject go : this.level.getBlocks()) {
            go.secondTick(msSinceLastSecond);
        }
        this.level.setRemainingTime(this.level.getRemainingTime() - (float) msSinceLastSecond /
                1000f);
    }

    private void updateAI() {
        for (NPC npc : this.level.getNpcs()) {

            //create line of sight
            Line lineOfSight = new Line(npc.getPos_x(), npc.getPos_y(), getPlayer().getPos_x(),
                    getPlayer().getPos_y());
            // check if player is too far awy
            if (lineOfSight.length() < npc.getSightDistance() * 32) {
                boolean obstacle = false;

                // check if there is a block in the way
                for (Block b : blockingBlocks) {
                    if (b.getHitbox().intersects(lineOfSight)) {
                        obstacle = true;
                        break;
                    }
                }
                if (!obstacle) {
                    Enums.Direction direction = GameUtils.getDirectionFromXY(lineOfSight.getDX(),
                            lineOfSight.getDY());
                    // is npc smart enough?
                    if (new Random().nextInt(100) < npc.getIntelligence()) {
                        npc.setDirection(direction);
                    }
                }
            }
        }
    }
}
