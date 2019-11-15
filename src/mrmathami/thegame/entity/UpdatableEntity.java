package mrmathami.thegame.entity;

import mrmathami.thegame.GameField;



public interface UpdatableEntity extends GameEntity {
	void onUpdate( GameField field);
}
