import lejos.nxt.*;

public class TestColorSensor
{
	public static void main(String[] args)
	{
		System.out.println("Following the line!");

		RobotRover r = new RobotRover();

		while (true)
		{
			if (r.getColor() == 1)
				System.out.println("Color is black.");
			if (r.getColor
		}
	}
}