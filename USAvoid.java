/*******************************************************************
* @authors Theodore Miller, Danny Nsouli, Nathan Walker, Sam Hanna *
********************************************************************
*
* This program instantiates a RobotRover object which contains all
* of the methods to drive the robot. It will call the method avoidObstacles()
* and use the touch sensor to avoid hitting a wall.
*
*/

import lejos.nxt.*;

public class USAvoid
{
	public static void main(String[] args)
	{
		System.out.println("Running Obstacle Avoider!");

		RobotRover r = new RobotRover();

		r.avoidObstaclesUltrasonic();

		Button.waitForAnyPress();
	}
}