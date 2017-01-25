package controllers;

import level.AbstractLevel;
import objects.Block;
import objects.Character;
import objects.Enums;
import objects.GameObject;
import objects.NPC;
import objects.Player;
import objects.Street;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import utils.GameUtils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Creates an instance of AbstractLevel and offers controls
 */
public class LevelController {

    /**
     * Instance created out of level class
     */
    private AbstractLevel level;

    /**
     * Contains all level characters including the player.
     */
    private Set<Character> characters = new HashSet<>();

    /**
     * Contains all blocking blocks from the level
     */
    private Set<Block> blockingBlocks = new HashSet<>();

    /**
     * If true, the level won´t receive any updates
     */
    private boolean IS_PAUSED = false;

    // Needed for doEverySecond()
    private int msSinceLastSecond = 0;

    /**
     * Initializes a new LevelController
     *
     * @param levelClass Class from which a LevelInstance should get created
     */
    public LevelController(Class levelClass) {
        AbstractLevel level = null;
        try {
            level = (AbstractLevel) levelClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.err.println("Level instantiation failed for class " + levelClass.getName());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.err.println("Could not access class " + levelClass.getName());
        }
        this.level = level;
        initializeLevel();
        this.level.setRemainingTime(this.level.getInitialLevelTime());
        characters.addAll(level.getNpcs());
        characters.add(level.getPlayer());
        for (Block b : level.getBlocks()) {
            if (b.isBlocking()) {
                this.blockingBlocks.add(b);
            }
        }
    }

    /**
     * Handles updates from the PlayingState
     *
     * @param delta Time in ms since last update call
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
     * Draws level on a given Graphic context
     *
     * @param g Graphic context
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
     * Checks if player is still alive (has beer left)
     *
     * @return true if alive, false if not
     */
    public boolean isPlayerDead() {
        return getPlayer().getBeerLevel() <= 0f;
    }

    /**
     * Checks if level time is up
     *
     * @return true if time is up, false if not
     */
    public boolean isTimeUp() {
        return this.level.getRemainingTime() <= 0f;
    }

    /**
     * How many time (in percent) is left?
     *
     * @return Intervall [0,1]
     */
    public float getRemainingTimePercentage() {
        return this.level.getRemainingTime() / this.level.getInitialLevelTime();
    }

    /**
     * How many beer (in percent) has the player left?
     *
     * @return Intervall [0,1]
     */
    public float getRemainingBeerPercentage() {
        return getPlayer().getBeerLevel() / 100;
    }

    /**
     * Get Player reference out of level instance
     *
     * @return Player object
     */
    public Player getPlayer() {
        return level.getPlayer();
    }

    /**
     * Sets the Players direction
     *
     * @param direction new direction
     */
    public void setPlayerDirection(Enums.Direction direction) {
        this.level.getPlayer().setDirection(direction);
    }

    /**
     * Transforms the initial block indices within the level into pixel coordinates and sets the
     * streetMap for finding the correct street assets accoording to their neighbours.
     */
    private void initializeLevel() {
        float blockSize = 32f;
        // before setting coordinates figure out which street type to use.
        boolean[][] streetMap = new boolean[25][20];
        for (Block b : this.level.getBlocks()) {
            int x = (int) b.getPos_x();
            int y = (int) b.getPos_y();
            streetMap[x][y] = false;
            if (b.getClass().equals(Street.class)) {
                streetMap[x][y] = true;
            }
        }
        for (Block b : this.level.getBlocks()) {
            if (b.getClass().equals(Street.class)) {
                GameUtils.setStreetType((Street) b, streetMap);
            }
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
     * Draws the image of a GameObject onto one given Graphic context
     *
     * @param g  Graphic context to draw on
     * @param go GameOjct to draw
     */
    private void drawImageOnGraphicsContext(Graphics g, GameObject go) {
        if (go.getImage() != null) {
            g.drawImage(go.getImage(), go.getPos_x() - go.getSize() / 2, go.getPos_y() - go
                    .getSize() / 2);
        }
    }

    /**
     * Performs character movement
     *
     * @param characters Set of characters that should move
     * @param delta      time in ms since last call
     */
    private void moveCharacters(Set<Character> characters, int delta) {
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
                    Set<GameObject> hitObjects = whichBlocksDoesCharacterHit(c);
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
     * Checks if a character does hit a blocking block or another character
     *
     * @param c Character to check
     * @return a set of GameObjects that the character intersects with
     */
    private Set<GameObject> whichBlocksDoesCharacterHit(Character c) {
        Set<GameObject> hitObjects = new HashSet<>();
        for (GameObject block : blockingBlocks) {
            if (block.isBlocking()) {
                if (block.checkIfHitBy(c.getHitbox())) {
                    hitObjects.add(block);
                }
            }
        }
        for (GameObject character : characters) {
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

    /**
     * Performs AI calculation for all NPC´s
     */
    private void updateAI() {
        for (NPC npc : this.level.getNpcs()) {
            //create line of sight
            Line lineOfSight = new Line(npc.getPos_x(), npc.getPos_y(), getPlayer().getPos_x(), getPlayer().getPos_y());
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
                    Enums.Direction direction = GameUtils.getDirectionFromXY(lineOfSight.getDX(), lineOfSight.getDY());
                    // is npc smart enough?
                    if (new Random().nextInt(100) < npc.getIntelligence()) {
                        npc.setDirection(direction);
                    }
                }
            }
        }
    }
}
