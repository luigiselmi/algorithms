package chapter1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * A stack (Last In First Out) based on a fixed array.
 * Pushes words in a stack and pops it when a "-" is encountered.
 * This examples uses Java Generics instead of a fixed type (String)
 * as elements of the stack.
 */
public class FixedCapacityStack<Item> {
	
	private Item[] a; // stack entries
	private int N; // size
	
	@SuppressWarnings("unchecked")
	public FixedCapacityStack(int cap) { 
		a = (Item[]) new Object[cap]; 
	}
	
	public boolean isEmpty() { return N == 0; }
	
	public int size() { return N; }
	
	public void push(Item item) { 
		if (N == a.length) resize(2 * a.length);
		a[N++] = item; 
	}
	
	public Item pop() { 
		Item item = a[--N];
		a[N] = null; //remove reference to the popped item
		if (N > 0 && N == a.length/4) resize(a.length/2);
		return item; 
	}
	
	// Move stack of size N <= max to a new array of size max.
	private void resize(int max) { 
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++)
		temp[i] = a[i];
		a = temp;
	}
	
	public static void main(String[] args) throws IOException {
		
		List<String> words = readWords();
		
		FixedCapacityStack<String> stack = new FixedCapacityStack<String>(100);
		
		for(String word: words) {
			if (! word.equals("-"))
				stack.push(word);
			else if (!stack.isEmpty()) System.out.print(stack.pop() + " ");
		}
		
		System.out.println("(" + stack.size() + " left on stack)");
	}
	
	private static List<String> readWords() throws IOException {
		String filePath = "resources/data.txt";
		List<String> words = Files.lines(Paths.get(filePath), Charset.defaultCharset())
				.flatMap(line -> Arrays.stream(line.split(" ")))
				.collect(Collectors.toList());
				
		return words;
	}
}
