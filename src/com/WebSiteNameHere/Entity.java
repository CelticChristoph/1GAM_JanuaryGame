package com.WebSiteNameHere;

//abstract class for all entities: Hero, Obstacles.

public abstract class Entity {
	public float x, y, sx, sy; //position x; position y; sx = size on x axis; sy = size on y axis;
	//size is just in case, if we'll need 'em for collision detection later.
	
	public abstract void render();
	public abstract void update();
}
