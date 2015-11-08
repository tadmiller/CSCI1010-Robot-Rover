/*******************************************************************
* @authors Theodore Miller, Danny Nsouli, Nathan Walker, Sam Hanna *
********************************************************************
*
* This program contains all of the methods to drive the NXT robot.
*
*/

import lejos.nxt.*;

public class RobotRover
{
	private final int speed = 300;
	private final double LINE_FORWARD_DURATION = 0.1;

	// Empty constructor.
	public RobotRover()
	{
	}

	/**
	 * Has the robot pause/wait for a given amount of time
	 * 
	 * @param The amount of time for the robot to wait, ie 0.1 = 1/10 of a second
	 */
	public void sleep(double time)
	{
		try
		{
			int t = (int)(time * 1000);
			Thread.sleep(t);
		}

		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * Gets the side in which we are off/need to adjust to in order to stay straight
	 * 
	 * @return 1 = turn right, -1 = turn left, 0 if no adjustment needed/error
	 */
	public int getOffSide()
	{
		UltrasonicSensor usLeft = new UltrasonicSensor(SensorPort.S3); // left
		UltrasonicSensor usRight = new UltrasonicSensor(SensorPort.S4); // right
		
//		System.out.println("Left is: " + usLeft.getDistance());
//		System.out.println("Right is: " + usRight.getDistance());
		
		if ((usLeft.getDistance() <= 10) && (usRight.getDistance() > 10))
			return 1; // we should adjust to turn right
		else if ((usLeft.getDistance() > 10) && (usRight.getDistance() <= 10))
			return -1; // we should adjust to turn left
		
		return 0; // error/no adjustment needed
	}
	
	// Follow a line with color sensor until a color is hit
	// Returns a result object (see below)
	public MovementResult followLineUntilStopped(double maxDistance)
	{
		ColorSensor colorSensor = new ColorSensor(SensorPort.S1);
		TouchSensor touchSensor = new TouchSensor(SensorPort.S2);
				
		Motor.B.setSpeed(200);
		Motor.C.setSpeed(200);
		
		double distanceTravelled = 0.0;
		boolean isEndpoint = false;
		boolean isWall = false;
				
		while (true)
		{
			if (touchSensor.isPressed())
			{
				System.out.println("Oops, hit a wall");
				isWall = true;
				break;
			}		
			
			ColorSensor.Color color = colorSensor.getColor();
			
			double hue = getColorSensorH();
			
			if (hue > 180 && hue < 210)
			{
				System.out.println("Blue detected");
				break;
			}
			else if (false)
			{
				// Should be changed to look for endpoint color
				isEndpoint = true;
				break;
			}
			
			findLine();

			distanceTravelled += LINE_FORWARD_DURATION;
			moveForward(LINE_FORWARD_DURATION);
		}
		System.out.println("Travelled: " + distanceTravelled);
		System.out.println("Wall:" + isWall);
		
		return new MovementResult(isEndpoint, isWall, distanceTravelled);
	}
	
	public void findLine()
	{
		boolean side = true; // left = true right = false
		double hue = getColorSensorH();
		int i = 1;
		
		Motor.B.setSpeed(400);
		Motor.C.setSpeed(400);
	
		System.out.println("Looking for the line..");
		
		while (hue > 80 || hue < 60)
		{
			if (side)
			{
				Motor.C.forward();
				Motor.B.backward();
			}
			else
			{
				Motor.C.backward();
				Motor.B.forward();
			}
			
			try
			{
				for (int j = 0; j < i; j++)
				{
					if (hue < 80 && hue > 60)
						break;
					
					Thread.sleep(j);
				}
			}

			catch(InterruptedException ex)
			{
				Thread.currentThread().interrupt();
			}
			
			i *= 2; // go twice the distance because we need to account for the distance we went in the opposite direction
			side = !side; // change the side we scan on
		}
		
		stop(); // stop
	}

	// black is 16 <=
	// white is > 290, < 310
	// wood is > 18, < 24
	public double getColorSensorH()
	{
		return getColorSensorHSV()[0];
	}

	public double[] getColorSensorHSV()
	{
		ColorSensor colorSensor = new ColorSensor(SensorPort.S1);

		ColorSensor.Color colors = colorSensor.getColor();
		

		double[] hsv = new double[3];
		// read colors
		int r = colors.getRed();
		int b = colors.getBlue();
		int g = colors.getGreen();
		
		double min = Math.min(r, Math.min(b,g));
		double max = Math.max(r, Math.max(b, g));
		double delta = max - min;
		hsv[2] = max/255; //set v to max as a percentage
		if (max != 0){
			hsv[1] = delta/max;
		}
		else{ //r = b = g =0 
			hsv[1] = 0; //s = 0;		// s = 0, v is undefined
			hsv[0] = -1; //h = -1;
			return hsv;
		}
		
		if (r == max){
			hsv[0] = (g-b)/delta; //h 
		}
		else{
			if (g == max)
				hsv[0] = 2 + (b - r)/delta; //h
			else
				hsv[0] = 4 + (r - g)/delta; //h
		}
		
		hsv[0] *=60;	//degrees
		if (hsv[0] < 0)
			hsv[0] +=360;
		
		return hsv;
	}

	// Move forward for a specified amount of time (or infinite).
	// Pass -1 as an argument for forward infinitely
	public void moveForward(double time)
	{
		if (time == -1)
			System.out.println("Going forward infinitely!");
		else
			//System.out.println("Going forward for " + time + " seconds!");

		Motor.B.setSpeed(speed);
		Motor.C.setSpeed(speed);

		if (time == -1)
		{
			Motor.B.forward();
			Motor.C.forward();
		}
		else
		{
			Motor.B.forward();
			Motor.C.forward();

			sleep(time);

			stop();
		}
	}

	// Drive backwards for a specified amount of time (or infinite).
	// Pass -1 as an argument for forward infinitely
	public void moveBackward(double time)
	{
		if (time == -1)
			System.out.println("Going backward infinitely!");
		else
			System.out.println("Going backward for " + time + " seconds!");


		Motor.B.setSpeed(speed);
		Motor.C.setSpeed(speed);

		if (time == -1)
		{
			Motor.B.backward();
			Motor.C.backward();
		}
		else
		{
			Motor.B.backward();
			Motor.C.backward();

			sleep(time);

			stop();
		}
	}

	// Slow down the motors then stop them.
	// This is because the motors will not stop synchronously.
	public void stop()
	{
		for (int i = Motor.B.getSpeed(); i > 0; i--)
		{
			Motor.B.setSpeed(i);
			Motor.C.setSpeed(i);
		}

		Motor.B.stop(true);
		Motor.C.stop(true);
	}

	// Turn 90 degrees left.
	public void turnLeft()
	{
		System.out.println("Turning left!");

		Motor.B.setSpeed(speed);
		Motor.C.setSpeed(speed);

		Motor.B.rotate(180, true);
		Motor.C.rotate(-180, true);

   		while(Motor.B.isMoving() && Motor.C.isMoving())
			Thread.yield();

		sleep(0.1);
	}

	// Turn 90 degrees right.
	public void turnRight()
	{
		System.out.println("Turning right!");

		Motor.B.setSpeed(speed);
		Motor.C.setSpeed(speed);

		Motor.B.rotate(-180, true);
		Motor.C.rotate(180, true);

   		while(Motor.B.isMoving() && Motor.C.isMoving())
			Thread.yield();

		sleep(0.1);
	}
	
	public void turnAround() {
		turnLeft();
		turnLeft();
	}
}