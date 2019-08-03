package project_3_new;

import java.util.ArrayList;
import java.util.Scanner;

public class Process_Handler implements Runnable {

	static ArrayList<Seller_Handler> sell = new ArrayList<Seller_Handler>();

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Enter number of customers:");
													
		Scanner scan = new Scanner(System.in); 
		int numCustomers = scan.nextInt();
		scan.close();
		Seats_Handler seat = new Seats_Handler();
		Clock_Simulator t = new Clock_Simulator();

		HSeller_Handler H1 = new HSeller_Handler(numCustomers, seat, "H1", t);

		MSeller_Handler M1 = new MSeller_Handler(numCustomers, seat, "M1", t);
		MSeller_Handler M2 = new MSeller_Handler(numCustomers, seat, "M2", t);
		MSeller_Handler M3 = new MSeller_Handler(numCustomers, seat, "M3", t);

		LSeller_Handler L1 = new LSeller_Handler(numCustomers, seat, "L1", t);
		LSeller_Handler L2 = new LSeller_Handler(numCustomers, seat, "L2", t);
		LSeller_Handler L3 = new LSeller_Handler(numCustomers, seat, "L3", t);
		LSeller_Handler L4 = new LSeller_Handler(numCustomers, seat, "L4", t);
		LSeller_Handler L5 = new LSeller_Handler(numCustomers, seat, "L5", t);
		LSeller_Handler L6 = new LSeller_Handler(numCustomers, seat, "L6", t);

		Thread t1 = new Thread(H1);
		Thread t2 = new Thread(M1);
		Thread t3 = new Thread(M2);
		Thread t4 = new Thread(M3);
		Thread t5 = new Thread(L1);
		Thread t6 = new Thread(L2);
		Thread t7 = new Thread(L3);
		Thread t8 = new Thread(L4);
		Thread t9 = new Thread(L5);
		Thread t10 = new Thread(L6);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		sell.add(H1);
		sell.add(M1);
		sell.add(M2);
		sell.add(M3);
		sell.add(L1);
		sell.add(L2);
		sell.add(L3);
		sell.add(L4);
		sell.add(L5);
		sell.add(L6);
		Process_Handler tester = new Process_Handler();
		tester.run();
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(70000);
			int sumTurnedAway = 0;

			// HSeller
			System.out.println("H1 sold: " + sell.get(0).num_Tickets_Sold);
			System.out.println("H1 turned away: " + sell.get(0).listOf_Customers.size());

			// MSeller
			for (int i = 1; i < 4; i++) {
				System.out.println("M" + i + " sold: " + sell.get(i).num_Tickets_Sold);
				System.out.println("M" + i + " turned away: " + sell.get(i).listOf_Customers.size());
			}

			// LSeller
			int sellerCount = 1;
			for (int i = 4; i < 10; i++) {
				System.out.println("L" + sellerCount + " sold: " + sell.get(i).num_Tickets_Sold);
				System.out.println("L" + sellerCount + " turned away: " + sell.get(i).listOf_Customers.size());
				sellerCount++;
			}

			for (int i = 0; i < sell.size(); i++) {
				// System.out.println(sell.get(i).name+"\t"+sell.get(i).listOf_Customers.size());
				sumTurnedAway = sumTurnedAway + sell.get(i).listOf_Customers.size();
				// System.out.println("Turned away:" +sumTurnedAway);
			}

			int sumNumSold = 0;
			for (int i = 0; i < sell.size(); i++) {
				sumNumSold += sell.get(i).num_Tickets_Sold;
				// sumTurnedAway =+ sell.get(i).turnedAway;
			}
			System.out.println("Total number of tickets sold: " + sumNumSold);
			System.out.println("Number of customer turned away: " + sumTurnedAway);
			/*
			 * for(int i = 0; i<sell.size(); i++){
			 * System.out.println(sell.get(i).name + "\t " +
			 * sell.get(i).listOfCustomers.size()); }
			 */

			System.out.println("Final seating chart:");
			sell.get(0).map.toString();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
