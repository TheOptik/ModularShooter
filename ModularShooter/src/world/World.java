package world;

import java.util.ArrayList;
import java.util.List;

import modules.BasicWeapon;
import objects.Enemy;
import objects.GameObject;
import objects.Protagonist;
import util.Coordinates;
import util.Drawable;
import util.Hitable;
import util.Tickable;

public class World {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final Protagonist PROTAGONIST = new Protagonist();
	public static final double SPAWN_PERCENTAGE = 100;
	private static final List<Tickable> TICKABLES = new ArrayList<>();
	private static final List<Drawable> DRAWABLES = new ArrayList<>();
	private static final List<Hitable> HITABLES = new ArrayList<>();
	private static long score = 0;

	private World() {
		// You shall not instantiate!
	}

	static {
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(1, 0)));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(-1, 0)));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(1, 1)));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(-1, 1)));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(1, -1)));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(-1, -1)));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(1, 0)));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(1, 0)));

	}

	public static void trySpawning() {

		if (Math.random() <= SPAWN_PERCENTAGE / 100) {
			addObject(Enemy.createRandomEnemy());
		}

	}

	public static List<Tickable> getAllTickableObjects() {
		List<Tickable> copy = new ArrayList<>();
		copy.addAll(TICKABLES);
		return copy;
	}

	public static List<Drawable> getAllDrawableObjects() {
		List<Drawable> copy = new ArrayList<>();
		copy.addAll(DRAWABLES);
		return copy;
	}

	public static List<Hitable> getAllHitableObjects() {
		List<Hitable> copy = new ArrayList<>();
		copy.addAll(HITABLES);
		return copy;
	}

	public static void addObject(GameObject object) {
		if (object instanceof Drawable) {
			DRAWABLES.add((Drawable) object);
		}
		if (object instanceof Tickable) {
			TICKABLES.add((Tickable) object);
		}
		if (object instanceof Hitable) {
			HITABLES.add((Hitable) object);
		}
	}

	public static void removeObject(GameObject object) {
		if (object instanceof Drawable) {
			DRAWABLES.remove((Drawable) object);
		}
		if (object instanceof Tickable) {
			TICKABLES.remove((Tickable) object);
		}
		if (object instanceof Hitable) {
			HITABLES.remove((Hitable) object);
		}
	}

	public static int getObjectCount() {
		return HITABLES.size() + DRAWABLES.size() + TICKABLES.size();
	}

	public static void addScore(int amount) {
		score += amount;
	}

	public static long getScore() {
		return score;
	}

}
