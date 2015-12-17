package projectiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Coordinates;
import util.Hitable;
import util.Velocity;
import world.World;

public class BasicProjectile extends Projectile {

	public BasicProjectile(Coordinates coordinates, Velocity velocity, boolean friendly) {
		super(coordinates, velocity, friendly);
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillRect(this.coordinates.xCoordinate, this.coordinates.yCoordinate, 2, 2);
	}

	@Override
	public void tick() {
		this.coordinates.calculateMovement(this.velocity);
		for (Hitable object : World.getAllHitableObjects()) {
			if (object.hitTest(this)) {
				object.hit();
			}
		}
	}

}
