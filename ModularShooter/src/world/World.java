package world;

import java.util.ArrayList;
import java.util.List;

import objects.Enemy;
import objects.GameObject;
import objects.Protagonist;

public class World {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final Protagonist PROTAGONIST = new Protagonist();
	public static final double spawnPercentage = 1;

	public static final List<GameObject> OBJECTS = new ArrayList<GameObject>();

	public static void trySpawning() {

		if (Math.random() <= spawnPercentage / 100) {
			OBJECTS.add(Enemy.createRandomEnemy());
		}

	}

}
