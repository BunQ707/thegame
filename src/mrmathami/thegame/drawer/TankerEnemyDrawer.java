package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.enemy.AbstractEnemy;


public final class TankerEnemyDrawer implements EntityDrawer {
	@Override
	public void draw(long tickCount,  GraphicsContext graphicsContext,  GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
		graphicsContext.setFill(Color.VIOLET);
		graphicsContext.fillRoundRect(screenPosX, screenPosY, screenWidth, screenHeight, 4, 4);
		graphicsContext.setFill(Color.BLACK);
//		graphicsContext.fillRoundRect(screenPosX, screenPosY-5, (double)((NormalEnemy)entity).getHealth()/100, 5, 4, 4);
		graphicsContext.fillText( Long.toString(((AbstractEnemy)entity).getHealth()), screenPosX, screenPosY-5);
	}
}
