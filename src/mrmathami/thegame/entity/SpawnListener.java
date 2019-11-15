package mrmathami.thegame.entity;

import mrmathami.thegame.GameField;



public interface SpawnListener extends GameEntity {
	void onSpawn( GameField field);
}
