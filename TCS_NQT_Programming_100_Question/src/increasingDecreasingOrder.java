import java.util.*;
public class increasingDecreasingOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Rearrange the array such that the first half is arranged in increasing order, and the second half is arranged in decreasing order
		
		int[] num = {4,3,2,1,5,6,7,8};
		int length=num.length;
		Arrays.sort(num);
		for (int i=0;i<length/2;i++) {
			System.out.print(num[i]+" ");
		}
		for (int i=length-1;i>=length/2;i--) {
			System.out.print(num[i]+" ");
		}
	}

}
