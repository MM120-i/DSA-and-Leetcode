package other;

public class minOperations {
	
    public int minimumOperationsToWriteY(int[][] grid) {
    	
    	final int n = grid.length;
    	final int mid = n / 2;
    	int yValues[] = new int[3];
    	int notyValues[] = new int[3];
    	
    	for(int i = 0; i < n; i++) {
    		
    		for(int j = 0; j < n; j++) {
    			
    			if((i == j && j <= mid) || (j == mid && i >= mid) || (i + j == n - 1 && j >= mid)) {
    				yValues[grid[i][j]]++;
    			}
    			else {
    				notyValues[grid[i][j]]++;
    			}
    		}
    	}
    	
    	int yValue0 = yValues[1] + yValues[2];
    	int yValue1 = yValues[0] + yValues[2];
    	int yValue2 = yValues[0] + yValues[1];
    	
    	int notyValue0 = notyValues[1] + notyValues[2];
    	int notyValue1 = notyValues[0] + notyValues[2];
    	int notyValue2 = notyValues[0] + notyValues[1];
    	
    	int result[] = new int[6];
    	result[0] = yValue0 + notyValue1;
    	result[1] = yValue1 + notyValue0;
    	result[2] = yValue1 + notyValue2;
    	result[3] = yValue2 + notyValue1;
    	result[4] = yValue2 + notyValue0;
    	result[5] = yValue0 + notyValue2;
    	
    	int x = Integer.MAX_VALUE;
    	
    	for(int i : result) {
    		x = Math.min(i, x);
    	}
    	
		return x; 
    }
}
