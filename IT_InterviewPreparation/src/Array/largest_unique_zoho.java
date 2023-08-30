package Array;
import java.util.*;
public class largest_unique_zoho {
//you  are  given an array so that largest number is unique by atleast as twice as much the other numbers in the array
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] numbers = {1,2,3,6};
		int max=Integer.MIN_VALUE;
		for (int i=0;i<numbers.length;i++) {
			if (max<numbers[i]) {
				max=numbers[i];
			}
		}
		System.out.println(max);
		int largest_validate= validate(numbers,max);
		System.out.println(largest_validate);

	}

	private static int validate(int[] numbers,int max) {
	// TODO Auto-generated method stub
		for (int i=0;i<numbers.length;i++) {
			if (numbers[i]==6) {
				continue;
			}else if (numbers[i]*2>max) {
				return -1;
			}
		}
		int largest= indexOfLargest(numbers,max);
		return largest;
	}

	private static int indexOfLargest(int[] numbers, int max) {
	// TODO Auto-generated method stub
	for (int i=0;i<numbers.length;i++) {
		if (numbers[i]==max) {
			return i;
		}
	}
	return -1;
	}

}