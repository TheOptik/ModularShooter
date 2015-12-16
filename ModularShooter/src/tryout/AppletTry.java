package tryout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class AppletTry extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Canvas canvas = new Canvas(1000, 1000);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		GridPane grid = new GridPane();
		grid.getChildren().add(canvas);

		double xVel = -0.984018725346984;
		double yVel = 0.579719750881266;
		double betrag;
		double startAngleRads;
		double startAngle;
		if (Math.signum(xVel) < 0 || Math.signum(yVel) < 0) {
			betrag = Math.sqrt(Math.pow(xVel, 2) + Math.pow(yVel, 2));
			startAngleRads = Math.acos(xVel / betrag);
			startAngle = Math.toDegrees(startAngleRads);
		} else {
			betrag = Math.sqrt(Math.pow(xVel, 2) + Math.pow(yVel, 2));
			startAngleRads = Math.acos(xVel / betrag);
			startAngle = Math.toDegrees(startAngleRads) - 90;
		}

		gc.setStroke(Color.RED);
		gc.setLineWidth(2);
		gc.strokeLine(500, 500, 500 + xVel * 80, 500 + yVel * 80);

		gc.strokeArc(500 - 40, 500 - 40, 80, 80, startAngle, 180, ArcType.OPEN);

		primaryStage.setScene(new Scene(grid));
		primaryStage.show();
	}

}
