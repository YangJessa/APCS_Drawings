/*
  Author: Jessa Yang
  Date: 8/31/23
  Notes: This class represents a House. The position of the House is all based on ratios to the window's dimensions. 
  default window values are set before the draw() method. W at the end of a variable indicates width and H indicates height.
 */

import processing.core.PApplet;

public class House // don't extend PApplet as PApplet is the window, not the house
{
	
	
	
	// FIELDS
	private int scaleW, scaleH, middleX, middleY, width, height, doorW, doorH, windowH, windowW, chimneyW, chimneyH;
	private double xRatio, yRatio, factor; 
		
	// CONSTRUCTORS
	
	public House() // default width and height
	{
		
		factor = 1; 
		// ratios for how to scale the x and y coords
		xRatio = 2;
		yRatio = 2;
		
		// width and height to scale the house to
		scaleW = 800;
		scaleH = 600;
		
		// coordinates of the middle of the house
		middleX = (int)(scaleW * xRatio);
		middleY = (int)(scaleH * yRatio);
						
		// house dimensions
		width = 5*scaleW/8; // formerly scaleW/2
		height = scaleH/2;
				
		// door dimensions
		doorW = width/4;
		doorH = 2*height/5;
				
		// window's  dimensions
		windowH = height/4;
		windowW = 3*width/20;
				
		// chimney dimensions
		chimneyW = width/5;
		chimneyH = 3*height/8;	
			

	}
	
	public House(double xR, double yR) // changed starting position
	{
		
		factor = 1; 

		// ratios for how to scale the x and y coords
		xRatio = xR;
		yRatio = yR;
		
		// width and height to scale the house to
		scaleW = 800;
		scaleH = 600;
		
		// coordinates of the middle of the house
		middleX = (int)(scaleW * xRatio);
		middleY = (int)(scaleH * yRatio);
						
		// house dimensions
		width = 5*scaleW/8; // formerly scaleW/2
		height = scaleH/2;
				
		// door dimensions
		doorW = width/4;
		doorH = 2*height/5;
				
		// window's  dimensions
		windowH = height/4;
		windowW = 3*width/20;
				
		// chimney dimensions
		chimneyW = width/5;
		chimneyH = 3*height/8;	
			

	}
	
	// METHODS
	
	public void draw(PApplet sf) // if you need something, add it as a parameter! sf - surface
	{		
		// width and height of surface
		scaleW = (int)(sf.width);
		scaleH =(int)(sf.height);
		
		updateLocation();
		updateDimensions();
		
		//sf.noStroke(); // no border 
		//sf.fill(0xd0,0xf2,0xa0);

		// house
		sf.rectMode(sf.CENTER);  // set rectMode to CENTER
		//sf.fill(255,201,188);  // set fill to light tan 
		sf.rect(middleX, middleY, width, height);
		
		// chimney
		sf.rect(middleX + 5*width/16, middleY - 15*height/20, chimneyW, chimneyH);
		
		// chimney top
		//sf.fill(0xdc,0xa7,0x81); // set fill to dark tan
		sf.rect(middleX + 5*width/16, middleY - 15*height/20 - chimneyH/2, 6*chimneyW/4, 6*chimneyH/16);

		// roof
		sf.triangle(middleX - width/2, middleY - height/2, 
					middleX + width/2, middleY - height/2, 
					middleX, middleY-height);
		
		// door
		sf.rect(middleX, middleY + height/2-doorH/2, doorW, doorH, doorW/2, doorW/2, 0, 0);
		
		// windows
		sf.rect(middleX + 3*windowW/2, middleY - doorH/3, windowW, windowH);
		sf.rect(middleX - 3*windowW/2, middleY - doorH/3, windowW, windowH);
	}
	
	public void scale(double f) // gets the number to factor the scaling by
	{
		if(!(factor*f >= 7.03998871212466 || factor*f <= 0.10692269082848212)) // limits of how far to shrink the house
		{
			factor*=f; 
		}
	}
	
	// updateDimensions() and updateLocation() different methods to control the order in which things are updated
	// *IMPORTANT* for the scaling, as the scaling just simulates making the window bigger, as it already scales to the window

	public void updateDimensions()
	{	
		// house dimensions
		width = (int)(5*scaleW*factor/8);
		height = (int)(scaleH*factor/2);
				
		// door dimensions
		doorW = width/4;
		doorH = 2*height/5;
				
		// window's dimensions
		windowH = height/4;
		windowW = 3*width/20;
		
		// chimney dimensions
		chimneyW = width/5;
		chimneyH = 3*height/8;
	}
	
	public void updateLocation()
	{
		middleX = (int)(scaleW/xRatio);
		middleY = (int)(scaleH/yRatio);
	}

	public void move(int x, int y)
	{
		xRatio = (double)scaleW/x;
		yRatio = (double)scaleH/y;

		middleX = (int)(scaleW/xRatio);
		middleY = (int)(scaleH/yRatio);
	}
	
	public Line getLine(String name)
	{
		if (name.equals("leftWall"))
		{
			//sf.rect(middleX, middleY, width, height);
			return new Line(middleX-width/2,middleY-height/2,middleX-width/2,middleY+height/2);
			
		} else if (name.equals("rightWall"))
		{
			return new Line(middleX+width/2,middleY-height/2,middleX+width/2,middleY+height/2);
			
		} else if (name.equals("leftRoof"))
		{
			//sf.triangle(middleX - width/2, middleY - height/2, 
			//		middleX + width/2, middleY - height/2, 
			//		middleX, middleY-height);
			
			return new Line(middleX-width/2,middleY-height/2,middleX,middleY-height);

		} else if (name.equals("leftRoof"))
		{
			return new Line(middleX+width/2,middleY-height/2,middleX,middleY-height);
			
		} else
		{
			return new Line(0,0,0,0);
		}
	}
}
	