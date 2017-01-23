package level;

import objects.Enums;
import objects.House;
import objects.NPC;
import objects.Player;
import objects.Spaeti;
import objects.Street;

import java.util.Random;

/**
 * Created by tom on 19.01.17.
 * <p>
 * This implements a demo level to have some data to play with.
 */
public class DemoLevel extends AbstractLevel {

    public DemoLevel() {

        this.levelName = "Demo Level";

        this.initialLevelTime = 500;

        // random level init for testing
        for (int x = 0; x < 25; x++) {
            for (int y = 0; y < 20; y++) {
                if (new Random().nextInt(4) > 2) {
                    if (new Random().nextInt(4) > 2) {
                        this.blocks.add(new Spaeti(x, y, Enums.Direction.LEFT));
                    } else {
                        this.blocks.add(new House(x, y));
                    }
                } else {
                    this.blocks.add(new Street(x, y));
                    if (this.player == null) {
                        this.player = new Player(x, y, 40f, 100f, 1f);
                    }
                    if (new Random().nextInt(20) >= 19) {
                        this.npcs.add(new NPC(x, y, 20f, "npc", 24, 50, Enums.AttackTarget
                                .PLAYER_SPEED, 50));
                    }
                }
            }
        }
    }
}
