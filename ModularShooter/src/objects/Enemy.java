package objects;

import util.Coordinates;
import util.Drawable;
import util.Tickable;
import util.Velocity;
import world.World;

public abstract class Enemy extends GameObject implements Tickable, Drawable {

	double health;

	public Enemy(double xCoordinate, double yCoordinate) {
		this.coordinates = new Coordinates(xCoordinate, yCoordinate);
		this.velocity = new Velocity(randomVelocity(), randomVelocity());
	}

	public static Enemy createRandomEnemy() {
		return new BasicEnemy();
	}

	@Override
	public void tick() {
		if (this.coordinates.xCoordinate > World.WIDTH || this.coordinates.xCoordinate < 0) {
			World.OBJECTS.remove(this);
		}
		if (this.coordinates.yCoordinate > World.HEIGHT || this.coordinates.yCoordinate < 0) {
			World.OBJECTS.remove(this);
		}
	}

}
