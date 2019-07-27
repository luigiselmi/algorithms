package searching;

public class RecursiveBinarySearch {
	
	/**
     * This class should not be instantiated.
     */
    private RecursiveBinarySearch() { }

    /**
	 * Recursive implementation of binary search 
	 */
	public static int indexOf(int key, int[] a, int lo, int hi) { 
		// Index of key in a[], if present, is not smaller than lo
		// and not larger than hi.
		if (lo > hi) return -1;
		int mid = lo + (hi - lo) / 2;
		if (key < a[mid]) return indexOf(key, a, lo, mid - 1);
		else if (key > a[mid]) return indexOf(key, a, mid + 1, hi);
		else return mid;
	}
	
	public static void main(String[] args) {
		// Initialize an array of integers
		int [] a = {1, 2, 4, 0, -3, 7};
		assert a.length == 6 : "ok"; // Not enabled by default. Use VM argument "-ea"
		
		// Search for a key in an array
		int key = 4;
		int index = RecursiveBinarySearch.indexOf(key, a, 0, a.length - 1);
		System.out.println("Key = " + key + " Index = " + index);
		
	}
}
