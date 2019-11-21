package mrmathami.thegame.entity.menu;

import mrmathami.thegame.Config;
import mrmathami.thegame.GameController;
import mrmathami.thegame.GameField;

public class NormalIcon extends AbstractMenu {
    public NormalIcon(long createdTick, double posX, double posY)
    {
        super(createdTick, posX, posY, Config.TILE_SIZE/30, Config.TILE_SIZE/30);
    }
    @Override
    public void onUpdate(GameField field)
    {
        setPosX(GameController.staticMousePx );
        setPosY(GameController.staticMousePy );
    }
//    public static void update()
//    {
//        setPosX(GameController.staticMousePx );
//        setPosY(GameController.staticMousePy );
//    }
}
