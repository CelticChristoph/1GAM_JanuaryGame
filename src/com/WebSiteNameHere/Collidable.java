package com.WebSiteNameHere;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Collidable extends Entity {

	private Image sprite = null;

	private int cx, cy;
	private float xSpeed;
	private Rectangle cBox;
	private boolean hero;

	public Collidable(int x, int y, int cx, int cy, int cdx, int cdy, boolean hero) {

		this.x = x;
		this.y = y;
		this.cx = cx;
		this.cy = cy;
		this.hero = hero;
		
		xSpeed = GameplayState.getForegroundSpeed();
		cBox = new Rectangle(cx, cy, cdx-cx, cdy-cy);
		
	}

	public Collidable(String loc, int x, int y, int cx, int cy, int cdx, int cdy, boolean hero) throws SlickException {

		sprite = new Image(loc);
		this.x = x;
		this.y = y;
		this.cx = cx;
		this.cy = cy;
		this.hero = hero;
		
		cBox = new Rectangle(x + cx, y + cy, cdx-cx, cdy-cy);
		
		xSpeed = GameplayState.getForegroundSpeed();
	}

	public void setSpeed() {
		xSpeed = GameplayState.getForegroundSpeed();
	}
	

	public void render() {
		sprite.draw(x, y);
	}
	
	public void render(org.newdawn.slick.Graphics g) {
		if(sprite != null)
			render();
		g.draw(cBox);
//		g.drawRect(x, y, cdx-cx, cdy-cy);
	}

	public void update() {
		if(!hero){
			x -= xSpeed;
		}
		
		cBox.setLocation(x + cx, y + cy);
		
//		System.out.println(x + ", " + xSpeed + ", " + GameplayState.getForegroundSpeed());
		
		if(x < 0) {
			x = 600;
		}
	}
	
	public void setCol(int x, int y, int cx, int cy, int cdx, int cdy){
		this.x = x;
		this.y = y;
		this.cx = cx;
		this.cy = cy;
		
		setColLoc();
		setColSize(cdx, cdy);
	}
	
	public void setColLoc(){
		cBox.setLocation(x + cx, y + cy);
	}
	
	public void setColSize(int cdx, int cdy){
		cBox.setSize(cdx - cx, cdy - cy);
	}
	
	public Shape getShape(){
		return cBox;
	}
	
	public boolean checkCollision(Collidable obsCol){
		if(cBox.intersects(obsCol.getShape())){
			System.out.println("OMG COLLISIONS AND STUFF.");
			return true;
		}
		else
			return false;
	}
	
}
