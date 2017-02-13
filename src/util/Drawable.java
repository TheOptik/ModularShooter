package util;

import javafx.scene.canvas.GraphicsContext;

/**
 * A GameObject implementing this Interface assures that it is visible on the
 * GUI. The draw method may NOT change the Object. For a method that changes the
 * Object every game tick see {@link Tickable#tick()}.
 */
@FunctionalInterface
public interface Drawable {
	
	public void draw(GraphicsContext graphicsContext);
	
}
