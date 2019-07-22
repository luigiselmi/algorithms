package fundamentals;

public class Fifo {

	public static void main(String[] args) {
		
		Queue<String> queue = new Queue<String>();
        
        queue.enqueue("S");
        queue.enqueue("i");
        queue.enqueue("r");
        queue.enqueue("i");
        queue.enqueue("s");
        
        // print the items in the same order they were entered
        for(String item: queue) {
        	System.out.println(item);
        }
	}

}
