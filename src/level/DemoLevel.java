package level;

import objects.GameBorder;
import objects.House;
import objects.NPC;
import objects.Player;
import objects.Street;
import utils.GameUtils;

import java.util.Random;

/**
 * Created by tom on 19.01.17.
 *
 * This implements a demo level to have some data to play with.
 */
public class DemoLevel extends AbstractLevel {

    public DemoLevel() {

        this.levelName = "Demo Level";

        this.initialLevelTime = 500;

        //TODO try if it works with index -1, n+1 for borders
        this.blocks.add(new GameBorder(-GameUtils.GAME_FIELD_HEIGHT / 2, GameUtils
                .GAME_FIELD_HEIGHT / 2, GameUtils.GAME_FIELD_HEIGHT, true));
        this.blocks.add(new GameBorder(GameUtils.GAME_FIELD_WIDTH + GameUtils.GAME_FIELD_HEIGHT /
                2, GameUtils.GAME_FIELD_HEIGHT / 2, GameUtils.GAME_FIELD_HEIGHT, true));
        this.blocks.add(new GameBorder(GameUtils.GAME_FIELD_WIDTH / 2, -GameUtils
                .GAME_FIELD_WIDTH / 2, GameUtils.GAME_FIELD_WIDTH, true));
        this.blocks.add(new GameBorder(GameUtils.GAME_FIELD_WIDTH / 2, GameUtils
                .GAME_FIELD_HEIGHT + GameUtils.GAME_FIELD_WIDTH / 2, GameUtils.GAME_FIELD_WIDTH,
                true));

        // random level init for testing
        for (int x = 0; x < 25; x++) {
            for (int y = 0; y < 20; y++) {
                if (new Random().nextInt(4) > 2) {
                    this.blocks.add(new House(x, y, 32, true));
                } else {
                    this.blocks.add(new Street(x, y, 32, false));
                    if (this.player == null) {
                        this.player = new Player(x, y, 20, true, 1f, 1f);
                    }
                    if (new Random().nextInt(20) >= 19) {
                        this.npcs.add(new NPC(x, y, 20, true, 0.5f));
                    }
                }
            }
        }
    }
}
