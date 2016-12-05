package modules;

import javafx.scene.canvas.GraphicsContext;
import objects.GameObject;
import util.Collectable;
import util.Drawable;
import util.Tickable;
import world.World;

public class DroppedModule extends GameObject implements Collectable, Drawable, Tickable {

	private Module module;

	public DroppedModule(Module module) {
		this.module = module;
	}

	@Override
	public boolean hitTest(GameObject other) {
		boolean xCoordinateIsValid = module.coordinates.xCoordinate < other.coordinates.xCoordinate + other.getSize()
				&& module.coordinates.xCoordinate + size > other.coordinates.xCoordinate;
		boolean yCoordinateIsValid = module.coordinates.yCoordinate < other.coordinates.yCoordinate + other.getSize()
				&& module.coordinates.yCoordinate + size > other.coordinates.yCoordinate;

		return xCoordinateIsValid && yCoordinateIsValid;
	}

	@Override
	public Module collect() {
		World.removeObject(this);
		return module;
	}

	@Override
	public void tick() {
		// TODO fill!
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		module.draw(graphicsContext);
	}

}
