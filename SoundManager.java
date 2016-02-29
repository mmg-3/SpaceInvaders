package src;

import processing.core.PApplet;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class SoundManager
{
	// audio framework object
	private static Minim minim;
	
	// audio players - we need one for each sound we plan on playing
	private static AudioPlayer fireSound;
	
	// reference to our canvas
	private static PApplet canvas;
	
	// startup method
	public static void startup(PApplet c)
	{
		// store a reference to our canvas;
		canvas = c;
		
		// create a new audio framework
		minim = new Minim(canvas);
		
		// load in the sound effect for shooting a missile and store
		// it in an AudioPlayer object
		fireSound = minim.loadFile("missile.mp3", 2048);
	}
	
	// play sound method
	public static void playFireSound()
	{
		fireSound.pause();
		fireSound.rewind();
		fireSound.play();
	}
	
}
