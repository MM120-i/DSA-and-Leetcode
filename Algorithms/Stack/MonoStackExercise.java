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
	 * Largest Rectangle in Histogram from leetcode
	 * 
	 * Given an integer array heights representing the heights of histogram bars, 
	 * write a function to find the largest rectangular area possible in a histogram, where each bar's width is 1.
	 */
	public static int largestRectangleArea(int[] heights) {
		
		Stack<Integer> stack = new Stack<>();
		int maxArea = 0, i = 0;
		
		while(i < heights.length) {
			
			if(stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
				stack.push(i++);
			}
			else {
				maxArea = helper(maxArea, stack, i, heights);
			}			
		}
		
		while(!stack.isEmpty()) {
			maxArea = helper(maxArea, stack, i, heights);
		}
		
		return maxArea;
	}
	
	private static int helper(int maxArea, Stack<Integer> stack, int i, int[] heights) {
		
		int top = stack.pop();
		int left = stack.isEmpty() ? - 1 : stack.peek();
		int width = i - left - 1;
		int area = heights[top] * width;
		maxArea = Math.max(maxArea, area);
		
		return maxArea;
	}
	
	/**
	 * Daily Temperatures from leetcode
	 * 
	 * Given an integer array temps representing daily temperatures, write a function to calculate 
	 * the number of days one has to wait for a warmer temperature after each given day. 
	 * The function should return an array answer where answer[i] represents the wait time for a warmer day after the ith day. 
	 * If no warmer day is expected in the future, set answer[i] to 0.
	 */
	public static int[] dailyTemp(int[] temps) {
		
		int[] result = new int[temps.length];
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < temps.length; i++) {
			
			while(!stack.isEmpty() && temps[i] > temps[stack.peek()]) {
				int index = stack.pop();
				result[index] = i - index;
			}
			
			stack.push(i);
		}
		
		return result;
	}
	
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
				int index = stack.pop();
				result[index] = nums[i];
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
		System.out.println("Greater: " + Arrays.toString(nextGreaterElement(x)));
		
		// next smaller element
		System.out.println("Smaller: " + Arrays.toString(nextSmallerElement(x)));
		
		// Daily temp
		int[] temps = {65, 70, 68, 60, 55, 75, 80, 74};
		System.out.println(Arrays.toString(dailyTemp(temps)));
		
		// Largest area histogram
		int[] heights = {2, 8, 5, 6, 2, 3};
		System.out.println("Largest rectangle area: " + largestRectangleArea(heights));
	}

}
