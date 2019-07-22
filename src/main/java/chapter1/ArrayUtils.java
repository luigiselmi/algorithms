package chapter1;

import java.util.Arrays;

public class ArrayUtils {
	/**
	 * A method can change the values of an array passed as an argument
	 * @param a
	 * @param factor
	 * @return
	 */
	public static int [] multiplyArray(int [] a, int factor) {
		for (int i = 0; i < a.length; i++)
			a[i] = a[i] * factor;
		return a; 
	}
	
	/**
	 * Finds a value (key) in a sorted array (Binary Search).
	 */
	public static int rank(int key, int[] a) { 
		//Arrays.sort(a); // doesn't need sort with the recursive implementation
		return rank(key, a, 0, a.length - 1); 
	}
	/**
	 * Recursive implementation of binary search 
	 */
	public static int rank(int key, int[] a, int lo, int hi) { 
		// Index of key in a[], if present, is not smaller than lo
		// and not larger than hi.
		if (lo > hi) return -1;
		int mid = lo + (hi - lo) / 2;
		if (key < a[mid]) return rank(key, a, lo, mid - 1);
		else if (key > a[mid]) return rank(key, a, mid + 1, hi);
		else return mid;
	}
	
	public static void main(String [] args) {
		
		// Initialize an array of integers
		int [] a = {1, 2, 4, 0, -3, 7};
		assert a.length == 6 : "ok"; // Not enabled by default. Use VM argument "-ea"
		
		// Search for a key in an array
		int key = 4;
		int index = ArrayUtils.rank(key, a);
		System.out.println("Key = " + key + " Index = " + index);
		
		// Element-wise multiplication on an array
		ArrayUtils.multiplyArray(a, 2);
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
		
		assert a.length == 6 : "ok";
	}

}
