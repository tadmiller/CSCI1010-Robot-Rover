import java.util.Stack;
// http://www.lejos.org/nxt/nxj/api/java/util/Stack.html

public class MazeRunner {
	
	Stack stack;
	
	public MazeRunner() {
		stack = new Stack();
	}
	
	public static void main(String[] args) {
		
	}
	
	public void moveForwardUntilStopped() {
		// Create a movement event, add to stack
		// Keep a log of distance moved
		
		// End scenarios:
		// If wall, call reverseToLast()
		// If intersection, call processNewIntersection()
		// If finish point, call reverseAll()
	}
	
	public void processNewIntersection() {
		// Read ultrasonic sensor to get whether or not
		// there is a wall on the left side
		
		// Call decideNextAction()
      
      UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S3);
      int distance=sonic.getDistance();
      Event ultraEvent;
      if(distance > 8 && distance <13){
         ultraEvent = Event.newIntersection(Event.WALL_DETECTED);
      }
      else{
         ultraEvent = Event.newIntersection(Event.UNCHECKED);
      } 
      Stack.push(ultraEvent);  
      decideNextAction();
	}
	
	public void decideNextAction() {
		// Decide what to do next
		
		// If stack is empty, call moveForwardUntilStopped()
		
		// If at an intersection, determine which paths have not
		// been checked and test them.
		
		// If at an intersection and all options have been tried,
		// call reverseToLast()
		
		// If none of the above, throw exception
	}
	
	public void reverseToLast() {
		// Reverse the stack so that you arrive at the last intersection
		
		// Use movement data to estimate where it is going to be
		
		// Don't forget to turn around at the beginning and end
		
		// Remove the old event(s)
		
		// Then, call decideNextAction()
	}
	
	public void reverseAll() {
		// Reverse the stack to return to the start
	}
	
	public void makeTurn(int direction) {
		// Make a turn in the given direction
		
		// Use direction types defined in Event and
		// RobotRover functions
		
		// Add a turn event to the stack
	}
	
}