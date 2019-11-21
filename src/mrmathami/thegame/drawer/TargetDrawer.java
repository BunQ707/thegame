package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrmathami.thegame.entity.GameEntity;

import mrmathami.thegame.entity.tile.Target;


public final class TargetDrawer implements EntityDrawer {
	@Override
	public void draw(long tickCount,  GraphicsContext graphicsContext,  GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
		graphicsContext.setStroke(Color.BLACK);
		graphicsContext.setLineWidth(4);
		graphicsContext.strokeRect(screenPosX, screenPosY, screenWidth, screenHeight);

		graphicsContext.setFill(Color.BLACK);

		graphicsContext.fillText( Long.toString(((Target)entity).getHealth()), screenPosX, screenPosY-5);
	}
}
