package core;

import controller.EntityController;

public class Movement {
	
	private Vector2D vector;
	private double velocity;
	
	
	public Movement(double velocity) {
		this.vector = new Vector2D(0, 0);
		this.velocity = velocity;
	}
	


	public void update(EntityController controller) {

		int deltaX = 0;
		int deltaY = 0;
		
		if(controller.isRequestingUp()) {
			deltaY --;
		}
		if(controller.isRequestingDown()) {
			deltaY ++;
		}
		if(controller.isRequestingRight()) {
			deltaX ++;
		}
		if(controller.isRequestingLeft()) {
			deltaX --;
		}
		
		vector = new Vector2D(deltaX, deltaY);
		vector.normalize();
		vector.multiply(velocity);
//		System.out.println(vector.length());
	}
	
	public Vector2D getVector() {
		return vector;
	}

	public void setVector(Vector2D vector) {
		this.vector = vector;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}



	public boolean isMoving() {
		
		return vector.length() > 0;
	}

	public void multiply(double multiplier) {
        vector.multiply(multiplier);
    }



	public void stop(boolean stopX, boolean stopY) {
		vector = new Vector2D(
				stopX ? 0: vector.getX(), 
				stopY ? 0: vector.getY());
	}



	public Vector2D getDirection() {
		Vector2D direction = Vector2D.copyOf(vector);
		direction.normalize();
		
		return direction;
	}



	public void add(Vector2D vector) {
		this.vector.add(vector);
	}
}

