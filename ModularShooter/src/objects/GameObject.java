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

	protected boolean isOutOfBounds() {
		return this.coordinates.xCoordinate > World.WIDTH || this.coordinates.xCoordinate < 0
				|| this.coordinates.yCoordinate > World.HEIGHT || this.coordinates.yCoordinate < 0;

	}
}
