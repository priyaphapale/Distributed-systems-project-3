package project_3_new;

import java.util.*;

public class Seller_Handler implements Runnable {
	public ArrayList<Customer_Handler> listOf_Customers;
	public Clock_Simulator t;
	public int num_Tickets_Sold;
	public int total_Customers;
	public int selling_Time;
	public int maxTime_ToServe;
	public int minTime_ToServe;
	public Seats_Handler map;
	public int row;
	public int col;
	public String name;
	public int turnedAway;

	/**
	 * Constructor for the Seller class.
	 * 
	 * @param customer_Arrival_Compare
	 *            - the number of customer in the seller's queue.
	 * @param mapOfSeats
	 *            - the 2D array of seats the sellers will use to sell seats to
	 *            the customer.
	 * @param SellerName
	 *            - the name of the seller.
	 * @param timer
	 *            - the time limit for the seller to know when to stop selling.
	 */
	public Seller_Handler(int num_Customers, Seats_Handler mapOfSeats, String SellerName, Clock_Simulator timer) {
		turnedAway = 0;
		name = SellerName;
		t = timer;
		row = 0;
		col = 0;
		map = mapOfSeats;
		total_Customers = num_Customers;
		num_Tickets_Sold = 0;
		listOf_Customers = new ArrayList<Customer_Handler>();
		maxTime_ToServe = 0;
		minTime_ToServe = 0;
		for (int i = 0; i < num_Customers; i++) {
			listOf_Customers.add(new Customer_Handler((int) (Math.random() * 59), SellerName + i));
		}
		Collections.sort(listOf_Customers, new customer_Arrival_Compare());
		for (int i = 0; i < listOf_Customers.size(); i++) {
			listOf_Customers.get(i).customer_Name = SellerName + "-" + i;
		}
	}

	//Compares the arrival time between customers.
	static class customer_Arrival_Compare implements Comparator<Customer_Handler> {
		public int compare(Customer_Handler c0, Customer_Handler c1) {
			return c0.compareTo(c1);
		}
	}
	
	//Allows the seller to start selling within the time limit.
	public void run() {
		boolean duplicate = false;
		
	
		boolean run = true;
		Customer_Handler hold = null;
		Customer_Handler hold2 = new Customer_Handler(999, "Temp");
		while (t.getElapsedTime() < 60) {
			int currentTime = t.getElapsedTime();
			if (!listOf_Customers.isEmpty() && !map.soldAllSeats()) {
				Customer_Handler current = listOf_Customers.get(0);
				hold = current;
				if (shouldSell(currentTime, current)) {
					// System.out.println(name + " is currently working on sale"
					// + " at time " + currentTime);
					String time = "";
					if (currentTime < 10) {
						time = "00:0" + currentTime;
					} else {
						time = "00:" + currentTime;
					}
					System.out.println("Arrival Time: " + time);
					//System.out.println("Seller: " + name);
					
					sellTicket(maxTime_ToServe, minTime_ToServe, current); // work for a time
				}
			}
			else if(map.soldAllSeats())
			{	
					if(hold2.customer_Name.equals(hold.customer_Name))
					{
						continue;
					}
					else
					{
						System.out.println("00:" + t.getElapsedTime() +"      "  +name + " " +hold.customer_Name +" was turned away");
						hold.turnAway();
					}
					
					hold2 = hold;
			}

		}		
			for(int i = 0 ; i < listOf_Customers.size();i++) 
			{
				if (!listOf_Customers.get(i).turnAway) 
				{
					System.out.println("00:" + t.getElapsedTime() +"      "  +name + " " +listOf_Customers.get(i).customer_Name +" was turned away");
					listOf_Customers.get(i).turnAway();
				}
			}
	}

	/**
	 * Gets the number of customers that will be turned away.
	 * 
	 * @return - the number of customers turned away
	 */
	public int getNumTurned() 
	{
		turnedAway = total_Customers - num_Tickets_Sold;
		return turnedAway;
	}

	/**
	 * Checks to see is a seat should be sold to the customer based on their
	 * arrival time.
	 * 
	 * @param currentMinute
	 *            - the current time the customer arrives.
	 * @param currentCustomer
	 *            - the name of the customer.
	 * @return - true or false which will determine if the ticket should be sold
	 *         to the customer.
	 */
	public boolean shouldSell(int currentMinute, Customer_Handler currentCustomer) 
	{
		if (currentMinute >= currentCustomer.customerArrivalTime)
		{
			if (currentMinute == currentCustomer.customerArrivalTime) 
			{
				System.out.println(currentCustomer.customer_Name + " arrives.");
			}
			return true;
		}
		return false;
	}

	/**
	 * The action of selling the ticket to the customer
	 * 
	 * @param minimumTime
	 *            - the minimum selling time the seller can take to complete a
	 *            transaction.
	 * @param maximumTime
	 *            - the maximum amount of time the seller can take to complete a
	 *            transaction.
	 * @param customer
	 *            - the customer that the seller is currently serving.
	 */
	public void sellTicket(int minimumTime, int maximumTime, Customer_Handler customer) 
	{
		int range = maximumTime - minimumTime + 1;
		selling_Time = minimumTime + (int) ((Math.random() * range));

		int timeToSell = selling_Time + t.secondsElapsed; // creates a random sale time depending on seller type
															// sale time
															// depending on
															// seller type
		System.out.println(t.secondsElapsed);
		System.out.println(name + " is selling in " + timeToSell);
		Integer tTS = new Integer(timeToSell);
		Integer elapsed = new Integer(t.secondsElapsed);
		System.out.println(elapsed.compareTo(tTS) == 0);

	}

	public void checkSeatMap(Customer_Handler c) {
		// remove first customer on list
		boolean x = true;
		for (int ro = 0; x; ro++) {
			for (int co = 0; x; co++) {
				if (map.isSold(ro, co)) {
					x = false;
					map.seats[ro][co] = c.customer_Name;
				}
			}
		}
	}

	public String toString() {
		return name;
	}
	
	// prints customers in list
	public void printMyCustomers() {
		Iterator i = listOf_Customers.iterator();
		while (i.hasNext()) {
			Customer_Handler current = (Customer_Handler) i.next();
			System.out.println(current.customerArrivalTime);
		}
	}
}
