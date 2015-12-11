package world;

import java.util.ArrayList;
import java.util.List;

import objects.GameObject;

public class World {

	public static final int WIDTH;
	public static final int HEIGHT;

	public static final List<GameObject> OBJECTS = new ArrayList<GameObject>();

	static {
		WIDTH = 800;
		HEIGHT = 800;
	}

}
