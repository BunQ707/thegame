package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.enemy.AbstractEnemy;
import mrmathami.thegame.entity.enemy.NormalEnemy;


public final class NormalEnemyDrawer implements EntityDrawer {

	@Override
	public void draw(long tickCount,  GraphicsContext graphicsContext,  GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
		graphicsContext.setFill(Color.HOTPINK);
		graphicsContext.fillRoundRect(screenPosX, screenPosY, screenWidth, screenHeight, 4, 4);
		// draw health
		graphicsContext.setFill(Color.BLACK);
//		graphicsContext.fillRoundRect(screenPosX, screenPosY-5, (double)((NormalEnemy)entity).getHealth()/100, 5, 4, 4);
		graphicsContext.fillText( Long.toString(((AbstractEnemy)entity).getHealth()), screenPosX, screenPosY-5);
	}
}
