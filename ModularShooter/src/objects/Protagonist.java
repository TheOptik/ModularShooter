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

	private final EventHandler<KeyEvent> keyBinding;

	public Protagonist() {

		this.xCoordinate = World.WIDTH / 2;
		this.yCoordinate = World.WIDTH / 2;
		this.velocity = new Velocity(0, 0);

		keyBinding = new EventHandler<KeyEvent>() {

			@Override
			public void handle(final KeyEvent event) {

				if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
					if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
						Protagonist.this.velocity.yVelocity = -1;
					}
					if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
						Protagonist.this.velocity.yVelocity = 1;
					}
					if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
						Protagonist.this.velocity.xVelocity = -1;
					}
					if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
						Protagonist.this.velocity.xVelocity = 1;
					}
					if (event.getCode() == KeyCode.SPACE) {
						System.out.println("PEW!!");
					}
				}
				if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
					// TODO haben wir hier was?
				}

			}

		};

	}

	public EventHandler<KeyEvent> getEventHandler() {
		return keyBinding;
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.setFill(new Color(0, 1, 0, 1));
		graphicsContext.fillRect(xCoordinate, yCoordinate, 1, 1);
	}

	@Override
	public void tick() {

		this.velocity.xVelocity *= 0.9;
		this.velocity.yVelocity *= 0.9;

	}

}
