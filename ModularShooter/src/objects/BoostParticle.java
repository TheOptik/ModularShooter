package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Velocity;

public class BoostParticle extends ParticleEffect {
	int maxLifeSpan;

	public BoostParticle(double xCoordinate, double yCoordinate, Velocity velocity) {
		super(xCoordinate, yCoordinate);
		this.velocity = new Velocity(velocity.xVelocity * -0.2, velocity.yVelocity * -0.2);
		this.maxLifeSpan = lifeSpan;
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {

		graphicsContext.setFill(new Color(1, 0, 1, Math.abs(lifeSpan / (double) maxLifeSpan)));
		double particleX = this.xCoordinate - (this.velocity.xVelocity * 10);
		double particleY = this.yCoordinate - (this.velocity.yVelocity * 10);

		graphicsContext.fillRect(particleX, particleY, 2, 2);
		// graphicsContext.fillArc(particleX, particleY, 20, 10, 20, 160,
		// ArcType.OPEN);

	}

	@Override
	public void tick() {
		super.tick();
		this.xCoordinate += this.velocity.xVelocity;
		this.yCoordinate += this.velocity.yVelocity;
	}

}
