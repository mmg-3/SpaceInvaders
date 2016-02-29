package src;

import processing.core.PApplet;


public class AlienMissile
{
	// x & y position
	public float x = 0;
	public float y = 0;
	
	// speed (y direction only)
	private float speed = -5;
	
	// canvas reference
	private PApplet canvas;
	
	// constructor
	public AlienMissile(PApplet canvas)
	{
		this.canvas = canvas;
	}
	
	// move the missile
	public void move()
	{
		this.y -= this.speed;
	}
	
	public void display()
	{
		this.canvas.fill(0,0,255);
		this.canvas.rect(this.x, this.y, 5,25);
	}
}
