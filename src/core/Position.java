package core;

public class Position {
	private double x;
	private double y;
	
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double d) {
		this.x = d;
	}
	public double getY() {
		return y;
	}
	public void setY(double d) {
		this.y = d;
	}

	public void apply(Movement movement) {
		Vector2D vector = movement.getVector();
		x += vector.getX();
		y += vector.getY();
	}
	
	
}
