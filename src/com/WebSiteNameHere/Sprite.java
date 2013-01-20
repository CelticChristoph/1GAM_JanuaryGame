
package com.WebSiteNameHere;

import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.Texture;

public class Sprite extends Image {
	public float x;
	public float y;

	public Sprite() {
		// TODO Auto-generated constructor stub
	}

	public Sprite(Image other) {
		super(other);
		// TODO Auto-generated constructor stub
	}

	public Sprite(Texture texture) {
		super(texture);
		// TODO Auto-generated constructor stub
	}

	public Sprite(String ref) throws SlickException {
		super(ref);
		// TODO Auto-generated constructor stub
	}

	public Sprite(ImageData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	public Sprite(String ref, Color trans) throws SlickException {
		super(ref, trans);
		// TODO Auto-generated constructor stub
	}

	public Sprite(String ref, int filter) throws SlickException {
		super(ref, filter);
		// TODO Auto-generated constructor stub
	}

	public Sprite(String ref, boolean flipped) throws SlickException {
		super(ref, flipped);
		// TODO Auto-generated constructor stub
	}

	public Sprite(int width, int height) throws SlickException {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	public Sprite(ImageData arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public Sprite(String ref, boolean flipped, int filter)
			throws SlickException {
		super(ref, flipped, filter);
		// TODO Auto-generated constructor stub
	}

	public Sprite(int width, int height, int f) throws SlickException {
		super(width, height, f);
		// TODO Auto-generated constructor stub
	}

	public Sprite(InputStream in, String ref, boolean flipped)
			throws SlickException {
		super(in, ref, flipped);
		// TODO Auto-generated constructor stub
	}

	public Sprite(String arg0, boolean arg1, int arg2, Color arg3)
			throws SlickException {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public Sprite(InputStream in, String ref, boolean flipped, int filter)
			throws SlickException {
		super(in, ref, flipped, filter);
		// TODO Auto-generated constructor stub
	}
}