package com.WebSiteNameHere;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;

public class Collidable extends Entity {

	private Image sprite;

	private final int cx, cy, cdx, cdy;
	private float xSpeed;
	private Rectangle cBox;

	public Collidable(int x, int y, int cx, int cy, int cdx, int cdy) throws SlickException {

		this.x = x;
		this.y = y;
		this.cx = cx;
		this.cy = cy;
		this.cdx = cdx;
		this.cdy = cdy;
		xSpeed = GameplayState.getForegroundSpeed();
		
		cBox = new Rectangle(cx, cy, cdx-cx, cdy-cy);
		
	}

	public Collidable(String loc, int x, int y, int cx, int cy, int cdx, int cdy) throws SlickException {

		sprite = new Image(loc);
		this.x = x;
		this.y = y;
		this.cx = cx;
		this.cy = cy;
		this.cdx = cdx;
		this.cdy = cdy;
		
		xSpeed = GameplayState.getForegroundSpeed();
	}

	public void setSpeed() {
		xSpeed = GameplayState.getForegroundSpeed();
	}
	

	public void render() {
		sprite.draw(x, y);	
	}
	
	public void render(org.newdawn.slick.Graphics g) {
		render();
		g.drawRect(x, y, cdx-cx, cdy-cy);
	}

	public void update() {
		x -= xSpeed;
		
		System.out.println(x + ", " + xSpeed + ", " + GameplayState.getForegroundSpeed());
		
		if(x < 0) {
			x = 600;
		}
	}



}
