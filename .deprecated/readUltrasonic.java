import lejos.nxt.*;

public class readUltrasonic{


	public static void main(String [] args){
		
		UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S3);
	      while (Button.readButtons() != Button.ID_ESCAPE)
			{
	      		LCD.clear();
	      		LCD.drawInt(sonic.getDistance(), 0, 3);
		 		 //Button.waitForPress();
		  		try {
					Thread.sleep(250);
		  		} catch (Exception e) {
			  		
		  		}
			}
		
			System.out.println("Done");
	}
}