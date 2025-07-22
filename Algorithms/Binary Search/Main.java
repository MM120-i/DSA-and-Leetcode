package BinarySearch;

public class Main {
	
	/**
	 * Apple Harvest (Koko Eating Bananas) from leetcode.
	 * 
	 * Bobby has an orchard of apple trees, and each tree has a certain number of apples on it.
	 * Bobby wants to collect all the apples by the end of the day by collecting a fixed number of apples per hour. He can only harvest apples from one tree per hour
     * if he finishes collecting apples from a tree before the hour is up, he must wait until the next hour to move to the next tree.
     * For example, if there are 3 apples on a tree and Bobby collects 1 apple per hour, it will take him 3 hours to finish collecting the apples on that tree.
     * If he harvests 2 apples per hour, it will take him 2 hours to finish collecting all the apples on that tree (he waits until the hour is up even though he finishes early).
     * Write a function to determine the slowest rate of apples Bobby can harvest per hour to finish the job in at most 'h' hours. 
     * The input to the function is an array of integers representing the number of apples on each tree and an integer 'h' representing the number of hours Bobby has to finish the job within.
	 */
	public static int appleHarvest(int[] apples, final int h) {
		
    	int left = 1;
    	int right = getMax(apples);
    	
    	while(left < right) {
    		
    		int mid = (left + right) / 2;
    		int time = timeTaken(mid, apples);
    		
    		if(time > h) {
    			left = mid + 1;
    		}
    		else {
    			right = mid;
    		}
    	}
    	
		return left;
	}
	
	private static int timeTaken(int rate, int[] apples) {
		
		int time = 0;
		
		for(int apple : apples) {
			time += (apple + rate - 1) / rate;
		}
		
		return time;
	}
	
	private static int getMax(int[] array) {
		
		int max = array[0];
		
		for(int value : array) {
			if(value > max) {
				max = value;
			}
		}
		
		return max;
	}
	
    public static int binarySearch(int[] nums, final int target) { 
    	
    	int left = 0;
    	int right = nums.length - 1;
    	
    	while(left <= right) {
    		
    		int mid = (left + right) / 2;
    		
    		if(nums[mid] == target) {
    			return mid;
    		}
    		
    		if(nums[mid] < target) {
    			left = mid + 1;
    		}
    		else {
    			right = mid - 1;
    		}
    	}
    	
    	return -1;
    }

	public static void main(String[] args) {
		
		  // binary search
		  final int target = 9;
		  int[] nums = {-1, 0, 3, 5, 9, 12};
    	System.out.println(binarySearch(nums, target));
    	
    	// apple harvest
    	final int h = 8;
    	int[] apples = {3, 6, 7};
    	System.out.println(appleHarvest(apples, h));
	}

}
