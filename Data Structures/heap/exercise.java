package Heap;

import java.util.*;

public class exercise {
	
	/**
	 * Find K Closest Elements from leetcode
	 * 
	 * Given a sorted array nums, a target value target, and an integer k, 
	 * find the k closest elements to target in the array, 
	 * where "closest" is the absolute difference between each element and target. 
	 * Return these elements in array, sorted in ascending order.
	 * @param nums
	 * @param k
	 * @param target
	 * @return
	 */
	public static List<Integer> kClosest(int[] nums, final int k, final int target) {
		
		if (nums.length == 0) {
			return null;
		}
		
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a,b) -> Integer.compare(b[0], a[0])); // reverse it to a max heap
		
		for (int num : nums) {
			
			int distance = Math.abs(num - target);
			
			if (heap.size() < k) {
				heap.offer(new int[] {distance, num});
			}
			else if (distance < -heap.peek()[0]) {
				heap.poll();	// remove the one with the largest distance (root cuz its a max heap)
				heap.offer(new int[] {-distance, num});
			}
		}
		
		List<Integer> distances = new ArrayList<Integer>();
		
		for (int[] pair: heap) {
			distances.add(pair[1]);	// get the number (not the distance value)
		}
		
		Collections.sort(distances);	// sort in ascending order
		
		return distances;
	}
	
	// Second implementation of Find K Closest Elements
	public List<Integer> findClosestElements(int[] nums, final int k, final int target){
		
		int left = 0;
		int right = nums.length - k;
		
		while (left < right) {
			
			int mid = left + (right - left) / 2;
			
			if (target - nums[mid] > nums[mid + k] - target) {
				left = mid + 1;
			}
			else {
				right = mid;
			}
		}
		
		List<Integer> result = new ArrayList<Integer>();
		
		for(int i = left; i < left + k; i++) {
			result.add(nums[i]);
		}
		
		return result;
	}
	
	/**
	 * Kth Largest Element in an Array from leetcode
	 * 
	 * Write a function that takes an array of unsorted integers nums and an integer k, and returns the kth largest element in the array. 
	 * This function should run in O(n log k) time, where n is the length of the array.
	 */
	public static int kthLargest(int[] nums, final int k) {
		
		if (nums.length == 0) {
			return -1;
		}
		
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		
		for(final int num : nums) {
			
			if (heap.size() < k) {
				heap.offer(num);	// fill up the heap to size k
			}
			else if(num > heap.peek()) {
				heap.poll();	// remove the smallest of top k
				heap.offer(num);	// add a bigger number
			}
		}
		
		return heap.peek();
	}

	public static void main(String[] args) {
		
		// kth largest
		final int k = 2;
		int[] nums = {5, 3, 2, 1, 4};
		System.out.println("k'th largest: " + kthLargest(nums, k));
	}

}
