package modules;

import util.Coordinates;
import util.Drawable;
import util.Tickable;

public abstract class Module implements Drawable, Tickable {

	public Module(Coordinates relativePosition) {
		this.relativePosition = relativePosition;
	}

	protected Coordinates relativePosition;

}
