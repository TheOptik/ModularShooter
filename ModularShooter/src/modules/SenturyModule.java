package modules;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import objects.Protagonist;
import objects.Sentury;
import util.Coordinates;
import world.World;

public class SenturyModule extends Module {

	private Sentury sentury;

	public SenturyModule(Coordinates coordinates) {
		super(coordinates);
		initialize();
	}

	public SenturyModule(Coordinates coordinates, Protagonist protagonist) {
		super(coordinates, protagonist);
		initialize();
	}

	private void initialize() {
		color = Color.AQUA;
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.setFill(color);
		graphicsContext.fillRect(calculateXCoordinate(), calculateYCoordinate(), size, size);

	}

	@Override
	public void hit() {
		World.removeObject(sentury);
		World.PROTAGONIST.removeModule(this);
	}

	@Override
	public void onBind() {
		sentury = new Sentury(this);
		World.addObject(sentury);
	}

}
