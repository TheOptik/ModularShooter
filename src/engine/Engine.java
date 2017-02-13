package engine;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
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
	
}
