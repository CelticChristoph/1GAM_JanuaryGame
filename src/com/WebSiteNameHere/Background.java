package com.WebSiteNameHere;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background extends Entity{

	private float xSpeed;
	private Image currentImage;
	private Image nextImage;
	private ArrayList<String> imageLocations;
	private Random random = new Random();


	public Background(float x, float y, float speed, ArrayList<String> imageLocations) throws SlickException {

		this.x = x;
		this.y = y;

		xSpeed = speed;


		this.imageLocations = new ArrayList<String>();
		for(String location : imageLocations)
		{
			System.out.print(location);
			this.imageLocations.add(location);
		}

		random.toString();
		currentImage = new Image(imageLocations.get(random.nextInt(imageLocations.size())));
		nextImage = new Image(imageLocations.get(random.nextInt(imageLocations.size())));
	}


	public float getSpeed()
	{
		return xSpeed;
	}

	@Override
	public void render() {

		currentImage.draw(x, y);
		nextImage.draw(x + 1024, y);
	}

	@Override
	public void update() {

		x -= xSpeed;

		if(x <= -1024)
		{
			try
			{
				currentImage = nextImage;
				nextImage = new Image(imageLocations.get(random.nextInt(imageLocations.size())));
				x = 0f;
			} catch(SlickException e)
			{
				e.printStackTrace();
			}
		}
	}
}
