package objects;

import util.Coordinates;
import util.Drawable;
import util.Hitable;
import util.Tickable;
import world.World;

public abstract class Enemy extends GameObject implements Tickable, Drawable, Hitable {

	double health;

	public Enemy(Coordinates coordinates) {
		this.size = 5;
		this.coordinates = coordinates;
		this.velocity = randomVelocity();
	}

	public static Enemy createRandomEnemy() {
		return new BasicEnemy();
	}

	@Override
	public void tick() {
		if (this.coordinates.xCoordinate > World.WIDTH || this.coordinates.xCoordinate < 0) {
			World.removeObject(this);
		}
		if (this.coordinates.yCoordinate > World.HEIGHT || this.coordinates.yCoordinate < 0) {
			World.removeObject(this);
		}
	}

	public boolean hitTest(GameObject other) {
		return coordinates.xCoordinate < other.coordinates.xCoordinate + other.size
				&& coordinates.xCoordinate + size > other.coordinates.xCoordinate
				&& coordinates.yCoordinate < other.coordinates.yCoordinate + other.size
				&& coordinates.yCoordinate + size > other.coordinates.yCoordinate;
	}

	public void hit() {
		World.addScore(10);
		World.removeObject(this);
	}

}
