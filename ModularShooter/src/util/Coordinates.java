package util;

public class Coordinates {

	public double xCoordinate;
	public double yCoordinate;

	public Coordinates(double xCoordinate, double yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public void calculateMovement(Velocity velocity) {
		this.xCoordinate += velocity.xVelocity;
		this.yCoordinate += velocity.yVelocity;

	}
}
