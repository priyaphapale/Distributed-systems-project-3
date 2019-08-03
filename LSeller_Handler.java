package project_3_new;

import project_3_new.Customer_Handler;
import project_3_new.Seats_Handler;
import project_3_new.Seller_Handler;

import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import project_3_new.Clock_Simulator;

/**
 * L Seller is a type of Seller. It sells within a specific row pattern and
 * takes the longest time to serve.
 */
public class LSeller_Handler extends Seller_Handler 
{

	/**
	 * 
	 * Constructor for the M Seller.
	 * 
	 * @param numberOfCustomers
	 *            - the maximum number of customers to serve.
	 * @param seats
	 *            - the seats that the L seller can serve.
	 * @param sellerName
	 *            - the name of the current L Seller.
	 * @param timer
	 *            - the timer to track the L seller.
	 */
	public LSeller_Handler(int numberOfCustomers, Seats_Handler seats, String sellerName, Clock_Simulator timer) 
	{
		super(numberOfCustomers, seats, sellerName, timer);
		maxTime_ToServe = 7;
		minTime_ToServe = 4;
		row = 9;
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
		selling_Time = (int) (Math.random() * 4 + 4);
		System.out.println("Expected completion time of "+customer.customer_Name+"= "+ selling_Time + " mins");
		while (timer.getElapsedTime() < selling_Time) 
		{
		} // Do nothing until it is time to sell.
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
		boolean turned = true;
		boolean x = true;
		for (int row = 9; row > 0 && x; row--) 
		{
			for (int col = 0; col < 10 && x; col++) 
			{
				if (!map.isSold(row, col)) 
				{
					x = false;
					Customer_Handler c = listOf_Customers.remove(0);
					// listOf_Customers.remove(0);
					num_Tickets_Sold++;
					map.seats[row][col] = customer.customer_Name;
					try
					{
						Thread.sleep(10);
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
					map.toString();
					System.out.println();
				} 
				else if (map.soldAllSeats() && turned) 
				{
					//System.out.println(customer.customer_Name + " turned away");
					turned = false;
				}
			}
		}
	}
}