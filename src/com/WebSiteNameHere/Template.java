package com.WebSiteNameHere;

import java.util.ArrayList;
import java.util.Random;

public class Template {
	private Random random = new Random();
	private ArrayList<Collidable> obsGroup = new ArrayList<Collidable>();

	public Template() {
		obsGroup.add(0, new Collidable("/res/sprites/traps/pit.png", "pit", 900, 568, 94, 15, 225, 30, false, true)); //pit
		obsGroup.add(1, new Collidable("/res/sprites/traps/tree.png", "tree", 1200, 10, 0, 0, 1, 1, false, false)); //tree
		obsGroup.add(2, new Collidable("/res/sprites/traps/branch.png", "branch", 1329, 445, 70, 52, 178, 67, false, true)); //branch
	}
	
	private void setNewLoc(int i, int x, int y){
		obsGroup.get(i).setX(x);
		obsGroup.get(i).setY(y);
	}

	//public Collidable(String loc, String name, int x, int y, int cx, int cy, int cdx, int cdy, boolean hero, boolean trap)
	public ArrayList<Collidable> getNextObs(){
		
		int blah = random.nextInt(6);
		if(blah == 0){}
		else if(blah == 1){
			setNewLoc(0, 900, 568);
			setNewLoc(1, 1200, 10);
			setNewLoc(2, 1334, 445);
			}
		else if(blah == 2){
			setNewLoc(0, 1300, 568);
			setNewLoc(1, 900, 10);
			setNewLoc(2, 1029, 468);
			}
		else if(blah == 3){
			setNewLoc(0, 1500, 568);
			setNewLoc(1, 900, 10);
			setNewLoc(2, 1034, 440);
			}
		else if(blah == 4){
			setNewLoc(0, 900, 568);
			setNewLoc(1, 1000, 10);
			setNewLoc(2, 1134, 400);
			}
		else if(blah == 5){
			setNewLoc(0, 900, 868);
			setNewLoc(1, 900, 10);
			setNewLoc(2, 1034, 445);
		}
		System.out.println(blah);
		return obsGroup;
	}
}
