package gamestates;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Robin on 05.01.2017.
 *
 * This is the Screen where the real game is played
 */
public class PlayingSate extends BasicGameState{

    public static final int PLAYING_STATE_ID = 1;

    private final int GAME_FIELD_WIDTH = 800;
    private final int GAME_FIELD_HEIGHT = 640;

    /*Set to true if quit menu shall popup*/
    private boolean quit;

    /*Movement fields for loop movement*/
    private boolean movePlayerUp = false;
    private boolean movePlayerDown = false;
    private boolean movePlayerLeft = false;
    private boolean movePlayerRight = false;

    private RoundedRectangle player;
    private float playerPosX = GAME_FIELD_WIDTH/2;
    private float playerPosY = GAME_FIELD_HEIGHT/2;

    //just for dev
    private String playerPosDisplay = "";


    public int getID() {
        return PLAYING_STATE_ID;
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        player = new RoundedRectangle(playerPosX, playerPosY,20, 20, 3);
        quit = false;
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString(playerPosDisplay, 20, 650);
        g.setColor(Color.red);
        player.setLocation(playerPosX, playerPosY);
        g.fill(player);
        g.setColor(Color.white);
        //gamefield separator
        g.drawLine(0, GAME_FIELD_HEIGHT, GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
        g.drawLine(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT, GAME_FIELD_WIDTH, 0);

        if(quit == true){
            g.drawString("Resume (R)", 250, 100);
            g.drawString("Menu (M)", 250, 150);
            g.drawString("Quit (Q)", 250, 200);
            if(quit == false){
                g.clear();
            }
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        playerPosDisplay = "xPos: " + playerPosX + " ,yPos: " + playerPosY;
        final Input input = container.getInput();

        final float playerSpeed = delta * .1f; //TODO hier dann sobald player klasse steht das speed einarbeiten
        /*movement of the player*/
        //up
        if(input.isKeyDown(Input.KEY_UP)){
            stopPlayerMovements();
            movePlayerUp = true;
        }
        //down
        if(input.isKeyDown(Input.KEY_DOWN)){
            stopPlayerMovements();
            movePlayerDown = true;
        }
        //left
        if(input.isKeyDown(Input.KEY_LEFT)){
            stopPlayerMovements();
            movePlayerLeft = true;
        }
        //right
        if(input.isKeyDown(Input.KEY_RIGHT)){
            stopPlayerMovements();
            movePlayerRight = true;
        }

        handlePlayerMovements(playerSpeed);

        //escape or resume game logic
        if(input.isKeyDown(Input.KEY_ESCAPE)){
            stopPlayerMovements();
            quit = true;
        }
        if(quit == true){
            if(input.isKeyDown(Input.KEY_R)){
                quit = false;
            }else if(input.isKeyDown(Input.KEY_M)){
                quit = false;
                game.enterState(MainMenuState.MAIN_MENU_STATE_ID);
            }else if(input.isKeyDown(Input.KEY_Q)){
                System.exit(0);
            }
            //TODO maybe put a restart current level in here
        }
    }

    /**
     * Helper method to turn off all movements
     */
    private void stopPlayerMovements(){
        movePlayerUp = false;
        movePlayerDown = false;
        movePlayerLeft = false;
        movePlayerRight = false;
    }

    /**
     * Helper method to move the player and make the update method smaller
     * @param playerSpeed The speed of the player
     */
    private void handlePlayerMovements(final float playerSpeed){
        if(movePlayerUp){
            if(playerPosY <= 0){
                playerPosY += playerSpeed;
                stopPlayerMovements();
            }else{
                playerPosY -= playerSpeed;
            }
        }
        if(movePlayerDown){
            if(playerPosY >= GAME_FIELD_HEIGHT - 20){ //wegen Spielerfigurgröße -20
                playerPosY -= playerSpeed;
                stopPlayerMovements();
            }else{
                playerPosY += playerSpeed;
            }
        }
        if(movePlayerLeft){
            if(playerPosX <= 0){
                playerPosX += playerSpeed;
                stopPlayerMovements();
            }else{
                playerPosX -= playerSpeed;
            }
        }
        if(movePlayerRight){
            if(playerPosX >= GAME_FIELD_WIDTH - 20){ //-20 wegen der Spielfigurgröße
                playerPosX -= playerSpeed;
                stopPlayerMovements();
            }else{
                playerPosX += playerSpeed;
            }
        }
    }
}
