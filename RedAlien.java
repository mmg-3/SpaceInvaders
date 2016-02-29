package src;

import processing.core.PApplet;


public class RedAlien extends Alien
{	
	// red aliens are Aliens!
	// they override the display method for the Alien superclass
	// but other than that they are the same
	
	public RedAlien(float x, float y, float speed, PApplet canvas)
	{
		// call the superconstructor
		super(x,y,speed, canvas);
	}
	
	// override the display method
	@Override
	public void display()
	{
		// make sure we aren't dead!
		if (!this.dead)
		{
			// instead of drawing an ellipse we can use the blue graphic image instead
			canvas.image(redGraphic,  this.x,  this.y);
		}
	}

}
