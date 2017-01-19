package controllers;

import level.AbstractLevel;
import objects.Block;
import objects.Character;
import objects.GameObject;
import objects.NPC;
import objects.Player;
import org.newdawn.slick.Graphics;
import utils.GameUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 19.01.17.
 */
public class LevelController {

    private AbstractLevel level;
    private List<Character> characters = new ArrayList<Character>(); // all level characters including player
    private List<Block> blockingBlocks = new ArrayList<Block>(); // all blocking blocks out of the level

    public LevelController(AbstractLevel level) {
        if(level == null) {
            throw new IllegalArgumentException("Level must not be null!");
        }
        this.level = level;
        characters.addAll(level.getNpcs());
        characters.add(level.getPlayer());
        for (Block b : level.getBlocks()) {
            if (b.isBlocking()) {
                this.blockingBlocks.add(b);
            }
        }

        // TODO set initial block positions according to GameSize
        // TODO find street pictures
        // TODO Set initial random directions for NPC
    }

    public void drawOnGraphicsContext(Graphics g) {
        for (Block b : this.level.getBlocks()) {
            if (b.getImage() != null) {
                g.drawImage(b.getImage(), b.getPos_x() - b.getSize() / 2, b.getPos_y() - b
                        .getSize() / 2);
            }
        }

        for (NPC npc : this.level.getNpcs()) {
            g.drawImage(npc.getImage(), npc.getPos_x() - npc.getSize() / 2, npc.getPos_y() - npc
                    .getSize() / 2);
        }

        Player p = this.level.getPlayer();
        g.drawImage(p.getImage(), p.getPos_x() - p.getSize() / 2, p.getPos_y() - p.getSize() / 2);
    }

    private void moveCharacters(List<Character> characters, int delta) {
        for (Character c : characters) {
            if (c.getDirection() != null) {
                float oldX = c.getPos_x();
                float oldY = c.getPos_y();
                float newX = oldX;
                float newY = oldY;
                float factor = delta * .1f * c.getSpeed();
                switch (c.getDirection()) {
                    case UP:
                        newY = oldY - factor;
                        break;
                    case DOWN:
                        newY = oldY + factor;
                        break;
                    case LEFT:
                        newX = oldX - factor;
                        break;
                    case RIGHT:
                        newX = oldX + factor;
                        break;
                }
                // set new location and test if this is legal
                c.setLocation(newX, newY);  // TODO set to closest hitpoint
                List<GameObject> hitObjects = whichBlocksDoesCharacterHit(c);
                if (hitObjects != null) {
                    for (GameObject go : hitObjects) {
                        go.interact(c);
                        c.interact(go);
                    }
                    c.setLocation(oldX, oldY);
                }
            }
        }
    }

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

    public void update(int delta) {
        moveCharacters(this.characters, delta);

        // TODO STUB
        // TODO Call doEverySecond()
    }

    /**
     * @return Intervall [0,1]
     */
    public float getRemainingTime() {
        //TODO STUB
        return 0f;
    }

    /**
     * @return Intervall [0,1]
     */
    public float getRemainingBeer() {
        //TODO STUB
        return 0f;
    }

    public Player getPlayer() {
        return level.getPlayer();
    }

    public void setPlayerDirection(GameUtils.Direction direction) {
        if (direction != null) {
            this.level.getPlayer().setDirection(direction);
        }
    }

    /**
     * Gets executed every second
     */
    private void doEverySecond() {
        // TODO UPDATE KI
        // TODO SET REMAINING TIME
        // TODO Reduce Player beer level
        /*
        Try to see player within sightDistance (without blocking blocks in between.
        If so, then apply direction change with a probability of intelligence where:
        0 = do not listen to that daaaamn controller
        100 = always do what the controller sez
         */
    }
}
