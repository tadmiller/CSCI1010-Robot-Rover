public class Event{
    
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
    public int eventType;
    
    // Intersection data
    public int leftStatus;
    public int rightStatus;
    public int forwardStatus;
    
    // Turn data
    public int turnType;
    
    // Movement data
    public double estimatedDistance;

    public Event() {
    }
    
    public static Event newIntersection(int left, int front, int right) {
        Event e = new Event();
        e.eventType = INTERSECTION;
        
        e.leftStatus = left;
        e.forwardStatus = front;
        e.rightStatus = right;
        
        return e;
    }
    
    // New intersection with only left side
    public static Event newIntersection(int left) {
        return Event.newIntersection(left, UNCHECKED, UNCHECKED);
    }
    
    public static Event newTurn(int direction) {
        Event e = new Event();
        e.eventType = TURN;
        
        e.turnType = direction;
        
        return e;
    }
    
    public static Event newMovement(int distance) {
        Event e = new Event();
        e.eventType = MOVEMENT;
        
        e.estimatedDistance = distance;
        
        return e;
    }
}