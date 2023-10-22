/*
  Author: Jessa Yang
  Date: 9/7/23
  Notes: 
 */

import processing.core.PApplet;

public class Line 
{
	// Fields are only for variables needed in MULTIPLE methods, they are
	// the properties of the class. Better to make it local than a field
	
	private double x1, x2, y1, y2; // x and y-coords of the ends of the line
	private boolean isDot; // is this line a dot? or in other words is (x1, y1) the same point as (x2, y2)
	
	public Line(double x1, double y1, double x2, double y2) // constructs a line from (x1, y1) to (x2, y2)
	{
		this.x1 =x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		if(x1==x2 && y1==y2)
		{
			isDot = true;
		}
		else
		{
			isDot=false;
		}
	}
	
	public void setPoint2(double newX2, double newY2) // sets this line's second point (x2, y2) to a new coordinate
	{
		x2 = newX2;
		y2 = newY2;
		
		if(x1==x2 && y1==y2)
		{
			isDot = true;
		}
		else
		{
			isDot=false;
		}
	}
	
	public void draw(PApplet drawer) // draws this line using the PApplet passed as an argument
	{
		drawer.line((float)x1, (float)y1, (float)x2, (float)y2);
	}
	
	/** STOP AND TEST **/
	
	// Returns the x coordinate of the intersection point 
	// of this line and the OTHER line (assuming they continue forever) except in special cases
	
	public double getIntersectionX(Line other)
	{
		double iX=0, num, denom;
		double x3 = other.getValue("x1"), x4 = other.getValue("x2");
		double y3 = other.getValue("y1"), y4 = other.getValue("y2");
		
		double slope1 = findSlope(x1, x2, y1, y2); 
		double slope2 = findSlope(x3, x4, y3, y4); 
		
		if ((slope1)<=slope2 && slope2 <=(slope1+0.0001))
		{
			iX= x1;
		} else if (isDot)
		{
			iX= x1;
		} else if (other.getIsDot())
		{
			iX = x3;	
		} else
		{
			num = (x1*y2-y1*x2)*(x3-x4)-(x1-x2)*(x3*y4-y3*x4);
			denom=(x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);
			iX = num/denom;
		}
		
		return iX;
	}
	
	// Returns the y coordinate of the intersection point 
	// of this line and the OTHER line (assuming they continue forever) except in special cases
	
	public double getIntersectionY(Line other)		
	{
		double iY=0, num, denom;
		double x3 = other.getValue("x1"), x4 = other.getValue("x2");
		double y3 = other.getValue("y1"), y4 = other.getValue("y2");

		double slope1 = findSlope(x1, x2, y1, y2); 
		double slope2 = findSlope(x3, x4, y3, y4); 
		
		//System.out.println("slope1:" + slope1);
		//System.out.println("slope2:" + slope2);

		if ((slope1)<=slope2 && slope2 <=(slope1+0.0001))
		{
			iY= y1;
		} else if (isDot)
		{
			iY = y1;
		} else if (other.getIsDot())
		{
			iY = y3;
		} else
		{
			num = (x1*y2-y1*x2)*(y3-y4)-(y1-y2)*(x3*y4-y3*x4);
			denom=(x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);
			iY = num/denom;
		}
		
		return iY;
	}
	
	
	/** STOP AND TEST **/
	
	// Returns true if this line segment and the segment OTHER intersect each other.
	// Returns false if they do not intersect
	public boolean intersects(Line other)
	{
		//System.out.println("(" + getIntersectionX(other) + "," + getIntersectionY(other) + ")");
		boolean intersects = false; // default value is false
		double x3 = other.getValue("x1"), x4 = other.getValue("x2"); // x-coords of Line other
		double y3 = other.getValue("y1"), y4 = other.getValue("y2"); // y-coords of Line other
		double iX = this.getIntersectionX(other), iY = this.getIntersectionY(other);
		
		if (iX<=isGreatest(x1, x2) && iX>=isLeast(x1, x2)) // if the intersection x-coord within the domain of the x-coords of THIS Line
		{
			if(iY<=isGreatest(y1, y2) && iY>=isLeast(y1, y2)) // if the intersection y-coord within the range of the y-coords of THIS Line
			{
				//System.out.println("iX: " + iX + " <= "+ isGreatest(x3, x4) + " >= " + isLeast(x3, x4));
				
				if (iX<=isGreatest(x3, x4) && iX>=isLeast(x3, x4)) // if the intersection x-coord within the domain of the x-coords of Line other
				{
					if (iY<=isGreatest(y3, y4) && iY>=isLeast(y3, y4)) // if the intersection y-coord within the range of the y-coords of Line other
					{
						//System.out.println("here");
						if (findSlope(x3, x4, y3, y4)==findSlope(x1, x2, y1, y2)) // if the lines are parallel/colinear
						{
							// slopes of a point on THIS Line to either end of the other Line
							double slope1 = findSlope(x1, x3, y1, y3);
							double slope2 = findSlope(x1, x4, y1, y4);

							if (slope1==slope2 || (slope1+"").equals(slope2+"") || slope1==0 || slope2==0) // if the slopes are equal or are both NaN
							{
								return true; 
							}
						} else if (other.getIsDot()) // if the Line other is a dot
						{
							System.out.println("tada");

							// slopes of a point on THIS Line to either end of the other Line
							double slope1 = findSlope(x1, x3, y1, y3);
							double slope2 = findSlope(x1, x4, y1, y4);

							if (slope1==slope2 || (slope1+"").equals(slope2+"")) 
							{
								System.out.println("slope1: " + slope1 + " slope2: " + slope2);

								return true; 
							}
							
						} else if (isDot) // if THIS Line is a dot
						{
//							System.out.println("isDot, iX: " + iX + " iY: " + iY);
//							System.out.println("x3: " + x3 + " y3: " + iY);

							double slope1 = findSlope(x3, x1, y3, y1); // slopes of a point on Line other to either end of the THIS Line
							double slope2 = findSlope(x3, x2, y3, y2);
							//System.out.println("slope1: " + slope1 + " slope2: " + slope2 + " NaN = NaN? " + ((slope1+"").equals(slope2+"")));
							if (slope1==slope2 || (slope1+"").equals(slope2+""))
							{
								return true; 
							}
							
						} 
						return true;
					} 
						
				}
			}
		}
		
		return intersects;

	}
	
	public double getValue(String name) // gets the value of the variable with the same name as the String name given
	{
		if (name.equals("x1"))
		{
			return x1;
		} else if (name.equals("x2"))
		{
			return x2;
		} else if (name.equals("y1"))
		{
			return y1;
		} else if (name.equals("y2"))
		{
			return y2;
		} else
		{
			return 0;
		}
	}
	
	public boolean getIsDot() // gets the value of the boolean isDot
	{
		return isDot;
	}
	
	private double findSlope(double x1, double x2, double y1, double y2) // finds the slope with the x and y-coords of two points
	{
		double slope = (x1-x2)/(y1-y2);
		return slope;
	}
	
	private double isGreatest(double x1, double x2) // returns the greatest of the two values
	{
		if (x1>x2)
		{
			return x1 + 0.000001; // to combat round-off error when used in the intersects() method :)
		}
		else if (x1<x2)
		{
			return x2 + 0.000001;
		}
		else
		{
			return x1 + 0.000001;
		}
	}
	
	private double isLeast(double x1, double x2) // returns the least of the two values
	{
		if (x1>x2)
		{
			return x2 - 0.000001; // to combat round-off error when used in the intersects() method :)
		}
		else if (x1<x2)
		{
			return x1  - 0.000001;
		}
		else
		{
			return x1  - 0.000001;
		}
	}
}