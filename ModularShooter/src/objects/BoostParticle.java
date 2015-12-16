package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
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

		graphicsContext.setStroke(new Color(1, 0, 1, Math.abs(lifeSpan / (double) maxLifeSpan)));
		// graphicsContext.fillRect(this.coordinates.xCoordinate,
		// this.coordinates.yCoordinate, 2, 2);
		double startAngleRads = Math.acos(this.velocity.xVelocity
				/ (Math.sqrt(Math.pow(this.velocity.xVelocity, 2) + Math.pow(this.velocity.yVelocity, 2))));
		double startAngle = Math.toDegrees(startAngleRads) - 90;
		;
		graphicsContext.strokeArc(this.coordinates.xCoordinate, this.coordinates.yCoordinate, 20, 20, startAngle, 180,
				ArcType.OPEN);
	}

	@Override
	public void tick() {
		super.tick();
		this.coordinates.xCoordinate += this.velocity.xVelocity;
		this.coordinates.yCoordinate += this.velocity.yVelocity;
	}

}
