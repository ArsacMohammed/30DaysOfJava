package SortingAlgorithm;
//Merge two sorted arrays
public class mergeSort {
	public static void main(String[] args) {
		int[] leftHalf = {1, 3, 5, 7};
        int n1 = leftHalf.length;
     
        int[]  rightHalf= {2, 4, 6, 8};
        int n2 = rightHalf.length;
        
        int [] arr =new int[n1+n2];
        merge(arr,leftHalf,rightHalf);
        for (int n : arr) {
        	System.out.print(n+" ");
        }
	}

	private static int[] merge(int[] arr, int[] leftHalf, int[] rightHalf) {
		// TODO Auto-generated method stub
		int leftSize=leftHalf.length;
		int rightSize=rightHalf.length;
		int i=0; // to loop through lefthalf
		int j=0; //to loop thourgh righthalf
		int k=0;  // to loop through the new arr[]
		while(i<leftSize && j<rightSize) {
			if (leftHalf[i]<rightHalf[j]){
				arr[k]=leftHalf[i];
				i++;
				k++;
			}else {
				arr[k]=rightHalf[j];
				j++;
				k++;
			}
		}
		
		while(i<leftSize) {
			arr[k]=leftHalf[i];
			i++;
			k++;
		}
		
		while(j<rightSize) {
			arr[k]=rightHalf[j];
			j++;
			k++;
		}
		
		
		return arr;
	}
}
