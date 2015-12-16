package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Velocity;

public class BoostParticle extends ParticleEffect {
	int maxLifeSpan;

	public BoostParticle(double xCoordinate, double yCoordinate, Velocity velocity) {
		super(xCoordinate, yCoordinate);
		this.size = 2;
		this.velocity = new Velocity(velocity.xVelocity * -0.2, velocity.yVelocity * -0.2);
		this.maxLifeSpan = lifeSpan;
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.setFill(new Color(1, 0, 1, Math.abs(lifeSpan / (double) maxLifeSpan)));
		graphicsContext.fillRect(this.coordinates.xCoordinate, this.coordinates.yCoordinate, size, size);
		System.out.println(this.coordinates.xCoordinate + " : " + this.coordinates.yCoordinate);
	}

	@Override
	public void tick() {
		super.tick();
		this.coordinates.xCoordinate += this.velocity.xVelocity;
		this.coordinates.yCoordinate += this.velocity.yVelocity;
	}

}
