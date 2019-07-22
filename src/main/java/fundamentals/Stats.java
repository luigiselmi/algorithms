package fundamentals;

public class Stats {

	public static void main(String[] args) {
		Bag<Double> numbers = new Bag<Double>();
		
		// Init the bag
		numbers.add(3.0);
		numbers.add(2.8);
		numbers.add(3.1);
		numbers.add(3.2);
		numbers.add(2.9);
		
		int N = numbers.size();
		
		double sum = 0.0;
		for(Double number: numbers) {
			sum += number;
		}
		
		double mean = sum / N;
		
		double sqrDiffSum = 0.0;
		for(Double number: numbers) {
			sqrDiffSum += (number - mean)*(number - mean);
		}
		
		double std = Math.sqrt(sqrDiffSum / (N - 1));
		
		System.out.println("Number of elements: " + N);
		System.out.println("Sum: " + sum);
		System.out.println("Mean: " + mean);
		System.out.println("Standard deviation: " + std);

	}

}
