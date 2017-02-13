package objects;

import modules.Module;
import util.Coordinates;
import util.Drawable;
import util.Hitable;
import util.Tickable;
import world.World;

public abstract class Enemy extends GameObject implements Tickable, Drawable, Hitable {

	double health;

	public Enemy(Coordinates coordinates) {
		this.coordinates = coordinates;
		this.velocity = randomVelocity();
	}

	@Override
	public void tick() {
		if (isOutOfBounds()) {
			World.removeObject(this);
		}
	}

	@Override
	public boolean hitTest(GameObject other) {
		final boolean xCoordinateIsValid = coordinates.xCoordinate < other.coordinates.xCoordinate + other.size
				&& coordinates.xCoordinate + size > other.coordinates.xCoordinate;
		final boolean yCoordinateIsValid = coordinates.yCoordinate < other.coordinates.yCoordinate + other.size
				&& coordinates.yCoordinate + size > other.coordinates.yCoordinate;

		return xCoordinateIsValid && yCoordinateIsValid;
	}

	@Override
	public void hit() {
		World.addScore(10);
		World.removeObject(this);
		if (Math.random() <= World.DROP_PERCENTAGE / 100) {
			World.addObject(Module.generateRandomModule(coordinates));
		}
	}

	public static Enemy createRandomEnemy() {
		return new BasicEnemy();
	}

	protected abstract void generateRandomBoostParticle();

}
