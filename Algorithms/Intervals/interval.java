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
	
	// Return minimum number of intervals to remove so the remaining intervals dont overlap.
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
	

	public static void main(String[] args) {
		
		// {start, end}
		int[][] intervals = {
				{0, 30},
				{5, 10},
				{15, 20}
		};
		
		System.out.println("Can attend meetings: " + canAttendMeetings(intervals));
	}
}
