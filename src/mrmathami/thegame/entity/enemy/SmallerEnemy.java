package mrmathami.thegame.entity.enemy;

import mrmathami.thegame.Config;
import mrmathami.thegame.GameField;

public final class SmallerEnemy extends AbstractEnemy {
    public SmallerEnemy(long createdTick, double posX, double posY) {
        super(createdTick, posX, posY,  Config.SMALLER_ENEMY_SIZE, Config.SMALLER_ENEMY_HEALTH, Config.SMALLER_ENEMY_ARMOR, Config.SMALLER_ENEMY_SPEED, Config.SMALLER_ENEMY_REWARD, Config.SMALLER_ENEMY_DAMAGE);
    }


}
