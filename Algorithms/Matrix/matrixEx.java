package matrix;

import java.util.*;

public class matrixEx {
	
	/**
	 * Set Matrix Zeroes from leetcode
	 * 
	 * Write a function to modify an m x n integer matrix matrix directly, such that if any element in the matrix is 0, 
	 * its entire corresponding row and column are set to 0. This transformation should be done in place, 
	 * without using any additional data structures for storage.
	 * 
	 * @param matrix
	 */
	public static void setZeros(int[][] matrix) {
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		Set<Integer> zeroRows = new HashSet<>();
		Set<Integer> zeroCols = new HashSet<>();
		
		// mark the rows and cols to be zeroed
		for(int i = 0; i < rows; i++) {
			
			for(int j = 0; j < cols; j++) {
				
				if(matrix[i][j] == 0) {
					zeroRows.add(i);
					zeroCols.add(j);
				}
			}
		}
		
		// zero out the marked rows
		for(int row : zeroRows) {
			System.out.println("row: " + row);
			for(int col = 0; col < cols; col++) {
				matrix[row][col] = 0;
			}
		}
		
		// zero out the marked cols
		for(int col : zeroCols) {
			System.out.println("col: " + col);
			for(int row = 0; row < rows; row++) {
				matrix[row][col] = 0;
			}
		}
	}
	
	/**
	 * Rotate Image from leetcode
	 * 
	 * Write a function to rotate an n x n 2D matrix representing an image by 90 degrees clockwise. 
	 * The rotation must be done in-place, meaning you should modify the input matrix directly without using an additional matrix for the operation.
	 * 
	 * @param matrix
	 */
	public static void rotate(int[][] matrix) {
		
		// swap across diagonal (transpose)
		for(int i = 0; i < matrix.length; i++) {
			
			for(int j = 0; j < matrix.length; j++) {
				
				// swap matrix[i][j] with matrx[j][i]
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		
		// reverse each row
		for(int i = 0; i < matrix.length; i++) {
			
			int left = 0;
			int right = matrix.length - 1;
			
			while(left < right) {
				
				int temp = matrix[i][left];
				matrix[i][left] = matrix[i][right];
				matrix[i][right] = temp;
				left++;
				right--;
			}
		}
	}
	
	private static void printMatrix(int[][] matrix) {
		
		for(int[] row : matrix) {
			
			for(int num : row) {
				System.out.print(num + " ");
			}
			
			System.out.println();
		}
	}
	
	
	/**
	 * Spiral Matrix from leetcode
	 * 
	 * Write a function to traverse an m x n matrix in spiral order and return all elements in a single list. 
	 * The traversal should start from the top left corner and proceed clockwise, spiraling inward until every element has been visited.
	 * 
	 * @param matrix
	 * @return
	 */
	public static List<Integer> spiralOrder(int[][] matrix){
		
		List<Integer> result = new ArrayList<>();
		
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return result;
		}
		
		int top = 0, left = 0;
		int bottom = matrix.length - 1;
		int right = matrix[0].length - 1;
		
		while(top <= bottom && left <= right) {
			
			// go from left to right
			for(int i = left; i <= right; i++) {
				result.add(matrix[top][i]);
			}
			top++;
			
			// go from top to bottom
			for(int i = top; i <= bottom; i++) {
				result.add(matrix[i][right]);
			}
			right--;
			
			// go from right to left
			if(top <= bottom) {
				
				for(int i = right; i >= left; i--) {
					result.add(matrix[bottom][i]);
				}
				bottom--;
			}
			
			// go from bottom to top
			if(left <= right) {

				for(int i = bottom; i >= top; i--) {
					result.add(matrix[i][left]);
				}
				left++;
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		
		// spiral order
		int[][] matrix = {
				{0, 1, 2},
				{3, 4, 5},
				{6, 7, 8},
		};
		
		System.out.println("Before Sprial order:");
		printMatrix(matrix);
		
		List<Integer> result = spiralOrder(matrix);
		System.out.println("\nAfter Sprial order:");
		System.out.println(result + "\n");
		
		
		// rotate image
        int[][] matrix2 = {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}
        };
        
        System.out.println("Before rotating:");
        printMatrix(matrix2);
        rotate(matrix2);
        System.out.println("\nAfter roating:");
        printMatrix(matrix2);
        System.out.println();
        
        // set zeros
        int[][] matrix3 = {
                {1, 2, 3},
                {4, 7, 0},
                {7, 8, 9}
        };
        
        System.out.println("Before setting zeros:");
        printMatrix(matrix3);
        setZeros(matrix3);
        System.out.println("\nAfter setting zeros:");
        printMatrix(matrix3);
	}
}
