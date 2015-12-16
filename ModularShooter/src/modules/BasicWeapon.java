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
			World.addObject(new BasicProjectile(new Coordinates(calculateXCoordinate(), calculateYCoordinate()),
					new Velocity(Math.signum(relativePosition.xCoordinate), Math.signum(relativePosition.yCoordinate)),
					true));
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

}
