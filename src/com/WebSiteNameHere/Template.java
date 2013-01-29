package com.WebSiteNameHere;

import java.util.ArrayList;
import java.util.Random;

public class Template {

	private Random random = new Random();
	private ArrayList<ArrayList<Collidable>> allObs = new ArrayList<ArrayList<Collidable>>();
	private ArrayList<Collidable> obsGroup = new ArrayList<Collidable>();
	
	
	public Template() {
		
		allObs.add(obsGroup);
	}

	//public Collidable(String loc, int x, int y, int cx, int cy, int cdx, int cdy, boolean hero, boolean trap)

	
	public ArrayList<Collidable> getNextObs(){
		
		int blah = random.nextInt(allObs.size());
		ArrayList<Collidable> tempObsGroup = new ArrayList<Collidable>();
		
		for(Collidable col : allObs.get(blah)){
			tempObsGroup.add(col);
		}
		
		return tempObsGroup;
	}
}
