package Greedy;

import java.util.*;

public class greed {
	
	/**
	 * Jump Game from leetcode
	 * 
	 * Write a function to determine whether you can travel from the first index to the last index of an integer array nums, 
	 * where each number in the array specifies the maximum distance you can jump from that index. 
	 * The function should return true if reaching the last index is possible, and false otherwise.
	 * 
	 * @param nums
	 * @return
	 */
	public static boolean maxJump(int[] nums) {
		
		int maxReach = 0;
		
		for(int i = 0; i < nums.length; i++) {
			
			if(i > maxReach) {
				return false;
			}
			
			maxReach = Math.max(maxReach, i + nums[i]);
		}
		
		return true;
	}
	
	/**
	 * Gas Station from leetcode
	 * 
	 * There are n gas stations along a circular route. You are given two integer arrays gas and cost of length n. At each gas station i, 
	 * gas[i] represents the amount of gas you receive by stopping at this station, and cost[i] represents the amount of 
	 * gas required to travel from station i to the next station. You begin the journey with an empty tank at one of the gas stations.
	 *
	 * Write a function to return the starting gas station's index if you can travel around the circuit once in the clockwise direction; 
	 * otherwise, return -1. Note that if there exists a solution, it is guaranteed to be unique. 
	 * Also, you can only travel from station i to station i + 1, and the last station will lead back to the first station.
	 * 
	 * @param gas
	 * @param cost
	 * @return
	 */
	public static int canCompleteCircuit(int[] gas, int[] cost) {
		
		if(gas.length != cost.length) {
			return -1;
		}
				
		int totalGas = 0, totalCost = 0;
		
		for(int i = 0; i < gas.length; i++) {
			totalGas += gas[i];
			totalCost += cost[i];
		}
		
		if(totalCost > totalGas) {
			return -1;
		}
		
		int start = 0, fuel = 0;
		
		for(int i = 0; i < gas.length; i++) {
			
			fuel += gas[i] - cost[i];
			
			if(fuel < 0) {
				start = i + 1;
				fuel = 0;
			}
		}
		
		return start;
		
	}
	
	/**
	 * Best Time to Buy and Sell Stock from leetcode
	 * 
	 * Write a function to determine the maximum profit you can obtain from a series of stock prices given in an array prices, 
	 * where prices[i] represents the stock price on the ith day. You are allowed to buy and then sell the stock once, 
	 * as long as as the sell date is after the buy date. If no profit can be made, the function should return 0.
	 */
	public static int maxProfit(int[] prices) {
		
		if(prices.length == 0) {
			return 0;
		}
		
		int minPrices = prices[0];
		int maxProfit = 0;
		
		for(int price : prices) {
			minPrices = Math.min(minPrices, price); 
			maxProfit = Math.max(maxProfit, price - minPrices);
		}
		
		return maxProfit;
	}
	
	/**
	 * Assign Cookies from leetcode
	 * 
	 * You are given two integer arrays:
	 * 
     	 * greeds (of size n), where each element represents the minimum size of a cookie that a child needs to be satisfied.
     	 * cookies (of size m), where each element represents the size of a cookie.
         * 
	 * Your task is to assign cookies to children such that as many children as possible are satisfied. 
	 * A child is satisfied if the cookie they receive is equal to or greater than their greed factor. 
	 * Each child can receive at most one cookie, and each cookie can be given to only one child. 
	 * Write a function to return the maximum number of children that can be satisfied.
	 */
	public static int content(int[] cookies, int[] greed) {
		
		Arrays.sort(cookies);
		Arrays.sort(greed);
		
		int n = greed.length;
		int m = cookies.length;
		int count = 0, i = 0, j = 0;
		
		while(i < n && j < m) {
			
			if(cookies[j] >= greed[i]) {
				count++;
				i++;
			}
			
			j++;
		}
		
		return count;
	}

	public static void main(String[] args) {
		
		// content
		int[] greeds = {1, 2, 3};
		int[] cookies = {1, 1};
		System.out.println("Max Number of content: " + content(cookies, greeds));
		
		// max profit
		int[] prices = {3, 4, 6, 2, 5, 8};
		System.out.println("maxProfit: " + maxProfit(prices));
		
		// gas station			
		int[] gas = {5, 2, 0, 3, 3};
		int[] cost = {1, 5, 5, 1, 1};
		System.out.println("Gas station: " + canCompleteCircuit(gas, cost));
		
		// Max jump
		int[] nums = {1, 3, 0, 1, 4};
		int[] nums2 = {2, 2, 1, 0, 5, 1, 1};
		System.out.println("Max Jump: " + maxJump(nums));
		System.out.println("Max Jump: " + maxJump(nums2));
	}
}
