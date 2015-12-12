package objects;

import util.Coordinates;
import util.Velocity;

public abstract class GameObject {

	Coordinates coordinates;
	protected Velocity velocity;

	protected double randomVelocity() {
		return (Math.random() - 0.5) * 2;
	}

}
