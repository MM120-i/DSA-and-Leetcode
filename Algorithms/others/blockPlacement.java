package other;

import java.util.*;

public class block {
	
	public List<Boolean> getResults(int[][] queries) {
		
		final int n = Math.min(50000, queries.length * 3);
		List<Boolean> ans = new ArrayList<>();
		Tree tree = new Tree(n + 1);
		TreeSet<Integer> obstacles = new TreeSet<>(Arrays.asList(0, n));
		
		for(int[] query : queries){
			
			final int type = query[0];
			
			if(type == 1) {
				final int x = query[0];
				obstacles.add(x);
			}
		}
		
		Iterator<Integer> it = obstacles.iterator();
		int x1 = it.next();
		
		while(it.hasNext()) {
			final int x2 = it.next();
			tree.add(x2, x2 - x1);
			x1 = x2;
		}
		
		for(int i = queries.length - 1; i >= 0; i--) {
			
			final int type = queries[i][0];
			final int x = queries[i][1];
			
			if(type == 2) {
				
				final Integer next = obstacles.higher(x);
				final Integer prev = obstacles.lower(x);
				
				if(next != null) {
					tree.add(next, next - prev);
				}
				
				obstacles.remove(x);
			}
			else {
				final int sz = queries[i][2];
				final int prev = obstacles.floor(x);
				ans.add(tree.get(prev) >= sz || x - prev >= sz);
			}
		}
		
		Collections.reverse(ans);
		
		return ans;
	}
}

class Tree{
	
	private int[] vals;
	
	public Tree(int n) {
		vals = new int[n + 1];
	}

	public void add(int i, int val) {
		
		while(i < vals.length) {
			vals[i] = Math.max(vals[i], val);
			i += lowerbit(i);
		}
	}

	private int lowerbit(int i) {
		return i & -i;
	}

	public int get(int i) {
		
		int res = 0;
		
		while(i > 0) {
			res = Math.max(res, vals[i]);
			i -= lowerbit(i);
		}
		
		return res;
	}	
}
