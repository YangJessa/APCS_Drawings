/*
  Author: Jessa Yang
  Date: 8/29/23
  Notes: This class represents a person. The position of the Person is all based on ratios to the window's dimensions. 
  Default window values are set before the draw() method. Variable naming is pretty much the same as the House class. A Person is drawn with it's bow.
  While a Person can technically HAVE-A(n) arrow, I thought the arrow would be better fit in the drawing surface, as a Person doesn't ALWAYS have one.
 */

import processing.core.PApplet;

public class Person
{
	private int scaleW, scaleH, middleX, middleY, bodyH, bodyW, headH, headW;
	private double xRatio, yRatio;
	private boolean hasArrow;
	String state, direction;
	
	public Person()
	{
		scaleW = 0;
		scaleH = 0;
		
		bodyH = scaleH/10;
		bodyW = scaleW/18;
		
		headH = 11*bodyH/12;
		headW = 5*bodyW/4;
		
		xRatio = 2;
		yRatio = 2;
				
		middleX = scaleW/6;
		middleY = scaleH-(scaleH/4);
		
		hasArrow = true;
		
		state = "shooting";
		direction = "right";
	}
	
	public Person(double xR, double yR)
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
		
		hasArrow = true;
		
		state = "shooting";
		direction = "right";

	}
	
	public void draw(PApplet sf)
	{
		scaleW = sf.width;
		scaleH = sf.height;
		
		updateLocation();
		updateDimensions();
		
		// body
		sf.line(middleX, middleY - bodyH/2, middleX, middleY + bodyH/2);
		
		// legs
		sf.line(middleX, middleY + bodyH/2, middleX + bodyW/2, middleY + 5*bodyH/4);
		sf.line(middleX, middleY + bodyH/2, middleX - bodyW/2, middleY + 5*bodyH/4);
		
		// head
		sf.ellipse(middleX, middleY - bodyH/2-headH/4, headW, headH);
		
		// arms and bow
		sf.noFill();

		if (!hasArrow)
		{
			// bow part
			sf.arc(middleX + 3*bodyW/8, middleY, headW, headH, -sf.HALF_PI, sf.HALF_PI);
			
			// string
			sf.line(middleX + 3*bodyW/8, middleY - bodyH/2, middleX + 3*bodyW/8, middleY + bodyH/2);
				
			/*
			// arrow head
			
			sf.fill(0);
			sf.triangle(middleX + 4*bodyW/8 + headW/2, middleY+middleY/80, middleX + 4*bodyW/8 + headW/2, middleY-middleY/80, middleX + 5*bodyW/8 + headW/2, middleY);
			*/
			
			// arms 
			sf.line(middleX - bodyW, middleY, middleX + bodyW, middleY); // (1*bodyW/8) is the little bit of the arrow that's drawn as the arm
			
		} else
		{
			// bow part
			sf.arc(middleX + 3*bodyW/8, middleY, headW, headH, -sf.HALF_PI, sf.HALF_PI);

			// string
			//sf.triangle((float)(middleX + 3*bodyW/8), (float)(middleY - bodyH/2), (float)(middleX + 3*bodyW/8), (float)(middleY + bodyH/2), (float)(middleX + bodyW/4), (float)(middleY));
			
			sf.line((float)(middleX + 3*bodyW/8), (float)(middleY - bodyH/2), (float)(middleX + bodyW/4), (float)(middleY));
			sf.line((float)(middleX + 3*bodyW/8), (float)(middleY + bodyH/2), (float)(middleX + bodyW/4), (float)(middleY));
			
			
			// arrow head
			/*
			sf.fill(0);
			sf.triangle(middleX + 3*bodyW/8 + headW/2, middleY+middleY/80, middleX + 3*bodyW/8 + headW/2, middleY-middleY/80, middleX + 4*bodyW/8 + headW/2, middleY);
			*/
			
			// arms
			sf.line(middleX, middleY, middleX + bodyW, middleY);
			sf.line(middleX - 3*bodyW/8, middleY + bodyH/3, middleX + bodyW/4, middleY);
			sf.line(middleX - 3*bodyW/8, middleY + bodyH/3, middleX, middleY);

		}
		
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

	public void walk(int distance)
	{
		xRatio = (double)scaleW/(middleX + distance);
		middleX = (int)(scaleW/xRatio);
	}
	
	public void shoot()
	{
		if (hasArrow)
		{
			hasArrow = false;
			state = "resting";
		}
		else
		{
			hasArrow = true;
			state = "shooting";
		}
	}
	
	public double getDouble(String name)
	{
		if (name.equals("middleX"))
		{
			return middleX;
		}
		else if (name.equals("middleY"))
		{
			return middleY;
		}
		else if (name.equals("bodyH"))
		{
			return bodyH;
		}
		else if (name.equals("bodyW"))
		{
			return bodyW;
			
		} else if (name.equals("headH"))
		{
			return headH;
			
		} else if (name.equals("headW"))
		{
			return headW;
		}
		else
		{
			return 0;
		}
	}
	
	public String getState()
	{
		return state;
	}
	
	public void flip()
	{
		if (direction.equals("right"))
		{
			direction = "left";

		} else if (direction.equals("left"))
		{
			direction = "right";
		}
	}
}
