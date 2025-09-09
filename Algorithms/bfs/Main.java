package Greedy;

import java.util.*;

public class Main {
	
	public Map<String, List<String>> adjList;
	
	public final int[][] matrix = {
		    {0, 0, 0},
		    {0, 1, 1},
		    {0, 1, 0}
	};
	
	public final int[][] directions = {
			{-1, 0},	// up
			{1, 0},		// down
			{0, -1},	// left
			{0, 1}		// right
	};
	
	public Main() {
		
		this.adjList = new HashMap<>(); 
		
		adjList.put("1", Arrays.asList("2", "4"));
		adjList.put("2", Arrays.asList("1", "3"));
		adjList.put("3", Arrays.asList("2", "4"));
		adjList.put("4", Arrays.asList("1", "3", "5"));
		adjList.put("5", Arrays.asList("4"));
	}
	
	// adjlist level by level
	public List<List<String>> bfsLevels(Map<String, List<String>> graph, String start){
		
		Queue<String> queue = new LinkedList<String>();
		queue.offer(start);
		
		Set<String> visited = new HashSet<String>();
		visited.add(start);
		
		List<List<String>> levels = new ArrayList<List<String>>();
		
		while(!queue.isEmpty()) {
			
			int levelSize = queue.size();
			List<String> currentLevel = new ArrayList<String>();
			
			for(int i = 0; i < levelSize; i++) {
				
				String node = queue.poll();
				currentLevel.add(node);
				
				for(String neighbor : graph.get(node)) {
					
					if (!visited.contains(neighbor)) {
						visited.add(neighbor);
						queue.offer(neighbor);
					}
				}
			}
			
			// we have finished processing all nodes at this level so we add it to the list
			levels.add(currentLevel);
		}
		
		return levels;
	}
	
	// bfs on matrix level by level
	public List<List<int[]>> bfsLevelByLevel(int [][] matrix){
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		// start at the top-left corner
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {0, 0});
		
		Set<String> visited = new HashSet<String>();
		visited.add("0,0");
		
		List<List<int[]>> levels = new ArrayList<List<int[]>>();
		
		while(!queue.isEmpty()) {
			
			int levelSize = queue.size();
			List<int[]>  currentLevel = new ArrayList<int[]>();
			
			for(int i = 0; i < levelSize; i++) {
				
				int[] position = queue.poll();
				int row = position[0], col = position[1];
				currentLevel.add(new int[] {row, col});
				
				for(int[] dir : directions) {
					
					int r = row + dir[0];
					int c = col + dir[1];
					
					if (r >= 0 && r < rows && c >= 0 && c < cols && !visited.contains(r + "," + c)) {
						visited.add(r + "," + c);
						queue.offer(new int[] {r, c});
					}
				}
			}
			
			// we have finished processing all nodes at this level so we add it to levels list
			levels.add(currentLevel);
		}
		
		return levels;
	}
	
	// bfs on matrix
	public void bfsMatrix(int[][] grid) {
		
		Set<String> visited = new HashSet<String>();
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {0, 0});
		visited.add("0,0");
		
		while(!queue.isEmpty()) {
			
			int[] current = queue.poll();
			int row = current[0], col = current[1];
			
			// enqueue neighbors
	        for (int[] dir : directions) {
	        	
	            int nRow = row + dir[0];
	            int mCol = col + dir[1];
				
				// check bounds and if neighbor is visited
				if (nRow >= 0 && nRow < grid.length && mCol >= 0 && mCol < grid[0].length && !visited.contains(nRow + "," + mCol)) {
					queue.offer(new int[] {nRow, mCol});
					visited.add(nRow + "," + mCol);
				}
			}
		}
	}
	
	// bfs on adjlist
	public void bfsAdjList(String start) {
		
		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		
		visited.add(start);
		queue.offer(start);
		
		while (!queue.isEmpty()) {
			
			String node = queue.poll();
			
			for(String neighbor : adjList.get(node)) {
				
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.offer(neighbor);
				}
			}
		}
	}
}
