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
		
		MovementResult result = robot.followLineUntilStopped(-1);
		
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
	

	// If at an intersection, determine which paths have not
	// been checked and test them.
	// If at an intersection and all options have been tried,
	// call reverseToLast()
	// If none of the above, throw exception
	
	// Check for three paths; left, right, or forwards
	// If one is open that hasn't been checked yet, send the robot in that direction
	public void decideNextAction()
	{
		// Decide what to do next
		// If stack is empty, call moveForwardUntilStopped()
		
		if (stack.isEmpty())
			moveForwardUntilStopped();

		Event e = (Event)stack.peek();

		if (e.isIntersection())
		{
			if (e.leftStatus == Event.UNCHECKED)
			{
				robot.turnLeft();
				e.leftStatus = Event.CHECK_IN_PROGRESS;
			}
			else if (e.rightStatus == Event.UNCHECKED)
			{
				robot.turnRight();
				e.rightStatus = Event.CHECK_IN_PROGRESS;
			}
			else if (e.forwardStatus == Event.UNCHECKED)
			{
				moveForwardUntilStopped();
				e.forwardStatus = Event.CHECK_IN_PROGRESS;
			}
			else
				reverseToLast();
		}
		else
			reverseToLast();
	}
	
	public void reverseToLast()
	{
		// Reverse the stack so that you arrive at the last intersection
		
		// Use movement data to estimate where it is going to be
		
		// Don't forget to turn around at the beginning and end
		
		// Remove the old event(s)
		
		// Then, call decideNextAction()
      
      while(true)
      {
         Event event = (Event) stack.pop();
         if(event.eventType == Event.MOVEMENT)
         {
            reverseMovement(event);
         }
         else if(event.eventType == Event.TURN)
         {
            reverseTurn(event, true);
         }
         else
         {
            robot.turnAround();
            decideNextAction();
            break;
         }
      }
	}
	
	// Reverse the stack to return to the start
	public void reverseAll()
	{
		while (!stack.isEmpty())
			reverseToLast();
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
	
	// Use the estimate from the event to get a general distance
	protected void reverseMovement(Event moveEvent)
	{
		robot.followLineUntilStopped(moveEvent.estimatedDistance);
	}
}