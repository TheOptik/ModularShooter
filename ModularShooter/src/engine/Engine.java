package engine;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import objects.GameObject;
import util.Drawable;
import util.Tickable;
import world.World;

public class Engine extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				for (GameObject object : World.OBJECTS) {
					if (object instanceof Tickable) {
						((Tickable) object).tick();
					}
					if (object instanceof Drawable) {
						((Drawable) object).draw(null);// FIXME grapics context
					}
				}
			}

		};
		timer.start();

	}

}
