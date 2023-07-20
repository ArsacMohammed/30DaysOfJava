
public class reverseArrayRecursion {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr = {1,2,3,4,5};
		int start=0;
		int end =arr.length-1;
		reverseArray(arr,start,end);
		printArray(arr);
	}

	private static void printArray(int[] arr) {
		// TODO Auto-generated method stub
		for (int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		
	}

	private static void reverseArray(int[] arr, int start, int end) {
		// TODO Auto-generated method stub
		
		if (start<end) {
			int temp=arr[start];
			arr[start]=arr[end];
			arr[end]=temp;
			reverseArray(arr,start+1,end-1);
		}
	}
}
