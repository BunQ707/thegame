package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import mrmathami.thegame.entity.GameEntity;


public final class MachineGunTowerDrawer implements EntityDrawer {
	@Override
	public void draw(long tickCount,  GraphicsContext graphicsContext,  GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
		graphicsContext.setFill(Color.DARKRED);
		graphicsContext.fillOval(screenPosX, screenPosY, screenWidth, screenHeight);
	}
}
