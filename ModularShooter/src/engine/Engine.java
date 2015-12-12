package engine;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import objects.GameObject;
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
		canvas.requestFocus();
		setupAnimationTimer();

		// TODO: auslagern!
		World.OBJECTS.add(World.PROTAGONIST);
		canvas.setOnKeyPressed(World.PROTAGONIST.getKeyPressed());
		canvas.setOnKeyReleased(World.PROTAGONIST.getKeyReleased());
	}

	private void setupStage(final Stage stage) {
		stage.setTitle("Modular Shooter");
		/*
		 * TODO: Just in Case stage.setOnCloseRequest(new
		 * EventHandler<WindowEvent>() { public void handle(final WindowEvent
		 * event) {
		 * 
		 * event.consume(); } });
		 */
		stage.setScene(setupScene());
		stage.show();
	}

	private Scene setupScene() {
		setupCanvas();
		Pane pane = new Pane();
		pane.setStyle(" -fx-background-color: black");
		pane.getChildren().add(canvas);
		return new Scene(pane, World.WIDTH, World.HEIGHT);
	}

	private void setupCanvas() {
		canvas = new Canvas(World.WIDTH, World.HEIGHT);
		canvas.setFocusTraversable(true);
		graphicalContext = canvas.getGraphicsContext2D();
	}

	private void setupAnimationTimer() {
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {

				World.trySpawning();
				graphicalContext.clearRect(0, 0, World.WIDTH, World.HEIGHT);

				List<GameObject> objectsCopy = new ArrayList<GameObject>();
				objectsCopy.addAll(World.OBJECTS);
				for (GameObject object : objectsCopy) {
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
