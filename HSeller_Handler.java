package project_3_new;

import project_3_new.Customer_Handler;
import project_3_new.Seats_Handler;
import project_3_new.Seller_Handler;
import project_3_new.Clock_Simulator;

/**
 * H Seller is a type of Seller. It sells exclusively in the first row and has
 * the shortest serving time.
 */
public class HSeller_Handler extends Seller_Handler 
{

	/**
	 * Constructor for a HSeller.
	 * 
	 * @param numberOfCustomers
	 *            - the maximum number of customers to serve.
	 * @param seats
	 *            - the seats that the H seller can serve.
	 * @param sellerName
	 *            - the name of the current H Seller.
	 * @param timer
	 *            - the timer to track the H seller.
	 */

	public HSeller_Handler(int numberOfCustomers, Seats_Handler seats, String sellerName, Clock_Simulator timer) 
	{
		super(numberOfCustomers, seats, sellerName, timer);
		maxTime_ToServe = 2; // The maximum amount of time a HSeller can take.
		minTime_ToServe = 1; // The minimum amount of time a LSeller can take.
		row = 0; // The only row that a HSeller can sell in.
	}

	/**
	 * Method to sell a ticket to the given customer.
	 * 
	 * @param minServeTime
	 *            - the minimum time to serve a customer.
	 * @param maxServeTime
	 *            - the maximum time to serve a customer.
	 * @param customer
	 *            - the customer to sell a ticket to.
	 */

	public void sellTicket(int minServeTime, int maxServeTime, Customer_Handler customer) 
	{
		Clock_Simulator timer = new Clock_Simulator();
		selling_Time = (int) Math.ceil((Math.random() * 2));
		// System.out.println(listOf_Customers.get(0).customer_Name + " arrives at
		// " + this.name + "'s queue");
		System.out.println("Expected completion time of "+customer.customer_Name+"= "+ selling_Time + " mins");
		
		while (timer.getElapsedTime() < selling_Time) 
		{
		} // do nothing until it is time to sell
		if (!listOf_Customers.isEmpty()) 
		{
			checkSeatMap(customer);
		}
	}

	/**
	 * Checks the seating map with synchronization between sellers.
	 * 
	 * @param customer
	 *            - the customer to place into the seating map.
	 */
	public synchronized void checkSeatMap(Customer_Handler customer) 
	{
		boolean emptySeat = true;
		boolean turned = true;
		for (int row = 0; row < 10 && emptySeat; row++) 
		{
			for (int col = 0; col < 10 && emptySeat; col++) 
			{
				if (!map.isSold(row, col)) 
				{
					num_Tickets_Sold++;
					Customer_Handler c = listOf_Customers.remove(0);
					// listOf_Customers.remove(0);
					emptySeat = false;
					map.seats[row][col] = customer.customer_Name;
					try 
					{
						Thread.sleep(100);
					} 
					catch (InterruptedException e) 
					{
						Thread.currentThread().interrupt();
					}
					System.out.println();
					//System.out.println(c.customer_Name + " bought a ticket and leaves.");
					int currentTime = t.getElapsedTime();
					String time = "";
					if (currentTime < 10) {
						time = "00:0" + currentTime;
					} else {
						time = "00:" + currentTime;
					}
					System.out.printf("Completion time of "+c.customer_Name+ "= " +time);
					System.out.println();
					/*Turnaround time= Completion time - arrival time
					 *Waiting time=  Turnaroundtime- Completion time
					 **/
					
					map.toString();
					System.out.println();
				} 
				else if (map.soldAllSeats() && turned) 
				{
					//System.out.println(customer.customerName + " turned away ");
					turned = false;
				}
			}
		}
	}
}
