package mrmathami.thegame;

public final class Config {
	/**
	 * Game name. Change it if you want.
	 */
	public static final String GAME_NAME = "E-One";
	/**
	 * Ticks per second
	 */
	public static final long GAME_TPS = 30;
	/**
	 * Nanoseconds per tick
	 */
	public static final long GAME_NSPT = Math.round(1000000000.0 / GAME_TPS);

	/**
	 * Size of the tile, in pixel.
	 * 1.0 field unit == TILE_SIZE pixel on the screen.
	 * Change it base on your texture size.
	 */
	public static final long TILE_SIZE = 30;
	/**
	 * Num of tiles the screen can display if fieldZoom is TILE_SIZE,
	 * in other words, the texture will be display as it without scaling.
	 */
	public static final long TILE_HORIZONTAL = 30;
	/**
	 * Num of tiles the screen can display if fieldZoom is TILE_SIZE,
	 * in other words, the texture will be display as it without scaling.
	 */
	public static final long TILE_VERTICAL = 17;//map size is 18 so the rest is for items
	/**
	 * An arbitrary number just to make some code run a little faster.
	 * Do not touch.
	 */
	public static final int _TILE_MAP_COUNT = (int) (TILE_HORIZONTAL * TILE_VERTICAL);


	/**
	 * Size of the screen.
	 */
	public static final long SCREEN_WIDTH = TILE_SIZE * TILE_HORIZONTAL;
	/**
	 * Size of the screen.
	 */
	public static final long SCREEN_HEIGHT = TILE_SIZE * TILE_VERTICAL;


	//Other config related to other entities in the game.

	//region Bullet
	public static final long NORMAL_BULLET_TTL = 8; //30 time to live
	public static final long NORMAL_BULLET_STRENGTH = 15;//30
	public static double NORMAL_BULLET_SPEED = 1;//0.3

	public static final long MACHINE_GUN_BULLET_TTL = 5;
	public static final long MACHINE_GUN_BULLET_STRENGTH = 10;
	public static final double MACHINE_GUN_BULLET_SPEED = 1;

	public static final long SNIPER_BULLET_TTL = 60;
	public static final long SNIPER_BULLET_STRENGTH = 60;
	public static final double SNIPER_BULLET_SPEED = 0.5;
	//endregion

	//region Tower
	public static final long NORMAL_TOWER_SPEED = 10;//30
	public static final double NORMAL_TOWER_RANGE = 5.0;//5.0
	public static final long NORMAL_TOWER_COST = -15;

	public static final long MACHINE_GUN_TOWER_SPEED = 5;
	public static final double MACHINE_GUN_TOWER_RANGE = 4.0;
	public static final long MACHINE_GUN_TOWER_COST = -30;

	public static final long SNIPER_TOWER_SPEED = 60;
	public static final double SNIPER_TOWER_RANGE = 8.0;
	public static final long SNIPER_TOWER_COST = -60;
	//endregion

	//region Enemy
	public static final double NORMAL_ENEMY_SIZE = 1;//0.9
	public static final long NORMAL_ENEMY_HEALTH = 20;
	public static final long NORMAL_ENEMY_ARMOR = 3;//3
	public static final double NORMAL_ENEMY_SPEED = 0.1;//0.3
	public static final long NORMAL_ENEMY_REWARD = 1;
	public static final long NORMAL_ENEMY_DAMAGE = -5;

	public static final double SMALLER_ENEMY_SIZE = 1;
	public static final long SMALLER_ENEMY_HEALTH = 10;
	public static final long SMALLER_ENEMY_ARMOR = 0;
	public static final double SMALLER_ENEMY_SPEED = 0.25;
	public static final long SMALLER_ENEMY_REWARD = 2;
	public static final long SMALLER_ENEMY_DAMAGE = -2;

	public static final double TANKER_ENEMY_SIZE = 1;
	public static final long TANKER_ENEMY_HEALTH = 50;
	public static final long TANKER_ENEMY_ARMOR = 5;
	public static final double TANKER_ENEMY_SPEED = 0.333333;
	public static final long TANKER_ENEMY_REWARD = 3;
	public static final long TANKER_ENEMY_DAMAGE = -2;

	public static final double BOSS_ENEMY_SIZE = 1;
	public static final long BOSS_ENEMY_HEALTH = 150;
	public static final long BOSS_ENEMY_ARMOR = 8;
	public static final double BOSS_ENEMY_SPEED = 0.05;
	public static final long BOSS_ENEMY_REWARD = 10;
	public static final long BOSS_ENEMY_DAMAGE = -8;
	//endregion

	private Config() {
	}


}
