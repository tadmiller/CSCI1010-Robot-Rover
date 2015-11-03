// This program follows a black line while moving clockwise

import lejos.nxt.*;

public class ColorLine
{
	public static void main(String[] args)
	{
		System.out.println("Following the line!");

		RobotRover r = new RobotRover();

		r.followLineColor();

		//r.turnLeft();

		//Button.waitForAnyPress();
	}
}