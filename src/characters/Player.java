package characters;

import org.newdawn.slick.geom.Circle;

/**
 * Created by Robin on 08.01.2017.
 * This class represents the player. It includes the avatar, speed and other properties of the player.
 * TODO maybe put also scores and names in here for persisting some stuff or so
 */
public class Player {

    //TODO replace with animation or image like in tutorial
    private Circle avatar;

    /**
     * Start Coordinates of the avatar. Must be final, so if you reset the player or restart game,
     * its always the same coordinates
     */
    private final float startCoordinateX;
    private final float startCoordinateY;

    /*The speed of the player*/
    private float speed;

    /**
     * Creates the player with given properties.
     * @param startCordX The X-Coordinate where the player is spawned.
     * @param startCordY The Y-Coordinate where the player is spawned.
     * TODO put the image/animation or so also in here
     */
    public Player (final float startCordX, final float startCordY, final float speed){
        this.avatar = new Circle(startCordX, startCordY, 20);
        this.startCoordinateX = startCordX;
        this.startCoordinateY = startCordY;
        //TODO think about a good range e.g. speed could be 1-10
        if(speed <= 0){
            this.speed = 1;
        }else {
            this.speed = speed;
        }
    }

    public Circle getAvatar() {
        return avatar;
    }

    public void setAvatar(Circle avatar) {
        this.avatar = avatar;
    }

    public float getStartCoordinateX() {
        return startCoordinateX;
    }

    public float getStartCoordinateY() {
        return startCoordinateY;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
