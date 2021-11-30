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
	public void setX(int x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public void apply(Movement movement) {
		Vector2D vector = movement.getVector();
		x += vector.getX();
		y += vector.getY();
	}
	
	
}
