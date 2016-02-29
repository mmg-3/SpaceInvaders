package src;

import processing.core.PApplet;
import processing.core.PImage;


public class Alien
{
	// all aliens need a position in space
	protected float x;
	protected float y;
	
	// am I dead?
	protected boolean dead = false;
	
	// all aliens need a speed value
	protected float speed;
		
	// alien size
	protected int size = 20;

	// all aliens need a reference to the PApplet canvas so they
	// can draw to the screen
	protected static PApplet canvas;

	// standard artwork files for all aliens
	protected static PImage blueGraphic;
	protected static PImage redGraphic;
	
	// alien constructor
	public Alien(float x, float y, float speed,PApplet canvas)
	{
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.canvas = canvas;
	}
	
	// check to see if an alien has been hit by a missile
	public int checkHit(float mx, float my)
	{
		// see if the coordinate we are testing is close to us (we can use the distance formula)
		// note that the PApplet class has a nifty distance formula built in!
		float testDistance = this.canvas.dist(this.x,  this.y,  mx,  my);
		
		// if we are close to the missle, mark ourselves as dead
		if (testDistance < size)
		{
			this.dead = true;
			return 1;
		}
		else 
			return 0;
	}
	
	// display the alien
	public void display()
	{
		// only display if we're not dead!
		if (this.dead == false)
		{
			this.canvas.fill(255,0,0);
			this.canvas.ellipse(this.x, this.y, size, size);
		}
	}
	
	// move the alien
	public void move()
	{
		this.x += this.speed;
		
		// did we hit the right edge?
		if (this.x > this.canvas.width)
		{
			// push us back onto the edge
			this.x = this.canvas.width;
			
			// flip our speed
			this.speed *= -1;
			
			// move us down by one row
			this.y += 25;
		}
		
		// did we hit the left edge?
		if (this.x < 0)
		{
			// push us back onto the edge
			this.x = 0;
			
			// flip our speed
			this.speed *= -1;
			
			// move us down by one row
			this.y += 25;
		}
	}
	
	// static Alien setup method
	// we use this method to load in our graphics
	// we only want to do this once since it would be silly to duplicate these graphics for
	// all instances of the Alien class (why waste the memory?)
	public static void setupArtwork(PApplet c)
	{
		// store the canvas
		Alien.canvas = c;
		
		// load the blue graphic
		Alien.blueGraphic = canvas.loadImage("alien1.png");
		
		// laod the red graphic
		Alien.redGraphic = canvas.loadImage("alien2.png");
	}

}
