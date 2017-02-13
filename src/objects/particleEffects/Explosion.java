package objects.particleEffects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Coordinates;
import world.World;

public class Explosion extends ParticleEffect {

	private Color color = Color.RED;
	private static final int maxLifeSpan = 100;
	private int lifeSpan;

	public Explosion(int size, Coordinates coordinates) {
		super(coordinates.xCoordinate - size / 2, coordinates.yCoordinate - size / 2);
		this.size = size;
		this.lifeSpan = maxLifeSpan;
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.setFill(color);
		graphicsContext.fillRect(coordinates.xCoordinate, coordinates.yCoordinate, size, size);
	}

	@Override
	public void tick() {
		double alpha = Math.abs(lifeSpan / (double) maxLifeSpan);
		color = new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.pow(alpha, 3) / 2);
		if (this.lifeSpan <= 0) {
			World.removeObject(this);
		}
		lifeSpan--;
	}

}
