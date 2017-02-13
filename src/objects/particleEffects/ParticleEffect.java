package objects.particleEffects;

import java.util.Random;

import objects.GameObject;
import util.Coordinates;
import util.Drawable;
import util.Tickable;
import world.World;

public abstract class ParticleEffect extends GameObject implements Drawable, Tickable {
	
	protected int lifeSpan;
	
	protected static final Random random = new Random();
	
	public ParticleEffect(double xCoordinate, double yCoordinate) {
		this.coordinates = new Coordinates(xCoordinate, yCoordinate);
		this.lifeSpan = random.nextInt(60);
	}
	
	@Override
	public void tick() {
		if (this.lifeSpan <= 0) {
			World.removeObject(this);
		}
		this.lifeSpan -= 1;
	}
	
}
