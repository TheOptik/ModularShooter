package util;

import javafx.scene.canvas.GraphicsContext;

@FunctionalInterface
public interface Drawable {

	public void draw(GraphicsContext graphicsContext);

}
