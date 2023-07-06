public class setMatrixZero{
	public static void main (String[] args) {
		int[][] matrix= {{1,1,1},{1,0,1},{1,1,1}};
		printMatrix(matrix);
		zeroMatrix(matrix);
		printMatrix(matrix)
		
		
		
		
	}

	private static void zeroMatrix(int[][] matrix) {
		// TODO Auto-generated method stub
		int numberOfRows=matrix.length;
		int numberOfColumn=matrix[0].length;
		
		boolean[] rowHasZero =new boolean[numberOfRows];
		boolean[] colHasZero= new boolean[numberOfColumn];
		// to find the zero and note the indices as ture in the boolean array
		
		for (int i=0;i<numberOfRows;i++) {
			for(int j=0;j<numberOfColumn;j++) {
				if (matrix[i][j]==0) {
					rowHasZero[i]=true;
					colHasZero[j]=true;
				}
			}
		}
		
		//change the corresponding row and column to zero with respect to the element .
		for (int i=0;i<numberOfRows;i++) {
			for(int j=0;j<numberOfColumn;j++) {
				if (rowHasZero[i] || colHasZero[j]) {
					matrix[i][j] = 0;				
				}
			}
		}
		
	}

	private static void printMatrix(int[][] matrix) {
		// TODO Auto-generated method stub
		for (int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
		
	}

}