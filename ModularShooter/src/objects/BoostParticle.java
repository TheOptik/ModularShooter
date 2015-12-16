package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import util.Velocity;

public class BoostParticle extends ParticleEffect {
	int maxLifeSpan;
	double startAngle;

	public BoostParticle(double xCoordinate, double yCoordinate, Velocity velocity) {
		super(xCoordinate, yCoordinate);
		this.size = 20;
		double startAngleRads = Math.acos(
				velocity.yVelocity / Math.sqrt(Math.pow(velocity.xVelocity, 2) + Math.pow(velocity.yVelocity, 2)));
		startAngle = Math.toDegrees(startAngleRads);
		this.velocity = new Velocity(velocity.xVelocity * -0.2, velocity.yVelocity * -0.2);
		this.maxLifeSpan = lifeSpan;
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {

		graphicsContext.setStroke(new Color(1, 0, 1, Math.abs(lifeSpan / (double) maxLifeSpan)));
		graphicsContext.strokeArc(this.coordinates.xCoordinate - size / 2, this.coordinates.yCoordinate - size / 2,
				size, size, startAngle, 180, ArcType.OPEN);
	}

	@Override
	public void tick() {
		super.tick();
		this.coordinates.xCoordinate += this.velocity.xVelocity;
		this.coordinates.yCoordinate += this.velocity.yVelocity;
	}

}
