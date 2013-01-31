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
	private String name;
	private boolean hero;
	private boolean trap;

	public Collidable(String name, int x, int y, int cx, int cy, int cdx, int cdy, boolean hero, boolean trap) {

		this.name = name;
		this.x = x;
		this.y = y;
		this.cx = cx;
		this.cy = cy;
		this.hero = hero;
		this.trap = trap;

		xSpeed = GameplayState.getForegroundSpeed();
		cBox = new Rectangle(cx, cy, cdx-cx, cdy-cy);

	}

	public Collidable(String loc, String name, int x, int y, int cx, int cy, int cdx, int cdy, boolean hero, boolean trap) throws SlickException {

		sprite = new Image(loc);
		this.name = name;
		this.x = x;
		this.y = y;
		this.cx = cx;
		this.cy = cy;
		this.hero = hero;
		this.trap = trap;

		cBox = new Rectangle(x + cx, y + cy, cdx-cx, cdy-cy);

		xSpeed = GameplayState.getForegroundSpeed();
	}

	public void setSpeed() {
		xSpeed = GameplayState.getForegroundSpeed();
	}
	
	public void setStationaty(){
		xSpeed = 0;
	}


	public void render() {
		sprite.draw(x, y);
	}

	public void render(org.newdawn.slick.Graphics g) {
		if(sprite != null)
			render();
	//	g.draw(cBox);
	}

	public void update() {
		if(!hero){
			x -= xSpeed;
		}

		cBox.setLocation(x + cx, y + cy);

		//		System.out.println(x + ", " + xSpeed + ", " + GameplayState.getForegroundSpeed());
/*
		if(x < 0) {
			x = 600;
		}
*/	}

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

	public boolean isHero(){
		return hero;
	}
	
	public boolean isTrap(){
		return trap;
	}

	public boolean checkCollision(Collidable obsCol){
		if(cBox.intersects(obsCol.getShape())){
			if(obsCol.isHero() || isHero()){
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	public int getFloor(){
		return (int)y + cy;
	}
	
	public int getLeftWall(){
		return (int)x + cx;
	}
	
	public String getName(){
		return name;
	}

}
