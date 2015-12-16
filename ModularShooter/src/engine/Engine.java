package engine;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import objects.GameObject;
import util.Drawable;
import util.Tickable;
import world.World;

public class Engine extends Application {

	private static Canvas canvas;
	private static GraphicsContext graphicalContext;

	static {
		setupCanvas();
		setupProtagonist();
	}

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setupStage(primaryStage);
		canvas.requestFocus();
		setupAnimationTimer();

	}

	private static void setupProtagonist() {
		World.addObject(World.PROTAGONIST);
		canvas.setOnKeyPressed(World.PROTAGONIST.getKeyPressed());
		canvas.setOnKeyReleased(World.PROTAGONIST.getKeyReleased());
	}

	private static void setupStage(final Stage stage) {
		stage.setTitle("Modular Shooter");
		stage.setScene(setupScene());
		stage.show();
	}

	private static Scene setupScene() {
		Pane pane = new Pane();
		pane.setStyle(" -fx-background-color: black");
		pane.getChildren().add(canvas);
		return new Scene(pane, World.WIDTH, World.HEIGHT);
	}

	private static void setupCanvas() {
		canvas = new Canvas(World.WIDTH, World.HEIGHT);
		canvas.setFocusTraversable(true);
		graphicalContext = canvas.getGraphicsContext2D();
	}

	private static void setupAnimationTimer() {

		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {

				World.trySpawning();
				graphicalContext.clearRect(0, 0, World.WIDTH, World.HEIGHT);

				for (GameObject object : World.getAllObjects()) {
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

	private static void printCanvas(Canvas canvas) {
		try {
			String path = "C:/Users/shu/Desktop/";
			System.out.println("Exporting Image");
			SnapshotParameters sp = new SnapshotParameters();
			WritableImage image = canvas.snapshot(sp, null);
			BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
			File file = new File(path + World.WIDTH + "x" + World.HEIGHT + ".png");
			ImageIO.write(bImage, "png", file);
			System.out.println("DONE!");
		} catch (Exception e) {

		}
	}

}
