/*
  Author: Jessa Yang
  Date: 9/19/23
  Notes: This class is mostly functional, however there is a bug/imperfect aspect where the arrow might 
  be posed weirdly on a part of that house if it is already touching that part of the house when the arrow is shot.
  Methods and variables of Arrow are similar to Person.
 */

import processing.core.PApplet;

public class Arrow // Arrow class! represents an arrow that can be drawn on a Surface
{	
	private int scaleW, scaleH, middleX, middleY, bodyH, bodyW, headH, headW;
	private double xRatio, yRatio;
	private boolean flying, hit, visible, collision, end;
	
	public Arrow()
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
		
		flying = false;
		hit = false;
		visible = true;
		collision = false;
		end = false;
	}
	
	public Arrow(double xR, double yR)
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
		
		flying = false;
		hit = false;
		visible = true;
		collision = false;
		end = false;


	}
	
	public Arrow(double xR, double yR, boolean show)
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
		
		flying = false;
		hit = false;
		visible = show;
		collision = false;
		end = false;


	}
	
	public Arrow(boolean show)
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
		
		flying = false;
		hit = false;
		visible = show;
		collision = false;
		end = false;


	}
	
	public void draw(PApplet sf)
	{
		scaleW = sf.width;
		scaleH = sf.height;
		
		updateLocation();
		updateDimensions();
		
		if (flying)
		{
				move(10);
			//System.out.println("fly");
		}
		
		if (middleX>=sf.width-10*bodyW/8 || collision)
		{
			hit = true;
			end = (middleX>=sf.width-10*bodyW/8);
			flying=false;
		}
		
		if (!visible)
		{
			sf.noFill();
			sf.noStroke();
		}
		else
		{
			sf.fill(0);
			sf.stroke(0);
		}
		// arrow head
		sf.triangle(middleX + 3*bodyW/8 + headW/2, middleY+middleY/80, middleX + 3*bodyW/8 + headW/2, middleY-middleY/80, middleX + 4*bodyW/8 + headW/2, middleY);
		//sf.stroke(200);
		sf.line(middleX+(2*bodyW/8), middleY, middleX + bodyW + (1*bodyW/8), middleY); // (1*bodyW/8) is the little bit of the arrow that's drawn as the arm

		//System.out.println();
		//sf.stroke(200);
		//sf.line(middleX+(3*bodyW/8)+headW/2, middleY, middleX + bodyW + (1*bodyW/8), middleY);
		sf.line(middleX+(2*bodyW/8), middleY, middleX + bodyW + (1*bodyW/8), middleY);
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
	
	public void move(double middleX, double middleY)
	{
		middleX = (int)(middleX);
		middleY = (int)(middleY);
		
		xRatio = (double)scaleW/(middleX);
		yRatio = (double)scaleH/(scaleH-middleY);
	}
	
	public void release()
	{
		//System.out.println("release");
		flying = true;
	}
	
	public void reset(double middleX, double middleY)
	{
		move(middleX, middleY);
		hit = false;
		collision = false;
		end = false;
	}
	
	public double getDouble(String name) // gets double fields
	{
		if (name.equals("xRatio"))
		{
			return xRatio;
		}
		else if (name.equals("yRatio"))
		{
			return yRatio;
		}
		else
		{
			return 0;
		}
	}
	
	public boolean getBoolean(String name) // gets boolean fields
	{
		if (name.equals("hit"))
		{
			return hit;
		}
		else if (name.equals("flying"))
		{
			return flying;
		}
		else if (name.equals("visible"))
		{
			return visible;
		} else if (name.equals("collision"))
		{
			return collision;
		} else
		{
			return false;
		}
	}
	
	public void checkCollision(House h, boolean moved) // needs a House to check the lines, is it called because the House moved?
	{	
		if (moved)
		{
			// Line line = new Line(middleX+(3*bodyW/8)+headW/2, middleY, middleX + bodyW + (1*bodyW/8), middleY);
			Line line = new Line(middleX+(2*bodyW/8), middleY, middleX + bodyW + (1*bodyW/8), middleY);
			
			if (!line.intersects(h.getLine("leftWall")) || !line.intersects(h.getLine("rightWall")) || !line.intersects(h.getLine("leftRoof")) || !line.intersects(h.getLine("rightRoof")))
			{
				if (hit && !end)
				{
					move(middleX, 7*scaleH/8 + middleY/40);
				}
			}
		} else
		{
			Line line = new Line(middleX+(2*bodyW/8), middleY, middleX + bodyW + (4*bodyW/8), middleY);
			//line = new Line(middleX+(3*bodyW/8)+headW/2, middleY, middleX + bodyW + (1*bodyW/8), middleY);
			if (line.intersects(h.getLine("leftWall")) || line.intersects(h.getLine("rightWall")) || line.intersects(h.getLine("leftRoof")) || line.intersects(h.getLine("rightRoof")))
			{
				collision = true;
			}
			else
			{
				collision = false;
			}
		}
		
	
	}


}