package modules;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Coordinates;
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
		double xPos = World.PROTAGONIST.coordinates.xCoordinate + relativePosition.xCoordinate * 5;
		double yPos = World.PROTAGONIST.coordinates.yCoordinate + relativePosition.yCoordinate * 5;
		graphicsContext.fillRect(xPos, yPos, 5, 5);

	}

	@Override
	public void tick() {
		if (cooldown <= 0) {
			// SHOOT!
			cooldown = defaultCooldown;
		}
		cooldown--;
	}

}
