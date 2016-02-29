package src;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class SpaceInvaders extends PApplet
{
	// all aliens need a reference to the PApplet canvas so they
	// can draw to the screen
	protected static PApplet canvas;

	// standard artwork files for home screen
	protected static PImage graphic;
	
	//artwork for win screen
	 protected static PImage winsplash;
	
		//artwork for lose screen
	 protected static PImage losesplash;
	 
	 
	protected static int state =1;
	
	
	protected static int score=0;
	
	protected int row =0;
	
	protected int numaliens= 20;
	
	// Player object
	public Player thePlayer;
	
	// Aliens
	public Alien[] theAliens;
	
	// Missile
	public Missile theMissile;
	
	//Alien missile
	public AlienMissile[] AlienMissile;
	
	// font so we can write text to the screen
	public PFont theFont;
	
	public void setup()
	{
		// size our canvas
		size(800,500);
		
		// create our player
		this.thePlayer = new Player(width/2,height-10,this);
		
		// setup the Alien class with its static setup method
		Alien.setupArtwork(this);
		
		SpaceInvaders.splash(this);
		
		SpaceInvaders.winnersplash(this);
		
		SpaceInvaders.losersplash(this);
		
		// create some aliens
		this.theAliens = new Alien[20];
		this.AlienMissile= new AlienMissile[20];
		
		for (int i = 0; i < this.theAliens.length; i++)
		{
			this.AlienMissile[i] = new AlienMissile(this);

			
			if (row%5==0)
			{
				row=0;
			}
				
			if (i<5)
			{
				if (Math.random() > 0.5)
				{
					this.theAliens[i] = new BlueAlien(row * 100, 25, 3, this);
				}
				else
				{
					this.theAliens[i] = new RedAlien(row* 100, 25, 3, this);
				}
			}
		else if (i<10)
			{
				
				if (Math.random() > 0.5)
				{
					this.theAliens[i] = new BlueAlien(row * 100, 75, 3, this);
				}
				else
				{
					this.theAliens[i] = new RedAlien(row * 100, 75, 3, this);
				}
			}	
				
		else if (i<15)
		{
			if (Math.random() > 0.5)
			{
				this.theAliens[i] = new BlueAlien(row * 100, 125, 3, this);
			}
			else
			{
				this.theAliens[i] = new RedAlien(row * 100, 125, 3, this);
			}
		}	
				
			else if (i<20)
			{
				if (Math.random() > 0.5)
				{
					this.theAliens[i] = new BlueAlien(row * 100, 175, 3, this);
				}
				else
				{
					this.theAliens[i] = new RedAlien(row * 100, 175, 3, this);
				}
			}
			row++;
		}
		
		
		// create our missile
		this.theMissile = new Missile(this);
		
		// load in our font
		// (note that you can export a new font by opening up Processing and clicking on Tools -> Create Font)
		this.theFont = loadFont("sansSerifFont24.vlw");
		
		// set the font we just loaded as our default font
		this.textFont( this.theFont );
		
		// start up our sound manager class
		SoundManager.startup(this);
	}
	
	public void draw()
	{
		if (state==4)
		{
			SpaceInvaders.losesplash = canvas.loadImage("Black Background.jpg");
			SpaceInvaders.canvas.image(losesplash, 0, 0);
			
			// write some text to the screen
			this.text("Space Invaders!",  25,  25);
			
			//write the score on the screen
			this.text("Score:"+score,  675,  25);
			
			this.text("You Were Our Last Hope \n Now Earth Is DOOMED",  300,  250);
			
		}
		
		if (state==3)
		{
			SpaceInvaders.winsplash = canvas.loadImage("Black Background.jpg");
			SpaceInvaders.canvas.image(winsplash, 0, 0);
			
			// write some text to the screen
			this.text("Space Invaders!",  25,  25);
			
			//write the score on the screen
			this.text("Score:"+score,  675,  25);
			
			this.text("You Stopped The Invasion \n And Saved Planet Earth!",  300,  250);


		}
		
		if (state==1)
		{
			// load the blue graphic
			SpaceInvaders.graphic = canvas.loadImage("spaceInvadersHome.jpg");
			SpaceInvaders.canvas.image(graphic,0,0);
			
			if (this.keyPressed)
			{
				state=2;
			}
		}
		 if (state==2)
		{
			// erase the background
			background(0);
			
			// move the player, if necessary
			if (this.keyPressed)
			{
				println(this.key);
				
				if (this.key == 'a')
				{
					this.thePlayer.moveLeft();
				}
				if (this.key == 'd')
				{
					this.thePlayer.moveRight();
				}
			}
			
			// move and display all the aliens
			for (int i = 0; i < this.theAliens.length; i++)
			{
				if (this.theAliens[i].dead==false)
				{
					score+=this.theAliens[i].checkHit(this.theMissile.x, this.theMissile.y);
					System.out.println(score);
					this.theAliens[i].move();
					this.theAliens[i].display();
				
				
					System.out.println("aliens: " +numaliens);
					//check if its dead
					if (this.theAliens[i].dead==true)
					{
						System.out.println("aliens: " +numaliens);
						numaliens-=1;
					}
					
					
					//shoot the missile at random times from random aliens
					if (Math.random()<.0005)
					{
						// reorient the missile to the aline's location
						this.AlienMissile[i].x = this.theAliens[i].x;
						this.AlienMissile[i].y = this.theAliens[i].y;
						
						// move and display the alien missile
						this.AlienMissile[i].move();
						this.AlienMissile[i].display();
						
						this.thePlayer.checkHit(this.AlienMissile[i].x, this.AlienMissile[i].y);						
					}
				}
				
				if (numaliens==0)
				{
					state = 3;
				}
				
			}
			
			if (thePlayer.dead)
			{
				state=4;
			}
			
			// move and display the missile
			this.theMissile.move();
			this.theMissile.display();
			
			for (int i=0;i<AlienMissile.length;i++)
			{
				if (this.theAliens[i].dead==false)
				{
					this.AlienMissile[i].move();
					this.AlienMissile[i].display();
					this.thePlayer.checkHit(this.AlienMissile[i].x, this.AlienMissile[i].y);						
				}
			}
			
			// draw the player
			this.thePlayer.display();
			
			// write some text to the screen
			this.text("Space Invaders!",  25,  25);
			
			//write the score on the screen
			this.text("Score:"+score,  675,  25);
			


		}
	}
	

	// this method only runs once when a key is pressed
	// this lets us play our sounds so that they don't "clobber" each
	// other by trying to play themselves over and over again
	public void keyPressed()
	{
		if (this.key == 's')
		{
			// play our fire sound
			SoundManager.playFireSound();
			
			// reorient the missile to the player's location
			this.theMissile.x = this.thePlayer.x+25;
			this.theMissile.y = this.thePlayer.y+25;
		}
		
	}
	
	public static void splash(PApplet c)
	{
		SpaceInvaders.canvas = c;
		
		SpaceInvaders.graphic = canvas.loadImage("spaceInvadersHome.jpg");

	}
	
	public static void winnersplash(PApplet c)
	{
		SpaceInvaders.canvas = c;
		
		SpaceInvaders.graphic = canvas.loadImage("spaceInvadersHome.jpg");

	}

	public static void losersplash(PApplet c)
	{
		SpaceInvaders.canvas = c;
		
		SpaceInvaders.graphic = canvas.loadImage("spaceInvadersHome.jpg");

	}
	
}
