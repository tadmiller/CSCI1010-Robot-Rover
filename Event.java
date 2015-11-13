/** A class to contain all of the possible events that could occur while running the maze
 **/

public class Event
    {
    
    /** EVENT TYPES **/
    public static final int INTERSECTION = 0;
    public static final int TURN = 1;
    public static final int MOVEMENT = 2;
    
    /** INTERSECTION STATUSES **/
    public static final int WALL_DETECTED = 6;
    public static final int FAILED = 7;
    public static final int UNCHECKED = 8;
    public static final int CHECK_IN_PROGRESS = 9;
    
    /** TURN DIRECTIONS **/
    public static final int LEFT_TURN = 3;
    public static final int RIGHT_TURN = 4;
    
    // General event type variable
    // Denotes whether an event is a turn, intersection, or movement
    public int eventType;
    
    // Intersection data
    // Whether or not a wall is known to be a failed direction or not
    public int leftStatus;
    public int rightStatus;
    public int forwardStatus;
    
    // Turn-specific data
    public int turnType;
    
    // Movement-specific data
    public double estimatedDistance;


    // Constructor
    public Event()
    {
    }

    // Basic methods for testing event type
    public boolean isIntersection()
    {
        return eventType == INTERSECTION;
    }

    public boolean isTurn()
    {
        return eventType == TURN;
    }

    public boolean isMovement()
    {
        return eventType == MOVEMENT;
    }
    
    // A method that returns a new intersection with given statuses in each direction
    public static Event newIntersection(int left, int front, int right)
    {
        Event e = new Event();
        e.eventType = INTERSECTION;
        
        e.leftStatus = left;
        e.forwardStatus = front;
        e.rightStatus = right;
        
        return e;
    }
    
    // New intersection with only left side
    // Normally the left side is all the data we start with
    public static Event newIntersection(int left) 
    {
        return Event.newIntersection(left, UNCHECKED, UNCHECKED);
    }
    
    // Make a turn event in a given direction
    public static Event newTurn(int direction) 
    {
        Event e = new Event();
        e.eventType = TURN;
        
        e.turnType = direction;
        
        return e;
    }
    
    // Make a movement event with a given distance
    public static Event newMovement(int distance)
    {
        Event e = new Event();
        e.eventType = MOVEMENT;
        
        e.estimatedDistance = distance;
        
        return e;
    }
}