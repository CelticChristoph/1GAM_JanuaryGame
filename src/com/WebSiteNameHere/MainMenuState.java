package com.WebSiteNameHere;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
public class MainMenuState extends BasicGameState {
 
    int stateID = -1;
    Image background = null;
    Image startGame = null; Image startGameSelected = null;
    Image exitGame = null; Image exitGameSelected = null;
    Image currentStart = null; Image currentExit = null;
 
    MainMenuState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    @Override
    public int getID() 
    {
        return stateID;
    }
 
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	background = new Image ("res/sprites/title.png");
    	startGame = new Image ("res/sprites/menuTexts/menu_NewGame1.png");
    	startGameSelected = new Image ("res/sprites/menuTexts/menu_NewGame2.png");
    	exitGame = new Image ("res/sprites/menuTexts/menu_Exit1.png");
    	exitGameSelected = new Image ("res/sprites/menuTexts/menu_Exit2.png");
    	
    	currentStart = startGame;
    	currentExit = exitGame;
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
    	background.draw(0, 0);
    	currentStart.draw(530, 440);
    	currentExit.draw(530, 507);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
    {
    	Input input  = gc.getInput();
    	if(input.isKeyPressed(Input.KEY_ESCAPE)){
    		gc.exit();
    	}
    	if(input.isKeyPressed(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)){
    		currentStart = startGameSelected;
    		currentExit = exitGame;
    	}
    	if(input.isKeyPressed(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)){
    		currentExit = exitGameSelected;
    		currentStart = startGame;
    	}
    	if(currentStart == startGameSelected && (input.isKeyPressed(Input.KEY_ENTER)||(input.isKeyPressed(Input.KEY_SPACE))))
    		sbg.enterState(JanuaryGame.GAMEPLAYSTATE);
    	if(currentExit == exitGameSelected && (input.isKeyPressed(Input.KEY_ENTER)||(input.isKeyPressed(Input.KEY_SPACE))))
    		gc.exit();
    }
 
}