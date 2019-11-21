package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import mrmathami.thegame.GameField;
import mrmathami.thegame.entity.GameEntity;


public final class NormalBulletDrawer implements EntityDrawer {
	private final RadialGradient gradient = new RadialGradient(
			0.0,
			0.0,
			0.5,
			0.5,
			5.0,//1.0
			true,
			CycleMethod.NO_CYCLE,
			new Stop(0.0, Color.YELLOW),
			new Stop(0.5, Color.RED),
			new Stop(1.0, Color.WHITE)
	);

	@Override
	public void draw(long tickCount,  GraphicsContext graphicsContext,  GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
//		graphicsContext.setFill(gradient);
//		graphicsContext.fillOval(screenPosX, screenPosY, screenWidth+3, screenHeight+3);
        graphicsContext.drawImage(GameField.NormalBullet,screenPosX-10, screenPosY-10, screenWidth+20, screenHeight+20);


	}
}
