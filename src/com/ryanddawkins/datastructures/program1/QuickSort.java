package com.ryanddawkins.datastructures.program1;

/**
 * Class to perform the quicksort operations needed for comparisons
 *
 * @author Ryan Dawkins
 * @package com.ryanddawkins.datastructures.program1
 */
public class QuickSort
{
    private int[] array;
    protected static int comparisonCount = 0;

    /**
     * Default constructor sends in null as default to the main constructor QuickSort(int[])
     */
    public QuickSort()
    {
	this(null);
    }

    /**
     * Main constructor that takes an integer array as an argument 
     *
     * @param array to be sorted
     */
    public QuickSort(int[] array)
    {
	this.array = array;
    }

    /**
     * Chainable mutator to set the array to be sorted
     *
     * @param array to be sorted
     * @return this
     */
    public QuickSort setArray(int[] array)
    {
	this.array = array;
	return this;
    }
    
    /**
     * Accessor to grab the sorted array
     *
     * @return this.array
     */
    public int[] getArray()
    {
	return this.array;
    }

    /**
     * Grabs the static comparison count
     *
     * @return comparisonCount
     */
    public int getComparisonCount()
    {
	return comparisonCount;
    }

    /**
     * Recursive quicksort method to sort by partitioning
     * Use: quicksort(A, 1, A.length)
     *
     * @param a array to be sorted by reference
     * @param p integer that is the pivot point
     * @param r which is the length of the array initially
     * @return void
     */
    public static void quicksort(int[] a, int p, int r)
    {
	comparisonCount++;
	if(p < r)
	    {
		int q = partition(a, p, r);
		quicksort(a, p, q-1);
		quicksort(a, q+1, r);
	    }
    }

    /**
     * Partition method to sort the array
     *
     * @param A array to be "partitioned"
     * @param p integer that is the pivot point
     * @param r last index to check
     * @return i next pivot point
     */
    public static int partition(int[] a, int p, int r)
    {
	int x = a[r];
	int i = p-1;
	for(int j = p; j <= r-1; j++)
	    {
		comparisonCount++;
		if(a[j] <= x)
		    {
			i = i+1;
			exchange(a, i, j);
		    }
	    }
	exchange(a, i+1, r);
	return i + 1;
    }

    /**
     * Method to swap integers using xor
     *
     * @param array
     * @param i index of the first element
     * @param j index of the second element
     * @return void
     */
    public static void exchange(int[] a, int i, int j)
    {
	// Checks to make sure they aren't equal else they'll be set to zero
	if(a[i] == a[j]) return;
	else
	    {
		a[i] ^= a[j];
		a[j] ^= a[i];
		a[i] ^= a[j];
	    }
    }

    /**
     * Chainable method to sort based off the objects member values
     *
     * @return this
     */
    public QuickSort quicksort()
    {
	quicksort(this.array, 0, this.array.length-1);
	return this;
    }

}