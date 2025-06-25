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
