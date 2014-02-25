package com.ryanddawkins.datastructures.program1;

/**
 * Average object to deal with business logic of creating averages
 *
 * @author Ryan Dawkins
 * @package com.ryanddawkins.datastructures.program1
 */
public class Average
{

    private static int startSize = 1100;
    private static int growthRate = 100;

    private int arraysize;
    private int index;
    private int total;
    private int count;

    /**
     * Constructor the requires the array size so we can get the index
     * for our Average[] array
     *
     * @param arraysize to calculate the index
     */
    public Average(int arraysize)
    {
	this.arraysize = arraysize;
	this.index = (arraysize - startSize) / growthRate;
	this.total = 0;
	this.count = 0;
    }

    /**
     * Method to add a comparison amount to the total
     *
     * @param comparisons the amount of comparions made in the quicksort
     * @return void
     */
    public void add(int comparisons)
    {
	this.total += comparisons;
	this.count++;
    }

    /**
     * Grabs the average based on the total amount of comparisons completed
     *
     * @return average for comparisons
     */
    public int getAverage()
    {
	return this.total / this.count;
    }

    /**
     * Method to grab the index of the average based on the arraysize
     *
     * @return int
     */
    public int getIndex()
    {
	return this.index;
    }

    /**
     * Method to be able to easily print averages in CSV format
     *
     * @return "index,average"
     */
    public String toString()
    {
	return this.getIndex()+","+getAverage();
    }

    /**
     * Static method to set the growth and start value for easier index calculations
     *
     * @param growth rate which the arraysize changes
     * @param start which is the start arraysize
     * @return void
     */
    public static void setGrowth(int growth, int start)
    {
	startSize = start;
	growthRate = growth;
    }
}
