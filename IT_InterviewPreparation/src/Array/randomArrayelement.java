package Array;
import java.util.Random;
public class randomArrayelement {
// program to create  arr with random numbers and sort them.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=5;
		Random random = new Random();
		int[] arr= new int[n];
		for (int i=0;i<n;i++) {
			arr[i]=random.nextInt(100);
		}
		for (int i=0;i<n;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		
		for (int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				if (arr[i]>arr[j]) {
					int temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}
			}
		}
		
		for (int i=0;i<n;i++) {
			System.out.print(arr[i]+" ");
		}
	}

}
