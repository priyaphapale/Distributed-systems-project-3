package project_3_new;

/**
 * Generic timer class to represent 'time simulation' for the Seller classes.
 * Runs a timer in terms of seconds - to represent the arrival time / time taken to sell.
 */
public class Clock_Simulator
{
	public int secondsElapsed = 0; // Amount of seconds that a specific object has ran for.
	public double startTime;

	/**
	 * Instantiates a timer in terms of milliseconds.
	 */
	public Clock_Simulator()
	{
		startTime = System.currentTimeMillis(); // Initialize start time.
	}

	/**
	 * Method to get the current elapsed time.
	 * @return int secondsElapsed - the amount of seconds that have passed.
     */
	public int getElapsedTime()
	{
		double elapsed = System.currentTimeMillis();
		int seconds = (int) ((elapsed - startTime) / 1000); // Conversion to seconds.
		secondsElapsed = seconds;
		return secondsElapsed;
	}
}