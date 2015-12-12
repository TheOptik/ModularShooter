package objects;

import util.Drawable;
import util.Tickable;
import world.World;

public abstract class ParticleEffect extends GameObject implements Drawable, Tickable {

	protected int lifeSpan;

	public ParticleEffect(double xCoordinate, double yCoordinate) {
		this.coordinates.xCoordinate = xCoordinate;
		this.coordinates.yCoordinate = yCoordinate;
		this.lifeSpan = (int) (60 * Math.random()) + 1;
	}

	@Override
	public void tick() {
		if (this.lifeSpan <= 0) {
			World.OBJECTS.remove(this);
		}
		this.lifeSpan -= 1;
	}

}
