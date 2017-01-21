package level;

import objects.House;
import objects.NPC;
import objects.Player;
import objects.Street;

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

        // random level init for testing
        for (int x = 0; x < 25; x++) {
            for (int y = 0; y < 20; y++) {
                if (new Random().nextInt(4) > 2) {
                    this.blocks.add(new House(x, y));
                } else {
                    this.blocks.add(new Street(x, y));
                    if (this.player == null) {
                        this.player = new Player(x, y, 1f, 1f);
                    }
                    if (new Random().nextInt(20) >= 19) {
                        this.npcs.add(new NPC(x, y, 0.5f, "npc"));
                    }
                }
            }
        }
    }
}
