package Array;

public class findSmallestNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] numbers = {1,2,3,4,45,5,6,7,8};
		int smallest_number=small(numbers);
		System.out.println(smallest_number);
	}
	public static int small(int[] numbers) {
		
		int min=Integer.MAX_VALUE;
		for (int i=0;i<numbers.length;i++) {
			if (min>numbers[i]) {
				min=numbers[i];
			}
		}
		return min;
		
	}
}
