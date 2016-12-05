package projectiles;

import objects.GameObject;
import util.Coordinates;
import util.Drawable;
import util.Tickable;
import util.Velocity;

public abstract class Projectile extends GameObject implements Drawable, Tickable {
	
	protected boolean friendly;
	
	public Projectile(Coordinates coordinates, Velocity velocity, boolean friendly) {
		this.coordinates = coordinates;
		this.velocity = velocity;
		this.friendly = friendly;
	}
	
}
