package util;

import javafx.scene.canvas.Canvas;

public class ResizableCanvas extends Canvas {

	public ResizableCanvas(double width, double height) {
		super(width, height);
	}

	@Override
	public boolean isResizable() {
		return true;
	}

	@Override
	public double prefWidth(double height) {
		return getWidth();
	}

	@Override
	public double prefHeight(double width) {
		return getHeight();
	}

	@Override
	public void resize(double width, double height) {
		super.setWidth(width);
		super.setHeight(height);
	}
}
