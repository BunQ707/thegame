package mrmathami.thegame.entity.tile.tower;

import mrmathami.thegame.GameEntities;
import mrmathami.thegame.GameField;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.UpdatableEntity;
import mrmathami.thegame.entity.bullet.AbstractBullet;
import mrmathami.thegame.entity.enemy.AbstractEnemy;
import mrmathami.thegame.entity.tile.AbstractTile;


import java.util.Collection;

public abstract class AbstractTower<E extends AbstractBullet> extends AbstractTile implements UpdatableEntity {
	private final double range;
	private final long speed;

	private long tickDown;

	protected AbstractTower(long createdTick, long posX, long posY, double range, long speed) {
		super(createdTick, posX, posY, 1L, 1L);
		this.range = range;
		this.speed = speed;
		this.tickDown = 0;
	}

	@Override
	public final void onUpdate( GameField field) {
		this.tickDown -= 1;
		if (tickDown <= 0) {
			// TODO: Find a target and spawn a bullet to that direction.
			//Use GameEntities.getFilteredOverlappedEntities to find target in range
			// Remember to set this.tickDown back to this.speed after shooting something.
			// this.tickDown = speed;
			for (AbstractEnemy tar: GameEntities.getFilteredOverlappedEntities(field.getEntities(), AbstractEnemy.class,
					getPosX()+getWidth()/2-range,
					getPosY()+getHeight()/2-range,
					getWidth()+2*range,
					getHeight()+2*range ) )
			{
				if (tar!=null)
				field.doSpawn(this.doSpawn(field.getTickCount(), this.getPosX()+0.25 , this.getPosY()+0.25, -this.getPosX()+tar.getPosX() , -this.getPosY() + tar.getPosY()) );
				break;
			}
			this.tickDown = speed;
		}
	}

	/**
	 * Create a new bullet. Each tower spawn different type of bullet.
	 * Override this method and return the type of bullet that your tower shot out.
	 * See NormalTower for example.
	 *
	 * @param createdTick createdTick
	 * @param posX posX
	 * @param posY posY
	 * @param deltaX deltaX
	 * @param deltaY deltaY
	 * @return the bullet entity
	 */

	protected abstract E doSpawn(long createdTick, double posX, double posY, double deltaX, double deltaY);
}
