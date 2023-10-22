/*
  Author: Jessa Yang
  Date: 9/19/23
  Notes: 
 */

import processing.core.PApplet;

public class Bow
{	
	private int scaleW, scaleH, middleX, middleY, bodyH, bodyW, headH, headW;
	private double xRatio, yRatio;
	
	public Bow()
	{
		scaleW = 0;
		scaleH = 0;
		
		// of the standard stickman
		bodyH = scaleH/10;
		bodyW = scaleW/18;
		
		headH = 11*bodyH/12;
		headW = 5*bodyW/4;
		
		xRatio = 2;
		yRatio = 2;
				
		middleX = scaleW/6;
		middleY = scaleH-(scaleH/4);
	}
	
	public Bow(double xR, double yR)
	{
		scaleW = 0;
		scaleH = 0;
		
		bodyH = scaleH/10;
		bodyW = scaleW/18;
		
		headH = 11*bodyH/12;
		headW = 5*bodyW/4;
		
		xRatio = xR;
		yRatio = yR;
				
		middleX = scaleW/6;
		middleY = scaleH-(scaleH/4);
	}
	
	public void draw(PApplet sf)
	{
		scaleW = sf.width;
		scaleH = sf.height;
		
		updateLocation();
		updateDimensions();
		
		sf.noFill();
		
		// bow part
		sf.arc(middleX + 3*bodyW/8, middleY, headW, headH, -sf.HALF_PI, sf.HALF_PI);

		// string
		sf.line(middleX + 3*bodyW/8, middleY - bodyH/2, middleX + 3*bodyW/8, middleY + bodyH/2);
		

	}
	
	// updateDimensions() and updateLocation() different methods to follow the style in House
	
	public void updateDimensions()
	{
		bodyH = scaleH/10;
		bodyW = scaleW/18;
		
		headH = 11*bodyH/12;
		headW = 5*bodyW/4;
	}
	
	public void updateLocation() 
	{
		middleX = (int)(scaleW/xRatio);
		middleY = (int)(scaleH-(scaleH/yRatio));
	}

	public void move(int distance)
	{
		xRatio = (double)scaleW/(middleX + distance);
		middleX = (int)(scaleW/xRatio);
	}

}