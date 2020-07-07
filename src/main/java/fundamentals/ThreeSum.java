package fundamentals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ThreeSum {
	
	// Do not instantiate.
    private ThreeSum() { }
    
	/*
	 * This algorithm takes a time proportional to N^3 where N is the input size
	 */
	public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
	
	/*
	 * This algorithm takes a time proportional to N^2*log(N) where N is the input size
	 */
	public static int fastCount(int [] a) {
		int n = a.length;
        Arrays.sort(a); // mergesort, running time N*log(N)
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j])); // running time log(N)
                if (k > j) count++;
            }
        }
        return count;
	}

	public static void main(String[] args) throws IOException {
		String filePath = "resources/fundamentals/8Kints.txt";
		int [] a = readNumbersFromFile(filePath);
		
		// uses count() 
		long start = System.currentTimeMillis();
		int triples = count(a); // number of triples of integers whose sum is 0
	    long stop = System.currentTimeMillis();
		double elapsedTime = (stop - start)/1000.0;
		System.out.println("Number of triples (count): " + triples + "\nTime elapsed (s): " + elapsedTime);
		
		// uses fastCount()
		long start1 = System.currentTimeMillis();
		int triples1 = fastCount(a); // number of triples of integers whose sum is 0
	    long stop1 = System.currentTimeMillis();
		double elapsedTime1 = (stop1 - start1)/1000.0;
		System.out.println("Number of triples (fast count): " + triples1 + "\nTime elapsed (s): " + elapsedTime1);
	}
	
	private static int [] readNumbersFromFile(String filePath) throws IOException {
		int [] integers = Files.lines(Paths.get(filePath), Charset.defaultCharset())
				.map(line -> line.trim())
				.mapToInt(Integer::valueOf)
				.toArray();
				
		return integers;
	}

}
