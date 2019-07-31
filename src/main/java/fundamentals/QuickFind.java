package fundamentals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuickFind {
	
	private int [] id;
	private int count;
	
	public QuickFind(int N) {
		count = N;
		id = new int [N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

	public void union(int p, int q) {
		int pID = find(p);
		int qID = find(q);
		
		if(pID == qID) return;
		
		for(int i = 0; i < id.length; i++)
			if (id[i] == pID) id[i] = qID;
		
		count--;
	}
	
	public int count() {
		return count;
	}
	
	public int find(int p) {
		return id[p];
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(args[0]));
			String s;
			int N = Integer.parseInt(reader.readLine());
			QuickFind qf = new QuickFind(N);
			while ((s = reader.readLine()) != null) {
				String [] pair = s.split(" ");
				int p = Integer.parseInt(pair[0]);
				int q = Integer.parseInt(pair[1]);
				if(qf.connected(p, q)) continue;
				qf.union(p, q); // ~N^2
				System.out.println(p + " " + q);
			}
			
			System.out.println(qf.count() + " connected components");
		}
		finally {
			reader.close();
		}

	}
	
}
