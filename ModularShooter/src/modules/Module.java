package modules;

import javafx.scene.paint.Color;
import objects.GameObject;
import objects.Protagonist;
import util.Coordinates;
import util.Drawable;
import util.Tickable;
import world.World;

public abstract class Module extends GameObject implements Drawable, Tickable {

	public Coordinates relativePosition;
	public Protagonist protagonist;
	protected Color color;

	public Module(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Module(Coordinates relativePosition, Protagonist protagonist) {
		this(relativePosition.multiply(5).add(protagonist.coordinates));
		this.relativePosition = relativePosition;
		this.protagonist = protagonist;
	}

	@Override
	public void tick() {
		this.coordinates = relativePosition.multiply(5).add(protagonist.coordinates);
	}

	public abstract void hit();

	protected double calculateXCoordinate() {
		if (protagonist != null) {
			return World.PROTAGONIST.coordinates.xCoordinate + relativePosition.xCoordinate * size;
		} else {
			return coordinates.xCoordinate;
		}
	}

	protected double calculateYCoordinate() {
		if (protagonist != null) {
			return World.PROTAGONIST.coordinates.yCoordinate + relativePosition.yCoordinate * size;
		} else {
			return coordinates.yCoordinate;
		}
	}

	public Color getColor() {
		return color;
	}

	public void onBind() {

	}

	public static DroppedModule generateRandomModule(Coordinates coordinates) {
		if (Math.random() < 0.9) {
			return new DroppedModule(new BasicWeapon(coordinates));
		} else {
			return new DroppedModule(new SenturyModule(coordinates));
		}
	}

}
