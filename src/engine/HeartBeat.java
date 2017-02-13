package engine;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Drawable;
import util.Tickable;
import world.World;

public class HeartBeat extends AnimationTimer {

	private static GraphicsContext graphicalContext;
	private static final long[] times = new long[10];
	private static int fps;
	private static long last;
	private static double deltaTime = 1;

	public HeartBeat(GraphicsContext graphicalContext) {
		HeartBeat.graphicalContext = graphicalContext;
	}

	@Override
	public void handle(long now) {

		if (fps != 0) {
			HeartBeat.setDeltaTime((now - last) / 16000000.0);
		}
		trackFPS(now);

		World.trySpawning();
		graphicalContext.clearRect(0, 0, World.getWIDTH(), World.getHEIGHT());
		World.PROTAGONIST.draw(graphicalContext);
		World.PROTAGONIST.tick();
		showData(graphicalContext);
		for (final Tickable object : World.getAllTickableObjects()) {
			object.tick();
		}
		for (final Drawable object : World.getAllDrawableObjects()) {
			object.draw(graphicalContext);
		}
	}

	protected static void showData(GraphicsContext graphicalContext) {

		graphicalContext.setFill(Color.GREEN);
		graphicalContext.fillText("Score:  ".concat(Long.toString(World.getScore())), 10, 15);
		graphicalContext.fillText("FPS:     ".concat(Integer.toString((int) (1 / (fps / 1000000000.0)))), 10, 30);
		graphicalContext.fillText("#Objc:  ".concat(Integer.toString(World.getObjectCount())), 10, 45);

	}

	private static void trackFPS(long now) {

		fps = 0;
		for (int i = 0; i < times.length; i++) {
			fps += times[i];
		}
		for (int i = 0; i < times.length - 1; i++) {
			times[i + 1] = times[i];
		}
		times[0] = now - last;
		fps /= 10;
		last = now;
	}

	public static double getDeltaTime() {
		return deltaTime;
	}

	public static void setDeltaTime(double deltaTime) {
		HeartBeat.deltaTime = deltaTime;
	}
}
