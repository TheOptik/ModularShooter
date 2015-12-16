package objects;

import util.Coordinates;
import util.Velocity;
import world.World;

public abstract class GameObject {

	public Coordinates coordinates;
	protected Velocity velocity;
	protected int size;

	protected Velocity randomVelocity() {

		double xVelocity = Math.random();
		double yVelocity = Math.random();
		if (xVelocity < 0.1 && yVelocity < 0.1) {
			xVelocity += 0.5;
		}
		return new Velocity(xVelocity * Math.signum(Math.random() - 0.5), yVelocity * Math.signum(Math.random() - 0.5));
	}

	public boolean hitTest(GameObject other) {

		if (other != this) {
			if (other.coordinates.xCoordinate >= this.coordinates.xCoordinate
					&& other.coordinates.xCoordinate <= this.coordinates.xCoordinate + size
					&& other.coordinates.yCoordinate >= this.coordinates.yCoordinate
					&& other.coordinates.yCoordinate <= this.coordinates.yCoordinate + size) {
				return true;
			}
		}
		return false;
	}

	public void hit() {
		World.removeObject(this);
	}

}
