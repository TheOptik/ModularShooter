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

		if (other == this) {
			return false;
		}
		if (other.coordinates.xCoordinate >= this.coordinates.xCoordinate
				&& other.coordinates.xCoordinate <= this.coordinates.xCoordinate + size
				&& other.coordinates.yCoordinate >= this.coordinates.yCoordinate
				&& other.coordinates.yCoordinate <= this.coordinates.yCoordinate + size) {
			return true;
		}
		return false;
	}

	public void hit() {
		World.removeObject(this);
	}

}
