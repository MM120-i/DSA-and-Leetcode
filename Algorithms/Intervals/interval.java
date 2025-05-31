package Intervals;

import java.util.*;

public class interval {
	
	
	// Check if any intervals overlap. If they do return false; otherwise true.
	public static boolean canAttendMeetings(int[][] intervals) {
		
		Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
		
		for(int i = 1; i < intervals.length; i++) {
			
			if(intervals[i][0] < intervals[i - 1][1]) {
				return false;
			}
		}
		
		return true;
	}
	
	// Merge all overlapping intervals and return the merged list.
	public static int[][] mergeIntervals(int[][] intervals){
		
		Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
		List<int[]> merged = new ArrayList<>();
		
		for(int[] interval : intervals) {
			
			if(merged.isEmpty() || interval[0] > merged.get(merged.size() - 1)[1]) {
				merged.add(interval);
			}
			else {
				int[] last = merged.get(merged.size() - 1);
				last[1] = Math.max(last[1], interval[1]);
			}
		}
		
		return merged.toArray(new int[merged.size()][]);
	}
	
	/**
	 * Non-Overlapping Intervals from leetcode.
	 * 
	 * Write a function to return the minimum number of intervals that must be removed from a given array intervals,
	 * where intervals[i] consists of a starting point starti and an ending point endi, to ensure that the remaining intervals do not overlap.
	 */
	public static int noOverlapIntervals(int[][] intervals) {
		
		if(intervals.length == 0) {
			return 0;
		}
		
		Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
		int count = 1;
		int end = intervals[0][1];
		
		for(int i = 1; i < intervals.length; i++) {
			
			if(intervals[i][0] >= end) {
				count++;
				end = intervals[i][1];
			}
		}
		
		return intervals.length - count;
	}
	
	/**
	 * Insert Interval from leetcode.
	 * 
	 * Given a list of intervals intervals and an interval newInterval, write a function to insert newInterval into a list of existing, non-overlapping, 
	 * and sorted intervals based on their starting points. The function should ensure that after the new interval is added, 
	 * the list remains sorted without any overlapping intervals, merging them if needed.
	 */
	public static int[][] insert(int[][] intervals, int[] newInterval) {
		
		List<int[]> merged = new ArrayList<>();
		int i = 0;
		int n = intervals.length;
				
		// Phase 1: Add intervals before newInterval
		while(i < n && intervals[i][1] < newInterval[0]) {
			merged.add(intervals[i]);
			i++;
		}
		
		// Phase 2: Merge overlapping intervals/
		while(i < n && intervals[i][0] <= newInterval[1]) {
			newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
			newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
			i++;
		}
		
		merged.add(newInterval);
		
		// Phase 3: Add remaining intervals
		while(i < n) {
			merged.add(intervals[i]);
			i++;
		}
				
		return merged.toArray(new int[merged.size()][]);
	}
	
	private static void printArray(int[][] array) {
		
		for(int i = 0; i < array.length; i++) {
			
			for(int j = 0; j < array[i].length; j++) {
				System.out.println(array[i][j] + " ");
			}
			
			System.out.println();
		}
	}

	public static void main(String[] args) {
		
		// {start, end}
		int[][] interval = {
				{0, 30},
				{5, 10},
				{15, 20}
		};
		
		System.out.println("Can attend meetings: " + canAttendMeetings(interval));
		
		// Insert
	    int[][] intervals = {{1,3},{6,9}};
		int[] newInterval = {2,5};
		int[][] result = insert(intervals, newInterval);
		
		printArray(result);
		
		
		// noOverlapIntervals
		int[][] intervals = {{1,3},{5,8},{4,10},{11,13}};
		
		System.out.println(noOverlapIntervals(intervals));

	}

}
