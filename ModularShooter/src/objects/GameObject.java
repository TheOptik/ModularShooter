package objects;

import util.Coordinates;
import util.Velocity;
import world.World;

public abstract class GameObject {

	public Coordinates coordinates;
	protected Velocity velocity;
	protected int size = 5;

	protected Velocity randomVelocity() {

		double xVelocity = Math.random();
		final double yVelocity = Math.random();
		if (xVelocity < 0.1 && yVelocity < 0.1) {
			xVelocity += 0.5;
		}
		return new Velocity(xVelocity * Math.signum(Math.random() - 0.5), yVelocity * Math.signum(Math.random() - 0.5));
	}

	protected boolean isOutOfBounds() {
		return this.coordinates.xCoordinate > World.getWIDTH() || this.coordinates.xCoordinate < 0
				|| this.coordinates.yCoordinate > World.getHEIGHT() || this.coordinates.yCoordinate < 0;

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
