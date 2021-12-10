package core;

public class Position {
	
	public static int PROXIMITY_RANGE = 8;
	
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

	public boolean isInRangeOf(Position position) {

		return Math.abs(x - position.getX()) < Position.PROXIMITY_RANGE&& Math.abs(y - position.getY()) < Position.PROXIMITY_RANGE;
	}

	public static Position copyOf(Position position) {
		
		return new Position(position.getX(), position.getY());
	}

	public void applyX(Movement movement) {
		Vector2D vector = movement.getVector();
		x += vector.getX();
	}
	public void applyY(Movement movement) {
		Vector2D vector = movement.getVector();
		y += vector.getY();
	}

	public void add(Position position) {
		x+= position.getX();
		y+= position.getY();
		
	}
	
}
