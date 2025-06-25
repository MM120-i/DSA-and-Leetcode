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
