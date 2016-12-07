package modules;

import engine.HeartBeat;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import objects.Protagonist;
import projectiles.BasicProjectile;
import util.Coordinates;
import util.Velocity;
import world.World;

public class BasicWeapon extends Module {
	
	private static final double BULLET_SPEED = 2;
	private int cooldown = 80;
	private final int defaultCooldown = cooldown;
	
	public BasicWeapon(Coordinates relativePosition) {
		super(relativePosition);
	}
	
	public BasicWeapon(Coordinates relativePosition, Protagonist protagonist) {
		super(relativePosition, protagonist);
	}
	
	@Override
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.YELLOW);
		graphicsContext.fillRect(calculateXCoordinate(), calculateYCoordinate(), size, size);
		
	}
	
	@Override
	public void tick() {
		super.tick();
		if (cooldown <= 0) {
			shoot();
			cooldown = defaultCooldown;
		}
		cooldown -= HeartBeat.getDeltaTime();
	}
	
	private double calculateXCoordinate() {
		if (protagonist != null) {
			return World.PROTAGONIST.coordinates.xCoordinate + relativePosition.xCoordinate * size;
		} else {
			return coordinates.xCoordinate;
		}
	}
	
	private double calculateYCoordinate() {
		if (protagonist != null) {
			return World.PROTAGONIST.coordinates.yCoordinate + relativePosition.yCoordinate * size;
		} else {
			return coordinates.yCoordinate;
		}
	}
	
	private void shoot() { // TODO wow, das ist..... unleserlich wie sau.
		
		if ((int) relativePosition.yCoordinate != 0) {
			if ((int) relativePosition.xCoordinate != 0) {
				final double absoluteVectorLength = Math
						.sqrt(Math.pow(relativePosition.xCoordinate, 2) + Math.pow(relativePosition.yCoordinate, 2));
				final double xVel = relativePosition.xCoordinate / absoluteVectorLength * BULLET_SPEED;
				final double yVel = relativePosition.yCoordinate / absoluteVectorLength * BULLET_SPEED;
				World.addObject(new BasicProjectile(calculateAbsoluteCoordinates(), new Velocity(xVel, yVel), true));
			} else {
				World.addObject(new BasicProjectile(calculateAbsoluteCoordinates(),
						new Velocity(0, Math.signum(relativePosition.yCoordinate) * BULLET_SPEED), true));
			}
		} else {
			if ((int) relativePosition.xCoordinate != 0) {
				World.addObject(new BasicProjectile(calculateAbsoluteCoordinates(),
						new Velocity(Math.signum(relativePosition.xCoordinate) * BULLET_SPEED, 0), true));
			}
		}
		
	}
	
	private Coordinates calculateAbsoluteCoordinates() {
		return new Coordinates(calculateXCoordinate(), calculateYCoordinate());
	}
	
}
