package core;

public class Vector2D {

	
	private double x;
	private double y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double length() {
		return Math.sqrt( x* x + y * y);
	}
	
	public void normalize() {
		double length = length();
		// Terinary to check if x and y is 0 before dividing
		x = x== 0 ? 0 : x/length;
		y = y== 0 ? 0 : y/length;
		
	}
	
	public void multiply(double velocity) {
		x *= velocity;
		y *= velocity;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public double getY() {
		// TODO Auto-generated method stub
		return y;
	} 
}
