package core;

import java.awt.Graphics;

public class Line
{
	private int Ax;
	private int Ay;
	
	private int Bx;
	private int By;
	
	
	private double Nx;
	private double Ny;
	
	double c;
	
	public Line(int Ax, int Ay, int Bx, int By)
	{
		this.Ax = Ax;
		this.Ay = Ay;

		this.Bx = Bx;
		this.By = By;
		
		double vx = Ax - Bx;
		double vy = Ay - By;
		
		double mag = Math.sqrt(vx*vx + vy*vy);
		
		
		Nx = -vy / mag;
		Ny =  vx / mag; 
		
		c = Ax * Nx + Ay * Ny;
	}
	
	public double distanceFrom(double Px, double Py)
	{
		return Px * Nx + Py * Ny - c;
	}

	public void draw(Graphics pen)
	{
		pen.drawLine(Ax, Ay, Bx, By);
	}
	
	public void moveLeft(int dx)
	{
		Ax -= dx;
		Bx -= dx;
	}
	
	public void moveRight(int dx)
	{
		Ax += dx;
		Bx += dx;
	}
	
	public void moveUp(int dy)
	{
		Ay -= dy;
		By -= dy;
	}
	
	public void moveDown(int dy)
	{
		Ay += dy;
		By += dy;
	}
	
	public void moveBy(int dx, int dy)
	{
		Ax += dx;
		Ay += dy;
		
		Bx += dx;
		By += dy;
	}

	public int getAx() {
		return Ax;
	}

	public void setAx(int ax) {
		Ax = ax;
	}

	public int getAy() {
		return Ay;
	}

	public void setAy(int ay) {
		Ay = ay;
	}

	public int getBx() {
		return Bx;
	}

	public void setBx(int bx) {
		Bx = bx;
	}

	public int getBy() {
		return By;
	}

	public void setBy(int by) {
		By = by;
	}

	public double getNx() {
		return Nx;
	}

	public void setNx(double nx) {
		Nx = nx;
	}

	public double getNy() {
		return Ny;
	}

	public void setNy(double ny) {
		Ny = ny;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}


}