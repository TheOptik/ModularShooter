package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Coordinates;
import world.World;

public class BasicEnemy extends Enemy {

	private boolean boostParticles = false;

	public BasicEnemy(Coordinates coordinates) {
		super(coordinates);
	}

	public BasicEnemy() {
		super(new Coordinates(Math.random() * World.WIDTH, Math.random() * World.HEIGHT));
	}

	@Override
	public void tick() {
		this.coordinates.xCoordinate += this.velocity.xVelocity;
		this.coordinates.yCoordinate += this.velocity.yVelocity;

		this.velocity.xVelocity *= 0.99;
		this.velocity.yVelocity *= 0.99;

		if (this.velocity.xVelocity < 0.1 && this.velocity.yVelocity < 0.1) {
			this.velocity = randomVelocity();
			boostParticles = true;
		}
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {

		graphicsContext.setFill(Color.RED);
		graphicsContext.fillRect(this.coordinates.xCoordinate, this.coordinates.yCoordinate, 5, 5);
		if (boostParticles) {

			for (int i = 0; i < (int) (Math.random() * 10 + 1); i++) {
				generateRandomBoostParticle();
			}

			boostParticles = false;
		}

	}

	public void generateRandomBoostParticle() {
		World.addObject(new BoostParticle(this.coordinates.xCoordinate + (Math.random() - 0.5) * 15,
				this.coordinates.yCoordinate + (Math.random() - 0.5) * 15, this.velocity));
	}

}
