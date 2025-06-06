package stack;

import java.util.*;

public class StackExercise {
	
	/**
	 * Valid Parenthesis from leetcode
	 * 
	 * Given an input string s consisting solely of the characters '(', ')', ', ', '[' and ']', 
	 * determine whether s is a valid string. A string is considered valid if every opening 
	 * bracket is closed by a matching type of bracket and in the correct order, 
	 * and every closing bracket has a corresponding opening bracket of the same type.
	 */
	public static boolean isValid(String s) {
		
		if(s.trim() == "" || s.length() <= 1) {
			return false;
		}
		
		Stack<Character> stack = new Stack<>();
		Map<Character, Character> mapping = new HashMap<>();
		
		mapping.put(')', '(');
		mapping.put('}', '{');
		mapping.put(']', '[');
		
		for(char ch : s.toCharArray()) {
			
			if(mapping.containsKey(ch)) {
				
				if(stack.isEmpty() || stack.peek() != mapping.get(ch)) {
					return false;
				}
				
				stack.pop();
			}
			else {
				stack.push(ch);
			}
		}
		
		return stack.isEmpty();
	}
	
	/**
	 * Decode String from leetcode
	 * 
	 * Given an encoded string, write a function to return its decoded string that follows a 
	 * specific encoding rule: k[encoded_string], where the encoded_string within the brackets 
	 * is repeated exactly k times. Note that k is always a positive integer. The input string is 
	 * well-formed without any extra spaces, and square brackets are properly matched. 
	 * Also, assume that the original data doesn't contain digits other than the ones that 
	 * specify the number of times to repeat the following encoded_string.
	 */
	public static String decodeString(String s) {
		
		if(s.trim() == "" || s.length() <= 1) {
			return "Invalid string";
		}
		
		Stack<String> stack = new Stack<>();
		StringBuilder currString = new StringBuilder();
		int currentNumber = 0;
				
		for(char ch : s.toCharArray()) {
			
			if(Character.isDigit(ch)) {
				currentNumber = currentNumber * 10 + (ch - '0');
			}
			else if(ch == '[') {
				stack.push(currString.toString());
				stack.push(String.valueOf(currentNumber));
				currString = new StringBuilder();
				currentNumber = 0;
			}
			else if(ch == ']') {
				int repeatedTimes = Integer.parseInt(stack.pop());
				String prevString = stack.pop();
				StringBuilder temp = new StringBuilder(prevString);
				temp.append(currString.toString().repeat(repeatedTimes));
				System.out.println(temp);
				currString = temp;
			}
			else {
				currString.append(ch);
			}
		}
				
		return currString.toString();
	}
	

	public static void main(String[] args) {
		
		// isValid
		System.out.println(isValid("()[]{}"));
		System.out.println(isValid("([)]"));
		System.out.println(isValid("{[]}"));
		
		// decodeString
		System.out.println(decodeString("3[a2[c]]"));
	}
}
