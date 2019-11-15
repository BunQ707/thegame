package mrmathami.thegame.entity;

import mrmathami.thegame.GameField;



public interface DestroyListener extends DestroyableEntity {
	void onDestroy( GameField field);
}
