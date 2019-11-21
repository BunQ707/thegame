package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrmathami.thegame.entity.GameEntity;

public class NormalTowerIconDrawer implements EntityDrawer{
    @Override
    public void draw(long tickCount, GraphicsContext graphicsContext, GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(screenPosX, screenPosY, screenWidth, screenHeight);
    }
}
