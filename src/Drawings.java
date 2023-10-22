/*
  Author: Jessa Yang
  Date: 8/29/23
  Notes: 
 */

import processing.core.PApplet;

public class Drawings {

	public static void main(String args[]) {
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		drawing.windowResizable(true);
	}
	

	
}
