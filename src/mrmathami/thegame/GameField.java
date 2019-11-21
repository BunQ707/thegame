package mrmathami.thegame;


import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import mrmathami.thegame.entity.*;
import mrmathami.thegame.entity.enemy.NormalEnemy;
import mrmathami.thegame.entity.menu.BuildMenu;
import mrmathami.thegame.entity.tile.tower.NormalTower;


import java.util.*;

/**
 * Game Field. Created from GameMap for each new stage. Represent the currently playing game.
 */
public final class GameField {

	private final Set<GameEntity> entities = new LinkedHashSet<>(Config._TILE_MAP_COUNT);
	 private final Collection<GameEntity> unmodifiableEntities = Collections.unmodifiableCollection(entities);
	 private final List<GameEntity> spawnEntities = new ArrayList<>(Config._TILE_MAP_COUNT);

	/**
	 * Field width
	 */
	private final double width;
	/**
	 * Field height
	 */
	private final double height;
	/**
	 * Field tick count
	 */
	private long tickCount;
	private int[][] mapLoaded;
	private long reward;


	static public Image NormalBullet;
//	static public Image MachineGunBullet;



	public GameField( GameStage gameStage) {
		this.width = gameStage.getWidth();
		this.height = gameStage.getHeight();
		this.tickCount = 0;
		this.mapLoaded = gameStage.getMap();
		entities.addAll(gameStage.getEntities());
		reward = 100;
		NormalBullet=new Image("NormalBullet.png");
//		MachineGunBullet= new Image("MachineGunBullet.png");
	}
    public long getReward() { return reward;}

    public void setReward(long reward) { this.reward+= reward;}

	public final double getWidth() {
		return width;
	}

	public final double getHeight() {
		return height;
	}

	public final long getTickCount() {
		return tickCount;
	}

    public final int[][] getMap() {return mapLoaded; }
    public final int getMapValAtXY(int posX, int posY) {return mapLoaded[posX][posY]; }


	/**
	 * @return entities on the field. Read-only list.
	 */

	public final Collection<GameEntity> getEntities() {
		return unmodifiableEntities;
	}

	/**
	 * Add an Entity to spawn list. Entity will be spawned at the end of this tick.
	 *
	 * @param entity Entity to spawn
	 */
	public final void doSpawn( GameEntity entity) {
		if (entity.isBeingOverlapped(0.0, 0.0, width, height)) spawnEntities.add(entity);
	}

	/**
	 * Do a tick, in other words, update the field after a fixed period of time.
	 * Current update sequence:
	 * 1. Update Entity:
	 * 1.1. UpdatableEntity update itself, including moving.
	 * 1.2. EffectEntity check collision to affect LivingEntity.
	 * 1.3. DestroyableEntity check and react if it is going to be destroyed.
	 * 2. Destroy Entity:
	 * 2.1. Destroy entities that are marked to be destroyed.
	 * 2.2. Destroy entities that are outside the field.
	 * 3. Spawn Entity: Add entities that are marked to be spawned.
	 */

	public final void tick() {

		this.tickCount += 1;

		//doSpawn(new NormalEnemy(0,13,2));
		// 1.1. Update UpdatableEntity
		for (final GameEntity entity : entities) {
			if (entity instanceof UpdatableEntity) ((UpdatableEntity) entity).onUpdate(this);
		}

		// 1.2. Update EffectEntity & LivingEntity
		for (final GameEntity entity : entities) {
			if (entity instanceof EffectEntity) {
				final EffectEntity effectEntity = (EffectEntity) entity;
				final Collection<LivingEntity> livingEntities = GameEntities.getAffectedEntities(entities,
						effectEntity.getClass(), entity.getPosX(), entity.getPosY(), entity.getWidth(), entity.getHeight());
				for (final LivingEntity livingEntity : livingEntities) {
					if (!effectEntity.onEffect(this, livingEntity)) break;
				}
			}
		}

		// 1.3. Update DestroyableEntity
		final List<GameEntity> destroyedEntities = new ArrayList<>(Config._TILE_MAP_COUNT);
		for (final GameEntity entity : entities) {
			if (entity instanceof DestroyableEntity && ((DestroyableEntity) entity).isDestroyed()) {
				if (entity instanceof DestroyListener) ((DestroyListener) entity).onDestroy(this);
				destroyedEntities.add(entity);
			}
		}

		// 2.1. Destroy entities
		entities.removeAll(destroyedEntities);

		// 2.2. Destroy entities
		entities.removeIf(entity -> !entity.isBeingOverlapped(0.0, 0.0, width, height));

		// 3. Spawn entities
		for (GameEntity entity : spawnEntities) {
			entities.add(entity);
			if (entity instanceof SpawnListener) ((SpawnListener) entity).onSpawn(this);
		}

		spawnEntities.clear();

	}
//	public void buildRequest(MouseEvent mouseEvent, int a, int b)
//	{
//		Rectangle lft =new Rectangle(a-1,b,1,1);
//		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent e) {
//				doSpawn(new NormalTower(getTickCount(), a,b));
//			}
//		};
//		lft.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
//	//	doSpawn(new NormalTower(getTickCount(), a,b));
//	}


}
