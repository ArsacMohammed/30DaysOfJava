import java.util.Arrays;
public class findigSmallestNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] numbers = {1,2,3,4,5,6,-1,5,0};
		int min =numbers[0];
		findMin(numbers,min);
		findMin2(numbers);
		
		
	}
	
	private static void findMin2(int[] numbers) {
		// TODO Auto-generated method stub
		Arrays.sort(numbers);
		System.out.println("The smallest number found  using Arrays.sort is:"+numbers[0]);
	}

	public static void findMin(int[] numbers,int min) {
		for (int i=0;i<numbers.length;i++) {
			if (numbers[i]<min) {
				min=numbers[i];
			}
		}
		System.out.println("The smallest number is "+min);
	}

}
