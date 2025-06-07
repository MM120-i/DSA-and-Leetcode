package stack;

import java.util.*;

/**
 * Mono stack is where the stack's elements r always in increasing or decreasing order.
 * 
 * Mono Increasing: Elements get larger from the top to bottom.
 * Mono Decreasing: Elements get smaller from top to bottom. 
 * 
 */
public class MonoStackExercise {
	
	/**
	 * Next greater element
	 * 
	 * For each element in the array, find the first greater element to its right. If none exists, use -1.
	 * 
	 * Input: [2, 1, 3, 2, 4, 3]
	 * Output: [3, 3, 4, 4, -1, -1]
	 */
	public static int[] nextGreaterElement(int[] nums) {
		
		int[] result = new int[nums.length];
		Stack<Integer> stack = new Stack<>();
		
		Arrays.fill(result, -1);
		
		for(int i = 0; i < nums.length; i++) {
			
			// While the stack isnt empty and the current number is greater than the peek's value
			while(!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
				int idx = stack.pop();
				result[idx] = nums[i];
			}
			
			stack.push(i);
		}
		
		return result;
	}
	
	/**
	 * Next Smaller Element (Implementation is very similar to next greater element).
	 */
	public static int[] nextSmallerElement(int[] nums) {
		
		int[] result = new int[nums.length];
		Stack<Integer> stack = new Stack<>();
		
		Arrays.fill(result, -1);
		
		for(int i = 0; i < nums.length; i++) {
			
			// While the stack isnt empty and the current number is greater than the peek's value
			while(!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
				int idx = stack.pop();
				result[idx] = nums[i];
			}
			
			stack.push(i);
		}
		
		return result;
	}

	public static void main(String[] args) {
		
		// next greater element
		int[] x = {2, 1, 3, 2, 4, 3};
		int[] greater = nextGreaterElement(x);
		System.out.println("Greater: " + Arrays.toString(greater));
		
		// next smaller element
		int[] smaller = nextSmallerElement(x);
		System.out.println("Smaller: " + Arrays.toString(smaller));
	}
}
