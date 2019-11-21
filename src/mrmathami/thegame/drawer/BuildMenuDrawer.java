package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrmathami.thegame.Config;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.menu.AbstractMenu;

public final class BuildMenuDrawer implements EntityDrawer {
    @Override
    public void draw(long tickCount, GraphicsContext graphicsContext, GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom)
    {
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(4);
        graphicsContext.strokeRect(screenPosX+4, screenPosY+4, screenWidth-8, screenHeight-8);
        graphicsContext.strokeRect(screenPosX+4- Config.TILE_SIZE, screenPosY+4, screenWidth-8, screenHeight-8);
        graphicsContext.strokeRect(screenPosX+4+ Config.TILE_SIZE, screenPosY+4, screenWidth-8, screenHeight-8);
        graphicsContext.strokeRect(screenPosX+4, screenPosY+4- Config.TILE_SIZE, screenWidth-8, screenHeight-8);
        graphicsContext.strokeRect(screenPosX+4, screenPosY+4+ Config.TILE_SIZE, screenWidth-8, screenHeight-8);

    }
}

