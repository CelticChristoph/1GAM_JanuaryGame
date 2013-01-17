package com.WebSiteNameHere;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
 
/**
 *
 * @author Drenguin
 *
 */
public class JanuaryGame extends StateBasedGame {
	
	//Constants that define different states
    public static final int MAINMENUSTATE          = 0;
    public static final int GAMEPLAYSTATE          = 1;
 
    public JanuaryGame()
    {
    	//Sets the title of the screen to "Red"
        super("Red");
        
        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new GameplayState(GAMEPLAYSTATE));
        
        //When the app begins running it will enter this state first
        this.enterState(GAMEPLAYSTATE);
    }
 
    public static void main(String[] args) throws SlickException
    {
         AppGameContainer app = new AppGameContainer(new JanuaryGame());
         
         //Make it 800x600
         app.setDisplayMode(800, 600, false);
         //This limits the fps to the screen refresh rate
         app.setVSync(true);
         //Let's get going!
         app.start();
    }
    
    //Just initializes the states
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException 
    {
    	this.getState(MAINMENUSTATE).init(gameContainer, this);
        this.getState(GAMEPLAYSTATE).init(gameContainer, this);
    }
}