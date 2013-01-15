package com.WebSiteNameHere;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class JanuaryGame extends BasicGame{
	
	static Hero hero;
	
	public JanuaryGame()
	{
		super("January Game");
	}
	
	
	
	public static void main(String[] args) throws SlickException
    {
         AppGameContainer app = new AppGameContainer(new JanuaryGame());

         hero = new Hero("Image file name goes here");
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
	
	

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		g.setColor(Color.green);
		g.drawString("Hello World!",200,200);
	}



	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}


}
