package Array;
import java.util.*;
public class reverseArrray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] numbers = {1,2,3,4,5,6,7};
		int [] reverse=reverseArray(numbers);
		for (int i=0;i<numbers.length;i++) {
			if (i==numbers.length-1) {
				System.out.print(reverse[i]);
			}else {
				System.out.print(reverse[i]+" ");
			}
			
		}
	}
	public static int[] reverseArray(int[] numbers) {
		int [] reverse = new int[numbers.length];
		int j=0;
		for(int i=numbers.length-1;i>=0;i--) {
			reverse[j]=numbers[i];
			j++;
		}
		return reverse;
	}
}
