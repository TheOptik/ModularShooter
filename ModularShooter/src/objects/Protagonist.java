package objects;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import util.Drawable;
import util.Tickable;
import util.Velocity;
import world.World;

public class Protagonist extends GameObject implements Tickable, Drawable {

	private final EventHandler<KeyEvent> keyPressed;
	private final EventHandler<KeyEvent> keyReleased;

	private boolean moving = false;

	public Protagonist() {

		this.xCoordinate = World.WIDTH / 2;
		this.yCoordinate = World.WIDTH / 2;
		this.velocity = new Velocity(0, 0);

		keyPressed = new EventHandler<KeyEvent>() {

			@Override
			public void handle(final KeyEvent event) {
				if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
					Protagonist.this.velocity.yVelocity = -1;
					moving = true;
				}
				if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
					Protagonist.this.velocity.yVelocity = 1;
					moving = true;
				}
				if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
					Protagonist.this.velocity.xVelocity = -1;
					moving = true;
				}
				if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
					Protagonist.this.velocity.xVelocity = 1;
					moving = true;
				}
				if (event.getCode() == KeyCode.SPACE) {
					System.out.println("PEW!!");
				}
			}

		};
		keyReleased = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				moving = false;
			}
		};

	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.setFill(new Color(0, 1, 0, 1));
		graphicsContext.fillRect(xCoordinate, yCoordinate, 5, 5);
	}

	@Override
	public void tick() {

		this.xCoordinate += velocity.xVelocity;
		this.yCoordinate += velocity.yVelocity;

		if (!moving) {
			this.velocity.xVelocity *= 0.9;
			this.velocity.yVelocity *= 0.9;
		}
	}

	public EventHandler<KeyEvent> getKeyPressed() {
		return keyPressed;
	}

	public EventHandler<KeyEvent> getKeyReleased() {
		return keyReleased;
	}

}
