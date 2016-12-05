package modules;

import objects.GameObject;
import objects.Protagonist;
import util.Coordinates;
import util.Drawable;
import util.Tickable;

public abstract class Module extends GameObject implements Drawable, Tickable {

	public Coordinates relativePosition;
	public Protagonist protagonist;

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

}
