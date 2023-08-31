package SortingAlgorithm;

public class sortAscendingDesendingOrder {
//this is also bubblesort algorithm
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr= {5,4,2,3,1};
		for (int i=0;i<arr.length;i++) {
			for (int j=i+1;j<arr.length;j++) {
				if (arr[i]>arr[j]) {
					int temp =arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}
			}
		}
		
		
		for (int i=arr.length-1;i>=0;i--) {
			System.out.print(arr[i]+" ");
		}
	}
	
	/* for ascending order 
	 * for (int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	 */

}
