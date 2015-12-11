package engine;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import objects.GameObject;
import objects.Protagonist;
import util.Drawable;
import util.Tickable;
import world.World;

public class Engine extends Application {

	private Canvas canvas;
	private GraphicsContext graphicalContext;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setupStage(primaryStage);
		setupAnimationTimer();

		// TODO: auslagern!
		World.OBJECTS.add(new Protagonist());
	}

	private void setupStage(final Stage stage) {
		stage.setTitle("Modular Shooter");
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(final WindowEvent event) {
				// TODO: Just in Case
				event.consume();
			}
		});
		stage.setScene(setupScene());
	}

	private Scene setupScene() {
		setupCanvas();
		Group group = new Group(canvas);
		return new Scene(group, 800, 800);
	}

	private void setupCanvas() {
		canvas = new Canvas(800, 800);
		graphicalContext = canvas.getGraphicsContext2D();
	}

	private void setupAnimationTimer() {
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				for (GameObject object : World.OBJECTS) {
					if (object instanceof Tickable) {
						((Tickable) object).tick();
					}
					if (object instanceof Drawable) {
						((Drawable) object).draw(graphicalContext);
					}
				}
			}
		};
		timer.start();
	}

}
