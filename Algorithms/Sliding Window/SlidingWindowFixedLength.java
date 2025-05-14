package test;

import java.util.*;
 
 /**
  *  Fixed length Sliding Window.
  *  
  * | Problem                                              | Type of Window  |
	| ---------------------------------------------------- | --------------- |
	| Longest substring without repeating characters       | Variable-length |
	| Longest substring with at most K distinct characters | Variable-length |
	| Max sum of subarray of size K                        | Fixed-length    |
	| Minimum length subarray with sum ≥ target            | Variable-length |
  */

public class SlidingWindow2 {

	//  Given an array of integers nums and an integer k, find the maximum sum of any contiguous subarray of size k. 
	public static int maxSubarr(int[] nums, int k) {
		
		int maxSum = 0;
		int windowSum = 0;
		int start = 0;
		
		for(int end = 0; end < nums.length; end++) {
			
			windowSum += nums[end];	// expand the window
			
			if(end - start + 1 == k) {
				maxSum = Math.max(maxSum, windowSum);	
				windowSum -= nums[start]; 	// contract the window
				start++;
			}
		}
		
		return maxSum;
	}
	
	// Fixed Length sliding window template.
	public int fixedLength(int[] nums, int k) {
		
		int state = 0;	// or use an appropriate data structure
		// Deque<Integer> state = new ArrayDeque<>();
		// Set<Character> state = new HashSet<>();
		// Map<Character, Integer> state = new HashMap<>();
		int maxResult = 0;
		int start = 0;
		
		for(int end = 0; end < nums.length; end++) {
			
			// Expand the window: Update state with nums[end]
			
			if(end - start + 1 == k) {
				// Process window of size k
				maxResult = Math.max(maxResult, state);	// or other condition
				
				// Shrink window size
				// Remove nums[start] from state
				start++;
			}
		}
		
		return maxResult;
	}

	public static void main(String[] args) {
		
		int[] nums = {2, 1, 5, 1, 3, 2};
		int k = 3;
		System.out.println("Max sum of subarray of size k: " + maxSubarr(nums, k));
	}
}
