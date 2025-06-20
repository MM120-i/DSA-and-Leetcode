package Greedy;

import java.util.*;

public class greed {
	
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
	}
}
