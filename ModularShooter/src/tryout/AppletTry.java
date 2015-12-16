package tryout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AppletTry extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Canvas canvas = new Canvas(1000, 1000);
		GridPane grid = new GridPane();
		grid.getChildren().add(canvas);

		primaryStage.setScene(new Scene(grid));
		primaryStage.show();
	}

}
