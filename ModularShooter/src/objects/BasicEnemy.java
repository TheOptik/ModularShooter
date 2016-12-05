package objects;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Coordinates;
import world.World;

public class BasicEnemy extends Enemy {

	private boolean boostParticles = false;
	private static Random random = new Random();

	public BasicEnemy(Coordinates coordinates) {
		super(coordinates);
	}

	public BasicEnemy() {
		super(new Coordinates(Math.random() * World.getWIDTH(), Math.random() * World.getHEIGHT()));
	}

	@Override
	public void tick() {
		super.tick();
		this.coordinates.xCoordinate += this.velocity.xVelocity;
		this.coordinates.yCoordinate += this.velocity.yVelocity;

		this.velocity.xVelocity *= 0.995;
		this.velocity.yVelocity *= 0.995;

		if (Math.abs(this.velocity.xVelocity) < 0.1 && Math.abs(this.velocity.yVelocity) < 0.1) {
			this.velocity = randomVelocity();
			boostParticles = true;
		}
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {

		graphicsContext.setFill(Color.RED);
		graphicsContext.fillRect(this.coordinates.xCoordinate, this.coordinates.yCoordinate, 5, 5);
		if (boostParticles) {
			for (int i = 0; i < (random.nextInt(4) * 3); i++) {
				generateRandomBoostParticle();
			}
			boostParticles = false;
		}

	}

	@Override
	protected void generateRandomBoostParticle() {
		World.addObject(new BoostParticle(this.coordinates.xCoordinate + (Math.random() - 0.5) * 15,
				this.coordinates.yCoordinate + (Math.random() - 0.5) * 15, this.velocity));
	}

}
