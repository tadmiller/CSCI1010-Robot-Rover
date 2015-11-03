/*******************************************************************
* @authors Theodore Miller, Danny Nsouli, Nathan Walker, Sam Hanna *
********************************************************************
*
* This program instantiates a RobotRover object which contains all
* of the methods to drive the robot. It will call the method 
* avoidObstaclesUltrasonic()
* and use the ultrasonic sensor to avoid hitting an object.
*
*/

import lejos.nxt.*;

public class USAvoid
{
	public static void main(String[] args)
	{
		System.out.println("Running US Obstacle Avoider!");

		RobotRover r = new RobotRover();

		r.avoidObstaclesUltrasonic();

		Button.waitForAnyPress();
	}
}