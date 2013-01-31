package com.WebSiteNameHere;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

//IMPORTANT: (0,0) is top left of screen
public class GameplayState extends BasicGameState {

	//Just a default to see if anything goes wrong
	int stateID = -1;

	private boolean isPaused = false;

	//Score stuff.
	private long Time;
	private long score;
	private float scoreMult;

	//Speed stuff.
	private float speedIncreaseInterval;
	private static float foregroundSpeed;
	private float foregroundSpeedComparison;

	//Create our hero using the hero class which extends Image
	private static Hero red;

	private static Collidable ground;
	
	//trap stuff
	private Template template;
	private ArrayList<Collidable> obsGroup1;
//	private ArrayList<Collidable> obsGroup2;
//	private ArrayList<Collidable> obsGroup3;
	
	//Create the backgrounds (which can also be foreground)
	//Must create a new ArrayList<String> for each layer of
	//background you want to create. (At least for now.)
	//Then make a Background class.
	ArrayList<String> foregroundImageLocations, midgroundImageLocations, backgroundImageLocations, secondBG;
	Background foreground, midground, background, secondbg;
	Image paused;

//	private Collidable colTest;
//	private Collidable colTakeTwo;
//	private Collidable colPit;

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
		Time = System.nanoTime();
		score = 0;
		scoreMult = 1f;

		//Sets the initial speed of ALL backgrounds.
		foregroundSpeed = 8f;
		foregroundSpeedComparison = foregroundSpeed;
		speedIncreaseInterval = 100 * scoreMult;

		red = new Hero(20, 580); //position x, y
		ground = new Collidable("ground", 0, 580, 0, 0, 820, 30, false, false);
		ground.setStationaty();
		
		template = new Template();

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
		secondBG.add("/res/sprites/bg/sky.png");
		backgroundImageLocations.add("/res/sprites/bg/background.png");
		midgroundImageLocations.add("/res/sprites/bg/midground.png");
		foregroundImageLocations.add("/res/sprites/bg/foreground.png");

		secondbg = new Background(0f, 0f, foregroundSpeed * .1f, secondBG);
		background = new Background(0f, 256f, foregroundSpeed * .56f, backgroundImageLocations);
		midground = new Background(0f, 60f, foregroundSpeed * .75f, midgroundImageLocations);
		foreground = new Background(0f, 536f, foregroundSpeed, foregroundImageLocations);
		
		obsGroup1 = template.getNextObs();

//		colTest = new Collidable("/res/sprites/traps/smallStump.png", "stump", 100, 550, 0, 0, 64, 64, false, true);
//		colTakeTwo = new Collidable("/res/sprites/test.png", "box", 500, 380, -200, 75, 500, 120, false, false); //183, 120
//		colPit = new Collidable("/res/sprites/traps/pit.png", "pit", 900, 568, 32, 9, 239, 30, false, true);

		paused = new Image("/res/sprites/pause.png");
		paused.setAlpha(0.8f);
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sb) throws SlickException
	{
		super.enter(gc, sb);
		init(gc, sb);
	}

	/**
	 * Used to change speed ONLY when speed of game changes.
	 */
	private void updateSpeeds() {
		foreground.setSpeed(foregroundSpeed);
		midground.setSpeed(foregroundSpeed * .75f);
		background.setSpeed(foregroundSpeed * .56f);
		secondbg.setSpeed(foregroundSpeed * .1f);

		//REMEMBER TO CHANGE THIS TO WORK FOR AN
		//ARRAY OF THESE THINGS \/\/\/\/
//		colTest.setSpeed();
//		colTakeTwo.setSpeed();
//		colPit.setSpeed();
		
		for(Collidable col : obsGroup1) { col.setSpeed(); }
/*		for(Collidable col : obsGroup2) { col.setSpeed(); }
		for(Collidable col : obsGroup3) { col.setSpeed(); }
*/	}
	
	public static boolean collidingGround(){
		//to be able to stand on branches add commented out code below
		
/*		for(Collidable col : obsGroup1) {
			if(col.getName() == "branch")
				return true;
		}
*/	
		return red.getHeroCol().checkCollision(ground);
	}
	
	public static int collidingGroundY(){
		//to be able to stand on branches add commented out code below
		
/*		for(Collidable col : obsGroup1) {
			if(col.getName() == "branch")
				return col.getFloor();
		}		
*/		
		if(red.getHeroCol().checkCollision(ground)){
			return ground.getFloor();
		}
		else
			return 0;
	}

	public static float getForegroundSpeed() {
		return foregroundSpeed;
	}

	private void pauseGame(StateBasedGame sbg) {
		isPaused = true;
		red.pauseAnimation();
		sbg.pauseUpdate();
	}

	private void whilePaused(GameContainer gc, StateBasedGame sbg){
		paused.draw(0, 0);
		Input input = gc.getInput();
		if(input.isKeyPressed(Input.KEY_ENTER)||input.isKeyPressed(Input.KEY_SPACE)){
			sbg.unpauseUpdate();
			red.resumeAnimation();
			isPaused=false;
		}
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			sbg.unpauseUpdate();
			isPaused=false;
			sbg.enterState(JanuaryGame.MAINMENUSTATE);
		}
	}
	
	private void getNewObsLayout(ArrayList<Collidable> group){
		System.out.println("Trying to get new obstacles");
		group.clear();
		group = template.getNextObs();
	}

	//Use render to draw everything
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		secondbg.render();
		background.render();
		midground.render();
		foreground.render();
		
//		ground.render(g);
//		red.getHeroCol().render(g);
		
//		colTest.render(g);
//		colTakeTwo.render(g);
//		colPit.render(g);
		for(Collidable col : obsGroup1){
			col.render(g);
		}
		
		red.render(sbg, g);

		if(isPaused)
			whilePaused(gc,sbg);

		g.drawString("Score: " + score, 650f, 10f);
		g.drawString("Speed: " + foregroundSpeed, 300f, 10f);
	}

	//This is where the meat of everything happens, updating position etc.
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		if(!gc.hasFocus())
			pauseGame(sbg);

		//Scoring is now handled by this timer
		if(System.nanoTime() - Time > 100000000){
			score += 1 * scoreMult;
			Time = System.nanoTime();
		}
		
		//decrease 100(also in init method) or scoreMultiplier gain to decrease speed gain intervals
		//(thus going faster sooner)
		if(((8 + (int)(score / speedIncreaseInterval)) > foregroundSpeed) && (8 + (int)(score / speedIncreaseInterval)) % 2 == 0){
				foregroundSpeed = 8 + (int)(score / speedIncreaseInterval);
				speedIncreaseInterval = 100 * scoreMult;
		}
		if(foregroundSpeedComparison != foregroundSpeed){
			updateSpeeds();
			foregroundSpeedComparison = foregroundSpeed;
			
			scoreMult += .5f;
		}
		
		//Obstacle stuff
		if(obsGroup1.get(obsGroup1.size() - 1).x < -256f){
			System.out.println("New set of obstacles called");
			getNewObsLayout(obsGroup1);
		}

/*		if(red.getHeroCol().checkCollision(colTakeTwo)){
			if(colTakeTwo.isTrap()){
				System.out.println("THIS SHOULDN'T BE HAPPENING.");
			}
			else if(red.x + 106 < colTakeTwo.getLeftWall()){
				System.out.println("COLLISION BAM");
			}
			else{
				red.y = colTakeTwo.getFloor() - 127;
			}
		}
*/		
		//Get all inputs
		Input input = gc.getInput();
		if (!red.getMode()){
			if(input.isKeyDown(Input.KEY_W))
				//Only jump when red is on the ground
				red.setAscending(true);
			else 
				red.setAscending(false);
			
			if(input.isKeyPressed(Input.KEY_S) && collidingGround())
				red.rolling=true;
		} else {
			if(input.isKeyDown(Input.KEY_W)){
				red.y -= 10f;
				if(red.y < 0) red.y = 0;
			}
			else if(input.isKeyDown(Input.KEY_S))
				red.y += 10f;
		}

		if(input.isKeyDown(Input.KEY_D) && (red.x <= 652f))
			red.x += 5;
		else if(input.isKeyDown(Input.KEY_A) && (red.x >= 20f))
			red.x -= 5f;
		if(input.isKeyPressed(Input.KEY_P) || input.isKeyPressed(Input.KEY_ESCAPE))
			pauseGame(sbg);

		secondbg.update();
		background.update();
		midground.update();
		foreground.update();
		ground.update();
		red.update();
		
		for(Collidable col : obsGroup1){
			col.update();
			if(red.getHeroCol().checkCollision(col))
				if(col.isTrap())
					System.out.println("OMG COLLISIONS AND STUFF.");
		}
	}
}