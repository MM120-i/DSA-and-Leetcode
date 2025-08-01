package Heap;

import java.util.*;

public class exercise {
	
	/**
	 * Merge K Sorted Lists from leetcode
	 * 
	 * Given k linked lists, each sorted in ascending order, in a list lists, 
	 * write a function to merge the input lists into one sorted linked list.
	 */
	public static List<Integer> mergeKLists(List<List<Integer>> lists) {
		
		if (lists == null || lists.isEmpty()) {
			return new ArrayList<>();
		}
		
		// Check if all lists r empty
		boolean hasNonEmpty = lists.stream().anyMatch(lst -> lst != null && !lst.isEmpty());
		
		if (!hasNonEmpty) {
			return new ArrayList<>();
		}
		
		// create a min heap based on element value
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[0], b[0]));
		
		for (int i = 0; i < lists.size(); i++) {
			
			if (lists.get(i) != null && !lists.get(i).isEmpty()) {
				// passing in new int[] {value, listIndex, elementIndex}
				heap.offer(new int[] { lists.get(i).get(0), i, 0 });
			}
		}
		
		List<Integer> result = new ArrayList<Integer>();
		
		// build the merged list
		while (!heap.isEmpty()) {
			
			int[] current = heap.poll();
			int value = current[0], listIndex = current[1], elementIndex = current[2];
			result.add(value);	// add the smallest value to result
			
			// if more elements remain in that list then push the next element
			if (elementIndex + 1 < lists.get(listIndex).size()) {
				int nextValue = lists.get(listIndex).get(elementIndex + 1);
				heap.offer(new int[] { nextValue, listIndex, elementIndex + 1});
			}
		}
		
		return result;
	}
	
	
	/**
	 * Find K Closest Elements from leetcode
	 * 
	 * Given a sorted array nums, a target value target, and an integer k, 
	 * find the k closest elements to target in the array, 
	 * where "closest" is the absolute difference between each element and target. 
	 * Return these elements in array, sorted in ascending order.
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
	
	public static Integer[] topKFrequent(int[] nums, final int k) {
		
		Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
		
		for (int num : nums) {
			frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
		}
		
		// max heap
		PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>((a, b) -> Integer.compare(b[1], a[1]));
		
		for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
			maxHeap.offer(new int[] {entry.getKey(), entry.getValue()});
		}
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < k; i++) {
			result.add(maxHeap.poll()[0]);
		}
		
	    return result.toArray(new Integer[0]);
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
