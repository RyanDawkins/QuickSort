package com.ryanddawkins.datastructures.program1;

//*******************************************************************
// CS4343 - Program 1
// Dawkins, Ryan
//
// Class to test the QuickSort algorithm
//*******************************************************************

import java.util.Random;
import java.io.IOException;
import java.io.PrintWriter;
import com.ryanddawkins.datastructures.program1.QuickSort;
import com.ryanddawkins.datastructures.program1.Average;

/**
 * Class to test the QuickSort class and check it's comparison rates
 *
 * @author Ryan Dawkins
 * @extends QuickSort
 */
public class TestQS extends QuickSort
{

    private Random random;
    private static Random stat_random;
    private static int MAX_VALUE = 100;
    private static int MIN_VALUE = 0;
    private static int GROWTH_RATE = 100;
    private static int START_SIZE = 1100;
    private static int LAST_SIZE = 10000;

    public static void main(String[] args)
    {

	if(!unitTests())
	    {
		System.out.println("Unit tests failed, exiting program");
		return;
	    }

	// Created static random object to avoid repeating randoms
	stat_random = new Random();

	// Sets the static values to the growth object
	Average.setGrowth(GROWTH_RATE, START_SIZE);
	Average[] scores = new Average[90];
	
	// Loops through all of the different array sizes
	for(int i = START_SIZE; i <= LAST_SIZE; i += GROWTH_RATE)
	    {
		Average average = new Average(i);
		for(int j = 0; j < 100; j++)
		    {
			// Makes sure we don't send a bad arraysize in
			TestQS test;
			try
			    {
				test = new TestQS(i, stat_random).genRandomArray().quicksort();
			    }
			catch(BadArraySizeException e)
			    {
				System.out.println(e.getMessage());
				return;
			    }
			// Adds the comparison value to the average object and sets the value as 0
			average.add(comparisonCount);
			comparisonCount = 0;
		    }
		scores[average.getIndex()] = average;
	    }
	exportToCSV(scores);
    }

    /**
     * Constructor that takes a default arraysize and a random object
     *
     * @param arraysize size of the array that you want to be filled
     * @param random Random object, should be static outside for other sorts
     */
    public TestQS(int arraysize, Random random)
    {
	super(new int[arraysize]);
	this.setRandom(random);
    }

    /**
     * Method to change the random object if necessary
     *
     * @param random object to deal with random values in genRandomArray()
     * @return void
     */
    public void setRandom(Random random)
    {
	this.random = random;
    }

    /**
     * Method to grab the random object
     *
     * @return this.random
     */
    public Random getRandom()
    {
	return this.random;
    }

    /**
     * Calls the quicksort method in the parent class and so we can write chainable methods
     *
     * @return this
     */
    public TestQS quicksort()
    {
	super.quicksort();
	return this;
    }

    /**
     * Method to generate the random array using object members
     *
     * @throws BadArrayException
     * @return TestQS
     */
    public TestQS genRandomArray() throws BadArraySizeException
    {
	genRandomArray(this.getArray().length, this.getArray(), this.random);
	return this;
    }

    /**
     * Method to generate the random array and checks to see if static random object was declared
     *
     * @param arraysize
     * @throws BadArrayException
     * @return randomArray
     */
    public static int[] genRandomArray(int arraysize) throws BadArraySizeException
    {
	int[] randomInts = new int[arraysize];
	stat_random = (stat_random != null) ? stat_random : new Random();
	return genRandomArray(arraysize, randomInts, stat_random);
    }

    /**
     * Method to generate random array by specifying all parameters
     *
     * @param arraysize
     * @param randomInts array that will be filled with random integers
     * @param random object that was seeded previously
     * @throws BadArrayException
     * @return randomInts an array filled with random integers
     */
    public static int[] genRandomArray(int arraysize, int[] randomInts, Random random) throws BadArraySizeException
    {
	if(arraysize == 0)
	    {
		throw new BadArraySizeException("The arraysize is 0");
	    }
	for(int i = 0; i < randomInts.length; i++)
	    {
		randomInts[i] = random.nextInt(MAX_VALUE) + MIN_VALUE;
	    }
	return randomInts;
    }

    /**
     * Chainable method to print all values each in a newline
     *
     * @return this to chain methods
     */
    public TestQS printlnArray()
    {
	for(int i = 0; i < this.getArray().length; i++)
	    {
		System.out.println(this.getArray()[i]);
	    }
	return this;
    }

    /**
     * Calls static method printArray and returns this for chainability
     *
     * @return this
     */
    public TestQS printArray()
    {
	printArray(this.getArray());
	return this;
    }

    /**
     * Method to print the entire array in the form [1, 2, 3, 4]
     *
     * @param randomInts array to be printed
     * @return void
     */
    public static void printArray(int[] randomInts)
    {
	int arraysize = randomInts.length;
	System.out.print("[");
	for(int i = 0; i < arraysize-1; i++)
	    {
		System.out.print(randomInts[i]+",");
	    }
	System.out.print(randomInts[arraysize-1]+"]\n");
    }

    /**
     * Unit test method to check if we are given a sorted array
     *
     * @param TestQS test
     * @boolean isSorted
     */
    public static boolean testSort(TestQS test)
    {
	int[] randomInts = test.getArray();
	if(randomInts[randomInts.length-2] > randomInts[randomInts.length-1]) return false;
	for(int i = 1; i < randomInts.length-1; i++)
	    {
		if(randomInts[i] > randomInts[i+1])
		    {
			return false;
		    }
	    }
	return true;
    }

    /**
     * Method to see if quicksort returns appropriate output
     *
     * @return boolean
     */
    public static boolean unitTests()
    {
	TestQS test = null;
	int size = 0;
	stat_random = null;
	boolean badSize = true;
	boolean badSizeException = false;
	boolean nullPointerException = false;
	while(badSize)
	    {
		try
		    {
			test = new TestQS(size, stat_random);
			test.genRandomArray().quicksort();
			badSize = false;
		    }
		catch(BadArraySizeException e)
		    {
			size = 25;
			badSizeException = true;
		    }
		catch(NullPointerException e)
		    {
			stat_random = new Random();
			nullPointerException = true;
		    }
	    }

	if(!badSizeException)
	    {
		System.out.println("Accepted length 0, test fails");
		return false;
	    }
	else if(!nullPointerException)
	    {
		System.out.println("Created new Random test failed");
		return false;
	    }
	else if(!testSort(test))
	    {
		System.out.println("Sort was in incorrect order: "+test.printArray());
		return false;
	    }
	else
	    {
		System.out.println("All unit tests passed");
		return true;
	    }
    }

    /**
     * Method to export to a csv file given by an array of averages that will try
     * to write to a file called results<some random integer>.txt
     *
     * @param scores an array of Average objects
     * @return void
     */
    private static void exportToCSV(Average[] scores)
    {
	if(scores.length == 0) return;
	boolean badIO = true;
	int count = 0;
	String fileName = "results.txt";
	PrintWriter pw = null;
	while(badIO && count < 10)
	    {
		try
		    {
			pw = new PrintWriter(fileName, "UTF-8");
			badIO = false;
		    }
		catch(IOException e)
		    {
			System.out.print("Error processing: "+fileName);
			fileName = "results"+(new Random()).nextInt()+".txt";
			System.out.println(" trying new file: "+fileName);
			count++;
		    }
	    }
	for(int i = 0; i < scores.length; i++)
	    {
		pw.println(scores[i]);
	    }
	pw.close();
    }

}

/**
 * This class is to throw an exception for bad arrays
 *
 */
class BadArraySizeException extends Exception
{
    public BadArraySizeException(String message)
	{
	    super(message);
	}
}