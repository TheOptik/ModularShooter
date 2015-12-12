package objects;

import util.Drawable;
import util.Tickable;
import util.Velocity;
import world.World;

public abstract class Enemy extends GameObject implements Tickable, Drawable {

	double health;

	public Enemy(double xCoordinate, double yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.velocity = new Velocity(randomVelocity(), randomVelocity());
	}

	public static Enemy createRandomEnemy() {
		return new BasicEnemy();
	}

	@Override
	public void tick() {
		if (this.xCoordinate > World.WIDTH || this.xCoordinate < 0) {
			World.OBJECTS.remove(this);
		}
		if (this.yCoordinate > World.HEIGHT || this.yCoordinate < 0) {
			World.OBJECTS.remove(this);
		}
	}

}
