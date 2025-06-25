class Solution {
    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
    	HashMap<Long, Integer> map = new HashMap<>();
    	long[] result = {(n - 1L) * (m - 1), 0, 0, 0, 0};
    	
    	for(int[] coords : coordinates) {
    		
    		for(int i = coords[0]; i < coords[0] + 2; i++) {
    			
    			for(int j = coords[1]; j < coords[1] + 2; j++) {
    				
    				if(i > 0 && i < m && 0 < j && j < n) {
    					
    					long index = i * 100000L + j;
    					result[map.getOrDefault(index, 0)]--;
    					map.put(index, map.getOrDefault(index, 0) + 1);
    					result[map.getOrDefault(index, 0)]++;
    				}
    			}
    		}
    	}
    	
    	return result;
    }
}
