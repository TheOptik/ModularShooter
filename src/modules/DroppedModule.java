package modules;

import engine.HeartBeat;
import javafx.scene.canvas.GraphicsContext;
import objects.GameObject;
import util.Collectable;
import util.Drawable;
import util.Tickable;
import world.World;

public class DroppedModule extends GameObject implements Collectable, Drawable, Tickable {
	
	private final Module module;
	private static int maxLifeSpan = 900;
	private float lifeSpan;
	
	public DroppedModule(Module module) {
		this.module = module;
		lifeSpan = maxLifeSpan;
	}
	
	@Override
	public boolean hitTest(GameObject other) {
		final boolean xCoordinateIsValid = module.coordinates.xCoordinate < other.coordinates.xCoordinate
				+ other.getSize() && module.coordinates.xCoordinate + size > other.coordinates.xCoordinate;
		final boolean yCoordinateIsValid = module.coordinates.yCoordinate < other.coordinates.yCoordinate
				+ other.getSize() && module.coordinates.yCoordinate + size > other.coordinates.yCoordinate;
		
		return xCoordinateIsValid && yCoordinateIsValid;
	}
	
	@Override
	public Module collect() {
		World.removeObject(this);
		return module;
	}
	
	@Override
	public void tick() {
		lifeSpan -= HeartBeat.getDeltaTime();
		if (lifeSpan <= 0) {
			World.removeObject(this);
		}
	}
	
	@Override
	public void draw(GraphicsContext graphicsContext) {
		module.draw(graphicsContext);
	}
	
}
