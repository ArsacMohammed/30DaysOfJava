package Array;

public class secondLargest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int secondLargest=Integer.MIN_VALUE;
		int largest=-1;
		int[] arr= {1,3,4,5,6,3,2,1,6,7,8,6,5};
		
		
		if (arr.length<2) {
			System.out.println("The length of the arr is not valid");
			return;
		}
		
		for (int i=0;i<arr.length;i++) {
			if (largest<arr[i]) {
				secondLargest=largest;
				largest=arr[i];
			}else if (secondLargest<arr[i] & arr[i]!=largest) {
				secondLargest=arr[i];
			}
		}
		System.out.println(secondLargest);
		System.out.println(largest);
	}

}
