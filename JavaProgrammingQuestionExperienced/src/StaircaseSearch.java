
public class StaircaseSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] matrix = {{1,2,3},
				{4,5,6},
				{7,8,9}
				};
		
		int target = 7;
		
		boolean found =isFound(matrix,target);
		System.out.println(found);
	}
	public static boolean isFound(int[][] matrix,int target) {
		int numberOfRows = matrix.length;
		int col = matrix[0].length-1;
		int row =0;
		
		
		while (row<numberOfRows && col >=0) {
			if (matrix[row][col]== target) {
				return true;
			}else if (matrix[row][col]>target) {
				col--;
				System.out.println(matrix[row][col]);
			}else {
				row++;
				System.out.println(matrix[row][col]);
			}
		}
		return false;
		
	}
}
