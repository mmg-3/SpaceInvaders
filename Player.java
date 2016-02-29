package src;

import processing.core.PApplet;


public class Player
{
	// keep track of x & y position
	public float x;
	public float y;
	public boolean dead =false;
	public float size =20;
	
	// keep track of speed
	private float speed = 3;
	
	// reference to the PApplet canvas (so we can draw directly onto it)
	private PApplet canvas;
	
	// constructor
	public Player(float x, float y, PApplet canvas)
	{
		this.x = x;
		this.y = y;
		this.canvas = canvas;
	}
	
	// move left
	public void moveLeft()
	{
		this.x -= this.speed;
	}
	
	// move right
	public void moveRight()
	{
		this.x += this.speed;
	}
	
	// display ourselves
	public void display()
	{
		this.canvas.fill(255);
		this.canvas.rect(x, y, 50, 10);
	}
	
	// check to see if an alien has been hit by a missile
	public void checkHit(float mx, float my)
	{
		// see if the coordinate we are testing is close to us (we can use the distance formula)
		// note that the PApplet class has a nifty distance formula built in!
		float testDistance = PApplet.dist(this.x,  this.y,  mx,  my);
		
		// if we are close to the missle, mark ourselves as dead
		if (testDistance < size)
		{
			this.dead = true;
			
		}
		
	}

}
