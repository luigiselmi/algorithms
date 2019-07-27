package fundamentals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import edu.princeton.cs.algs4.StdOut;


public class DoublingRatio {
	
	private static final int MAXIMUM_INTEGER = 1000000;

	public static double timeTrial(int n) {
		Random rnd = new Random();
		rnd.setSeed(System.currentTimeMillis());
		int[] a = new int[n];
		int min = - MAXIMUM_INTEGER;
		int max = MAXIMUM_INTEGER;
        for (int i = 0; i < n; i++) {
            a[i] = min + rnd.nextInt(max - min);
        }
        long start = System.currentTimeMillis();
        ThreeSum.count(a);
        long stop = System.currentTimeMillis();
        return (stop - start)/1000.0;
    }
	
	public static void main(String[] args) {
		double prev = timeTrial(100);
        for (int n = 200; true; n += n) {
            double time = timeTrial(n);
            System.out.println("N = " +  n + ", Time = " + time + ", Ratio = " + time/prev);
            prev = time;
        } 
	}
	
}
