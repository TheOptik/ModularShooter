package world;

import java.util.ArrayList;
import java.util.List;

import modules.BasicWeapon;
import objects.Enemy;
import objects.GameObject;
import objects.Protagonist;
import util.Collectable;
import util.Coordinates;
import util.Drawable;
import util.Hitable;
import util.Tickable;

public class World {
	private static int width = 800;
	private static int height = 800;
	public static final Protagonist PROTAGONIST = new Protagonist();
	public static final double SPAWN_PERCENTAGE = 100;
	private static final List<Tickable> TICKABLES = new ArrayList<>();
	private static final List<Drawable> DRAWABLES = new ArrayList<>();
	private static final List<Hitable> HITABLES = new ArrayList<>();
	private static final List<Collectable> COLLECTABLES = new ArrayList<>();
	public static final double DROP_PERCENTAGE = 100;
	private static long score = 0;
	
	private World() {
		// You shall not instantiate!
	}
	
	static {
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(0, -2), PROTAGONIST));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(0, -1), PROTAGONIST));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(0, 1), PROTAGONIST));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(1, 0), PROTAGONIST));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(-1, 0), PROTAGONIST));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(2, 0), PROTAGONIST));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(-2, 0), PROTAGONIST));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(2, -1), PROTAGONIST));
		PROTAGONIST.addModule(new BasicWeapon(new Coordinates(-2, -1), PROTAGONIST));
	}
	
	public static void trySpawning() {
		
		if (Math.random() <= SPAWN_PERCENTAGE / 100) {
			addObject(Enemy.createRandomEnemy());
		}
		
	}
	
	public static List<Tickable> getAllTickableObjects() {
		final List<Tickable> copy = new ArrayList<>();
		copy.addAll(TICKABLES);
		return copy;
	}
	
	public static List<Drawable> getAllDrawableObjects() {
		final List<Drawable> copy = new ArrayList<>();
		copy.addAll(DRAWABLES);
		return copy;
	}
	
	public static List<Hitable> getAllHitableObjects() {
		final List<Hitable> copy = new ArrayList<>();
		copy.addAll(HITABLES);
		return copy;
	}
	
	public static List<Collectable> getAllCollectableObjects() {
		final List<Collectable> copy = new ArrayList<>();
		copy.addAll(COLLECTABLES);
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
		if (object instanceof Collectable) {
			COLLECTABLES.add((Collectable) object);
		}
	}
	
	public static void removeObject(GameObject object) {
		if (object instanceof Drawable) {
			DRAWABLES.remove(object);
		}
		if (object instanceof Tickable) {
			TICKABLES.remove(object);
		}
		if (object instanceof Hitable) {
			HITABLES.remove(object);
		}
		if (object instanceof Collectable) {
			COLLECTABLES.remove(object);
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
	
	public static int getWIDTH() {
		return width;
	}
	
	public static void setWIDTH(int width) {
		World.width = width;
	}
	
	public static int getHEIGHT() {
		return height;
	}
	
	public static void setHEIGHT(int height) {
		World.height = height;
	}
	
}
