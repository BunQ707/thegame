package mrmathami.thegame;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import mrmathami.thegame.drawer.GameDrawer;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.enemy.BossEnemy;
import mrmathami.thegame.entity.enemy.NormalEnemy;
import mrmathami.thegame.entity.menu.*;
import mrmathami.thegame.entity.tile.Target;
import mrmathami.thegame.entity.tile.spawner.NormalSpawner;
import mrmathami.thegame.entity.tile.tower.MachineGunTower;
import mrmathami.thegame.entity.tile.tower.NormalTower;
import mrmathami.thegame.entity.tile.tower.SniperTower;
import mrmathami.utilities.ThreadFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Box;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A game controller. Everything about the game should be managed in here.
 */
public final class GameController extends AnimationTimer {
	/**
	 * Advance stuff. Just don't touch me. Google me if you are curious.
	 */
	private static final ScheduledExecutorService SCHEDULER = Executors.newSingleThreadScheduledExecutor(
			new ThreadFactoryBuilder()
					.setNamePrefix("Tick")
					.setDaemon(true)
					.setPriority(Thread.NORM_PRIORITY)
					.build()
	);

	/**
	 * The screen to draw on. Just don't touch me. Google me if you are curious.
	 */
	private final GraphicsContext graphicsContext;

	/**
	 * Game field. Contain everything in the current game field.
	 * Responsible to update the field every tick. Cập nhật field mỗi tick
	 * Kinda advance, modify if you are sure about your change.
	 */
	private GameField field;
	/**
	 * Game drawer. Responsible to draw the field every tick.
	 * Kinda advance, modify if you are sure about your change.
	 */
	private GameDrawer drawer;

	/**
	 * Beat-keeper Manager. Just don't touch me. Google me if you are curious.
	 */
	private ScheduledFuture<?> scheduledFuture;

	/**
	 * The tick value. Just don't touch me.
	 * Google me if you are curious, especially about volatile.
	 * WARNING: Wall of text.
	 */
	private volatile long tick;

	public static boolean isContinue;

	public static int mousePaintStatus;

	public static double staticMousePx;
	public static double staticMousePy;
	//0: default
	//1: normal tower
	//2: sniper tower
	//3: machinegun tower
	//4: destroy
	/**
	 * The constructor.
	 *
	 * @param graphicsContext the screen to draw on
	 */
	public GameController(GraphicsContext graphicsContext) {
		// The screen to draw on
        isContinue=true;
        mousePaintStatus=0;
		this.graphicsContext = graphicsContext;

		// Just a few acronyms.
		final long width = Config.TILE_HORIZONTAL;
		final long height = Config.TILE_VERTICAL;

		// The game field. Please consider create another way to load a game field.
		// TODO: I don't have much time, so, spawn some wall then :)
		this.field = new GameField(GameStage.load("/stage/demo.txt"));



		// The drawer. Nothing fun here.
		//
		this.drawer = new GameDrawer(graphicsContext, field);

		// Field view region is a rectangle region
		// [(posX, posY), (posX + SCREEN_WIDTH / zoom, posY + SCREEN_HEIGHT / zoom)]
		// that the drawer will select and draw everything in it in an self-defined order.
		// Can be modified to support zoom in / zoom out of the map.
		drawer.setFieldViewRegion(0.0, 0.0, Config.TILE_SIZE);
	}

	/**
	 * Beat-keeper. Just don't touch me.
	 */
	private void tick() {
		//noinspection NonAtomicOperationOnVolatileField
		this.tick += 1;
	}

	/**
	 * A JavaFX loop.
	 * Kinda advance, modify if you are sure about your change.
	 *
	 * @param now not used. It is a timestamp in nanosecond.
	 */
	@Override
	public void handle(long now) {
	    if (!isContinue)
        {
//            boolean isquit=false;
//
//            Image image = new Image("/stage/gameover.jpg");
//				graphicsContext.drawImage(image, 0, 0);
//
				//            while (isquit!=true)
				//			{
				//				if ()
				//				{
			endGameRequest();
				//				}
				//			}


        }

		// don't touch me.
		final long currentTick = tick;
		final long startNs = System.nanoTime();

		// do a tick, as fast as possible
		field.tick();

//		// if it's too late to draw a new frame, skip it.
//		// make the game feel really laggy, so...
//		if (currentTick != tick) return;

		// draw a new frame, as fast as possible.
		drawer.render();

		// MSPT display. MSPT stand for Milliseconds Per Tick.
		// It means how many ms your game spend to update and then draw the game once.
		// Draw it out mostly for debug
//		final double mspt = (System.nanoTime() - startNs) / 1000000.0;
//		graphicsContext.setFill(Color.BLACK);
//		graphicsContext.fillText(String.format("MSPT: %3.2f", mspt), 0, 12);
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillText( "REWARD: "+Long.toString(field.getReward()), Config.SCREEN_WIDTH/2-30, 16.6*30);
		switch (mousePaintStatus)
		{
			case 0:
				graphicsContext.fillText( "...", 0, 16.6*30);
				break;
			case 1:
				graphicsContext.fillText( "Build Normal Tower", 0, 16.6*30);
				break;
			case 2:
				graphicsContext.fillText( "Build Sniper Tower", 0, 16.6*30);
				break;
			case 3:
				graphicsContext.fillText( "Build Machine Gun Tower", 0, 16.6*30);
				break;
		}
		// if we have time to spend, do a spin
		while (currentTick == tick) Thread.onSpinWait();
	}

	/**
	 * Start rendering and ticking. Just don't touch me.
	 * Anything that need to initialize should be in the constructor.
	 */
	@Override
	public void start() {
		// Start the beat-keeper. Start to call this::tick at a fixed rate.
		this.scheduledFuture = SCHEDULER.scheduleAtFixedRate(this::tick, 0, Config.GAME_NSPT, TimeUnit.NANOSECONDS);
		// start the JavaFX loop.
		super.start();
		//Starts the AnimationTimers.
		//Once it is started, the handle(long) method of this AnimationTimers will be called in every frame.
		// The AnimationTimers can be stopped by calling stop().
	}

	/**
	 * On window close request.
	 * Kinda advance, modify if you are sure about your change.
	 *
	 * @param windowEvent currently not used
	 */
	final void closeRequestHandler(WindowEvent windowEvent) {
		scheduledFuture.cancel(true);
		stop();
		Platform.exit();
		System.exit(0);
	}
	final void endGameRequest()
    {
        scheduledFuture.cancel(true);
        stop();
        Platform.exit();
        System.exit(0);

    }

	/**
	 * Key down handler.
	 *
	 * @param keyEvent the key that you press down
	 */

	final void keyDownHandler(KeyEvent keyEvent) {
		final KeyCode keyCode = keyEvent.getCode();
		if (keyCode == KeyCode.W) {
		} else if (keyCode == KeyCode.S) {
		} else if (keyCode == KeyCode.A) {
		} else if (keyCode == KeyCode.D) {
		} else if (keyCode == KeyCode.I) {
		} else if (keyCode == KeyCode.J) {
		} else if (keyCode == KeyCode.K) {
		} else if (keyCode == KeyCode.L) {
		} else if (keyCode == KeyCode.ESCAPE) {
		    isContinue=false;
		}
	}

	/**
	 * Key up handler.
	 *
	 * @param keyEvent the key that you release up.
	 */
	final void keyUpHandler(KeyEvent keyEvent) {
		final KeyCode keyCode = keyEvent.getCode();
		if (keyCode == KeyCode.W) {
		} else if (keyCode == KeyCode.S) {
		} else if (keyCode == KeyCode.A) {
		} else if (keyCode == KeyCode.D) {
		} else if (keyCode == KeyCode.I) {
		} else if (keyCode == KeyCode.J) {
		} else if (keyCode == KeyCode.K) {
		} else if (keyCode == KeyCode.L) {
        } else if (keyCode == KeyCode.DIGIT1) { mousePaintStatus=1;
		} else if (keyCode == KeyCode.DIGIT2) { mousePaintStatus=2;
		} else if (keyCode == KeyCode.DIGIT3) { mousePaintStatus=3;
		} else if (keyCode == KeyCode.ADD) {
//            field.doSpawn(NormalSpawner.);
        }
	}

	/**
	 * Mouse down handler.
	 *
	 * @param mouseEvent the mouse button you press down.
	 */
	final void recthandler(MouseEvent mouseEvent)
	{
		field.doSpawn(new NormalTower(field.getTickCount(), (int)(mouseEvent.getX()/Config.TILE_SIZE),(int)(mouseEvent.getY()/Config.TILE_SIZE)));
	}

	public void mouseMoveHandler(MouseEvent mouseEvent)
	{
		staticMousePx=mouseEvent.getX();

		staticMousePy=mouseEvent.getY();
		System.out.println(staticMousePx/30+" "+ staticMousePy/30);
//
	}

	final void mouseDownHandler(MouseEvent mouseEvent) {
		double placeX=staticMousePx/Config.TILE_SIZE;
		double placeY=staticMousePy/Config.TILE_SIZE;
		switch (mousePaintStatus)
		{
			case 0:
				break;
			case 1:
				if (field.getReward() + Config.NORMAL_TOWER_COST >= 0 && field.getMapValAtXY( Math.toIntExact( Math.round(placeX-placeX%1) ), Math.toIntExact( Math.round(placeY-placeY%1) ) )==5)
				{
					field.doSpawn(new NormalTower(field.getTickCount(), Math.round(placeX-placeX%1), Math.round(placeY-placeY%1)));
					field.setReward(Config.NORMAL_TOWER_COST);
				}
				resetMousePaintStatus();
				break;
			case 2:
				if (field.getReward() + Config.SNIPER_TOWER_COST >= 0&& field.getMapValAtXY( Math.toIntExact( Math.round(placeX-placeX%1) ), Math.toIntExact( Math.round(placeY-placeY%1) ) )==5) {
					field.doSpawn(new SniperTower(field.getTickCount(), Math.round(placeX-placeX%1), Math.round(placeY-placeY%1)));
					field.setReward(Config.SNIPER_TOWER_COST);
				}
				resetMousePaintStatus();
				break;
			case 3:
				if (field.getReward() + Config.MACHINE_GUN_TOWER_COST >= 0&& field.getMapValAtXY( Math.toIntExact( Math.round(placeX-placeX%1) ), Math.toIntExact( Math.round(placeY-placeY%1) ) )==5) {
					field.doSpawn(new MachineGunTower(field.getTickCount(), Math.round(placeX-placeX%1), Math.round(placeY-placeY%1)));
					field.setReward(Config.MACHINE_GUN_TOWER_COST);
				}
				resetMousePaintStatus();
				break;

		}
	}
	public void resetMousePaintStatus()
	{
		mousePaintStatus=0;
	}

	/**
	 * Mouse up handler.
	 *
	 * @param mouseEvent the mouse button you release up.
	 */
	final void mouseUpHandler(MouseEvent mouseEvent) {
//		mouseEvent.getButton(); // which mouse button?
//		// Screen coordinate. Remember to convert to field coordinate
//		drawer.screenToFieldPosX(mouseEvent.getX());
//		drawer.screenToFieldPosY(mouseEvent.getY());
	}
}
