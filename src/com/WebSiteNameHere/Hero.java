package com.WebSiteNameHere;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;

public class Hero extends Entity{
	public int lives;
	public float velocity;
	
	private Animation run;
	private Animation ascend;
	private Animation fall;
	private Animation land;
	
	private XMLPackedSheet sheet;
	private float spd = 1.5f;  //speed of overall animation
	
	public Hero(int x, int y)
	{
		this.x=x;
		this.y=y;
		
		velocity=-10f; //needed for a smooth animation start
		
		//setting up animations
		setUpAnimations();
		//run animation set to start at the beginning, other animations have to be stopped when setting them up.
		run.start();
	}
	
	public void update(){
		//update animation depending on velocity (more will be added later, with more animations)
		if(velocity>0){
			run.stop();
			ascend.restart(); //animations with .setLooping(false) have to be restarted when called upon
		}
		else if (velocity<0 && y<GameplayState.GROUND_HEIGHT){
			ascend.stop();
			fall.restart();
		}
		else if (velocity<0 && y==GameplayState.GROUND_HEIGHT && velocity>-7){
			fall.stop();
			land.restart();
		}
		else if(run.isStopped() && y==GameplayState.GROUND_HEIGHT && velocity<-7){
			land.stop();
			run.start();
		}
	}
	
	public void render(){ 
			//rendering according to what animation is running
			//these will need to be edited later to implement other animations as well
			//by simply adding &&anotherAnimationInstance.isStopped()
		if(run.isStopped() && fall.isStopped() && land.isStopped())
			ascend.draw(x,y);
		if(run.isStopped() && ascend.isStopped() && land.isStopped())
			fall.draw(x,y);
		if(run.isStopped() && ascend.isStopped() && fall.isStopped())
			land.draw(x,y);
		if(ascend.isStopped() && fall.isStopped() && land.isStopped())
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
        for (int i=1;i<=4;i++) {
        	ascend.addFrame(sheet.getSprite("jump"+(i)+".png"), 150);
        }
        ascend.setAutoUpdate(true);
        ascend.setLooping(false);
        ascend.setSpeed(spd);
        ascend.stop();
        
        fall = new Animation();
        for (int i=4;i<=5;i++) {
        	fall.addFrame(sheet.getSprite("jump"+(i)+".png"), 150);
        }
        fall.setAutoUpdate(true);
        fall.setLooping(false);
        fall.setSpeed(spd);
        fall.stop();
        
        land = new Animation();
        for (int i=6;i<=8;i++) {
        	land.addFrame(sheet.getSprite("jump"+(i)+".png"), 150);
        }
        land.setAutoUpdate(true);
        land.setLooping(false);
        land.setSpeed(spd);
        land.stop();
	}
}
