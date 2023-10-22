/*
  Author: Jessa Yang
  Date: 8/29/23
  Notes: The window for drawing. 
 */

import processing.core.PApplet;
import processing.event.MouseEvent;


public class DrawingSurface extends PApplet 
{
	
	// "super." to look at methods
	private House house; // the window has a house
	private Person person; // the window has a person
	private Background background; // the window has a background
	private Bow bow;
	private Arrow arrow, clone;

	public DrawingSurface() 
	{
		house = new House(1.6, 1.6); // coordinates would be window width/1.6 and window height/1.6
		person = new Person(6, 6); // coordinates would be window width/6 and window height - window height/6
		arrow = new Arrow(6, 6);
		background = new Background();
		//clone = new Arrow(false);
		//arrow = new Arrow(person);
	}
	
	
	public void settings() 
	{
		setSize(800,600); // size of the window
	}
	
	
	public void setup() 
	{
		//noLoop(); // only redraws when called to redraw
	}
	
	
	public void draw() 
	{
		//background(0xe8,0xfc,0xf9);
		//background(255);
		
		push();
		background.draw(this);
		pop();
		
		stroke(0);
		strokeWeight(3);
		rectMode(CORNER);
		rect(0,7*height/8, width, 1*height/8); // ground

		push();
		house.draw(this);
		pop();
		
		push();
		person.draw(this);
		pop();		
		
		push();
		arrow.draw(this);
		if (arrow.getBoolean("flying"))
		{
			arrow.checkCollision(house, false);
		}
		
		//System.out.println("col: " + arrow.getBoolean("collision") + " hit: " +  arrow.getBoolean("hit"));
		stroke(200);
		//clone.draw(this);
		
		pop();
		
		
		// Beginning of instructions
		
		push();
		fill(0);
		textSize(20);
		text("Instructions: \n"
				+ "up/down arrow keys - scales house \n"
				+ "right/left arrow keys - moves the person \n"
				+ "m/n keys - changes the sky to morning or night", width/50, height/30);
		pop();
		
	}
	
	public void mousePressed()
	{
		house.move(mouseX, mouseY);	
	    arrow.checkCollision(house, true);

	}
	
	public void keyPressed()
	{
		if (key == CODED) // for the arrow keys
		{
		    if (keyCode == UP) 
		    {
			    house.scale(1.05);
			    arrow.checkCollision(house, true);
		  
		    } else if (keyCode == DOWN) 
		    {
		    	house.scale(0.95);
			    arrow.checkCollision(house, true);

		    } else if (keyCode==RIGHT)
		    {
		    		person.walk(20);
		    		if(!arrow.getBoolean("hit")&&!arrow.getBoolean("flying"))
		    		{
			    		arrow.move(20);
		    		}
		    		//person.flip(); feature not done yet
		    		
		    } else if (keyCode==LEFT)
		    {
		    		person.walk(-20);
		    		if(!arrow.getBoolean("hit")&&!arrow.getBoolean("flying"))
		    		{
			    		arrow.move(-20);
		    		}
		    		//person.flip();
		    }
		}
		
		// for other keys
		
		if (key=='n') // night, darker
		{
			background.changeBackground("n");
		}
		else if (key=='m') // morning, lighter
		{
			background.changeBackground("m");
		}
		else if (key == ' ')
		{
			if (person.getState().equals("shooting") && !arrow.getBoolean("flying"))
			{
				arrow.release();
				person.shoot();
				//System.out.println("shot");

				//arrow.move(25);
			} else if (!arrow.getBoolean("flying"))
			{
				arrow.reset(person.getDouble("middleX"), person.getDouble("middleY"));
				//System.out.println("reset");
				person.shoot();
			}
			
		}
	}
}
