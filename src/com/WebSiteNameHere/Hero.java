package com.WebSiteNameHere;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;

public class Hero extends Entity{
	public int lives;
	public float vertVelocity;

	private Animation run;
	private Animation ascend;
	private Animation fall;
	private Animation land;
	
	private int jumpSeg = 0;

	private XMLPackedSheet sheet;
	private float spd = 1.5f;  //speed of overall animation

	public Hero(int x, int y)
	{
		this.x=x;
		this.y=y;

		vertVelocity = -10f; //Set to -10 for a smooth animation start
		//This is her vertical velocity, and helps determine which animation
		//needs to be used.

		//setting up animations
		setUpAnimations();
		//run animation set to start at the beginning, other animations have to be stopped when setting them up.
		run.start();
	}

	public void update(){
		//update animation depending on velocity (more will be added later, with more animations)
		if(vertVelocity>0){
			run.stop();
			
			if(ascend.isStopped())
				ascend.restart();
			if(ascend.getFrame() == ascend.getFrameCount())
			{
				jumpSeg = 1;
			}
				
			
			//ascend.restart(); //animations with .setLooping(false) have to be restarted when called upon
		}
		else if (vertVelocity<0 && y<GameplayState.GROUND_HEIGHT){
			ascend.stop();
			
			if(fall.isStopped())
				fall.restart();
			if(fall.getFrame() == fall.getFrameCount())
			{
				jumpSeg = 2;
			}
		}
		else if (vertVelocity<-7 && y==GameplayState.GROUND_HEIGHT && vertVelocity>-10){
			fall.stop();

			if(land.isStopped())
				land.restart();
			if(land.getFrame() == land.getFrameCount())
			{
				jumpSeg = 3;
			}
		}
		else if(run.isStopped() && y==GameplayState.GROUND_HEIGHT && vertVelocity<-10){
			land.stop();
			if(run.isStopped())
				run.start();
			jumpSeg = 0;

		}
	}

	public void render(){ 
		//rendering according to what animation is running
		//these will need to be edited later to implement other animations as well
		//by simply adding &&anotherAnimationInstance.isStopped()
		if(run.isStopped() && fall.isStopped() && land.isStopped()) // && jumpSeg == 0)
			ascend.draw(x,y);
		if(run.isStopped() && ascend.isStopped() && land.isStopped()) // && jumpSeg == 1)
			fall.draw(x,y);
		if(run.isStopped() && ascend.isStopped() && fall.isStopped()) //&& jumpSeg == 2)
			land.draw(x,y);
		if(ascend.isStopped() && fall.isStopped() && land.isStopped()) // && jumpSeg == 0)
			run.draw(x,y);
	}

	private void setUpAnimations(){
		//I am too tired atm, thus copy paste code


		//getting SpriteSheet
		try {
			sheet = new XMLPackedSheet("res/sprites/player/RedSpriteSheet.png", "res/sprites/player/RedSpriteSheet.xml");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		//setting up running animation according to sprite names
		run = new Animation();
		for (int i=1;i<=9;i++) {
			run.addFrame(sheet.getSprite("run"+(i)+".png"), 150);
		}
		//these should be self-explanatory
		run.setAutoUpdate(true);
		run.setLooping(true);
		run.setSpeed(spd);

		//setting up jumping animation (3 parts of it) according to sprite names.
		ascend = new Animation();
		for (int i=1;i<=5;i++) {
			ascend.addFrame(sheet.getSprite("jump"+(i)+".png"), 150);
		}
		ascend.setAutoUpdate(true);
		ascend.setLooping(false);
		ascend.setSpeed(spd - .5f);
		ascend.stop();

		fall = new Animation();
		for (int i=4;i<=6;i++) {
			fall.addFrame(sheet.getSprite("jump"+(i)+".png"), 150);
		}
		fall.setAutoUpdate(true);
		fall.setLooping(false);
		fall.setSpeed(spd -.5f);
		fall.stop();

		land = new Animation();
		for (int i=6;i<=8;i++) {
			land.addFrame(sheet.getSprite("jump"+(i)+".png"), 150);
		}
		land.setAutoUpdate(true);
		land.setLooping(false);
		land.setSpeed(spd - .5f);
		land.stop();
	}
}
