package mrmathami.thegame.entity.enemy;

import mrmathami.thegame.GameEntities;
import mrmathami.thegame.GameField;
import mrmathami.thegame.entity.*;
import mrmathami.thegame.entity.tile.Road;
import mrmathami.thegame.Config;
import mrmathami.thegame.entity.tile.Target;


import java.util.Collection;

public abstract class AbstractEnemy extends AbstractEntity implements UpdatableEntity, EffectEntity, LivingEntity, DestroyListener {
	private static final double SQRT_2 = Math.sqrt(2.0) / 2.0;
	private static final double[][] DELTA_DIRECTION_ARRAY = {
			{0.0, -1.0}, {0.0, 1.0}, {-1.0, 0.0}, {1.0, 0.0},
			{-SQRT_2, -SQRT_2}, {SQRT_2, SQRT_2}, {SQRT_2, -SQRT_2}, {-SQRT_2, SQRT_2},
	};

	private long health;
	private long armor;
	private double speed;
	private long reward;
	private long damage;

	private int status;

	protected AbstractEnemy(long createdTick, double posX, double posY, double size, long health, long armor, double speed, long reward, long damage) {
		super(createdTick, posX, posY, size, size);
		this.health = health;
		this.armor = armor;
		this.speed = speed;
		this.reward = reward;
		this.damage= damage;
	}

	private static double evaluateDistance( Collection<GameEntity> overlappableEntities,
			 GameEntity sourceEntity, double posX, double posY, double width, double height) {
		double distance = 0.0;
		double sumArea = 0.0;
		for (GameEntity entity : GameEntities.getOverlappedEntities(overlappableEntities, posX, posY, width, height)) {
			if (sourceEntity != entity && GameEntities.isCollidable(sourceEntity.getClass(), entity.getClass())) return Double.NaN;
			if (entity instanceof Road) {
				final double entityPosX = entity.getPosX();
				final double entityPosY = entity.getPosY();
				final double area = (Math.min(posX + width, entityPosX + entity.getWidth()) - Math.max(posX, entityPosX))
						* (Math.min(posY + height, entityPosY + entity.getHeight()) - Math.max(posY, entityPosY));
				sumArea += area;
				distance += area * ((Road) entity).getDistance();
			}
		}
		return distance / sumArea;
	}

    public final void goPikachu(GameField field)
    {

//        int x= (int) getPosX();
//        System.out.println(getPosX());
//        int y= (int) getPosY();
//        System.out.println(getPosY());
//        int roadValue= field.getMapValAtXY(x,y);
//        System.out.println("road found: "+roadValue);
        if (getPosX()%2==0 || (getPosX()+1)%2==0)
			if (getPosY()%2==0 || (getPosY()+1)%2==0)
			{
				int x= (int) getPosX();
//				System.out.println(getPosX()+" now x: "+x);
				int y= (int) getPosY();
//				System.out.println(getPosY()+" now y: "+y);
				status= field.getMapValAtXY(x,y);
			}

        switch (status)
        {
            case 1:
                setPosX( (double) Math.round( (getPosX()-speed)*100 )/100 );
                break;
            case 3:
                setPosX( (double)Math.round( (getPosX()+speed)*100 )/100 );
                break;
            case 2:
                //Math.round( (getPosY()+speed)*10 )/10
                setPosY( (double)Math.round( (getPosY()-speed)*100 )/100 );
                break;
            case 4:
                setPosY((double)Math.round( (getPosY()+speed)*100 )/100);
                break;

        }
    }
	@Override
	public final void onUpdate( GameField field) {
	    goPikachu(field);
//		final double enemyPosX = getPosX();
//		final double enemyPosY = getPosY();
//		final double enemyWidth = getWidth();
//		final double enemyHeight = getHeight();
//		final Collection<GameEntity> overlappableEntities = GameEntities.getOverlappedEntities(field.getEntities(),
//				getPosX() - speed, getPosY() - speed, speed + getWidth() + speed, speed + getHeight() + speed);
//		double minimumDistance = Double.MAX_VALUE;
//		double newPosX = enemyPosX;
//		double newPosY = enemyPosY;
//		for (double realSpeed = speed * 0.125; realSpeed <= speed; realSpeed += realSpeed) {
//			for (double[] deltaDirection : DELTA_DIRECTION_ARRAY) {
//				final double currentPosX = enemyPosX + deltaDirection[0] * realSpeed;
//				final double currentPosY = enemyPosY + deltaDirection[1] * realSpeed;
//				final double currentDistance = evaluateDistance(overlappableEntities, this, currentPosX, currentPosY, enemyWidth, enemyHeight);
//				if (currentDistance < minimumDistance) {
//					minimumDistance = currentDistance;
//					newPosX = currentPosX;
//					newPosY = currentPosY;
//				}
//			}
//		}
//		setPosX(newPosX);
//		setPosY(newPosY);
	}

	@Override
	public final void onDestroy( GameField field) {
		// TODO: reward
		field.setReward(reward);
	}

	@Override
	public final boolean onEffect( GameField field,  LivingEntity livingEntity) {
		// TODO: harm the target
        ((Target)livingEntity).doEffect(damage);
		this.health = Long.MIN_VALUE;
		return false;
	}

	@Override
	public final long getHealth() {
		return health;
	}

	@Override
	public final void doEffect(long value) {
		if (health != Long.MIN_VALUE && (value < -armor || value > 0))
		{
			this.health += armor + value;
//			System.out.println("health: "+health);
		}
	}

	@Override
	public final void doDestroy() {
		this.health = Long.MIN_VALUE;
	}

	@Override
	public final boolean isDestroyed() {
		return health <= 0L;
	}
}
