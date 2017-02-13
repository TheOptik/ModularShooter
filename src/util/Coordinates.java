package util;

import engine.HeartBeat;

public class Coordinates {

	public double xCoordinate;
	public double yCoordinate;

	public Coordinates(double xCoordinate, double yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public void calculateMovement(Velocity velocity) {
		this.xCoordinate += velocity.xVelocity * HeartBeat.getDeltaTime();
		this.yCoordinate += velocity.yVelocity * HeartBeat.getDeltaTime();

	}

	public Coordinates multiply(int multiplier) {
		return new Coordinates(xCoordinate * multiplier, yCoordinate * multiplier);
	}

	public Coordinates subtract(Coordinates coordinates) {
		return new Coordinates(xCoordinate - coordinates.xCoordinate, yCoordinate - coordinates.yCoordinate);
	}

	public Coordinates add(Coordinates coordinates) {
		return new Coordinates(xCoordinate + coordinates.xCoordinate, yCoordinate + coordinates.yCoordinate);
	}

	@Override
	public String toString() {
		return xCoordinate + " : " + yCoordinate;
	}

	public Coordinates divide(int divisor) {
		return new Coordinates(xCoordinate / divisor, yCoordinate / divisor);
	}

	public Coordinates round() {
		return new Coordinates((int) (xCoordinate + 0.5 * Math.signum(xCoordinate)),
				(int) (yCoordinate + 0.5 * Math.signum(yCoordinate)));
	}

	public double magnitude() {
		return Math.sqrt(xCoordinate * xCoordinate + yCoordinate * yCoordinate);
	}

}
