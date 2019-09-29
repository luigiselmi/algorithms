package fundamentals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * A stack (Last In First Out) based on a fixed capacity array.
 * Pushes words in a stack and pops it when a "-" is encountered.
 */
public class FixedCapacityStackOfStrings {
	
	private String[] a; // stack entries
	private int N; // size
	
	public FixedCapacityStackOfStrings(int cap) { 
		a = new String[cap]; 
	}
	
	public boolean isEmpty() { return N == 0; }
	
	public int size() { return N; }
	
	public void push(String item) { 
		a[N++] = item; 
	}
	
	public String pop() { 
		return a[--N]; 
	}
	
	public static void main(String[] args) throws IOException {
		
		List<String> words = readWords(args[0]);
		
		FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(100);
		
		for(String word: words) {
			if (! word.equals("-"))
				stack.push(word);
			else if (!stack.isEmpty()) System.out.print(stack.pop() + " ");
		}
		
		System.out.println("(" + stack.size() + " left on stack)");
	}
	
	private static List<String> readWords(String path) throws IOException {
		String filePath = path;
		List<String> words = Files.lines(Paths.get(filePath), Charset.defaultCharset())
				.flatMap(line -> Arrays.stream(line.split(" ")))
				.collect(Collectors.toList());
				
		return words;
	}
}
