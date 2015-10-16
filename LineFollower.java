import lejos.nxt.*;

public class LineFollower
{
	public static void main(String[] args)
	{
		System.out.println("Following the line!");

		RobotRover r = new RobotRover();

		r.followLine();

		//r.turnLeft();

		//Button.waitForAnyPress();
	}
}