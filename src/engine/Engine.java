package engine;

import java.awt.Dimension;
import java.awt.Toolkit;
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
import util.ResizableCanvas;
import world.World;

public class Engine extends Application {
	
	private static boolean fullscreen;
	private static ResizableCanvas canvas;
	private static GraphicsContext graphicalContext;
	private static AnimationTimer timer;
	
	static {
		setupCanvas();
		setupProtagonist();
	}
	
	public static void main(String[] args) {
		if (args.length > 0) {
			try {
				fullscreen = Boolean.parseBoolean(args[0]);
			} catch (final Exception e) {
				System.out.println("no boolean provided");
			}
		}
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		setupStage(primaryStage);
		canvas.requestFocus();
		setupAnimationTimer();
		
	}
	
	private static void setupProtagonist() {
		canvas.setOnKeyPressed(World.PROTAGONIST.getKeyPressed());
		canvas.setOnKeyReleased(World.PROTAGONIST.getKeyReleased());
	}
	
	private static void setupStage(final Stage stage) {
		stage.setTitle("Modular Shooter");
		if (fullscreen) {
			final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			World.setHEIGHT((int) dim.getHeight());
			World.setWIDTH((int) dim.getWidth());
			canvas.resize(World.getWIDTH(), World.getHEIGHT());
			stage.setFullScreenExitHint("");
			stage.setFullScreen(fullscreen);
			World.PROTAGONIST.coordinates.xCoordinate = World.getWIDTH() / 2.0;
			World.PROTAGONIST.coordinates.yCoordinate = World.getHEIGHT() / 2.0;
		}
		stage.setScene(setupScene());
		stage.show();
	}
	
	private static Scene setupScene() {
		final Pane pane = new Pane();
		pane.setStyle(" -fx-background-color: black");
		pane.getChildren().add(canvas);
		return new Scene(pane, World.getWIDTH(), World.getHEIGHT());
	}
	
	private static void setupCanvas() {
		canvas = new ResizableCanvas(World.getWIDTH(), World.getHEIGHT());
		canvas.setFocusTraversable(true);
		graphicalContext = canvas.getGraphicsContext2D();
	}
	
	private static void setupAnimationTimer() {
		
		timer = new HeartBeat(graphicalContext);
		timer.start();
		
	}
	
	@SuppressWarnings("unused")
	private static void printCanvas(Canvas canvas) { // NOSONAR
		try {
			final String path = "C:/Users/shu/Desktop/";
			System.out.println("Exporting Image"); // NOSONAR
			final SnapshotParameters sp = new SnapshotParameters();
			final WritableImage image = canvas.snapshot(sp, null);
			final BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
			final File file = new File(path + World.getWIDTH() + "x" + World.getHEIGHT() + ".png");
			ImageIO.write(bImage, "png", file);
			System.out.println("DONE!"); // NOSONAR
		} catch (final Exception e) { // NOSONAR
		
		}
	}
	
}
