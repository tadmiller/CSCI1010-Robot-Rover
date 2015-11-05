import lejos.nxt.*;

public class TestTurn {
	public static void main(String[] args) {
		RobotRover r = new RobotRover();
		while (true) {
			r.turnLeft();
			r.sleep(1.0);
		}
	}
}