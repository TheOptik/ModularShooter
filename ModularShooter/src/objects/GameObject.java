package objects;

import util.Coordinates;
import util.Velocity;

public abstract class GameObject {

	public Coordinates coordinates;
	protected Velocity velocity;

	protected Velocity randomVelocity() {
		return new Velocity((Math.random() - 0.5) * 2,(Math.random() - 0.5) * 2);
	}
	
}
