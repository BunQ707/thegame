package mrmathami.thegame.entity.menu;

import mrmathami.thegame.GameEntities;
import mrmathami.thegame.GameField;
import mrmathami.thegame.entity.*;
import mrmathami.thegame.entity.tile.Road;
import mrmathami.thegame.Config;
import mrmathami.thegame.entity.tile.Target;

public abstract class AbstractMenu extends AbstractEntity implements  UpdatableEntity, DestroyListener, DestroyableEntity {
    private int status;

    protected AbstractMenu(long createdTick, double posX, double posY, double size, GameEntity entity) {
        super(createdTick, posX , posY, size, size);
        status=1;
    }
    protected AbstractMenu(long createdTick, double posX, double posY, double width, double height) {
        super(createdTick, posX, posY, width, height);
        status=1;
    }
    @Override
    public void onUpdate(GameField field)
    {

    }
    @Override
    public final void doDestroy() {
        status *=-1;
    }

    @Override
    public final boolean isDestroyed() {
        return status <= 0;
    }

    @Override
    public final void onDestroy( GameField field) {
    }


}
