package modules;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import projectiles.BasicProjectile;
import util.Coordinates;
import util.Velocity;
import world.World;

public class BasicWeapon extends Module {

	private int cooldown = 80;
	int defaultCooldown = 80;

	public BasicWeapon(Coordinates relativePosition) {
		super(relativePosition);
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.YELLOW);
		graphicsContext.fillRect(calculateXCoordinate(), calculateYCoordinate(), 5, 5);

	}

	@Override
	public void tick() {
		if (cooldown <= 0) {
			shoot();
			cooldown = defaultCooldown;
		}
		cooldown--;
	}

	private double calculateXCoordinate() {
		return World.PROTAGONIST.coordinates.xCoordinate + relativePosition.xCoordinate * 5;
	}

	private double calculateYCoordinate() {
		return World.PROTAGONIST.coordinates.yCoordinate + relativePosition.yCoordinate * 5;
	}

	private void shoot() {

		if (relativePosition.yCoordinate != 0) {
			if (relativePosition.xCoordinate != 0) {
				double absoluteVectorLength = Math
						.sqrt(Math.pow(relativePosition.xCoordinate, 2) + Math.pow(relativePosition.yCoordinate, 2));
				double xVel = (relativePosition.xCoordinate / absoluteVectorLength);
				double yVel = (relativePosition.yCoordinate / absoluteVectorLength);
				World.addObject(new BasicProjectile(calculateAbsoluteCoordinates(), new Velocity(xVel, yVel), true));
			} else {
				World.addObject(new BasicProjectile(calculateAbsoluteCoordinates(),
						new Velocity(0, Math.signum(relativePosition.yCoordinate)), true));
			}
		} else {
			if (relativePosition.xCoordinate != 0) {
				World.addObject(new BasicProjectile(calculateAbsoluteCoordinates(),
						new Velocity(Math.signum(relativePosition.xCoordinate), 0), true));
			}
		}

	}

	private Coordinates calculateAbsoluteCoordinates() {
		return new Coordinates(calculateXCoordinate(), calculateYCoordinate());
	}

}
