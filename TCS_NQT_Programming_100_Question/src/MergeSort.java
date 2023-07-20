import java.util.Arrays;

//MergeSort using recursion 
//reference youtube video --- https://youtu.be/bOk35XmHPKs
public class MergeSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1,4,6,23,2,6,3,98,34};
		
		mergeSort(arr);
		for (int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}

	public  static void mergeSort(int[] arr) {
		// TODO Auto-generated method stub
		if (arr.length<2) {
			return ;
		}
		int mid = arr.length/2;
		int [] leftHalf = new int[mid];
		int [] rightHalf = new int[arr.length-mid];
		
		for (int i=0;i<mid;i++) {
			leftHalf[i]=arr[i];
		}
		for (int i=mid;i<arr.length;i++) {
			rightHalf[i-mid]=arr[i];
		}
		
		mergeSort(leftHalf);
		mergeSort(rightHalf);
		merge(arr,leftHalf,rightHalf);
		
		
	}

	public static void merge(int[] arr, int[] leftHalf, int[] rightHalf) {
		// TODO Auto-generated method stub
		int leftSize=leftHalf.length;
		int rightSize=rightHalf.length;
		int i=0,j=0,k=0;
		while (i<leftSize && j<rightSize) {
			if (leftHalf[i]<=rightHalf[j]) {
				arr[k]=leftHalf[i];
				i++;
			}else {
				arr[k]=rightHalf[j];
				j++;
			}
			k++;
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
	}
}
