package test;

import java.util.*;

// Variable length SlidingWindow.
public class SlidingWindow {
	
	/**
	 * Longest Repeating Character Replacement from leetcode.
	 * 
	 * Write a function to find the length of the longest substring containing the same letter in a given string s, 
	 * after performing at most k operations in which you can choose any character of the string and change it to any other uppercase English letter.
	 * 
	 * Time: O(n)
	 * Space: O(1)
	 */
	public static int characterReplacement(String s, int k) {
		
		Map<Character, Integer> state = new HashMap<>();
		int maxFreq = 0, maxLength = 0, start = 0;
		
		for(int end = 0; end < s.length(); end++) {
			
			char currentChar = s.charAt(end);
			state.put(currentChar, state.getOrDefault(currentChar, 0) + 1);
			maxFreq = Math.max(maxFreq, state.get(currentChar));
			
			// Shrink the window if its invalid
			if(end - start + 1 > maxFreq + k) {
				char startChar = s.charAt(start);
				state.put(startChar, state.get(startChar) - 1);
				start++;
			}
			
			maxLength = Math.max(maxLength, end - start + 1);
		}
		
		return maxLength;
	}
	
	/**
	 * Longest Substring Without Repeating Characters from leetcode
	 *
	 * Write a function to return the length of the longest substring in a provided string s where all characters in the substring are distinct.
	 * 
	 * Time: O(n)
	 * Space: O(k), where k is the size of the character set. 
	 */
	public static int longestSubstring(String s) {
		
		Map<Character, Integer> state = new HashMap<>();
		int start = 0, maxLength = 0;
				
		for(int end = 0; end < s.length(); end++) {
			
			char currentChar = s.charAt(end);
			
			if(state.containsKey(currentChar)) {
				start = Math.max(start, state.get(currentChar) + 1);
			}
			
			state.put(currentChar, end);
			maxLength = Math.max(maxLength, end - start + 1);
		}
		
		return maxLength;
	}

	/**
	 *  Fruit Into Baskets from leetcode
	 *
	 * 	Write a function to calculate the maximum number of fruits you can collect from an integer array fruits,
	 * 	where each element represents a type of fruit. You can start collecting fruits from any position in the array,
	 * but you must stop once you encounter a third distinct type of fruit.
	 * The goal is to find the longest subarray where at most two different types of fruits are collected.
	 * 
	 * @param fruits
	 * @return
	 */
	public static int fruitIntoBaskets(int[] fruits) {
		
		Map<Integer, Integer> state = new HashMap<>();
		int start = 0, maxFruit = 0;
		
		for(int end = 0; end < fruits.length; end++) {

			state.put(fruits[end], state.getOrDefault(fruits[end], 0) + 1);
			
			while(state.size() > 2) {
				
				state.put(fruits[start], state.get(fruits[start]) - 1);
				
				if(state.get(fruits[start]) == 0) {
					state.remove(fruits[start]);
				}
				
				start++;
			}
			
			maxFruit = Math.max(maxFruit, end - start + 1);
		}
		
		return maxFruit;
	}

	public static void main(String[] args) {
		
		// Fruit Into Baskets
		int[] fruits = {3, 3, 2, 1, 2, 1, 0};
		System.out.println("Max fruits collected: " + fruitIntoBaskets(fruits));
		
		
		// Longest Substring Without Repeating Characters
		String s1 = "eghghhgg";
		String s2 = "substring";
		
		System.out.println("Input: eghghhgg => Output: " + longestSubstring(s1));  
        System.out.println("Input: substring => Output: " + longestSubstring(s2));
		
		// Longest Character Replacement
		String s = "BBABCCDD";
		int k = 2;
		System.out.println("Output: " + characterReplacement(s, k));
	}
}
