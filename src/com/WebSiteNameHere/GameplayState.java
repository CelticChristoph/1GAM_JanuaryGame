package com.WebSiteNameHere;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

//IMPORTANT: (0,0) is top left of screen
public class GameplayState extends BasicGameState {
	
	//Just a default to see if anything goes wrong
    int stateID = -1;
    
    //Default gravity constant, needs to be played with
    public static final float GRAVITY = 9.8f;
    public static final int GROUND_HEIGHT = 450;
    
    //Create our hero using the hero class which extends Image
    Hero red;
    Sprite bg;
 
    GameplayState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    @Override
    public int getID() 
    {
        return stateID;
    }
    
    //Use this method to set up all variables such as the images
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
    {
    	red = new Hero("/res/sprites/player/placeholderRRH.png");
    	red.velocity = 0.0f;
    	red.x = 20;
    	red.y = 450;
    	bg = new Sprite("/res/concepts/setting/InfiRun_Set1.jpg");
    	bg.x = 0;
    	bg.y = 0;
    }
    
    //Use render to draw everything
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
    {
    	g.drawImage(bg, bg.x, bg.y);
    	g.drawImage(red, red.x, red.y);
    }
    
    //This is where the meat of everything happens, updating position etc.
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
    {
    	//Get all inputs
    	Input input = gc.getInput();
    	if(input.isKeyDown(Input.KEY_W))
        {
    		//Only jump when red is on the ground
    		if(red.y==GROUND_HEIGHT)
    			red.velocity = 6;
        }
    	
    	//Update variables
    	red.velocity -= GRAVITY/60.0f;
    	
    	//Update positions
    	red.y = red.y-red.velocity;
    	if(red.y>GROUND_HEIGHT) {
    		red.y = GROUND_HEIGHT;
    	}
    }
 
}