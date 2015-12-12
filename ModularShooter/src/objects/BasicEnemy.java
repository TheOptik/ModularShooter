package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import world.World;

public class BasicEnemy extends Enemy {

	private boolean boostParticles = false;

	public BasicEnemy(double xCoordinate, double yCoordinate) {
		super(xCoordinate, yCoordinate);
	}

	public BasicEnemy() {
		super(Math.random() * World.WIDTH, Math.random() * World.HEIGHT);
	}

	@Override
	public void tick() {
		this.xCoordinate += this.velocity.xVelocity;
		this.yCoordinate += this.velocity.yVelocity;

		this.velocity.xVelocity *= 0.99;
		this.velocity.yVelocity *= 0.99;

		if (this.velocity.xVelocity < 0.1 && this.velocity.yVelocity < 0.1) {
			this.velocity.xVelocity = randomVelocity();
			this.velocity.yVelocity = randomVelocity();
			boostParticles = true;
		}
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {

		graphicsContext.setFill(Color.RED);
		graphicsContext.fillRect(xCoordinate, yCoordinate, 5, 5);
		if (boostParticles) {
			World.OBJECTS.add(new BoostParticle(this.xCoordinate, this.yCoordinate, this.velocity));
			boostParticles = false;
		}

	}

}
