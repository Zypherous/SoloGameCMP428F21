package entity;

import java.awt.Image;

import controller.Controller;
import core.Position;
import core.Size;
import gfx.SpriteLibrary;

public class Enemy extends GameObject{
	private Player player;
	private int number;
	public Enemy(Rect rect, int number, Player player) {
		this.position = new Position(
		(double)rect.getX(),
		(double)rect.getY()
		);
		this.size = new Size(rect.getW(),rect.getH());
		this.rect = rect;
		this.player = player;
		rect.setX((int)this.position.getX());
		rect.setY((int)this.position.getY());
	}

	@Override
	public void update() {
		if(this.rect.overlaps( player.getRect())){
			int dx = (int) this.getPosition().getX() + 
					(int)Math.abs(player.getPosition().getX() 
							- this.getPosition().getX())
							;
			int dy = (int)this.getPosition().getY() + 
					(int)Math.abs(player.getPosition().getY() 
							- this.getPosition().getY())
							;
			this.getPosition().setX(dx);
			this.getPosition().setY(dy);
			this.rect.setX(dx);
			this.rect.setY(dy);
		}
		
	}

	@Override
	public Image getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumber() {
		return this.number;
	}
}
