/*
  Author: Jessa Yang
  Date: 8/29/23
  Notes: This is my experimentation, it controls the background color. 
 */

import processing.core.PApplet;

public class Background
{
	private float h, s, b; // HSB values for background color
	
	public Background()
	{
		h = 50;
		s = 8;
		b = 100; 
	}
	
	public void draw(PApplet sf)
	{
		sf.colorMode(sf.HSB, 100, 100, 100);
		sf.background(h, s, b);
	}
	
	public void changeBackground(String key)
	{
		// for the hue
		if (key.equals("n") && !(h+5>72))
		{
			h+=5;
		}
		else
		{
			if ((key.equals("m")) && !(h-5<50))
			{
				h-=5;
			}
		}
		
		// for the saturation
		if (key.equals("n") && !(s+8>60))
		{
			s+=5;
		}
		else
		{
			if ((key.equals("m")) && !(s-8<8))
			{
				s-=5;
			}
		}
	
		// for the brightness
		if (key.equals("n") && b!=30)
		{
			b-=5;
		}
		else
		{
			if ((key.equals("m")) && b!=100)
			{
				b+=5;
			}
		}
	}

}
