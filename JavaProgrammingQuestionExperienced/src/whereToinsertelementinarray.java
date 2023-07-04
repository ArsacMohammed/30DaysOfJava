
public class whereToinsertelementinarray {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 9};
        int target = 4;

        int index = findInsertionPoint(arr, target);

        System.out.println("Insertion Point: " + index);
    }

    public static int findInsertionPoint(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;  // Element found at index mid
            } else if (arr[mid] < target) {
                low = mid + 1;  // Target is in the right half
            } else {
                high = mid - 1;  // Target is in the left half
            }
        }

        // Element not found, return the insertion point
        return low;
    }



	private static boolean presentInArray(char[] input,int num) {
		// TODO Auto-generated method stub
		for (int i=0;i<input.length;i++) {
			if (input[i]==num) {
				return true;
			}
		}
		return false;
	}

}
