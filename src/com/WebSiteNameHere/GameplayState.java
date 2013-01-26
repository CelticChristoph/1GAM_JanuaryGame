package com.WebSiteNameHere;

import java.util.ArrayList;

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

	private final long initialTime = System.nanoTime();

	//Stuff
	Double foregroundX;

	//Score stuff.
	private long score;
	private float scoreMult;
	private long prevScore;
	private boolean scoreFlag;

	private static float foregroundSpeed;

	//Create our hero using the hero class which extends Image
	Hero red;

	//Create the backgrounds (which can also be foreground)
	//Must create a new ArrayList<String> for each layer of
	//background you want to create. (At least for now.)
	//Then make a Background class.
	ArrayList<String> foregroundImageLocations, midgroundImageLocations, backgroundImageLocations, secondBG;
	Background foreground, midground, background, secondbg;
	
	private Collidable colTest;

	GameplayState(int stateID) 
	{
		this.stateID = stateID;
	}

	public int getID() 
	{
		return stateID;
	}

	//Use this method to set up all variables such as the images
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		score = 0;
		scoreMult = 1f;

		System.out.println();
		//Sets the initial speed of ALL backgrounds.
		foregroundSpeed = 8f;

		red = new Hero(20, 450); //position x, y

		//-----
		//Initialize each ArrayList<String> that you made for each layer of background
		//then .add() the string location of each file you want to add. After that,
		//initialize the Background class object, and pass it the parameters you want.
		//Parameters are (X-coordinate, Y-coordinate, Speed, ArrayList<String>).
		//-Celtic
		//-----
		//Use of simple array might be more resource friendly, not sure if we need
		//dynamically changing size
		//-Alex
		//-----
		secondBG = new ArrayList<String>();
		backgroundImageLocations = new ArrayList<String>();
		midgroundImageLocations = new ArrayList<String>();
		foregroundImageLocations = new ArrayList<String>();
		secondBG.add("/res/sprites/bg/SecondBackground1.png");
		backgroundImageLocations.add("/res/sprites/bg/background1.png");
		midgroundImageLocations.add("/res/sprites/bg/midground1.png");
		foregroundImageLocations.add("/res/sprites/bg/foreground1.png");
		foregroundImageLocations.add("/res/sprites/bg/foreground2.png");

		secondbg = new Background(0f, 0f, foregroundSpeed * .1f, secondBG);
		background = new Background(0f, 375f, foregroundSpeed * .56f, backgroundImageLocations);
		midground = new Background(0f, 56f, foregroundSpeed * .75f, midgroundImageLocations);
		foreground = new Background(0f, 536f, foregroundSpeed, foregroundImageLocations);
		
		colTest = new Collidable("/res/sprites/traps/smallStump.png", 100, 550, 0, 0, 64, 64);
	}

	private void updateSpeeds() {
		foreground.setSpeed(foregroundSpeed);
		midground.setSpeed(foregroundSpeed * .75f);
		background.setSpeed(foregroundSpeed * .56f);
		secondbg.setSpeed(foregroundSpeed * .1f);
		
		//REMEMBER TO CHANGE THIS TO WORK FOR AN
		//ARRAY OF THESE THINGS \/\/\/\/
		colTest.setSpeed();
	}

	public static float getForegroundSpeed() {
		return foregroundSpeed;

	}

	//Use render to draw everything
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		secondbg.render();
		background.render();
		midground.render();
		foreground.render();
		red.render();
		colTest.render(g);

		g.drawString("Score: " + score, 650f, 10f);
		g.drawString("Speed: " + foregroundSpeed, 300f, 10f);
	}

	//This is where the meat of everything happens, updating position etc.
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		score = (((System.nanoTime() - initialTime)/100000000) * (long)scoreMult);

		//Change the "100" to change how quickly
		//the speed and multiplier increase.
		//You are ~10 score per second at this rate.
		if(score % (100 * scoreMult) == 0){

			if(prevScore != score)
				scoreFlag = false;

			if(prevScore == score){
				scoreFlag = true;
			}
			if(scoreFlag == false){

				//Change the "10f" to change the amount
				//of speed increase.
				foregroundSpeed += 10f;
				updateSpeeds();

				scoreMult += .5f;
				System.out.println("Faster!");
			}

			prevScore = score;
			scoreFlag = true;
		}

		//Get all inputs
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_W)&&(red.y==GROUND_HEIGHT))
			//Only jump when red is on the ground
			red.vertVelocity = 7;
		else if(input.isKeyPressed(Input.KEY_S)&&(red.y==GROUND_HEIGHT))
			red.rolling=true;
		else if(input.isKeyDown(Input.KEY_D)&&(red.x<=652f)) //800-128-20
			red.x+=1f;
		else if(input.isKeyDown(Input.KEY_A)&&(red.x>=20f))
			red.x-=1f;

		secondbg.update();
		background.update();
		midground.update();
		foreground.update();
		red.update();
		colTest.update();

		red.vertVelocity -= GRAVITY/60.0f;
		red.y =red.y-red.vertVelocity;

		if(red.y>GROUND_HEIGHT) {
			red.y = GROUND_HEIGHT;
		}
		if(red.vertVelocity < -11f)
			red.vertVelocity = -10.5f;
	}

}