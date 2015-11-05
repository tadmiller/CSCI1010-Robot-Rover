public class MovementResult
{	
	public boolean endpoint;
	public double duration;
	public boolean wall;

	public MovementResult(boolean endpoint, boolean wall, double duration)
	{
		endpoint = endpoint;
		duration = duration;
		wall = wall;
	}
}