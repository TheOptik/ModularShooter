package objects;

import engine.HeartBeat;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import modules.SenturyModule;
import objects.particleEffects.Explosion;
import util.Coordinates;
import util.Drawable;
import util.Hitable;
import util.Tickable;
import world.World;

public class Sentury extends GameObject implements Tickable, Drawable {
	
	private static final int blastPower = 25;
	private static final int minimalDistance = 5;
	private static final float maxVisibility = 0.3f;
	private int distance;
	private double angle;
	private Color color;
	private Color lineColor;
	private SenturyModule senturyModule;
	private float visibility = maxVisibility;
	private float visibilityStep = -0.003f;
	
	public Sentury(SenturyModule senturyModule) {
		this.senturyModule = senturyModule;
		distance = (int) ((senturyModule.relativePosition.magnitude() + minimalDistance) * size);
		angle = Math.random() * 360;
		color = senturyModule.getColor();
		this.coordinates = new Coordinates(World.PROTAGONIST.coordinates.xCoordinate + World.PROTAGONIST.size / 2.0,
				World.PROTAGONIST.coordinates.yCoordinate + World.PROTAGONIST.size / 2.0);
	}
	
	@Override
	public void draw(GraphicsContext graphicsContext) {
		lineColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), visibility);
		double x = World.PROTAGONIST.coordinates.xCoordinate + World.PROTAGONIST.size / 2.0;
		double y = World.PROTAGONIST.coordinates.yCoordinate + World.PROTAGONIST.size / 2.0;
		coordinates.xCoordinate = x + Math.sin(angle) * distance;
		coordinates.yCoordinate = y + Math.cos(angle) * distance - size / 2.0;
		strokeOrbit(graphicsContext, x, y);
		drawSentury(graphicsContext);
	}
	
	private void drawSentury(GraphicsContext graphicsContext) {
		graphicsContext.setFill(color);
		graphicsContext.fillRect(coordinates.xCoordinate - size / 2.0, coordinates.yCoordinate - size / 2.0, size,
				size);
	}
	
	private void strokeOrbit(GraphicsContext graphicsContext, double x, double y) {
		double radius = distance * 2;
		graphicsContext.setStroke(lineColor);
		graphicsContext.strokeOval(x - radius / 2, y - radius / 2, radius, radius);
	}
	
	@Override
	public void tick() {
		angle += (1.0 / distance) * HeartBeat.getDeltaTime();
		if (angle > 360) {
			angle = 0;
		}
		visibility += visibilityStep * HeartBeat.getDeltaTime();
		if (visibility >= maxVisibility || visibility <= 0) {
			visibilityStep *= -1;
			visibility = Math.max(0, Math.min(1, visibility));
		}
		for (Hitable hitable : World.getAllHitableObjects()) {
			if (hitable.hitTest(this)) {
				setOffExplosion();
			}
		}
		
	}
	
	private void setOffExplosion() {
		Explosion explosion = new Explosion(blastPower * 10, coordinates);
		World.addObject(explosion);
		for (Hitable hitable : World.getAllHitableObjects()) {
			if (hitable.hitTest(explosion)) {
				hitable.hit();
				World.removeObject(this);
				World.PROTAGONIST.removeModule(senturyModule);
			}
		}
	}
	
}
