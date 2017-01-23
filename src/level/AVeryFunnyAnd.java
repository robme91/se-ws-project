package level;

import objects.*;

/** Generated Level class by LevelGenerator from .nbno file*/
public class AVeryFunnyAnd extends AbstractLevel {
public AVeryFunnyAnd() {
super();
this.levelName = "AVeryFunnyAndCoolLevel";
this.initialLevelTime = 60;
this.player = new Player(11, 19, 70f, 4f, 50f);
this.npcs.add(new NPC(16, 1, 40f, "Schnorrer", 10, 30, Enums.AttackTarget.DRINK, 30));

this.npcs.add(new NPC(20, 3, 40f, "Schnorrer", 10, 30, Enums.AttackTarget.DRINK, 30));

this.npcs.add(new NPC(2, 6, 40f, "Schnorrer", 10, 30, Enums.AttackTarget.DRINK, 30));

this.npcs.add(new NPC(23, 6, 25f, "Polizist", 24, 40, Enums.AttackTarget.PLAYER_SPEED, 70));

this.npcs.add(new NPC(20, 7, 40f, "Schnorrer", 10, 30, Enums.AttackTarget.DRINK, 30));

this.npcs.add(new NPC(23, 16, 25f, "Polizist", 24, 40, Enums.AttackTarget.PLAYER_SPEED, 70));

this.blocks.add(new House(0, 0));

this.blocks.add(new House(1, 0));

this.blocks.add(new House(2, 0));

this.blocks.add(new House(3, 0));

this.blocks.add(new House(4, 0));

this.blocks.add(new House(5, 0));

this.blocks.add(new House(6, 0));

this.blocks.add(new House(7, 0));

this.blocks.add(new House(8, 0));

this.blocks.add(new House(9, 0));

this.blocks.add(new House(9, 1));

this.blocks.add(new House(9, 2));

this.blocks.add(new House(14, 2));

this.blocks.add(new House(15, 2));

this.blocks.add(new House(16, 2));

this.blocks.add(new House(0, 3));

this.blocks.add(new House(1, 3));

this.blocks.add(new House(3, 3));

this.blocks.add(new House(4, 3));

this.blocks.add(new House(5, 3));

this.blocks.add(new House(9, 3));

this.blocks.add(new House(14, 3));

this.blocks.add(new House(15, 3));

this.blocks.add(new House(16, 3));

this.blocks.add(new House(9, 4));

this.blocks.add(new House(14, 4));

this.blocks.add(new House(15, 4));

this.blocks.add(new House(16, 4));

this.blocks.add(new House(9, 5));

this.blocks.add(new House(14, 5));

this.blocks.add(new House(16, 5));

this.blocks.add(new House(9, 6));

this.blocks.add(new House(9, 7));

this.blocks.add(new House(9, 8));

this.blocks.add(new House(12, 8));

this.blocks.add(new House(9, 9));

this.blocks.add(new House(12, 9));

this.blocks.add(new House(9, 10));

this.blocks.add(new House(12, 10));

this.blocks.add(new House(13, 10));

this.blocks.add(new House(14, 10));

this.blocks.add(new House(19, 10));

this.blocks.add(new House(20, 10));

this.blocks.add(new House(9, 11));

this.blocks.add(new House(20, 11));

this.blocks.add(new House(20, 12));

this.blocks.add(new House(9, 13));

this.blocks.add(new House(15, 13));

this.blocks.add(new House(20, 13));

this.blocks.add(new House(9, 14));

this.blocks.add(new House(15, 14));

this.blocks.add(new House(18, 14));

this.blocks.add(new House(20, 14));

this.blocks.add(new House(9, 15));

this.blocks.add(new House(15, 15));

this.blocks.add(new House(18, 15));

this.blocks.add(new House(20, 15));

this.blocks.add(new House(9, 16));

this.blocks.add(new House(9, 17));

this.blocks.add(new Street(10, 0));

this.blocks.add(new Street(11, 0));

this.blocks.add(new Street(12, 0));

this.blocks.add(new Street(13, 0));

this.blocks.add(new Street(14, 0));

this.blocks.add(new Street(15, 0));

this.blocks.add(new Street(16, 0));

this.blocks.add(new Street(17, 0));

this.blocks.add(new Street(18, 0));

this.blocks.add(new Street(19, 0));

this.blocks.add(new Street(20, 0));

this.blocks.add(new Street(21, 0));

this.blocks.add(new Street(22, 0));

this.blocks.add(new Street(23, 0));

this.blocks.add(new Street(24, 0));

this.blocks.add(new Street(0, 1));

this.blocks.add(new Street(1, 1));

this.blocks.add(new Street(2, 1));

this.blocks.add(new Street(3, 1));

this.blocks.add(new Street(4, 1));

this.blocks.add(new Street(5, 1));

this.blocks.add(new Street(6, 1));

this.blocks.add(new Street(7, 1));

this.blocks.add(new Street(8, 1));

this.blocks.add(new Street(10, 1));

this.blocks.add(new Street(11, 1));

this.blocks.add(new Street(12, 1));

this.blocks.add(new Street(13, 1));

this.blocks.add(new Street(14, 1));

this.blocks.add(new Street(15, 1));

this.blocks.add(new Street(16, 1));

this.blocks.add(new Street(17, 1));

this.blocks.add(new Street(18, 1));

this.blocks.add(new Street(19, 1));

this.blocks.add(new Street(20, 1));

this.blocks.add(new Street(21, 1));

this.blocks.add(new Street(22, 1));

this.blocks.add(new Street(23, 1));

this.blocks.add(new Street(24, 1));

this.blocks.add(new Street(0, 2));

this.blocks.add(new Street(1, 2));

this.blocks.add(new Street(2, 2));

this.blocks.add(new Street(3, 2));

this.blocks.add(new Street(4, 2));

this.blocks.add(new Street(5, 2));

this.blocks.add(new Street(6, 2));

this.blocks.add(new Street(7, 2));

this.blocks.add(new Street(8, 2));

this.blocks.add(new Street(10, 2));

this.blocks.add(new Street(11, 2));

this.blocks.add(new Street(12, 2));

this.blocks.add(new Street(13, 2));

this.blocks.add(new Street(17, 2));

this.blocks.add(new Street(18, 2));

this.blocks.add(new Street(19, 2));

this.blocks.add(new Street(20, 2));

this.blocks.add(new Street(21, 2));

this.blocks.add(new Street(22, 2));

this.blocks.add(new Street(23, 2));

this.blocks.add(new Street(24, 2));

this.blocks.add(new Street(6, 3));

this.blocks.add(new Street(7, 3));

this.blocks.add(new Street(8, 3));

this.blocks.add(new Street(10, 3));

this.blocks.add(new Street(11, 3));

this.blocks.add(new Street(12, 3));

this.blocks.add(new Street(13, 3));

this.blocks.add(new Street(17, 3));

this.blocks.add(new Street(18, 3));

this.blocks.add(new Street(19, 3));

this.blocks.add(new Street(20, 3));

this.blocks.add(new Street(21, 3));

this.blocks.add(new Street(22, 3));

this.blocks.add(new Street(23, 3));

this.blocks.add(new Street(24, 3));

this.blocks.add(new Street(0, 4));

this.blocks.add(new Street(1, 4));

this.blocks.add(new Street(2, 4));

this.blocks.add(new Street(3, 4));

this.blocks.add(new Street(4, 4));

this.blocks.add(new Street(5, 4));

this.blocks.add(new Street(6, 4));

this.blocks.add(new Street(7, 4));

this.blocks.add(new Street(8, 4));

this.blocks.add(new Street(10, 4));

this.blocks.add(new Street(11, 4));

this.blocks.add(new Street(12, 4));

this.blocks.add(new Street(13, 4));

this.blocks.add(new Street(17, 4));

this.blocks.add(new Street(18, 4));

this.blocks.add(new Street(19, 4));

this.blocks.add(new Street(20, 4));

this.blocks.add(new Street(21, 4));

this.blocks.add(new Street(22, 4));

this.blocks.add(new Street(23, 4));

this.blocks.add(new Street(24, 4));

this.blocks.add(new Street(0, 5));

this.blocks.add(new Street(1, 5));

this.blocks.add(new Street(2, 5));

this.blocks.add(new Street(3, 5));

this.blocks.add(new Street(4, 5));

this.blocks.add(new Street(5, 5));

this.blocks.add(new Street(6, 5));

this.blocks.add(new Street(7, 5));

this.blocks.add(new Street(8, 5));

this.blocks.add(new Street(10, 5));

this.blocks.add(new Street(11, 5));

this.blocks.add(new Street(12, 5));

this.blocks.add(new Street(13, 5));

this.blocks.add(new Street(17, 5));

this.blocks.add(new Street(18, 5));

this.blocks.add(new Street(19, 5));

this.blocks.add(new Street(20, 5));

this.blocks.add(new Street(21, 5));

this.blocks.add(new Street(22, 5));

this.blocks.add(new Street(23, 5));

this.blocks.add(new Street(24, 5));

this.blocks.add(new Street(0, 6));

this.blocks.add(new Street(1, 6));

this.blocks.add(new Street(2, 6));

this.blocks.add(new Street(3, 6));

this.blocks.add(new Street(4, 6));

this.blocks.add(new Street(5, 6));

this.blocks.add(new Street(6, 6));

this.blocks.add(new Street(7, 6));

this.blocks.add(new Street(8, 6));

this.blocks.add(new Street(10, 6));

this.blocks.add(new Street(11, 6));

this.blocks.add(new Street(12, 6));

this.blocks.add(new Street(13, 6));

this.blocks.add(new Street(14, 6));

this.blocks.add(new Street(15, 6));

this.blocks.add(new Street(16, 6));

this.blocks.add(new Street(17, 6));

this.blocks.add(new Street(18, 6));

this.blocks.add(new Street(19, 6));

this.blocks.add(new Street(20, 6));

this.blocks.add(new Street(21, 6));

this.blocks.add(new Street(22, 6));

this.blocks.add(new Street(23, 6));

this.blocks.add(new Street(24, 6));

this.blocks.add(new Street(0, 7));

this.blocks.add(new Street(1, 7));

this.blocks.add(new Street(2, 7));

this.blocks.add(new Street(3, 7));

this.blocks.add(new Street(4, 7));

this.blocks.add(new Street(5, 7));

this.blocks.add(new Street(6, 7));

this.blocks.add(new Street(7, 7));

this.blocks.add(new Street(8, 7));

this.blocks.add(new Street(10, 7));

this.blocks.add(new Street(11, 7));

this.blocks.add(new Street(12, 7));

this.blocks.add(new Street(13, 7));

this.blocks.add(new Street(14, 7));

this.blocks.add(new Street(15, 7));

this.blocks.add(new Street(16, 7));

this.blocks.add(new Street(17, 7));

this.blocks.add(new Street(18, 7));

this.blocks.add(new Street(19, 7));

this.blocks.add(new Street(20, 7));

this.blocks.add(new Street(21, 7));

this.blocks.add(new Street(22, 7));

this.blocks.add(new Street(23, 7));

this.blocks.add(new Street(24, 7));

this.blocks.add(new Street(0, 8));

this.blocks.add(new Street(1, 8));

this.blocks.add(new Street(2, 8));

this.blocks.add(new Street(3, 8));

this.blocks.add(new Street(4, 8));

this.blocks.add(new Street(5, 8));

this.blocks.add(new Street(6, 8));

this.blocks.add(new Street(7, 8));

this.blocks.add(new Street(8, 8));

this.blocks.add(new Street(10, 8));

this.blocks.add(new Street(11, 8));

this.blocks.add(new Street(13, 8));

this.blocks.add(new Street(14, 8));

this.blocks.add(new Street(15, 8));

this.blocks.add(new Street(16, 8));

this.blocks.add(new Street(17, 8));

this.blocks.add(new Street(18, 8));

this.blocks.add(new Street(19, 8));

this.blocks.add(new Street(20, 8));

this.blocks.add(new Street(21, 8));

this.blocks.add(new Street(22, 8));

this.blocks.add(new Street(23, 8));

this.blocks.add(new Street(24, 8));

this.blocks.add(new Street(0, 9));

this.blocks.add(new Street(1, 9));

this.blocks.add(new Street(2, 9));

this.blocks.add(new Street(3, 9));

this.blocks.add(new Street(4, 9));

this.blocks.add(new Street(5, 9));

this.blocks.add(new Street(6, 9));

this.blocks.add(new Street(7, 9));

this.blocks.add(new Street(8, 9));

this.blocks.add(new Street(10, 9));

this.blocks.add(new Street(11, 9));

this.blocks.add(new Street(13, 9));

this.blocks.add(new Street(14, 9));

this.blocks.add(new Street(15, 9));

this.blocks.add(new Street(16, 9));

this.blocks.add(new Street(17, 9));

this.blocks.add(new Street(18, 9));

this.blocks.add(new Street(19, 9));

this.blocks.add(new Street(20, 9));

this.blocks.add(new Street(21, 9));

this.blocks.add(new Street(22, 9));

this.blocks.add(new Street(23, 9));

this.blocks.add(new Street(24, 9));

this.blocks.add(new Street(0, 10));

this.blocks.add(new Street(1, 10));

this.blocks.add(new Street(2, 10));

this.blocks.add(new Street(3, 10));

this.blocks.add(new Street(4, 10));

this.blocks.add(new Street(5, 10));

this.blocks.add(new Street(6, 10));

this.blocks.add(new Street(7, 10));

this.blocks.add(new Street(8, 10));

this.blocks.add(new Street(10, 10));

this.blocks.add(new Street(11, 10));

this.blocks.add(new Street(15, 10));

this.blocks.add(new Street(16, 10));

this.blocks.add(new Street(17, 10));

this.blocks.add(new Street(18, 10));

this.blocks.add(new Street(21, 10));

this.blocks.add(new Street(22, 10));

this.blocks.add(new Street(23, 10));

this.blocks.add(new Street(24, 10));

this.blocks.add(new Street(0, 11));

this.blocks.add(new Street(1, 11));

this.blocks.add(new Street(2, 11));

this.blocks.add(new Street(3, 11));

this.blocks.add(new Street(4, 11));

this.blocks.add(new Street(5, 11));

this.blocks.add(new Street(6, 11));

this.blocks.add(new Street(7, 11));

this.blocks.add(new Street(8, 11));

this.blocks.add(new Street(10, 11));

this.blocks.add(new Street(11, 11));

this.blocks.add(new Street(12, 11));

this.blocks.add(new Street(13, 11));

this.blocks.add(new Street(14, 11));

this.blocks.add(new Street(15, 11));

this.blocks.add(new Street(16, 11));

this.blocks.add(new Street(17, 11));

this.blocks.add(new Street(18, 11));

this.blocks.add(new Street(19, 11));

this.blocks.add(new Street(21, 11));

this.blocks.add(new Street(22, 11));

this.blocks.add(new Street(23, 11));

this.blocks.add(new Street(24, 11));

this.blocks.add(new Street(0, 12));

this.blocks.add(new Street(1, 12));

this.blocks.add(new Street(2, 12));

this.blocks.add(new Street(3, 12));

this.blocks.add(new Street(4, 12));

this.blocks.add(new Street(5, 12));

this.blocks.add(new Street(6, 12));

this.blocks.add(new Street(7, 12));

this.blocks.add(new Street(8, 12));

this.blocks.add(new Street(10, 12));

this.blocks.add(new Street(11, 12));

this.blocks.add(new Street(12, 12));

this.blocks.add(new Street(13, 12));

this.blocks.add(new Street(14, 12));

this.blocks.add(new Street(15, 12));

this.blocks.add(new Street(16, 12));

this.blocks.add(new Street(17, 12));

this.blocks.add(new Street(18, 12));

this.blocks.add(new Street(19, 12));

this.blocks.add(new Street(21, 12));

this.blocks.add(new Street(22, 12));

this.blocks.add(new Street(23, 12));

this.blocks.add(new Street(24, 12));

this.blocks.add(new Street(0, 13));

this.blocks.add(new Street(1, 13));

this.blocks.add(new Street(2, 13));

this.blocks.add(new Street(3, 13));

this.blocks.add(new Street(4, 13));

this.blocks.add(new Street(5, 13));

this.blocks.add(new Street(6, 13));

this.blocks.add(new Street(7, 13));

this.blocks.add(new Street(8, 13));

this.blocks.add(new Street(10, 13));

this.blocks.add(new Street(11, 13));

this.blocks.add(new Street(12, 13));

this.blocks.add(new Street(13, 13));

this.blocks.add(new Street(14, 13));

this.blocks.add(new Street(16, 13));

this.blocks.add(new Street(17, 13));

this.blocks.add(new Street(18, 13));

this.blocks.add(new Street(19, 13));

this.blocks.add(new Street(21, 13));

this.blocks.add(new Street(22, 13));

this.blocks.add(new Street(23, 13));

this.blocks.add(new Street(24, 13));

this.blocks.add(new Street(0, 14));

this.blocks.add(new Street(1, 14));

this.blocks.add(new Street(2, 14));

this.blocks.add(new Street(3, 14));

this.blocks.add(new Street(4, 14));

this.blocks.add(new Street(5, 14));

this.blocks.add(new Street(6, 14));

this.blocks.add(new Street(7, 14));

this.blocks.add(new Street(8, 14));

this.blocks.add(new Street(10, 14));

this.blocks.add(new Street(11, 14));

this.blocks.add(new Street(12, 14));

this.blocks.add(new Street(13, 14));

this.blocks.add(new Street(14, 14));

this.blocks.add(new Street(16, 14));

this.blocks.add(new Street(17, 14));

this.blocks.add(new Street(19, 14));

this.blocks.add(new Street(21, 14));

this.blocks.add(new Street(22, 14));

this.blocks.add(new Street(23, 14));

this.blocks.add(new Street(24, 14));

this.blocks.add(new Street(0, 15));

this.blocks.add(new Street(1, 15));

this.blocks.add(new Street(2, 15));

this.blocks.add(new Street(3, 15));

this.blocks.add(new Street(4, 15));

this.blocks.add(new Street(5, 15));

this.blocks.add(new Street(6, 15));

this.blocks.add(new Street(7, 15));

this.blocks.add(new Street(8, 15));

this.blocks.add(new Street(10, 15));

this.blocks.add(new Street(11, 15));

this.blocks.add(new Street(12, 15));

this.blocks.add(new Street(13, 15));

this.blocks.add(new Street(14, 15));

this.blocks.add(new Street(16, 15));

this.blocks.add(new Street(17, 15));

this.blocks.add(new Street(19, 15));

this.blocks.add(new Street(21, 15));

this.blocks.add(new Street(22, 15));

this.blocks.add(new Street(23, 15));

this.blocks.add(new Street(24, 15));

this.blocks.add(new Street(0, 16));

this.blocks.add(new Street(1, 16));

this.blocks.add(new Street(2, 16));

this.blocks.add(new Street(3, 16));

this.blocks.add(new Street(4, 16));

this.blocks.add(new Street(5, 16));

this.blocks.add(new Street(6, 16));

this.blocks.add(new Street(7, 16));

this.blocks.add(new Street(8, 16));

this.blocks.add(new Street(10, 16));

this.blocks.add(new Street(11, 16));

this.blocks.add(new Street(12, 16));

this.blocks.add(new Street(13, 16));

this.blocks.add(new Street(14, 16));

this.blocks.add(new Street(16, 16));

this.blocks.add(new Street(17, 16));

this.blocks.add(new Street(18, 16));

this.blocks.add(new Street(19, 16));

this.blocks.add(new Street(20, 16));

this.blocks.add(new Street(21, 16));

this.blocks.add(new Street(22, 16));

this.blocks.add(new Street(23, 16));

this.blocks.add(new Street(24, 16));

this.blocks.add(new Street(0, 17));

this.blocks.add(new Street(1, 17));

this.blocks.add(new Street(2, 17));

this.blocks.add(new Street(3, 17));

this.blocks.add(new Street(4, 17));

this.blocks.add(new Street(5, 17));

this.blocks.add(new Street(6, 17));

this.blocks.add(new Street(7, 17));

this.blocks.add(new Street(8, 17));

this.blocks.add(new Street(10, 17));

this.blocks.add(new Street(11, 17));

this.blocks.add(new Street(12, 17));

this.blocks.add(new Street(13, 17));

this.blocks.add(new Street(14, 17));

this.blocks.add(new Street(15, 17));

this.blocks.add(new Street(16, 17));

this.blocks.add(new Street(17, 17));

this.blocks.add(new Street(18, 17));

this.blocks.add(new Street(19, 17));

this.blocks.add(new Street(20, 17));

this.blocks.add(new Street(21, 17));

this.blocks.add(new Street(22, 17));

this.blocks.add(new Street(23, 17));

this.blocks.add(new Street(24, 17));

this.blocks.add(new Street(0, 18));

this.blocks.add(new Street(1, 18));

this.blocks.add(new Street(2, 18));

this.blocks.add(new Street(3, 18));

this.blocks.add(new Street(4, 18));

this.blocks.add(new Street(5, 18));

this.blocks.add(new Street(6, 18));

this.blocks.add(new Street(7, 18));

this.blocks.add(new Street(8, 18));

this.blocks.add(new Street(9, 18));

this.blocks.add(new Street(10, 18));

this.blocks.add(new Street(11, 18));

this.blocks.add(new Street(12, 18));

this.blocks.add(new Street(13, 18));

this.blocks.add(new Street(14, 18));

this.blocks.add(new Street(15, 18));

this.blocks.add(new Street(16, 18));

this.blocks.add(new Street(17, 18));

this.blocks.add(new Street(18, 18));

this.blocks.add(new Street(19, 18));

this.blocks.add(new Street(20, 18));

this.blocks.add(new Street(21, 18));

this.blocks.add(new Street(22, 18));

this.blocks.add(new Street(23, 18));

this.blocks.add(new Street(24, 18));

this.blocks.add(new Street(0, 19));

this.blocks.add(new Street(1, 19));

this.blocks.add(new Street(2, 19));

this.blocks.add(new Street(3, 19));

this.blocks.add(new Street(4, 19));

this.blocks.add(new Street(5, 19));

this.blocks.add(new Street(6, 19));

this.blocks.add(new Street(7, 19));

this.blocks.add(new Street(8, 19));

this.blocks.add(new Street(9, 19));

this.blocks.add(new Street(10, 19));

this.blocks.add(new Street(11, 19));

this.blocks.add(new Street(12, 19));

this.blocks.add(new Street(13, 19));

this.blocks.add(new Street(14, 19));

this.blocks.add(new Street(15, 19));

this.blocks.add(new Street(16, 19));

this.blocks.add(new Street(17, 19));

this.blocks.add(new Street(18, 19));

this.blocks.add(new Street(19, 19));

this.blocks.add(new Street(20, 19));

this.blocks.add(new Street(21, 19));

this.blocks.add(new Street(22, 19));

this.blocks.add(new Street(23, 19));

this.blocks.add(new Street(24, 19));

this.blocks.add(new Spaeti(2, 3, Enums.Direction.UP));

this.blocks.add(new Spaeti(15, 5, Enums.Direction.DOWN));

this.blocks.add(new Spaeti(9, 12, Enums.Direction.LEFT));

this.blocks.add(new Spaeti(15, 16, Enums.Direction.RIGHT));

}
}