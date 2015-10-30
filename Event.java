public class Event{
   
   public static final int INTERSECTION = 0;
   public static final int TURN = 1;
   public static final int MOVEMENT = 2;
   public static final int WALL = 6;
   public static final int FAILED = 7;
   public static final int UNCHECKED = 8;
   public static final int CHECK_IN_PROGRESS = 9;
   
   public int leftStatus;
   public int rightStatus;
   public int forwardStatus;


   public Event(){
   }
   
   
   //intersections
      //status left right forward
         //wall, failed route, unchecked, check in progress
   //turns
      //direction
   //movements
      //distance
}