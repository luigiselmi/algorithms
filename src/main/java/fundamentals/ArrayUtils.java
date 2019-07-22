package fundamentals;

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
	
	public static void main(String [] args) {
		
		// Initialize an array of integers
		int [] a = {1, 2, 4, 0, -3, 7};
		assert a.length == 6 : "ok"; // Not enabled by default. Use VM argument "-ea"
		
		// Element-wise multiplication on an array
		ArrayUtils.multiplyArray(a, 2);
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
		
	}

}
