import lejos.nxt.*;

public class GetHSV {
	public static void main(String[] args)
	{
		while (true)
		{
			RobotRover robot = new RobotRover();
			double[] hsv = robot.getColorSensorHSV();
			
			System.out.println("Hue: " + hsv[0]);
			System.out.println("Sat: " + hsv[1]);
			System.out.println("Val: " + hsv[2]);
			
			robot.sleep(1);
			LCD.clearDisplay();
		}
		
	}
}