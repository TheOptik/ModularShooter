package objects;

import util.Coordinates;
import util.Velocity;

public abstract class GameObject {

	public Coordinates coordinates;
	protected Velocity velocity;

	protected double randomVelocity() {
		return (Math.random() - 0.5) * 2;
	}

}
