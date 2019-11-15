package mrmathami.thegame.entity;

import mrmathami.thegame.GameField;



public interface EffectEntity extends DestroyableEntity {
	boolean onEffect( GameField field,  LivingEntity livingEntity);
}
