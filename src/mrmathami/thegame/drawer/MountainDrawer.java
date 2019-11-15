package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrmathami.thegame.drawer.EntityDrawer;
import mrmathami.thegame.entity.GameEntity;


public final class MountainDrawer implements EntityDrawer {
	@Override
	public void draw(long tickCount,  GraphicsContext graphicsContext,  GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
		graphicsContext.setFill(Color.DARKGREEN);
		graphicsContext.fillRect(screenPosX, screenPosY, screenWidth, screenHeight);
	}
}
