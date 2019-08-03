package project_3_new;

public class Customer_Handler implements Comparable<Customer_Handler>
{
	public int customerArrivalTime; // Arrival time of a specific customer.
	public String customer_Name; // Name of the customer.
	public boolean turnAway;

	/**
	 * Constructor for a customer.
	 * @param time - the time to initialize for a customer.
	 * @param name - the name to initialize for a customer.
     */
	public Customer_Handler(int time, String name)
	{
		customerArrivalTime = time;	// Setting the customer's arrival time to the given time.
		customer_Name = name; // Setting the customer's name to the given name.
		turnAway = false;
	}

	/**
	 * Comparable method for comparing two customer's arrival times.
	 * @param customer - the customer we are comparing.
	 * @return value - the customer which should be served first.
     */
	public int compareTo(Customer_Handler customer) 
	{
		if (this.customerArrivalTime > customer.customerArrivalTime)
		{
			return 1; // Customer arrived later.
		}
		else if (this.customerArrivalTime < customer.customerArrivalTime)
		{
			return -1; // Customer arrived earlier.
		}
		else return 0; // Customers arrived at the same time.
	}
	
	/**
	 * sets the time the customer was turned away
	 * @param turnAwayTime - the time the customer was turned away
	 */
	public void turnAway()
	{
		this.turnAway = true;
	}
}