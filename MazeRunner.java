import java.util.Stack;
import lejos.nxt.*;
// http://www.lejos.org/nxt/nxj/api/java/util/Stack.html

public class MazeRunner
{
	
	Stack stack;
	RobotRover robot;
	
	public MazeRunner()
	{
		stack = new Stack();
		robot = new RobotRover();
	}
	
	public static void main(String[] args)
	{
		MazeRunner m = new MazeRunner();
		m.decideNextAction();
	}
	
	public void moveForwardUntilStopped()
	{
		// Create a movement event, add to stack
		// Keep a log of distance moved
		
		// End scenarios:
		// If wall, call reverseToLast()
		// If intersection, call processNewIntersection()
		// If finish point, call reverseAll()
      
		Event moveEvent = Event.newMovement(0);
		
		MovementResult result = robot.followLineUntilStopped();
		
		moveEvent.estimatedDistance = result.duration;
		
		stack.push(moveEvent);
		
		if (result.wall)
		{
			reverseToLast();
		}
		else if (result.endpoint)
		{
			reverseAll();
		}
		else {
			processNewIntersection();
		}
	}
	
	public void processNewIntersection()
	{
		// Read ultrasonic sensor to get whether or not
		// there is a wall on the left side
		
		// Call decideNextAction()
      
		UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S3);
		int distance=sonic.getDistance();
		Event ultraEvent;

		if(distance > 8 && distance <13)
			ultraEvent = Event.newIntersection(Event.WALL_DETECTED);
		else
			ultraEvent = Event.newIntersection(Event.UNCHECKED);

		stack.push(ultraEvent);  
		decideNextAction();
	}
	
	public void decideNextAction()
	{
		// Decide what to do next
		// If stack is empty, call moveForwardUntilStopped()
		
		if (stack.isEmpty())
			moveForwardUntilStopped();

		Event e = (Event)stack.peek();

		if (e.isIntersection())
		{

		}
		else if (e.isTurn())
		{

		}
		else if (e.isMovement())
		{

		}
		else
			reverseToLast();

		// If at an intersection, determine which paths have not
		// been checked and test them.
	
		// If at an intersection and all options have been tried,
		// call reverseToLast()
		
		// If none of the above, throw exception
	}
	
	public void reverseToLast()
	{
		// Reverse the stack so that you arrive at the last intersection
		
		// Use movement data to estimate where it is going to be
		
		// Don't forget to turn around at the beginning and end
		
		// Remove the old event(s)
		
		// Then, call decideNextAction()
	}
	
	public void reverseAll()
	{
		// Reverse the stack to return to the start
	}
	
	public void makeTurn(int direction)
	{
		// Make a turn in the given direction
		
		// Use direction types defined in Event and
		// RobotRover functions
		
		// Add a turn event to the stack
      
		Event newEvent;
		newEvent = Event.newTurn(direction);
		stack.push(newEvent);

		if(direction==Event.LEFT_TURN)
		{
			robot.turnLeft();
		}
      	else
      	{
        	robot.turnRight();
        }
	}
	
	protected void reverseTurn(Event turnEvent, boolean andTurnAround)
	{
		// If standard, just turn in the same direction as the turn was
		// If need to turn around, turn in the opposite direction
      
      if(andTurnAround){
         if(turnEvent.turnType == Event.LEFT_TURN){
            robot.turnRight();
         }
         else{
            robot.turnLeft();
         }
      }
      else{
         if(turnEvent.turnType == Event.LEFT_TURN){
            robot.turnLeft();
         }
         else{
            robot.turnRight();
         }

      }
	}
	
	protected void reverseMovement(Event moveEvent)
	{
		// Use the estimate from the event to get a general distance
	}
	
	
	
}