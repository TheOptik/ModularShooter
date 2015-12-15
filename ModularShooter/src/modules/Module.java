package modules;

import util.Coordinates;
import util.Drawable;
import util.Tickable;

public abstract class Module implements Drawable, Tickable {

	protected Coordinates relativePosition;
	
	public Module(Coordinates relativePosition) {
		this.relativePosition = relativePosition;
	}

}
