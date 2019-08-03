package project_3_new;

import project_3_new.Customer_Handler;
import project_3_new.Seats_Handler;
import project_3_new.Seller_Handler;
import project_3_new.Clock_Simulator;

/**
 * M Seller is a type of Seller. It sells within a specific set of rows and
 * takes a medium amount of time.
 */
public class MSeller_Handler extends Seller_Handler 
{
	/**
	 * Constructor for the M Seller.
	 * 
	 * @param numberOfCustomers
	 *            - the maximum number of customers to serve.
	 * @param seats
	 *            - the seats that the M seller can serve.
	 * @param sellerName
	 *            - the name of the current M Seller.
	 * @param timer
	 *            - the timer to track the M seller.
	 */
	public MSeller_Handler(int numberOfCustomers, Seats_Handler seats, String sellerName, Clock_Simulator timer) 
	{
		super(numberOfCustomers, seats, sellerName, timer);
		maxTime_ToServe = 4;
		minTime_ToServe = 2;
		row = 5;
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
		selling_Time = (int) (Math.random() * 3 + 2);
		// System.out.println(listOf_Customers.get(0).customer_Name + " arrives at
		// " + this.name + "'s queue");
		System.out.println("Expected completion time of "+customer.customer_Name+"= "+ selling_Time + " mins");
		while (timer.getElapsedTime() < selling_Time) 
		{
		} // Do nothing until it's time to sell.
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
		int counter = 0;
		boolean turned = true;
		boolean x = true;
		for (int ro = 5; (ro > 0 && ro < 10) && x; counter++)
		{
			for (int co = 0; co < 10 && x; co++)
			{
				if (!map.isSold(ro, co)) 
				{
					x = false;
					Customer_Handler c = listOf_Customers.remove(0);
					num_Tickets_Sold++;
					map.seats[ro][co] = customer.customer_Name;
					try 
					{
						Thread.sleep(10);
					} 
					catch (InterruptedException e)
					{
						Thread.currentThread().interrupt();
					}
					System.out.println();
					
					int currentTime = t.getElapsedTime();
					String time = "";
					if (currentTime < 10) {
						time = "00:0" + currentTime;
					} else {
						time = "00:" + currentTime;
					}
					System.out.printf("Completion time of "+c.customer_Name+ "= " +time);
					System.out.println();
					map.toString();
					System.out.println();
				} 
				else if (map.soldAllSeats() && turned)
				{
					//System.out.println(customer.customer_Name +  " turned away");
					turned = false;
				}
			}
			if (counter % 2 == 0) 
			{
				ro = ro - counter;
			} 
			else 
			{
				ro = ro + counter;
			}
		}
	}
}