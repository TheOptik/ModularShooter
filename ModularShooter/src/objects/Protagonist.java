package objects;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import modules.Module;
import util.Coordinates;
import util.Drawable;
import util.Tickable;
import util.Velocity;
import world.World;

public class Protagonist extends GameObject implements Tickable, Drawable {

	private final EventHandler<KeyEvent> keyPressed;
	private final EventHandler<KeyEvent> keyReleased;
	private static final List<Module> MODULES = new ArrayList<>();

	private boolean right;
	private boolean left;
	private boolean up;
	private boolean down;

	public Protagonist() {
		this.size = 5;
		this.coordinates = new Coordinates(World.WIDTH / 2, World.HEIGHT / 2);
		this.velocity = new Velocity(0, 0);

		keyPressed = generateKeyPressedEventhandler();
		keyReleased = generateKeyReleasedEventHandler();

	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.setFill(new Color(0, 1, 0, 1));
		graphicsContext.fillRect(this.coordinates.xCoordinate, this.coordinates.yCoordinate, size, size);

		for (Module mod : Protagonist.MODULES) {
			mod.draw(graphicsContext);
		}
	}

	@Override
	public void tick() {

		this.coordinates.xCoordinate += velocity.xVelocity;
		this.coordinates.yCoordinate += velocity.yVelocity;

		if (!(left || right)) {
			this.velocity.xVelocity *= 0.9;
		}
		if (!(up || down)) {
			this.velocity.yVelocity *= 0.9;
		}
		if (!left && right) {
			this.velocity.xVelocity = 1;
		}
		if (!right && left) {
			this.velocity.xVelocity = -1;
		}
		if (!up && down) {
			this.velocity.yVelocity = 1;
		}
		if (!down && up) {
			this.velocity.yVelocity = -1;
		}

		for (Module mod : Protagonist.MODULES) {
			mod.tick();
		}

	}

	public EventHandler<KeyEvent> getKeyPressed() {
		return keyPressed;
	}

	public EventHandler<KeyEvent> getKeyReleased() {
		return keyReleased;
	}

	public void addModule(Module module) {
		Protagonist.MODULES.add(module);
	}

	private EventHandler<KeyEvent> generateKeyPressedEventhandler() {
		return new EventHandler<KeyEvent>() {

			@Override
			public void handle(final KeyEvent event) {

				if (event.isAltDown() && event.getCode() == KeyCode.F4) {
					Platform.exit();
				}

				if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
					Protagonist.this.velocity.yVelocity = -1;
					up = true;
				}
				if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
					Protagonist.this.velocity.yVelocity = 1;
					down = true;
				}
				if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
					Protagonist.this.velocity.xVelocity = -1;
					left = true;
				}
				if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
					Protagonist.this.velocity.xVelocity = 1;
					right = true;
				}
				if (event.getCode() == KeyCode.SPACE) {
					System.out.println("PEW!!"); // NOSONAR
				}
			}

		};
	}

	private EventHandler<KeyEvent> generateKeyReleasedEventHandler() {
		return new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
					Protagonist.this.velocity.yVelocity *= 0.9;
					up = false;
				}
				if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
					Protagonist.this.velocity.yVelocity *= 0.9;
					down = false;
				}
				if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
					Protagonist.this.velocity.xVelocity *= 0.9;
					left = false;
				}
				if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
					Protagonist.this.velocity.xVelocity *= 0.9;
					right = false;
				}
			}
		};
	}

}
