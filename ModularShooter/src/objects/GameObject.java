package objects;

import util.Velocity;

public abstract class GameObject {

	double xCoordinate;
	double yCoordinate;
	protected Velocity velocity;

	protected double randomVelocity() {
		return (Math.random() - 0.5) * 2;
	}

}
