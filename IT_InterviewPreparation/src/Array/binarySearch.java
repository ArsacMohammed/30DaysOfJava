package Array;

public class binarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] numbers = {1,2,3,4,3,5,3,2,7,8,9,7,6};
		int target = 1;
		int start = 0;
		int end=numbers.length-1;
		boolean found=false;
		while(start<=end) {
			int mid = (start+end)/2;
			if (numbers[mid]==target) {
				System.out.println("the target is  present ");
				found=true;
				break;
			}else if(numbers[mid]>target) {
				end=mid-1;
			}else if (numbers[mid]<target) {
				start=mid+1;
			}
		}
		if (!found) { // handle this separately like this .
			System.out.println("not present");
		}
	}

}
