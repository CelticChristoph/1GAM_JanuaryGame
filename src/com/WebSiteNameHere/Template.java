package com.WebSiteNameHere;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.SlickException;

public class Template {

	private Random random = new Random();
	private ArrayList<ArrayList<Collidable>> allObs = new ArrayList<ArrayList<Collidable>>();
	private ArrayList<Collidable> obsGroup1 = new ArrayList<Collidable>();
//	private ArrayList<Collidable> obsGroup2 = new ArrayList<Collidable>();
	
	
	public Template() {
		allObs.add(obsGroup1);
//		allObs.add(obsGroup2);
	}

	//public Collidable(String loc, String name, int x, int y, int cx, int cy, int cdx, int cdy, boolean hero, boolean trap)
	public ArrayList<Collidable> getNextObs(){
		int blah = random.nextInt(allObs.size());
		
		switch(blah){
		
		case 0:
			allObs.get(0).clear();
			try {
				allObs.get(0).add(new Collidable("/res/sprites/traps/pit.png", "pit", 900, 568, 32, 9, 239, 30, false, true)); //pit
			} catch (SlickException e) { e.printStackTrace(); }
//		case 1:
		}
		
	return allObs.get(blah);
	}
}
