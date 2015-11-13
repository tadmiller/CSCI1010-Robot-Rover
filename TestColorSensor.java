/*******************************************************************
* @authors Theodore Miller, Danny Nsouli, Nathan Walker, Sam Hanna *
********************************************************************
*
* This program tests the color sensor methods.
*
*/

import lejos.nxt.*;

public class TestColorSensor
{
	
	
	public static void main(String[] args)
	{
		System.out.println("Following the line!");

		RobotRover r = new RobotRover();
		int color = -1;
		int tempColor = -2;
		
		while (true)
		{
			tempColor = r.getColor();
			if (tempColor != color)
			{
				color = tempColor;
				
				if (color == r.BLACK)
					System.out.println("Color is black.");
				else if (color == r.BLUE)
					System.out.println("Color is blue.");
				else if (color == r.GREY)
					System.out.println("Color is grey.");
				else if (color == r.BROWN)
					System.out.println("Color is brown.");
			
			}
			r.sleep(1);
		}
	}
}