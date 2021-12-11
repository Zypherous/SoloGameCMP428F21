package entity;
import java.awt.Color;
import java.awt.Graphics;

import display.Camera;

// Class to represent Axis-Aligned Rectangles


public class Rect
{
	private int x;
	private int y;
	
	
	int w;
	int h;
	
	private int velx;
	int vely;
	
	Color color = Color.RED;
	
	public Rect(int x, int y, int w, int h)
	{
		this.setX(x);
		this.setY(y);
		
		this.w = w;
		this.h = h;
		
		this.setVelx(5);
		this.vely = 5;
	}
	
	public Rect(int x, int y, int w, int h, int velx, int vely, Camera camera)
	{
		this.setX(x - (int)camera.getPosition().getX());
		this.setY(y - (int)camera.getPosition().getY());
		
		this.w = w;
		this.h = h;
		
		this.setVelx(velx);
		this.vely = vely;
	}
	
	public Rect(int x, int y, int w, int h, Color color)
	{
		this.setX(x);
		this.setY(y);
		
		this.w = w;
		this.h = h;
		
	    setColor(color);
	}

	public void setBounds(int x, int y, int w, int h)
	{
		this.setX(x);
		this.setY(y);
		
		this.w = w;
		this.h = h;
	}
	
	public void moveLeft(int dx)
	{
		setX(getX() - dx);
	}
	
	public void moveRight(int dx)
	{
		setX(getX() + dx);
	}
	
	public void moveUp(int dy)
	{
		setY(getY() - dy);
	}
	
	public void moveDown(int dy)
	{
		setY(getY() + dy);
	}
	
	public void moveBy(int dx, int dy)
	{
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
	public void resizeBy(int dw, int dh)
	{
		w *= dw;
		h *= dh;
	}
	
	public boolean overlaps(Rect r)
	{
		return (getX() + w >= r.getX()      ) &&
			   (getX()     <= r.getX() + r.w) &&
			   (getY() + h >= r.getY()      ) &&
			   (getY()     <= r.getY() + r.h);
	}
	public boolean overlaps(GameObject gameObject)
	{
		return (getX() + w >= (int)gameObject.getPosition().getX()) &&
			   (getX()     <= (int)gameObject.getPosition().getX() + (int)gameObject.getSize().getWidth()) &&
			   (getY() + h >= (int)gameObject.getPosition().getY()      ) &&
			   (getY()     <= (int)gameObject.getPosition().getY() + (int)gameObject.getSize().getHeight());
	}
	
	public boolean contains(int mx, int my)
	{
		return ( mx > getX()   ) &&   
			   ( mx < getX()+w ) && 
			   ( my > getY()   ) && 
			   ( my < getY()+h );
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	
	public void draw(Graphics pen, Color color)
	{
		pen.setColor(color);
		
		pen.drawRect(getX(), getY() , w, h);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelx() {
		return velx;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setVelx(int velx) {
		this.velx = velx;
	}
}