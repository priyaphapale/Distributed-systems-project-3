package project_3_new;

/**
 * Class which holds the layout of the theatre through a String array.
 * Each index represents a seat in the theatre layout.
 */
public class Seats_Handler 
{
    String[][] seats;  

    /**
     * Constructor for the Seats.
     * Creates a 2-D Array of Strings to represent seats.
     */
    public Seats_Handler() 
    {
        seats = new String[10][10];
        for (int row = 0; row < seats.length; row++) 
        {
            for (int col = 0; col < seats.length; col++) 
            {
                seats[row][col] = "---";
            }
        }      
    }

    /**
     * @param row - the current row.
     * @param col - the current column.
     * @return boolean - if the seat is sold or not
     */
    public boolean isSold(int row, int col)
    {
        if ("---".compareTo(seats[row][col]) == 0) 
        {
            return false;
        }
        return true;
    }

    /**
     * Method to convert the current 'seat' as a String for printing purposes.
     * @return the seat object as a String.
     */
    public String toString()
    {
    	int counter=0;
        for (int i = 0; i < seats.length; i++) 
        {
            for (int x = 0; x < seats.length; x++) 
            {
            	if(counter == 9)
            	{
            		System.out.print(seats[i][x] + "\t");
            		System.out.println();
            		counter=0;
            	}
            	else
            	{
            		System.out.print(seats[i][x] + "\t");
            		counter++;
            	}                
            }
            System.out.println();
        }
        return "";
    }

    /**
     * Checks to see if there is an available seat.
     * @return whether or not there is any seats available.
     */
    public boolean soldAllSeats() 
    {
        boolean soldAllSeats = true;
        for (int i = 0; i < seats.length; i++)
        {
            for (int x = 0; x < seats.length; x++) 
            {
                if (!isSold(i, x))
                {
                    soldAllSeats = false;
                }
            }
        }
        return soldAllSeats;
    }

    /**
     * Generic tester for testing the 2D String of seats.
     */
    public static void main(String[] args) 
    {
        Seats_Handler s = new Seats_Handler();
        for (int i = 0; i < 10; i++)
        {
            for (int x = 0; x < 10; x++)
            {
                s.seats[i][x] = "l";
            }
        }
        System.out.println(s.soldAllSeats());
    }
}